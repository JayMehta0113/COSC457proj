package admin;

import java.awt.*;
import javax.swing.*;


public class assignTeachers_ui extends JFrame {
    public assignTeachers_ui() {
        // Set up the frame
        setTitle("Assign Teachers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Assign Teachers to Courses", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for assigning teachers
        JPanel assignPanel = new JPanel();
        assignPanel.setLayout(new GridLayout(3, 2, 10, 10));

         // Course ID label and text field
         JLabel courseLabel = new JLabel("Course ID:");
         JTextField courseField = new JTextField();
         
         // Teacher ID label and text field
         JLabel teacherLabel = new JLabel("Teacher ID:");
         JTextField teacherField = new JTextField();
        
        // Assign button
        JButton assignButton = new JButton("Assign");

        // Add components to the panel
        assignPanel.add(courseLabel);
        assignPanel.add(courseField);
        assignPanel.add(teacherLabel);
        assignPanel.add(teacherField);
        assignPanel.add(assignButton);

        // Add the panel to the frame
        add(assignPanel, BorderLayout.CENTER);


         // Action listener for the Assign button
         assignButton.addActionListener(e -> {
            String courseId = courseField.getText().trim();
            String teacherId = teacherField.getText().trim();
            
            if (courseId.isEmpty() || teacherId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Course ID and Teacher ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Simulate the assignment action
                JOptionPane.showMessageDialog(this, "Assigned Teacher ID " + teacherId + " to Course ID " + courseId, "Success", JOptionPane.INFORMATION_MESSAGE);
                // You can add database or logic code to assign the teacher to the course here.
            }
        });







        //frame vis
        setVisible(true);
    }

}