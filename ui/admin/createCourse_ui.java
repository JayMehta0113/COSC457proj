
package ui.admin;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createCourse_ui extends JFrame {
    private Connection connection; // Database connection object

    // Constructor to set up the "Create Course" page
    public createCourse_ui() {

        //initalize connection
        DC DC = new DC();

        // Set the title of the window
        setTitle("Create Course");

        // Close the window only when the X button is pressed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the size of the window
        setSize(450, 350);

        // Use a BorderLayout
        setLayout(new BorderLayout());

        // Title Section
        JLabel titleLabel = new JLabel("Create Course", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Input Form Panel for Course Details
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Course ID Field
        formPanel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField(10);
        formPanel.add(courseIdField);

        // Course Name Field
        formPanel.add(new JLabel("Course Name:"));
        JTextField courseNameField = new JTextField(15);
        formPanel.add(courseNameField);

        // Course Description Field
        formPanel.add(new JLabel("Description:"));
        JTextArea descriptionArea = new JTextArea(3, 15);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane);

        // Grade Level Field
        formPanel.add(new JLabel("Grade Level:"));
        JTextField gradeLevelField = new JTextField(10);
        formPanel.add(gradeLevelField);

        // Subject ID Field
        formPanel.add(new JLabel("Subject ID:"));
        JTextField subjectIdField = new JTextField(10);
        formPanel.add(subjectIdField);

        // Teacher ID Field
        formPanel.add(new JLabel("Teacher ID:"));
        JTextField teacherIdField = new JTextField(10);
        formPanel.add(teacherIdField);

        // Add the form panel to the center of the window
        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveCourseBtn = new JButton("Save Course");
        JButton cancelCourseBtn = new JButton("Cancel");

        // Add buttons to the button panel
        buttonPanel.add(saveCourseBtn);
        buttonPanel.add(cancelCourseBtn);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the database connection
        try {
            connection = DC.getConnection(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return; // Exit if connection fails
        }

        // Action listener for Save button
        saveCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get course details from input fields
                String courseId = courseIdField.getText();
                String courseName = courseNameField.getText();
                String description = descriptionArea.getText();
                String gradeLevel = gradeLevelField.getText();
                String subjectId = subjectIdField.getText();
                String teacherId = teacherIdField.getText();

                // Simple validation: Ensure all fields are filled
                if (courseId.isEmpty() || courseName.isEmpty() || description.isEmpty() || gradeLevel.isEmpty()
                        || subjectId.isEmpty() || teacherId.isEmpty()) {
                    JOptionPane.showMessageDialog(createCourse_ui.this,
                            "Please fill all fields before saving.",
                            "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Save course details to the database
                try {
                    String sql = "INSERT INTO Course (course_Id, course_Name, description, grade_Level, subject_Id, teacher_Id) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, Integer.parseInt(courseId)); // Convert courseId to integer
                    preparedStatement.setString(2, courseName);
                    preparedStatement.setString(3, description);
                    preparedStatement.setInt(4, Integer.parseInt(gradeLevel)); // Convert gradeLevel to integer
                    preparedStatement.setInt(5, Integer.parseInt(subjectId)); // Convert subjectId to integer
                    preparedStatement.setInt(6, Integer.parseInt(teacherId)); // Convert teacherId to integer

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(createCourse_ui.this,
                                "Course Created Successfully!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Close the window after saving
                    } else {
                        JOptionPane.showMessageDialog(createCourse_ui.this,
                                "Failed to create course. Please try again.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(createCourse_ui.this,
                            "Error saving course to database: " + ex.getMessage(),
                            "Database Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(createCourse_ui.this,
                            "Course ID, Grade Level, Subject ID, and Teacher ID must be numbers.",
                            "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Action listener for Cancel button
        cancelCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        // Frame visibility
        setVisible(true);
    }
}
