package com.domrowka;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>{
    String firstName;
    String lastName;
    StudentCondition condition;
    int birthYear;
    double points;

    public Student(String fN, String lN, StudentCondition con, int bY, double pts) {
        firstName=fN;
        lastName=lN;
        condition=con;
        birthYear=bY;
        points=pts;
    }

    public Student(){
        firstName="Nowy";
        lastName="Student";
        points = 0;
    }

    public void setFirstName(String nm){
        firstName = nm;
    }

    public void setLastName(String nm){
        lastName = nm;
    }

    public void setCondition(StudentCondition stcnd){
        condition = stcnd;
    }

    public void setBirthYear(int y){
        birthYear = y;
    }

    public void addPoints(int pts) {
        points+=pts;
    }

    public void removePoints(int pts) {
        points -= pts;
        if (points<0){
            points = 0;
        }
    }

    public void print() {
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Condition: " + condition);
        System.out.println("Birth year: " + birthYear);
        System.out.println("Points: " + points);
    }

    @Override
    public int compareTo(Student o) {
        int compareNames = lastName.compareTo(o.lastName);
        if(compareNames==0) {
            return firstName.compareTo(o.firstName);
        }
        else {
            return compareNames;
        }
    }

    public void editStudent(){

    }
}
