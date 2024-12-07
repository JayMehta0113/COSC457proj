package admin;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


     // Create and add a label for User ID field
     add(new JLabel("User ID:"));
     // Initialize the User ID field with the provided userId 
     userIdField = new JTextField(userId);
     userIdField.setEditable(false);
     // Add the User ID field to the dialog
     add(userIdField);

     // Create and add a label for Name field
     add(new JLabel("Name:"));
     // Initialize the Name field with the provided name
     nameField = new JTextField(name);
     // Add the Name field to the dialog
     add(nameField);

     // Create and add a label for Role field
     add(new JLabel("Role:"));
     // Initialize the Role field with the provided role
     roleField = new JTextField(role);
     // Add the Role field to the dialog
     add(roleField);

     // Create and add a Save button to the dialog
     JButton saveButton = new JButton("Save");
     // Create and add a Cancel button to the dialog
     JButton cancelButton = new JButton("Cancel");
     add(saveButton);
     add(cancelButton);


     //Action Listeners

     //save
     saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){

            //get the updated values from the name and role fields
            String name = nameField.getText();
            String role = roleField.getText();

            //display a message that it updated
            JOptionPane.showMessageDialog(editUser_ui.this, 
                        "User Updated:\nID: " + originalUserId + "\nName: " + name + "\nRole: " + role,
                        "User Updated", JOptionPane.INFORMATION_MESSAGE);

            
            //close the dialog after updating
            dispose();
        }
     });

    //Action listener for cancel
    cancelButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the dialog 
            dispose();
        }
    });




    setVisible(true);
}
}
