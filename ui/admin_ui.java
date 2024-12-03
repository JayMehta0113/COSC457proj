package ui;



//package ui;
import java.awt.BorderLayout;
import javax.swing.*;

public class admin_ui extends JFrame {
    public admin_ui(){
        setTitle("Admin Ui");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,300);
        setLayout((new BorderLayout()));

        JLabel label = new JLabel("Welcome to the admin ui" , SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
    
}
