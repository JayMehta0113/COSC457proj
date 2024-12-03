package ui.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class addUser_ui extends JDialog {
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField roleField;

    public addUser_ui(JFrame parent) {
        super(parent, "Add User", true);
        setLayout(new GridLayout(4, 2));
        setSize(300, 200);

        // User ID field
        add(new JLabel("User ID:"));
        userIdField = new JTextField();
        add(userIdField);

        // Name field
        add(new JLabel("Name:"));
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
}
