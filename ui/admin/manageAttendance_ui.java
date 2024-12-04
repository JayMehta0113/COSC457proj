package ui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class manageAttendance_ui extends JFrame {
    public manageAttendance_ui() {
        // Set up the frame
        setTitle("Manage Attendance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Manage Attendance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Define column names 
        String[] columnNames = {"Student ID", "Name", "Attendance Status"};

        // Sample data for the table (student ID, name, and attendance status)
        String[][] sampleData = {
            {"101", "John Doe", "Present"},
            {"102", "Jane Smith", "Absent"},
            {"103", "Michael Johnson", "Present"},
            {"104", "Emily Davis", "Absent"}
        };

        // Display the attendance data
        JTable attendanceTable = new JTable(sampleData, columnNames);
        
        // Add the table  for scrolling
        JScrollPane tableScrollPane = new JScrollPane(attendanceTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Panel for buttons 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton markAttendanceBtn = new JButton("Mark Attendance");
        JButton editAttendanceBtn = new JButton("Edit Attendance");
        JButton generateReportBtn = new JButton("Generate Report");

        // Add buttons to the button panel
        buttonPanel.add(markAttendanceBtn);
        buttonPanel.add(editAttendanceBtn);
        buttonPanel.add(generateReportBtn);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);


        //Action listeners

        //action listener for mark attendance
        markAttendanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Mark Attendance UI when the button is clicked
                new markAttendance_ui().setVisible(true);
            }
        });


        //action listener for edit attendance
        editAttendanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Mark Attendance UI when the button is clicked
                new editAttendance_ui().setVisible(true);
            }
        });

        //frame vis
        setVisible(true);
    }
}