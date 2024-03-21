package com.javabase.jdk8;

public class Man extends Person implements Action {

    private String name = initName();

    private static String staticName = initStaticName();

    public String initName() {
        System.out.println("Man:name init");
        return "";
    }

    public static String initStaticName() {
        System.out.println("Man:staticName init");
        return "";
    }

    public Man() {
        System.out.println("Man:consturctor");
    }

    {
        System.out.println("Man:{}");
    }

    static {
        System.out.println("Man:static{}");
    }

    public void say() {
        super.say();
        System.out.println("Man:say()");
    }

    public String getName() {
        return name;
    }

    public static String getStaticName() {
        return staticName;
    }

}
