package com.ncamc.spring.tactics.demo;

import com.ncamc.spring.tactics.impl.Add;
import com.ncamc.spring.tactics.impl.Divide;
import com.ncamc.spring.tactics.impl.Multiply;
import com.ncamc.spring.tactics.impl.Substract;
import com.ncamc.spring.tactics.to.Context;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new Add());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
        context.setStrategy(new Substract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
        context.setStrategy(new Multiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
        context.setStrategy(new Divide());
        System.out.println("10 / 5 = " + context.executeStrategy(10, 5));
    }
}