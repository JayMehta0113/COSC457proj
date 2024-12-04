package ui.admin;


import java.awt.*;
import javax.swing.*;

//declare tf for userid name and role
public class editUser_ui extends JDialog {
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField roleField;
    private String originalUserId;




//Constructor
public editUser_ui(JFrame parent, String userId, String name, String role){

    //call superclass for parent window 
    super(parent, "Edit User", true);

    //set the layout manage to gridlayout 4 rows 2 col
    setLayout(new GridLayout(4,2));

    //set the dialog window
    setSize(300,200);

    //Store the original userId;
    this.originalUserId = userId;








    setVisible(true);
}
}
