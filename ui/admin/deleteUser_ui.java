/*package ui.admin;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class deleteUser_ui extends JDialog {
    // Constructor that creates the delete user confirmation dialog
    public deleteUser_ui(JFrame parent, String userId, String name) {
        // Call the superclass constructor to set the parent window, title, and modal behavior
        super(parent, "Delete User", true);
        setLayout(new BorderLayout());
        setSize(300, 150);

        // Create a label with a confirmation message
        JLabel messageLabel = new JLabel("Are you sure you want to delete user: " + name + " (ID: " + userId + ")?", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Create a panel for the buttons (Yes, No)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add the buttons to the panel
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);


        //Actiion Listeners
        //Yes
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can add database or table removal logic here
                // In this example, we just show a confirmation message
                JOptionPane.showMessageDialog(deleteUser_ui.this, 
                        "User " + name + " has been deleted.", "User Deleted", JOptionPane.INFORMATION_MESSAGE);
                
                // Close the dialog after deletion
                dispose();
            }
        });

        //No
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog 
                dispose();
            }
        });

        


        //dialog vis
        setVisible(true);

    }
}*/
package ui.admin;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class deleteUser_ui extends JDialog {
    private Connection connection;

    // Constructor that creates the delete user confirmation dialog
    public deleteUser_ui(JFrame parent, String userId, String name) {
        // Call the superclass constructor to set the parent window, title, and modal behavior
        super(parent, "Delete User", true);
        setLayout(new BorderLayout());
        setSize(300, 150);

        // Create a label with a confirmation message
        JLabel messageLabel = new JLabel("Are you sure you want to delete user: " + name + " (ID: " + userId + ")?", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Create a panel for the buttons (Yes, No)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add the buttons to the panel
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the database connection
        try {
            connection = DC.getConnection(); // Ensure you have a proper database connection utility
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error connecting to database: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return; // Exit if connection fails
        }

        // ActionListener for "Yes" button
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserFromDatabase(userId); // Call method to delete user from database
            }
        });

        // ActionListener for "No" button
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without deleting
                dispose();
            }
        });

        // Dialog visibility
        setVisible(true);
    }

    // Method to delete the user from the database
    private void deleteUserFromDatabase(String userId) {
        try {
            // Start transaction to ensure integrity
            connection.setAutoCommit(false);

            // Check if user exists in any of the tables (Teacher, Staff, Guardian, Student)
            boolean userFound = false;
            String deleteQuery = null;
            String tableName = null;

            // Check if user exists in Teacher table
            if (userExistsInTable("Teacher", userId)) {
                deleteQuery = "DELETE FROM Teacher WHERE teacher_Id = ?";
                tableName = "Teacher";
            } 
            // Check if user exists in Staff table
            else if (userExistsInTable("Staff", userId)) {
                deleteQuery = "DELETE FROM Staff WHERE staff_Id = ?";
                tableName = "Staff";
            } 
            // Check if user exists in Guardian table
            else if (userExistsInTable("Guardian", userId)) {
                deleteQuery = "DELETE FROM Guardian WHERE guardian_Id = ?";
                tableName = "Guardian";
            } 
            // Check if user exists in Student table
            else if (userExistsInTable("Student", userId)) {
                deleteQuery = "DELETE FROM Student WHERE student_Id = ?";
                tableName = "Student";
            }

            // If the user exists in one of the tables, delete them
            if (deleteQuery != null) {
                PreparedStatement stmt = connection.prepareStatement(deleteQuery);
                stmt.setString(1, userId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, 
                        "User " + userId + " has been deleted from " + tableName + ".", 
                        "User Deleted", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "User " + userId + " not found in " + tableName + ".", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                stmt.close();
            } else {
                // If the user wasn't found in any table
                JOptionPane.showMessageDialog(this, 
                    "User " + userId + " not found in any table.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Commit the transaction
            connection.commit();
            dispose(); // Close the dialog after deletion
        } catch (SQLException ex) {
            try {
                // Rollback the transaction in case of an error
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, 
                "Error deleting user: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                // Set auto-commit back to true
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to check if the user exists in the specified table
    private boolean userExistsInTable(String tableName, String userId) throws SQLException {
        String query = "SELECT 1 FROM " + tableName + " WHERE " + tableName.toLowerCase() + "_Id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();
        boolean exists = rs.next(); // If there's a result, the user exists
        rs.close();
        stmt.close();
        return exists;
    }
}
