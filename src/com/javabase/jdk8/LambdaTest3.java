package com.javabase.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream extends interface:BaseStream 内部实现还是基于iterator IntStream LongStream
 * DoubleStream
 *
 */
public class LambdaTest3 {

    public static void main(String[] args) {

        Stream.of(Arrays.asList(1, 2, 3)).forEach((a) -> System.out.println(a));

        IntStream.of(1, 2, 3).forEach((a) -> System.out.println(a));

        LongStream.of(1, 2, 3).forEach((a) -> System.out.println(a));

        DoubleStream.of(1.1, 2.2, 3.3).forEach((a) -> System.out.println(a));

        List<Integer> list = Arrays.asList(1, 2, 3);
        // ArrayUtil实现了forEach方法，可直接使用
        list.forEach((a) -> System.out.println(a));

        // java.util.Collection中有默认的stream实现
        list.stream().forEach((a) -> System.out.println(a));

    }


}
