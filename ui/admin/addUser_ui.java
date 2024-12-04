package ui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*; 


public class addUser_ui extends JDialog {
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField roleField;
    private Connection connection;

    public addUser_ui(JFrame parent) {
        super(parent, "Add User", true);
        setLayout(new GridLayout(4, 2));
        setSize(300, 200);

        // User ID field
        add(new JLabel("User ID:"));
        userIdField = new JTextField();
        add(userIdField);

        // Name field
        add(new JLabel("Name (First Last):"));
        nameField = new JTextField();
        add(nameField);

        // Role field
        add(new JLabel("Role:"));
        roleField = new JTextField();
        add(roleField);

        // Save and Cancel buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        add(saveButton);
        add(cancelButton);

         // Initialize the database connection
         try {
            connection = DC.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error connecting to database: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return; // Exit if connection fails
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // display the entered data as a message
                String userId = userIdField.getText();
                String name = nameField.getText();
                String role = roleField.getText();

                JOptionPane.showMessageDialog(addUser_ui.this, 
                        "User Added:\nID: " + userId + "\nName: " + name + "\nRole: " + role,
                        "User Added", JOptionPane.INFORMATION_MESSAGE);

                dispose(); // Close the dialog after adding the user
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog if the user cancels
            }
        });





        setVisible(true);
    }


        private void addUserToDatabase() {
    String userId = userIdField.getText();
    String[] nameParts = nameField.getText().split(" ");
    String role = roleField.getText();

    if (nameParts.length < 2) {
        JOptionPane.showMessageDialog(this, 
            "Please enter a valid name (First Last).", 
            "Input Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String firstName = nameParts[0];
    String lastName = nameParts[1];

    try {
        PreparedStatement statement = null;
        switch (role.toLowerCase()) {
            case "teacher":
                String teacherQuery = "INSERT INTO Teacher (teacher_Id, first_Name, last_Name, role_Id) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(teacherQuery);
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setInt(4, 1); // Example role_Id for Teacher
                break;

            case "student":
                String studentQuery = "INSERT INTO Student (student_Id, first_Name, last_Name, role_Id) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(studentQuery);
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setInt(4, 2); // Example role_Id for Student
                break;

            case "admin":
                String adminQuery = "INSERT INTO Admin (admin_Id, first_Name, last_Name, role_Id) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(adminQuery);
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setInt(4, 3); // Example role_Id for Admin
                break;

            default:
                JOptionPane.showMessageDialog(this, 
                    "Invalid role. Please enter Teacher, Student, or Admin.", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
        }

        // Execute the statement
        if (statement != null) {
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, 
                "User added successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error adding user to database: " + ex.getMessage(), 
            "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, 
            "User ID must be a number.", 
            "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}