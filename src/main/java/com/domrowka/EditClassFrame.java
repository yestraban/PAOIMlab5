package com.domrowka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EditClassFrame extends JFrame implements ActionListener{
    Class classToEdit;
    JTextField nameField;
    JTextField maxCountField;
    JButton rammusButton;
    EditClassFrame(Class classInput){
        classToEdit = classInput;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon rammus = new ImageIcon("C:/Users/domro/IdeaProjects/PAOIMlab5/indeks.jpg");
        this.setSize(500, 300);
        this.setLayout(new GridLayout(3,2));
        nameField = new JTextField();
        maxCountField = new JTextField();
        rammusButton = new JButton("Ok");
        rammusButton.addActionListener(this);
        JLabel maxCountLabel = new JLabel();
        maxCountLabel.setText("Input new max count:");
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Input new Name:");
        JLabel ramLabel = new JLabel();
        ramLabel.setIcon(rammus);
        nameField.setPreferredSize(new Dimension(100, 50));
        maxCountField.setPreferredSize(new Dimension(200, 100));

        this.add(nameLabel);
        this.add(nameField);
        this.add(maxCountLabel);
        this.add(maxCountField);
        this.add(rammusButton);
        this.add(ramLabel);


        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==rammusButton){
            classToEdit.setMaxCount(Integer.parseInt(maxCountField.getText()));
            classToEdit.setName(nameField.getText());
            System.out.println(classToEdit.getName());
            System.out.println(classToEdit.getMaxCount());
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
