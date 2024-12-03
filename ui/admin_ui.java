package ui;



//package ui;
import java.awt.*;
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

        // Display Panel
        //Text box
        JTextArea displayArea = new JTextArea();
        //text area not editable
        displayArea.setEditable(false);
        //wrap text area with scroll
        JScrollPane scrollPane = new JScrollPane(displayArea);
        //attach the scroll pane with text
        add(scrollPane, BorderLayout.CENTER);


        
        setVisible(true);
    }
    
}
