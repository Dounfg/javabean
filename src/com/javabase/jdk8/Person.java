package com.javabase.jdk8;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int id = initId();

    private static int staticId = initStaticId();

    public Person() {
        System.out.println("Person:constructor");
    }

    {
        System.out.println("Person:{}");
    }

    static {
        System.out.println("Person:static{}");
    }

    private static int initStaticId() {
        System.out.println("Person:staticId init");
        return 0;
    }

    private int initId() {
        System.out.println("Person:id init");
        return 0;
    }

    public void say() {
        System.out.println("Person:say()");
    }

    public int getId() {
        return id;
    }

    public static int getStaticId() {
        return staticId;
    }

    public static void main(String[] args) {
        new Man().say();
    }

    /*******************/
    private List<Children> childrens = new ArrayList<>();

    public List<Children> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Children> childrens) {
        this.childrens = childrens;
    }

}
