package com.ncamc.unsafe;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestUnsafe {

    private int value;

    private volatile int a;

    public static String name = "Hydra";
    int age;

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = reflectGetUnsafe();
        // 内存操作
//        memoryTest(unsafe);
        // 内存屏障
//        memoryBarrier(unsafe);
        // 对象操作
//        objectTest(unsafe);
        // CAS操作 CAS 即比较并替换（Compare And Swap)，是实现并发算法时常用到的一种技术
//        casTest(unsafe);
        // 线程调度
//        threadTest(unsafe);
        // class测试
        staticTest(unsafe);
    }

    private static void staticTest(Unsafe unsafe) throws NoSuchFieldException {
        TestUnsafe testUnsafe = new TestUnsafe();
        // 也可以用下面的语句触发类初始化
        // 1.
        // unsafe.ensureClassInitialized(User.class);
        // 2.
        // System.out.println(User.name);
        System.out.println(unsafe.shouldBeInitialized(TestUnsafe.class));
        Field sexField = TestUnsafe.class.getDeclaredField("name");
        long fieldOffset = unsafe.staticFieldOffset(sexField);
        Object fieldBase = unsafe.staticFieldBase(sexField);
        Object object = unsafe.getObject(fieldBase, fieldOffset);
        System.out.println(object);
    }

    private static void threadTest(Unsafe unsafe) {
        Thread mainThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("subThread try to unPark mainThread");
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }).start();

        System.out.println("park main mainThread");
        unsafe.park(false, 0L);
        System.out.println("unPark mainThread success");
    }

    private static void casTest(Unsafe unsafe) {
        TestUnsafe casTest = new TestUnsafe();
        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                casTest.increment(i, unsafe);
                System.out.print(casTest.a + StrUtil.EMPTY);
            }
        }).start();
        new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                casTest.increment(i, unsafe);
                System.out.print(casTest.a + StrUtil.EMPTY);
            }
        }).start();
    }

    private void increment(int x, Unsafe unsafe) {
        while (true) {
            try {
                long fieldOffset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("a"));
                if (unsafe.compareAndSwapInt(this, fieldOffset, x - 1, x)) {
                    break;
                }
            } catch (NoSuchFieldException e) {
                log.error(e.getMessage());
            }
        }
    }

    private static void objectTest(Unsafe unsafe) throws Exception {
        long offset = unsafe.objectFieldOffset(TestUnsafe.class.getDeclaredField("value"));
        TestUnsafe testUnsafe = new TestUnsafe();
        System.out.println("value before putInt: " + testUnsafe.value);
        unsafe.putInt(testUnsafe, offset, 42);
        System.out.println("value after putInt: " + testUnsafe.value);
        System.out.println("value after putInt: " + unsafe.getInt(testUnsafe, offset));
    }

    private static void memoryBarrier(Unsafe unsafe) {
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();
        while (true) {
            boolean flag = changeThread.isFlag();
            //加入读内存屏障
            unsafe.loadFence();
            if (flag) {
                System.out.println("detected flag changed");
                break;
            }
        }
        System.out.println("main thread end");
    }

    private static void memoryTest(Unsafe unsafe) {
        long size = 4;
        // 分配新的本地空间
        long adds = unsafe.allocateMemory(size);
        // 重新调整内存空间的大小
        long adds1 = unsafe.reallocateMemory(adds, size * 2);
        System.out.printf("adds: %s%n", adds);
        System.out.printf("adds1: %s%n", adds1);
        try {
            // 将内存设置为指定值
            unsafe.setMemory(null, adds, size, (byte) 1);
            for (int i = 0; i < 2; i++) {
                // 内存拷贝
                unsafe.copyMemory(null, adds, null, adds1 + size * i, 4);
            }
            System.out.printf("unsafe.getInt(): %s%n", unsafe.getInt(adds));
            System.out.printf("unsafe.getLong(): %s%n", unsafe.getLong(adds1));
        } finally {
            // 清除内存
            unsafe.freeMemory(adds);
            unsafe.freeMemory(adds1);
        }
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
