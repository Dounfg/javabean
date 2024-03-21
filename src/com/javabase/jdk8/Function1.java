package com.javabase.jdk8;

/**
 * 如果一个类同时实现了两个接口,并且接口中都有相同的default方法,则该类必须重写接口中default的方法。
 * 若需要使用接口中的default方法,则需指定接口名.super.方法名()
 *
 */
public class Function1 implements FunctionInterface1, FunctionInterface2 {

    @Override
    public void execute() {
        System.out.println("Function1:execute 1");
    }

    @Override
    public String getName() {

        String func1Name = FunctionInterface1.super.getName();
        String func2Name = FunctionInterface2.super.getName();
        System.out.println(func1Name);
        System.out.println(func2Name);

        return "Function1:getName()";
    }

    public static void main(String[] args) {
        Function1 func1 = new Function1();
        System.out.println(func1.getName());

    }

}
