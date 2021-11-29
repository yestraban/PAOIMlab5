package com.domrowka;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MainFrame extends JFrame implements ActionListener {
    JButton deleteClassButton;
    JButton addClassButton;
    JButton editClassButton;
    JButton deleteStudentButton;
    JButton addStudentButton;
    JButton editStudentButton;
    JButton filterButton;
    JButton resetFilterButton;
    ClassContainer classes = new ClassContainer();
    final Class[] selectedClass = {new Class()};
    final Student[] selectedStudent = {new Student()};
    final JTable classesTable;
    JTable studentsTable;
    JTextField studentNameField;
    JTextField classNameField;
    List<Student> tempStudentList;

    TableModel dataModelClass = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            return classes.classes.size();
        }

        @Override
        public String getColumnName(int columnIndex){
            String[] names = {"Name", "Count", "Max Count"};
            return names[columnIndex];
        }
        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
//                Set<String> keySet = classes.classes.keySet();
            List<String[]> data = classes.getData();
            String[] temp =data.get(rowIndex);
            return temp[columnIndex];

        }
    };

    TableModel dataModelStudent = new AbstractTableModel() {
        @Override
        public int getRowCount() {
            return selectedClass[0].studentList.size();
        }

        @Override
        public String getColumnName(int columnIndex){
            String[] names = {"First Name", "Last Name","Points", "Birth Year", "Condition"};
            return names[columnIndex];
        }
        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            List<String[]> data = selectedClass[0].getData();
            String[] temp =data.get(rowIndex);

            return temp[columnIndex];
        }
    };


    MainFrame(ClassContainer classesInput){

        classes = classesInput;
        deleteClassButton = new JButton("Delete class");
        addClassButton = new JButton("Add Class");
        editClassButton = new JButton("Edit Class");
        deleteStudentButton = new JButton("delete Student");
        addStudentButton = new JButton("add Student");
        editStudentButton = new JButton("edit Student");
        filterButton = new JButton("filter");
        resetFilterButton = new JButton("reset filter");

        deleteClassButton.addActionListener(this);
        addClassButton.addActionListener(this);
        editClassButton.addActionListener(this);
        deleteStudentButton.addActionListener(this);
        addStudentButton.addActionListener(this);
        editStudentButton.addActionListener(this);
        filterButton.addActionListener(this);
        resetFilterButton.addActionListener(this);

        classesTable = new JTable(dataModelClass);
        studentsTable = new JTable(dataModelStudent);

        classesTable.setCellSelectionEnabled(true);
        studentsTable.setCellSelectionEnabled(true);

        ListSelectionModel select = classesTable.getSelectionModel();
        ListSelectionModel select2 = studentsTable.getSelectionModel();

        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        select2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent f) {
                int row = studentsTable.getSelectedRow();
                selectedStudent[0] = selectedClass[0].studentList.get(row);
            }
        });

        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent g) {
                int row = classesTable.getSelectedRow();
                String data = (String)classesTable.getValueAt(row,0);
                selectedClass[0] = classes.classes.get(data); //can i do it like that??
                studentsTable.updateUI();
            }
        });
        tempStudentList = selectedClass[0].studentList;
        studentNameField = new JTextField();
        classNameField = new JTextField();

        JLabel classNameLabel = new JLabel("Name of a new class:");
        JLabel firstNameLabel = new JLabel("filter by:");

        studentNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentsTable.updateUI();
            }
        });


        JScrollPane classesTablePane = new JScrollPane(classesTable);
        JScrollPane studentTablePane = new JScrollPane(studentsTable);

        classesTable.setFillsViewportHeight(true);
        this.setTitle("Title");
        this.setLayout(new GridLayout(7, 2));
        this.setSize(1000, 1000);
        //this.add(classesTable.getTableHeader());
        this.add(classesTablePane);
        //this.add(studentsTable.getTableHeader());
        this.add(studentTablePane);


        this.add(deleteClassButton);
        this.add(deleteStudentButton);
        this.add(classNameLabel);
        this.add(firstNameLabel);
        this.add(classNameField);
        this.add(studentNameField);
        this.add(filterButton);
        this.add(resetFilterButton);
        this.add(addClassButton);
        this.add(addStudentButton);
        this.add(editClassButton);
        this.add(editStudentButton);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deleteClassButton){
            classes.removeClass(selectedClass[0].getName());
            classesTable.updateUI();
            studentsTable.updateUI();
        }

        if(e.getSource()==deleteStudentButton){
            selectedClass[0].getStudent(selectedStudent[0]);
            studentsTable.updateUI();
            classesTable.updateUI();
        }

        if(e.getSource()==addClassButton){
            Class newClass = new Class();
            if(Objects.equals(classNameField.getText(), "")){
                return;
            }
            newClass.name = classNameField.getText();
            classes.addClass(newClass);
            studentsTable.updateUI();
            classesTable.updateUI();
            classNameField.setText("");
        }

        if(e.getSource()==addStudentButton){
            Student newStudent = new Student();
            selectedClass[0].addStudent(newStudent);
            studentsTable.updateUI();
            classesTable.updateUI();
        }

        if(e.getSource()==editClassButton){
            EditClassFrame frame = new EditClassFrame(selectedClass[0]);
            studentsTable.updateUI();
            classesTable.updateUI();
        }

        if(e.getSource()==editStudentButton){
            EditStudentFrame frame = new EditStudentFrame(selectedStudent[0]);
            studentsTable.updateUI();
            classesTable.updateUI();
        }

        if(e.getSource()==filterButton){
            if(Objects.equals(studentNameField.getText(), "")){
                return;
            }
            if(!tempStudentList.isEmpty()){
                selectedClass[0].studentList = tempStudentList;
            }
            tempStudentList = selectedClass[0].studentList;
            selectedClass[0].studentList = selectedClass[0].searchPartial(studentNameField.getText());
            studentsTable.updateUI();
            classesTable.updateUI();
        }

        if (e.getSource()==resetFilterButton){
            if(!tempStudentList.isEmpty()){
                selectedClass[0].studentList = tempStudentList;
            }
            studentsTable.updateUI();
            classesTable.updateUI();
        }

    }
}
