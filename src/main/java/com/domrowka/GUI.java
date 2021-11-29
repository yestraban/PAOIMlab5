package com.domrowka;
import javax.swing.*;
import java.awt.*;


public class GUI {
    public static void main(String[] args) {
        ClassContainer tempClasses = new ClassContainer();
        Class tempclass = new Class();
        tempclass.addStudent(new Student("Dominik", "Mrowka", StudentCondition.present, 2000, 69));
        tempclass.addStudent(new Student("Nikodem", "Mrowka", StudentCondition.present, 2000, 69));

        tempClasses.addClass(tempclass);
        MainFrame frame = new MainFrame(tempClasses);
    }
}
