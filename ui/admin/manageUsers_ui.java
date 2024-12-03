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






    }
}