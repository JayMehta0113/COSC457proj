package ui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//extends jframe to make a new window
public class manageUsers_ui extends JFrame {
    //constructor
    public manageUsers_ui(){

        //Set the title of the window
        setTitle("ManageUsers");

        //closes window only when x is pressed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //set the size of the window
        setSize(500,400);
        //layout manager
        setLayout(new BorderLayout());



        //Title

        //Create  title text Manage Users
        JLabel titleLabel = new JLabel("Manage Users", SwingConstants.CENTER);
        //set the font to arial
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        //add the title label to the top of the layout
        add(titleLabel, BorderLayout.NORTH);



        //User table Panel
        // Define the column names for the user table
        String[] columnNames = {"User ID", "Name", "Role"};

        // Define sample data for the user table (each row represents a user with ID, Name, and Role)
        String[][] sampleData = {
            {"1", "Alice", "Admin"},
            {"2", "Bob", "Teacher"},
            {"3", "Charlie", "Student"}
        };

        // Create a JTable with the sample data and column names
        JTable userTable = new JTable(sampleData, columnNames);

        // Create a JScrollPane to allow scrolling
        JScrollPane tableScrollPane = new JScrollPane(userTable);

        // Add the JScrollPane containing the table to the center of the frame using BorderLayout.CENTER
        add(tableScrollPane, BorderLayout.CENTER);

        
        //Button Panel
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addUserBtn = new JButton("Add User");
        JButton editUserBtn = new JButton("Edit User");
        JButton deleteUserBtn = new JButton("Delete User");
 
        // Add buttons to panel
        buttonPanel.add(addUserBtn);
        buttonPanel.add(editUserBtn);
        buttonPanel.add(deleteUserBtn);
 
        // Add button panel to bottom
        add(buttonPanel, BorderLayout.SOUTH);
 




        //Action Listeners for buttons

        //add user
        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open the add user dialog
                new addUser_ui(manageUsers_ui.this);
            }
        });

        //Edit User
        editUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow != -1) { // Check if a row is selected
                    String userId = (String) userTable.getValueAt(selectedRow, 0);
                    String name = (String) userTable.getValueAt(selectedRow, 1);
                    String role = (String) userTable.getValueAt(selectedRow, 2);

                    // Open the Edit User dialog and pass the selected user's data
                    new editUser_ui(manageUsers_ui.this, userId, name, role);
                } else {
                    JOptionPane.showMessageDialog(manageUsers_ui.this, "Please select a user to edit.",
                            "No User Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //Frame vis
        setVisible(true);
    }
}