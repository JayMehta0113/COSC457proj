package admin;

import java.awt.*;
import javax.swing.*;

// Create the EditAttendance_ui class that extends JFrame
public class editAttendance_ui extends JFrame {
    // Constructor for the EditAttendance_ui class
    public editAttendance_ui() {
        // Set the title of the window to "Edit Attendance"
        setTitle("Edit Attendance");

        // Set the default close operation to dispose the window when closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the size of the window to 400px width and 300px height
        setSize(400, 300);

        // Set the layout manager for the frame to BorderLayout
        setLayout(new BorderLayout());

        // Create a label for the title with the text "Edit Attendance" and center it
        JLabel titleLabel = new JLabel("Edit Attendance", SwingConstants.CENTER);

        // Set the font for the title label to Arial, bold, size 20
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Add the title label to the top (NORTH) section of the frame
        add(titleLabel, BorderLayout.NORTH);

        // Create a new panel with a GridLayout (3 rows, 2 columns) for student info
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Create a label for the student ID
        JLabel studentLabel = new JLabel("Student ID:");

        // Create a text field for inputting the student ID
        JTextField studentIdField = new JTextField();

        // Create a label for the student name
        JLabel nameLabel = new JLabel("Student Name:");

        // Create a text field for inputting the student name
        JTextField nameField = new JTextField();

        // Create a label for the attendance status
        JLabel statusLabel = new JLabel("Attendance Status:");

        // Create a combo box for selecting attendance status (Present or Absent)
        String[] statuses = {"Present", "Absent"};
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

        // Add the panel (containing the form) to the center of the frame
        add(panel, BorderLayout.CENTER);

        // Create a new panel to hold the submit button with FlowLayout (center-aligned with padding)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Create a button labeled "Submit Changes" to submit the attendance changes
        JButton submitBtn = new JButton("Submit Changes");

        // Add the submit button to the button panel
        buttonPanel.add(submitBtn);

        // Add the button panel to the bottom (SOUTH) section of the frame
        add(buttonPanel, BorderLayout.SOUTH);
    



        //frame vis
        setVisible(true);
    }
}
