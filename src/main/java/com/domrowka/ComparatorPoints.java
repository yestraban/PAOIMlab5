package com.domrowka;

import java.util.Comparator;

public abstract class ComparatorPoints implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        if(o1.points>o2.points) {
            return 1;
        }
        else if(o1.points<o2.points) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
