package com.javabase.solution;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoGroup {

    /*Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。

    给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。

             

    示例 1：



    输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
    输出：["B","C"]
    解释：
    你的 id 为 0（绿色），你的朋友包括（黄色）：
    id 为 1 -> watchedVideos = ["C"] 
    id 为 2 -> watchedVideos = ["B","C"] 
    你朋友观看过视频的频率为：
    B -> 1 
    C -> 2

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/get-watched-videos-by-your-friends
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        Map<Integer, List<Integer>> friendMap = new HashMap<>();

        for (int i = 0; i < friends.length; i++){
            List<Integer> f = Arrays.stream(friends[i]).boxed().collect(Collectors.toList());

            friendMap.put(i, f);
        }

        Set<Integer> videoUsers = getLevelFriends(friendMap, id, level).stream().collect(Collectors.toSet());

        List<String> videoList = new ArrayList<>();

        for (Integer i: videoUsers) {
            videoList.addAll(watchedVideos.get(i));
        }

        return videoList.stream()
                        .collect(Collectors.toMap(obj->obj,a->1,(k1,k2)->k1=k1+1))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String,Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                        .map(Map.Entry::getKey).collect(Collectors.toList());


//        videoList.sort(Comparator.comparing(x -> videoList.stream().filter(x::equals).count()).thenComparing(Object::toString));
//        return videoList.stream().distinct().collect(Collectors.toList());

//        return videoList.stream()
//                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .sorted((m1, m2) -> m1.getValue().compareTo(m2.getValue()))
//                .map(x -> x.getKey())
//                .collect(Collectors.toList());

    }

    private static List<Integer> getLevelFriends(Map<Integer, List<Integer>> friends, int id, int level){

        if(level == 0){
            return new ArrayList<>(id);
        }

        List<Integer> fs0 = friends.get(id);
        if(level == 1){
            return fs0;
        }

        List<Integer> res = fs0;

        for (int i = 2; i <= level; i++) {

            List<Integer> fs = new ArrayList<>(res);
            res = new ArrayList<>();
            for (Integer j : fs) {
                res.addAll(friends.get(j));
            }

        }

        return res.stream().filter(x -> x != id).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        /*
        [["bjwtssmu"],["aygr","mqls"],["vrtxa","zxqzeqy","nbpl","qnpl"],["r","otazhu","rsf"],["bvcca","ayyihidz","ljc","fiq","viu"]]
        [[3,2,1,4],[0,4],[4,0],[0,4],[2,3,1,0]]
        3
        1*/

        List<String> aa = Arrays.asList(new String[]{"bjwtssmu"});
        List<String> bb = Arrays.asList(new String[]{"aygr","mqls"});
        List<String> cc = Arrays.asList(new String[]{"vrtxa","zxqzeqy","nbpl","qnpl"});
        List<String> dd = Arrays.asList(new String[]{"r","otazhu","rsf"});
        List<String> ee = Arrays.asList(new String[]{"bvcca","ayyihidz","ljc","fiq","viu"});

        List<List<String>> watchedVideos = new ArrayList<>();
        watchedVideos.add(aa);
        watchedVideos.add(bb);
        watchedVideos.add(cc);
        watchedVideos.add(dd);
        watchedVideos.add(ee);
        int[][] friends = new int[][]{{3,1,2,4},{0,4},{4,0},{0,4},{2,3,1,0}};
        int id = 3;
        int level = 1;

        watchedVideosByFriends(watchedVideos, friends, id, level).forEach(System.out::println);

        List<String> aaa = Arrays.asList(new String[]{"A","B","B","A","C","G","G","G","F"});

        aaa.stream()
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue().compareTo(m1.getValue()))
                .map(x -> x.getKey())
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);


        String[] strs = {"bvcca","viu","bjwtssmu","fiq","ayyihidz","ljc"};
        final List<String> strings = Arrays.asList(strs);
        strings.sort(Comparator.comparing(x -> strings.stream().filter(x::equals).count()).reversed().thenComparing(Object::toString));
        final List<String> collect = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);


        strings.stream()
                .collect(Collectors.toMap(obj->obj,a->1,(k1,k2)->k1=k1+1))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey).collect(Collectors.toList()).forEach(System.out::println);
    }
}
