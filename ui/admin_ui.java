package ui;



//package ui;
import java.awt.BorderLayout;
import java.awt.Font;
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








        
        setVisible(true);
    }
    
}
