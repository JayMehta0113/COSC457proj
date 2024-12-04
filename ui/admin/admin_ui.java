package ui.admin;



//package ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class admin_ui extends JFrame {
    public admin_ui(){
        setTitle("Admin Ui");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,300);
        setLayout((new BorderLayout()));
        
        //Welcome label
        JLabel label = new JLabel("Admin Dashboard" , SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        //Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton manageUsersBtn = new JButton("Manage Users");
        JButton createCoursesBtn = new JButton("Create Courses");
        JButton manageAttendanceBtn = new JButton("Manage Attendance");
        JButton generateReportsBtn = new JButton("Generate Reports");
        JButton assignTeachersBtn = new JButton("Assign Teachers");

        //Add buttons to panel
        buttonPanel.add(manageUsersBtn);
        buttonPanel.add(createCoursesBtn);
        buttonPanel.add(manageAttendanceBtn);
        buttonPanel.add(generateReportsBtn);
        buttonPanel.add(assignTeachersBtn);

        //add to the left side of the frame
        add(buttonPanel, BorderLayout.WEST);
        // Use FlowLayout for the display area
        JPanel displayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        //Create adminid label
        JLabel adminIdLabel = new JLabel("Admin ID:");
        //Text Field for admin Id
        JTextField adminIdField = new JTextField();
        //text field size
        adminIdField.setPreferredSize(new Dimension(100, 30)); 

        // Add label and text field to display panel
        displayPanel.add(adminIdLabel);
        displayPanel.add(adminIdField);
  

        //attach the display to center
        add(displayPanel, BorderLayout.CENTER);

        //action listeners


        //add action listener for manage users
        manageUsersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new manageUsers_ui().setVisible(true); // Open Manage Users UI
            }
        });


        //add action listener for create courses
        createCoursesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Create Course UI when the button is clicked
                new createCourse_ui(); // Create and show the Create Course UI
            }
        });

        //frame vis
        setVisible(true);
    }
    
}
