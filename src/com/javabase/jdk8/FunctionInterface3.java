package com.javabase.jdk8;

public interface FunctionInterface3 extends FunctionInterface1, FunctionInterface2 {

    default String getName() {
        String func1Name = FunctionInterface1.super.getName();
        String func2Name = FunctionInterface2.super.getName();
        System.out.println(func1Name);
        System.out.println(func2Name);
        return "FunctionInterface3:getName()";
    }

}
