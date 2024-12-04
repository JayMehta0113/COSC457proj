/* 
package ui.admin;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class manageAttendance_ui extends JFrame {

    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private Connection connection;

    public manageAttendance_ui() {
        // Set up the frame
        setTitle("Manage Attendance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Manage Attendance", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Define column names
        String[] columnNames = {"Student ID", "First Name", "Last Name", "Status"};

        // Initialize table model and table
        tableModel = new DefaultTableModel(columnNames, 0);
        attendanceTable = new JTable(tableModel);

        // Add the table with scrolling
        JScrollPane tableScrollPane = new JScrollPane(attendanceTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton markAttendanceBtn = new JButton("Mark Attendance");
        JButton editAttendanceBtn = new JButton("Edit Attendance");
        JButton generateReportBtn = new JButton("Generate Report");

        // Add buttons to the button panel
        buttonPanel.add(markAttendanceBtn);
        buttonPanel.add(editAttendanceBtn);
        buttonPanel.add(generateReportBtn);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the database connection
        try {
            connection = DC.getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return; // Exit if connection fails
        }

        // Load attendance data from the database
        loadAttendanceData();

        // Action listeners
        markAttendanceBtn.addActionListener(e -> new markAttendance_ui().setVisible(true));
        editAttendanceBtn.addActionListener(e -> new editAttendance_ui().setVisible(true));

        // Frame visibility
        setVisible(true);
    }

    private void loadAttendanceData() {
        String query = "SELECT student_id, fname, lname, status FROM Attendance";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Clear existing rows in the table model
            tableModel.setRowCount(0);

            // Iterate through the result set and add rows to the table
            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String attendanceStatus = rs.getString("status");
                tableModel.addRow(new Object[]{studentId, firstName, lastName, attendanceStatus});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading attendance data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    */
    package ui.admin;

    import java.awt.*;
    import java.sql.*;
    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    
    public class manageAttendance_ui extends JFrame {
    
        private JTable attendanceTable;
        private DefaultTableModel tableModel;
        private Connection connection;
    
        public manageAttendance_ui() {
            // Set up the frame
            setTitle("Manage Attendance");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(600, 400);
            setLayout(new BorderLayout());
    
            // Title label
            JLabel titleLabel = new JLabel("Manage Attendance", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
    
            // Define column names
            String[] columnNames = {"Student ID", "First Name", "Last Name", "Attendance Status"};
    
            // Initialize table model and table
            tableModel = new DefaultTableModel(columnNames, 0);
            attendanceTable = new JTable(tableModel);
    
            // Add the table with scrolling
            JScrollPane tableScrollPane = new JScrollPane(attendanceTable);
            add(tableScrollPane, BorderLayout.CENTER);
    
            // Panel for buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton markAttendanceBtn = new JButton("Mark Attendance");
            JButton editAttendanceBtn = new JButton("Edit Attendance");
            JButton generateReportBtn = new JButton("Generate Report");
    
            // Add buttons to the button panel
            buttonPanel.add(markAttendanceBtn);
            buttonPanel.add(editAttendanceBtn);
            buttonPanel.add(generateReportBtn);
    
            // Add button panel to the bottom of the frame
            add(buttonPanel, BorderLayout.SOUTH);
    
            // Initialize the database connection
            try {
                connection = DC.getConnection(); // Assuming DC is a class managing your database connection
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                return; // Exit if connection fails
            }
    
            // Load attendance data from the database
            loadAttendanceData();
    
            // Action listeners
            markAttendanceBtn.addActionListener(e -> new markAttendance_ui().setVisible(true));
            editAttendanceBtn.addActionListener(e -> new editAttendance_ui().setVisible(true));
    
            // Frame visibility
            setVisible(true);
        }
    
        private void loadAttendanceData() {
            // SQL query to fetch first name, last name, and status from the Attendance table and Student table
            String query = "SELECT s.student_id, s.first_name AS fname, s.last_name AS lname, a.status " +
                           "FROM Attendance a " +
                           "JOIN Student s ON a.student_id = s.student_id";
    
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
    
                // Clear existing rows in the table model
                tableModel.setRowCount(0);
    
                // Iterate through the result set and add rows to the table
                while (rs.next()) {
                    String studentId = rs.getString("student_id");
                    String firstName = rs.getString("fname");
                    String lastName = rs.getString("lname");
                    String attendanceStatus = rs.getString("status");
                    tableModel.addRow(new Object[]{studentId, firstName, lastName, attendanceStatus});
                }
    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error loading attendance data: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        public static void main(String[] args) {
            // Run the UI
            SwingUtilities.invokeLater(() -> new manageAttendance_ui());
        }
    }
    