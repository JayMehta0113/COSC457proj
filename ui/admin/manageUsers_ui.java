package ui.admin;

import java.awt.*;
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






        //Frame vis
        setVisible(true);
    }
}