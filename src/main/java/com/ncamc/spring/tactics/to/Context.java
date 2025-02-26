package com.ncamc.spring.tactics.to;

import com.ncamc.spring.tactics.Strategy;
import lombok.Data;

@Data
public class Context {
    private Strategy strategy;

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
