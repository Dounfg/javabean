package com.javabase.fastjson;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// import com.alibaba.fastjson.JSONObject;


/**
 * .
 *
 * @Author: zhangky
 * @Date: 2019/9/3 13:37
 * @Description:
 */
public class JsonTest {
    public static void main(String[] args) {
        // JSONObject jsonObject = new JSONObject();
        // Object ass = null;
        // Map<String,String> aa = (Map)ass;
        // jsonObject.put("a1","bbb1");
        // jsonObject.put("a2","bbb2");
        // jsonObject.put("a3","bbb3");
        // System.out.println(jsonObject.toString());
        // System.out.println(jsonObject.toJSONString());

        // List<String> aaa = Arrays.asList("aaa","bbb","ccc");
        // aaa.stream().filter(a->a.equals("aaa")).findAny();

//        String pos = "";
//
//        final String posX2 = Optional.ofNullable(pos).orElse("300");
//
//        System.out.println("__________");
//        System.out.println(posX2);
//        System.out.println("__________");
//        System.out.println("__________");
//
//        final String posX1 = Optional.of(pos).orElse("300");
//        System.out.println("__________");
//        System.out.println(posX1);
//        String date2 = "2022-11-12 23:34:23";
//        System.out.println(date2.replace("-","").substring(0,8));
//        System.out.println(date2.substring(11,19));

        String str = "Do you like watching Game of Thrones 小米pro";
        System.out.println(str.contains("like"));

        /* this will print false as the contains() method is
         * case sensitive. Here we have mentioned letter "l"
         * in upper case and in the actual string we have this
         * letter in the lower case.
         */
        System.out.println(str.contains("Like"));
        System.out.println(str.contains("小米Pro"));
        System.out.println(str.contains("小米PRo"));
        System.out.println(str.toLowerCase(Locale.ROOT).contains("小米PRo".toLowerCase(Locale.ROOT)));
        System.out.println(str.toLowerCase(Locale.ROOT).contains("小米PRo".toLowerCase(Locale.ROOT)));

        String content1 = "小米雷军雷军手机";
        String content2 = "小米雷军手机";
        String pattern = "小米.{0,3}手机";
        boolean isMatch1 = Pattern.matches(pattern, content1);
        boolean isMatch2 = Pattern.matches(pattern, content2);
        System.out.println("字符串中是否包含了 '小米2手机' 子字符串? " + isMatch1);
        System.out.println("字符串中是否包含了 '小米2手机' 子字符串? " + isMatch2);

    }
}
