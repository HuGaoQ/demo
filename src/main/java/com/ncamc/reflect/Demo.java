package com.ncamc.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        // 获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例
        Class<?> targetClass = Class.forName("com.ncamc.reflect.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        // 获取 TargetObject 类中定义的所有方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.printf("method: %s%n", method.getName());
        }

        // 获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "JavaGuide");

        // 获取指定参数并对参数进行修改
        Field value = targetClass.getDeclaredField("value");
        value.setAccessible(true);
        value.set(targetObject, "JavaGuide");

        // 获取 private 方法并调用
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }
}
