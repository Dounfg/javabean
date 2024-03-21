package com.javabase.jdk8;

public interface Action {

    public int var = initVar();

    public static int initVar() {
        System.out.println("Action:init var");
        return 0;
    }

    default void say() {
        System.out.println("Action: say...");
    }

}
