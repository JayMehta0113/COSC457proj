package ui.admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class createCourse_ui extends JFrame {
    // Constructor to set up the "Create Course" page
    public createCourse_ui() {

        // Set the title of the window
        setTitle("Create Course");

        // Closes the window only when the X button is pressed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the size of the window
        setSize(500, 400);

        // Layout manager
        setLayout(new BorderLayout());

        // Title Section
        JLabel titleLabel = new JLabel("Create Course", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Input Form Panel for Course Details
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Course Name Field
        formPanel.add(new JLabel("Course Name:"));
        JTextField courseNameField = new JTextField();
        formPanel.add(courseNameField);

        // Course Code Field
        formPanel.add(new JLabel("Course Code:"));
        JTextField courseCodeField = new JTextField();
        formPanel.add(courseCodeField);

        // Course Description Field
        formPanel.add(new JLabel("Description:"));
        JTextArea descriptionArea = new JTextArea(4, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane);

        // Instructor Name Field
        formPanel.add(new JLabel("Instructor:"));
        JTextField instructorField = new JTextField();
        formPanel.add(instructorField);

        // Add the form panel to the center of the window
        add(formPanel, BorderLayout.CENTER);

        // Button Panel for saving the course
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveCourseBtn = new JButton("Save Course");
        JButton cancelCourseBtn = new JButton("Cancel");

        // Add buttons to the button panel
        buttonPanel.add(saveCourseBtn);
        buttonPanel.add(cancelCourseBtn);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        //Action Listeners


        //Action listener for save button
        saveCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get course details from input fields
                String courseName = courseNameField.getText();
                String courseCode = courseCodeField.getText();
                String description = descriptionArea.getText();
                String instructor = instructorField.getText();

                // Simple validation: Ensure all fields are filled
                if (courseName.isEmpty() || courseCode.isEmpty() || description.isEmpty() || instructor.isEmpty()) {
                    JOptionPane.showMessageDialog(createCourse_ui.this,
                            "Please fill all the fields before saving.",
                            "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Show success message (in a real app, you would save to a database)
                    JOptionPane.showMessageDialog(createCourse_ui.this,
                            "Course Created:\n" + "Name: " + courseName + "\nCode: " + courseCode + "\nInstructor: " + instructor,
                            "Course Created", JOptionPane.INFORMATION_MESSAGE);

                    // Close the window after saving
                    dispose();
                }
            }
        });




        //Action listener for cancel button
        cancelCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Close the window
                dispose();
            }
        });



        //Frame vis
        setVisible(true);
    }
}
