package com.javabase.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Luoma {

    public static int romanToInt(String s) {

        if (s.length() == 0 || s.length() > 15) {
            return 0;
        }

        if (exists(s)) {
            return 0;
        }

        Map<String, Integer> dict = new HashMap<>();
        dict.put("I", 1);
        dict.put("V", 5);
        dict.put("X", 10);
        dict.put("L", 50);
        dict.put("C", 100);
        dict.put("D", 500);
        dict.put("M", 1000);
        int result = 0;
        String[] str = s.split("");

        for(int i=0; i < str.length; i++) {
            result = result + dict.get(str[i]);
        }

        for(int j=1; j < str.length; j++) {
            if (dict.get(str[j-1]) < dict.get(str[j])) {
                result = result - dict.get(str[j-1]) * 2;
            }
        }

        return result;

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

        int[][] aaa = {{1,2},{2,3},{3,4} };
        System.out.println(aaa.length);
        //romanToInt("XXI");
        
    }

}
