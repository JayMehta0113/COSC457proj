package admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class markAttendance_ui extends JFrame {
    public markAttendance_ui() {
        // Set the title of the window
        setTitle("Mark Attendance");
        
        // Set the default close operation to dispose the window when closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Set the size of the frame to 400px by 300px
        setSize(400, 300);
        
        // Set the layout manager to BorderLayout for organizing components
        setLayout(new BorderLayout());
    
        // Create a label with the text "Mark Attendance" and center-align it
        JLabel titleLabel = new JLabel("Mark Attendance", SwingConstants.CENTER);
        
        // Set the font of the title label to Arial, bold, size 20
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        // Add the title label to the top of the frame using BorderLayout.NORTH
        add(titleLabel, BorderLayout.NORTH);
    
        // Create a panel with a GridLayout to organize the student input fields
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
    
        // Create a label for the student ID field
        JLabel studentLabel = new JLabel("Student ID:");
        
        // Create a text field for entering the student ID
        JTextField studentIdField = new JTextField();
    
        // Create a label for the student name field
        JLabel nameLabel = new JLabel("Student Name:");
        
        // Create a text field for entering the student's name
        JTextField nameField = new JTextField();
    
        // Create a label for the attendance status selection
        JLabel statusLabel = new JLabel("Attendance Status:");
        
        // Create an array of statuses for the JComboBox
        String[] statuses = {"Present", "Absent"};
        
        // Create a combo box for selecting attendance status
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    
        // Add the student ID label and text field to the panel
        panel.add(studentLabel);
        panel.add(studentIdField);
        
        // Add the student name label and text field to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        
        // Add the attendance status label and combo box to the panel
        panel.add(statusLabel);
        panel.add(statusComboBox);
    
        // Add the panel to the center of the frame
        add(panel, BorderLayout.CENTER);
    
        // Create a panel for the submit button and set its layout to FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        // Create a button to submit the attendance
        JButton submitBtn = new JButton("Submit Attendance");
    
        // Add the submit button to the button panel
        buttonPanel.add(submitBtn);
    
        // Add the button panel to the bottom of the frame using BorderLayout.SOUTH
        add(buttonPanel, BorderLayout.SOUTH);
    


        //Action listeners

        // Action listener for Submit button
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from the input fields
                String studentId = studentIdField.getText();
                String studentName = nameField.getText();
                String attendanceStatus = (String) statusComboBox.getSelectedItem();

                // Check if all fields are filled
                if (studentId.isEmpty() || studentName.isEmpty()) {
                    JOptionPane.showMessageDialog(markAttendance_ui.this, "Please fill in all fields.",
                            "Incomplete Data", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Here you can update the attendance status in the database or data structure
                    JOptionPane.showMessageDialog(markAttendance_ui.this,
                            "Attendance for " + studentName + " (" + studentId + ") marked as " + attendanceStatus,
                            "Attendance Submitted", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Optionally, clear the fields after submission
                    studentIdField.setText("");
                    nameField.setText("");
                    statusComboBox.setSelectedIndex(0);
                }
            }
        });




        //frame vis
        setVisible(true);
    }
}
