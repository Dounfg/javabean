package com.javabase.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Luomabak {

    public static int romanToInt(String s) {

        if (s.length() == 0 || s.length() > 15) {
            return 0;
        }

        if (exists(s)) {
            return 0;
        }

        Map<String, Long> dict = new HashMap<>();
        dict.put("I", 1L);
        dict.put("V", 5L);
        dict.put("X", 10L);
        dict.put("L", 50L);
        dict.put("C", 100L);
        dict.put("D", 500L);
        dict.put("M", 1000L);

        Map<String, Long> map = Stream.of(s.split("")).collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        Long result = map.keySet().stream().mapToLong(x->map.get(x) * dict.get(x)).sum();

        String[] str = s.split("");
        for(int i=1; i < str.length; i++) {
            if (dict.get(str[i-1]) < dict.get(str[i])) {
                result = result - dict.get(str[i-1]) * 2;
            }
        }

        return result.intValue();

    }

    private static boolean exists(String s) {

        List<String> list = Arrays.asList(new String[] { "I", "V", "X", "L", "C", "D", "M" });

        List<String> chars = Arrays.asList(s.split(""));

        for (int i = 0; i < chars.size(); i++) {
            if (!list.contains(chars.get(i))){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        romanToInt("XXI");
        
    }

}
