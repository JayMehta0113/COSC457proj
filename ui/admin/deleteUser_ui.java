package ui.admin;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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



        


        //dialog vis
        setVisible(true);

    }
}