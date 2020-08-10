package com.design;

import static java.lang.System.out;
import static com.design.Mustang.car;

class Foo {
    private String attribute;

    public Foo(String a) {
        this.attribute = a;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}

public class Main {
    public static void main(String[] args) {
        out.println("AAAA");

        car.drive();

        Object xx = new Object();

        Foo f = new Foo("f");
        changeReference(f); // It won't change the reference!
        modifyReference(f); // It will change the object that the reference variable "f" refers to!
        System.out.println(f.getAttribute());
    }

    public static void changeReference(Foo a) {
        Foo b = new Foo("b");
        a = b;
    }

    public static void modifyReference(Foo c) {
        // c.setAttribute("c");
        Foo x = new Foo("x");
        c = x;
    }
}