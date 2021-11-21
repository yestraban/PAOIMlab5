package com.domrowka;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MainFrame extends JFrame implements ActionListener {
    JButton deleteClassButton;
    JButton addClassButton;
    JButton editClassButton;
    JButton deleteStudentButton;
    JButton addStudentButton;
    JButton editStudentButton;
    ClassContainer classes = new ClassContainer();
    final Class[] selectedClass = {new Class()};
    final Student[] selectedStudent = {new Student()};

    MainFrame(){
        deleteClassButton = new JButton();
        addClassButton = new JButton();
        editClassButton = new JButton();
        deleteStudentButton = new JButton();
        addStudentButton = new JButton();
        editStudentButton = new JButton();

        deleteClassButton.addActionListener(this);
        addClassButton.addActionListener(this);
        editClassButton.addActionListener(this);
        deleteStudentButton.addActionListener(this);
        addStudentButton.addActionListener(this);
        editStudentButton.addActionListener(this);

        TableModel dataModelClass = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return selectedClass.length;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Set<String> keySet = classes.classes.keySet();
                Object[] nazwy = keySet.toArray();
                return nazwy[rowIndex];
            }
        };

        final JTable classesTable = new JTable(dataModelClass);
        classesTable.setCellSelectionEnabled(true);
        ListSelectionModel select = classesTable.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = classesTable.getSelectedRow();
                String data = (String)classesTable.getValueAt(row,0);
                selectedClass[0] = classes.classes.get(data); //can i do it like that??
            }
        });
        this.setTitle("Title");
        this.setLayout(new GridLayout(4, 2));
        this.setSize(1000, 1000);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deleteClassButton){
            classes.removeClass(selectedClass[0].getName());
        }

        if(e.getSource()==deleteStudentButton){
            selectedClass[0].getStudent(selectedStudent[0]);
        }

        if(e.getSource()==addClassButton){
            Class newClass = new Class();
            classes.addClass(newClass);
        }

        if(e.getSource()==editClassButton){
            EditClassFrame frame = new EditClassFrame(selectedClass[0]);
        }

        if(e.getSource()==editStudentButton){
            EditStudentFrame frame = new EditStudentFrame(selectedStudent[0]);
        }

    }
}
