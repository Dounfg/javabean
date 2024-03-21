package com.javabase.jdk8;

public class Function2 implements FunctionInterface3 {

    @Override
    public void execute() {
        System.out.println("Function2:execute");
    }

    public String getAllNames() {
        return FunctionInterface3.super.getName();
    }

    public static void main(String[] args) {
        System.out.println(new Function2().getAllNames());
    }

}
