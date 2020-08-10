package com.design;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Mustang extends Car {

    public static Car car = new Mustang();

    protected void drive() {
        System.out.println("mustang");
    }

    public static void main(String[] args) {

        car.drive();

        Car car2 = new Mustang();
        car2.drive();

        List<String> arr = Arrays.asList("Apple", "Coconut", "Banana", "Pineapple");
        Set<String> arrSet = new HashSet<String>(arr);
        Iterator<String> it = arrSet.iterator();
        while (it.hasNext()) {
            String fruit = it.next();
            if (fruit.startsWith("Coco"))
                it.remove();
            System.out.println(fruit);
        }
        System.out.println("#################");
        it = arrSet.iterator();
        while (it.hasNext()) {
            String fruit = it.next();
            System.out.println(fruit);
        }
    }

    @Override
    public void doUnlock() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doLock() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doAlarm() {
        // TODO Auto-generated method stub

    }
}