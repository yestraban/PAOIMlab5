package com.domrowka;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Set;

public class EditStudentFrame extends JFrame implements ActionListener{
    Student studentToEdit;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField birthField;
    JTextField addPtsField;
    JTextField rmPtsField;

    JButton firstNameButt;
    JButton lastNameButt;
    JButton birthButt;
    JButton addPtsButt;
    JButton rmPtsButt;
    JButton stCndButt;

    JComboBox conditionBox;

    StudentCondition[] stCond= new StudentCondition[]{StudentCondition.catchingUp, StudentCondition.sick,StudentCondition.present, StudentCondition.notPresent};

    EditStudentFrame(Student input){
        studentToEdit = input;
        this.setSize(500, 300);
        this.setLayout(new GridLayout(6,3));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        birthField  = new JTextField();
        addPtsField = new JTextField();
        rmPtsField = new JTextField();


        firstNameButt = new JButton("ok");
        lastNameButt = new JButton("ok");
        birthButt = new JButton("ok");
        addPtsButt = new JButton("ok");
        rmPtsButt = new JButton("ok");
        stCndButt = new JButton("ok");

        firstNameButt.addActionListener(this);
        lastNameButt.addActionListener(this);
        birthButt.addActionListener(this);
        addPtsButt.addActionListener(this);
        rmPtsButt.addActionListener(this);
        stCndButt.addActionListener(this);

        JLabel firstNameLabel = new JLabel("New first name:");
        JLabel lastNameLabel = new JLabel("New last name:");
        JLabel birthLabel = new JLabel("New birth year:");
        JLabel addPtsLabel = new JLabel("Add points:");
        JLabel rmPtsLabel = new JLabel("Remove points:");
        JLabel stCndLabel = new JLabel("Change condition:");


//        String[] = {"catchingUp", "sick",
//                "present",
//                notPresent}

        conditionBox = new JComboBox(stCond);
        conditionBox.addActionListener(this);


        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(firstNameButt);

        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(lastNameButt);

        this.add(birthLabel);
        this.add(birthField);
        this.add(birthButt);

        this.add(addPtsLabel);
        this.add(addPtsField);
        this.add(addPtsButt);

        this.add(rmPtsLabel);
        this.add(rmPtsField);
        this.add(rmPtsButt);

        this.add(stCndLabel);
        this.add(conditionBox);
        this.add(stCndButt);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==firstNameButt){
            studentToEdit.setFirstName(firstNameField.getText());
        }
        if(e.getSource()==lastNameButt){
            studentToEdit.setLastName(lastNameField.getText());
        }
        if(e.getSource()==birthButt){
            studentToEdit.setBirthYear(Integer.parseInt(birthField.getText()));
        }
        if(e.getSource()==addPtsButt){
            studentToEdit.addPoints(Integer.parseInt(addPtsField.getText()));
        }
        if(e.getSource()==rmPtsButt){
            studentToEdit.removePoints(Integer.parseInt(rmPtsField.getText()));
        }
        if(e.getSource()==stCndButt){
            StudentCondition temp = stCond[conditionBox.getSelectedIndex()];
            studentToEdit.setCondition(temp);
        }
    }
}
