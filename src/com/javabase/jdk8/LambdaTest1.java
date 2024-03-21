package com.javabase.jdk8;

public class LambdaTest1 {

    public static void main(String[] args) {

        /* 实现接口的三种方式 */

        // 1.创建一个类去实现该接口
        FunctionInterface1 func1 = new Function1();
        func1.execute();

        // 2.匿名内部类
        FunctionInterface1 func2 = new FunctionInterface1() {

            @Override
            public void execute() {
                System.out.println("Function1:execute 2");
            }
        };
        func2.execute();

        // 3.Lambda表达式
        FunctionInterface1 func3 = () -> {
            System.out.println("Function1:execute 3");
        };
        // or
        // func3 = () -> System.out.println("Function1:execute");

        func3.execute();

        FunctionInterface1 func4 = () -> System.out.println("Function1:execute 4");

        func4.execute();

    }

}
