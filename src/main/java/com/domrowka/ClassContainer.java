package com.domrowka;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class ClassContainer {
    Map<String,Class> classes = new HashMap<>();

    public void addClass(String name, int size) {
        Class addThis = new Class(name, size);
        classes.put(name, addThis);
    }

    public void addClass(Class addThis) {
        classes.put(addThis.name, addThis);
    }

    public void  removeClass(String name) {
        classes.remove(name);
    }

    public List findEmpty() {
        List<Class> returnList = new ArrayList<Class>();
        Set<String> keySet = classes.keySet();
        for (String name: keySet) {
            if (classes.get(name).count == 0) {
                returnList.add(classes.get(name));
            }
        }
        return returnList;
    }

    public void summary() {
        Set<String> keySet = classes.keySet();
        for (String name: keySet) {
            double percent = ((double)classes.get(name).count)/((double)classes.get(name).maxCount+1);
            percent *= 100;
            System.out.println(name+" "+percent);
        }
    }

    public List<String[]> getData(){
        List<String[]> data = new ArrayList<String[]>();
        Set<String> keySet = classes.keySet();
        for (String name: keySet) {
            String[] temp = {classes.get(name).getName(), String.valueOf(classes.get(name).count), String.valueOf(classes.get(name).maxCount)};
            data.add(temp);
        }
        return data;
    }

    public void thanosSnap() {
        Set<String> keySet = classes.keySet();
        for (String name: keySet) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100);
            if (randomNum>=50) {
                removeClass(name);
            }
        }
        System.out.println("Perfectly balanced, as all things should be");

    }
}
