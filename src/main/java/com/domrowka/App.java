package com.domrowka;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void abc( String[] args ) {
        Student student0= new Student("Dominik", "Krowka", StudentCondition.present, 2000, 20);
        Student student1= new Student("Maciej", "Kleryk", StudentCondition.sick, 1999, 30);
        Student student2= new Student("Róża", "Kusza", StudentCondition.notPresent, 2000, 25);
        Student student3= new Student("Michał", "Kwarc", StudentCondition.present, 1998, 20);
        Student student4= new Student("Witkor", "Ciklasz", StudentCondition.present, 2000, 21);

        Class class0 = new Class("DreamTeam", 4);
        Class class1 = new Class("Matryce", 15);    //create some students and classes

        student0.print();       //print the single student
        System.out.println("");

        class0.addStudent(student0);
        class0.addStudent(student1);
        class0.addStudent(student2);//add students to group

        class0.addStudent(student0);    //try adding existing student

        class0.addStudent(student3);
        class0.addStudent(student4);    //try adding student over capacity

        class0.summary();   //print class
        System.out.println("");

        class0.sortByName();    //print sorted class
        class0.summary();
        System.out.println("");

        class0.sortByPoints();
        class0.summary();   //print sorted by points
        System.out.println("");

        System.out.println(class0.countByCondition(StudentCondition.present));  //print amt of present students
        System.out.println("");

        class0.search("Dominik", "Krowka").print();     //print found student
        System.out.println("");

        List<Student> search= class0.searchPartial("min");
        search.get(0).print(); //print first record containing "min"
        System.out.println("");

        student3.print();
        class0.addPoints(student3, 20.0);   //add points to student
        student3.print();
        System.out.println("");

        class0.removePoints(student3, 10.0);   //REMOVE points from student
        student3.print();
        System.out.println("");

        class0.changeCondition(student0, StudentCondition.sick);    //change condition to sick
        student0.print();
        System.out.println("");

        class0.getStudent(student0);        //remove student
        class0.summary();
        System.out.println("");

        class0.max().print();   //print max student
        System.out.println("");

        ClassContainer ccontainer = new ClassContainer();       //create class container

        ccontainer.addClass(class0);
        ccontainer.addClass("Nowa", 15);            //add classes to container

        ccontainer.summary();       //print container
        System.out.println("");

        List<Class> returnList = ccontainer.findEmpty();
        System.out.println(returnList.size());      //find empty classes and print ammount (cant print empty class)
        System.out.println("");

        ccontainer.removeClass("Nowa");
        ccontainer.summary();                       //remove class
        System.out.println("");

        ccontainer.thanosSnap();            //snap the class
        ccontainer.summary();


    }
}
