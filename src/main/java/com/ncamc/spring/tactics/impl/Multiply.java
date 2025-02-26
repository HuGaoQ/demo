package com.ncamc.spring.tactics.impl;

import com.ncamc.spring.tactics.Strategy;

public class Multiply implements Strategy {
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }
}