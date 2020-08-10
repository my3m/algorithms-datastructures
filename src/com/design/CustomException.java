package com.design;

class Parent {

    static {
        System.out.println("A");
    }

    static {
        System.out.println("B");
    }

    void msg() throws RuntimeException {
        System.out.println("parent");
    }
}

class TestExceptionChild2 extends Parent {
    void msg() throws RuntimeException {
        System.out.println("child");
    }

    static {
        System.out.println("D");
    }

    static {        
        System.out.println("C");
    }



    public static void main(String args[]) {
        Parent p = new TestExceptionChild2();
        try {
            p.msg();
        } catch (Exception e) {
        }
    }
}