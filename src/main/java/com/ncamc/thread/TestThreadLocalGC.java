package com.ncamc.thread;

/**
 * 手动设置LOCAL为null测试gc回收
 */
public class TestThreadLocalGC {
    static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        LOCAL.set("测试ThreadLocalMap弱引用自动回收");
        Thread thread = Thread.currentThread();
        LOCAL = null;
        System.gc();
        System.out.println(thread.getName() + ":" + thread.getState());
    }
}
