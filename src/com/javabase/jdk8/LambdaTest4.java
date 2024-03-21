package com.javabase.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LambdaTest4 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 2);

        System.out.println("-------Sorted--------------: ");

        list.stream().sorted().forEach(System.out::println);

        System.out.println("-------Filter--------------");

        list.stream().filter(a -> a > 1).count();// 计算过滤的个数

        list.stream().filter(a -> a > 1).forEach(System.out::println);

        System.out.println("-------Match--------------");
        // 只要集合中有一个元素匹配条件则为true,否则为false
        boolean result = list.stream().anyMatch(a -> a > 2);
        System.out.println("anyMath:" + result);

        result = list.stream().allMatch(a -> a > 1);
        System.out.println("allMath:" + result);

        System.out.println("-------Get--------------");
        list.stream().limit(2).forEach(System.out::println);

        // 获取集合中任意一个元素
        Optional<Integer> optional = list.stream().findAny();

        // 如果元素为空则设置初始值0
        int intResult = optional.orElse(0);

        // 如果元素为空则复杂初始值
        // intResult=optional.orElseGet(()->new Integer(1000));

        // 如果元素为空则抛出异常
        // intResult=optional.orElseThrow(RuntimeException::new);

        System.out.println("findAny:" + intResult);

        // 获取集合中第一个元素
        optional = list.stream().findFirst();

        intResult = optional.orElse(0);

        System.out.println("findFirst:" + intResult);

        System.out.println("------Calc------------");
        intResult = list.stream().max((a, b) -> a.compareTo(b)).orElse(0);
        System.out.println("max:" + intResult);

        intResult = list.stream().max((a, b) -> b.compareTo(a)).orElse(0);
        System.out.println("min:" + intResult);

        intResult = list.stream().reduce((a, b) -> a + b).orElse(0);
        System.out.println("sum:" + intResult);

        // Compile 累加器
        //
        //

    }

}
