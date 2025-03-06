package com.ncamc.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("1.255433");

        // 加
        System.out.println(a.add(b).doubleValue());
        // 减
        System.out.println(a.subtract(b));
        // 乘
        System.out.println(a.multiply(b));
        // 无法除尽，抛出 ArithmeticException 异常
        System.out.println(a.divide(b));
        // 除
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));
        // 比较大小 -1 表示a<b  0 表示a=b  1表示a>b
        System.out.println(a.compareTo(b));
        // 保留几位小数
        System.out.println(c.setScale(3, RoundingMode.HALF_DOWN));
    }
}
