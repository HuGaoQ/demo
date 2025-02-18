package com.ncamc.base;

// ThreadLocal 的使用
public class LoginUserLocal {

    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(Object obj) {
        THREAD_LOCAL.set(obj);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static Object get() {
        return THREAD_LOCAL.get();
    }

}