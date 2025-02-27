package com.ncamc.reflect;

import lombok.Data;
import lombok.Setter;

@Setter
public class TargetObject {

    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String value) {
        System.out.printf("l love %s%n", value);
    }

    private void privateMethod() {
        System.out.printf("value is %s%n", value);
    }

}
