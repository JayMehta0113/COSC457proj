import java.awt.*;
import javax.swing.*;

public class MainScreen {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("School Database App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Layout with 4 buttons vertically
        frame.setLayout(new GridLayout(4, 1));

        // Teacher button
        JButton teacherButton = new JButton("Teacher UI");
        // Admin button
        JButton adminButton = new JButton("Admin UI");
        // Guardian button
        JButton guardianButton = new JButton("Guardian UI");
        // Student button
        JButton studentButton = new JButton("Student UI");

        // Action listeners for buttons
        teacherButton.addActionListener(e -> openPlaceHolderUI("Teacher UI"));
        adminButton.addActionListener(e -> openPlaceHolderUI("Admin UI"));
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
}
