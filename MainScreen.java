import java.awt.*;
import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import ui.admin_ui;


public class MainScreen {
    public static void main(String[] args) {
        //create the main frame
        JFrame frame = new JFrame("School Database app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        //4 buttons vertically
        frame.setLayout(new GridLayout(4,1));

        //Teacher button
        JButton teacherButton = new JButton("Teacher UI");
        //Admin button
        JButton adminButton = new JButton("Admin UI");

        //Guardian
        JButton guardianButton = new JButton("Guardian UI");

        //Student
        JButton studentButton = new JButton("Student UI");


        //action listener to buttons
        teacherButton.addActionListener(e -> openPlaceHolderUI("Teacher UI"));
        //open admin Ui
        adminButton.addActionListener(e -> openAdminUI());
        guardianButton.addActionListener(e -> openPlaceHolderUI("Guardian UI"));
        studentButton.addActionListener(e -> openPlaceHolderUI("Student UI"));


        // Add buttons to the frame
        frame.add(teacherButton);
        frame.add(adminButton);
        frame.add(guardianButton);
        frame.add(studentButton);


         // Make the frame visible
        frame.setVisible(true);
    }

     // Method to open a placeholder UI
    private static void openPlaceHolderUI(String title) {
        JFrame placeholderFrame = new JFrame(title);
        placeholderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        placeholderFrame.setSize(300, 200);

        JLabel label = new JLabel(title + " - This is a placeholder", SwingConstants.CENTER);
        placeholderFrame.add(label);

        placeholderFrame.setVisible(true);
    }



    //Method to open the Ui for admin
    private static void openAdminUI(){
        SwingUtilities.invokeLater(() -> {
            new admin_ui();

        });
    }
}