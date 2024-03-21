package com.javabase.jdk8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Lambda中几个常用的函数
 * 接口名 接口参数 返回值 说明 
 * Predicate T boolean 这个元素在集合中存在吗 
 * Consumer T void 输出一个值
 * Fucntion T R 获取A对象中的名称 
 * Supplier None T 工厂方法 
 * UnaryOperator T T 逻辑非(!)
 * BinaryOperator (T,T) T 求两个数的乘积
 **/
public class LambdaTest2 {


    public static void main(String[] args) {

        // 类型推断

        Map<String, Integer> map = new HashMap<String, Integer>();

        Map<String, Integer> map2 = new HashMap<>();

        useHashMap(new HashMap<>());

        boolean booleanResult = predicateTest(5);
        System.out.println(booleanResult);

        consumerTest(5);

        String result = functionTest(5);
        System.out.println(result);

        supplierTest(String::new);

        // 方法引用
        // byte[]::new Supplier<byte[]> supplier=()->{return new byte[]{}};
        // String::new Supplier<String> supplier=()->{return new String()};
        // A::getName Function<A,String> func=a->{return a.getName()};

        unaryOperatorTest(3);

        binaryOperatorTest(4, 5);

    }

    private static void binaryOperatorTest(int a, int b) {
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        binaryOperator.apply(a, b);
    }

    private static void unaryOperatorTest(int a) {
        UnaryOperator<Integer> unaryOperator = x -> x + 3;
        unaryOperator.apply(a);
    }


    private static String functionTest(int a) {
        Function<Integer, String> func = i -> "-" + i + "-";
        return func.apply(a);
    }


    private static void consumerTest(int a) {
        Consumer<Integer> consumer = x -> System.out.println(x);
        consumer.accept(a);
    }

    private static boolean predicateTest(int a) {
        Predicate<Integer> predicate = (Integer x) -> {
            return x > 5;
        };

        predicate = (x) -> x > 5;
        boolean result = predicate.test(a);
        return result;
    }

    private static <T> T supplierTest(Supplier<T> supplier) {
        return supplier.get();
    }


    public static void useHashMap(Map<String, Integer> hashMap) {

    }

}
