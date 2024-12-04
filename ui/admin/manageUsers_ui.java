
    package ui.admin;

    import java.awt.*;
    import java.awt.event.*;
    import java.sql.*;
    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    
    // Extends JFrame to create a new window
    public class manageUsers_ui extends JFrame {
        private JTable userTable;
        private DefaultTableModel tableModel;
        private Connection connection;
    
        // Constructor
        public manageUsers_ui() {
            // Set the title of the window
            setTitle("Manage Users");
    
            // Close window only when X is pressed
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
            // Set the size of the window
            setSize(500, 400);
            // Layout manager
            setLayout(new BorderLayout());
    
            // Title
            JLabel titleLabel = new JLabel("Manage Users", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(titleLabel, BorderLayout.NORTH);
    
            // User Table Panel
            String[] columnNames = {"User ID", "Name", "Role"};
            tableModel = new DefaultTableModel(columnNames, 0);
            userTable = new JTable(tableModel);
    
            // Create a JScrollPane to allow scrolling
            JScrollPane tableScrollPane = new JScrollPane(userTable);
    
            // Add the JScrollPane containing the table to the center of the frame using BorderLayout.CENTER
            add(tableScrollPane, BorderLayout.CENTER);
    
            // Button Panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton addUserBtn = new JButton("Add User");
            JButton editUserBtn = new JButton("Edit User");
            JButton deleteUserBtn = new JButton("Delete User");
    
            // Add buttons to panel
            buttonPanel.add(addUserBtn);
            buttonPanel.add(editUserBtn);
            buttonPanel.add(deleteUserBtn);
    
            // Add button panel to bottom
            add(buttonPanel, BorderLayout.SOUTH);
    
            // Action Listeners for buttons
            addUserBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the add user dialog
                    new addUser_ui(manageUsers_ui.this);
                }
            });
    
            // Edit User
            editUserBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String userId = (String) userTable.getValueAt(selectedRow, 0);
                        String name = (String) userTable.getValueAt(selectedRow, 1);
                        String role = (String) userTable.getValueAt(selectedRow, 2);
    
                        // Open the Edit User dialog and pass the selected user's data
                        new editUser_ui(manageUsers_ui.this, userId, name, role);
                    } else {
                        JOptionPane.showMessageDialog(manageUsers_ui.this, "Please select a user to edit.",
                                "No User Selected", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
    
            // Delete User
            deleteUserBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String userId = (String) userTable.getValueAt(selectedRow, 0);
                        String name = (String) userTable.getValueAt(selectedRow, 1);
    
                        // Open the Delete User confirmation dialog and pass the selected user's data
                        new deleteUser_ui(manageUsers_ui.this, userId, name);
                    } else {
                        // Show a warning message if no user is selected
                        JOptionPane.showMessageDialog(manageUsers_ui.this, "Please select a user to delete.",
                                "No User Selected", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
    
            // Frame visibility
            setVisible(true);
    
            // Initialize the database connection
            try {
                connection = DC.getConnection();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                return; // Exit if connection fails
            }
    
            // Fetch and populate user data
            loadUsersData();
        }
    
        // Method to load and display data from the database
        private void loadUsersData() {
            // Clear existing rows
            tableModel.setRowCount(0);
    
            // SQL query to combine data from Teacher, Student, Guardian, and Staff tables
            String query = "SELECT t.teacher_Id AS user_id, t.first_Name, t.last_Name, rp.role_Name "
                         + "FROM Teacher t "
                         + "JOIN Roles_Permission rp ON t.role_Id = rp.role_Id "
                         + "UNION "
                         + "SELECT s.student_Id AS user_id, s.first_Name, s.last_Name, rp.role_Name "
                         + "FROM Student s "
                         + "JOIN Roles_Permission rp ON s.role_Id = rp.role_Id "
                         + "UNION "
                         + "SELECT g.guardian_Id AS user_id, g.first_Name, g.last_Name, rp.role_Name "
                         + "FROM Guardian g "
                         + "JOIN Roles_Permission rp ON g.role_Id = rp.role_Id "
                         + "UNION "
                         + "SELECT st.staff_Id AS user_id, st.first_Name, st.last_Name, rp.role_Name "
                         + "FROM Staff st "
                         + "JOIN Roles_Permission rp ON st.role_Id = rp.role_Id";
    
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
    
                // Process the result set
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    String name = rs.getString("first_Name") + " " + rs.getString("last_Name");
                    String role = rs.getString("role_Name");
    
                    // Add data to table
                    tableModel.addRow(new Object[]{userId, name, role});
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading user data: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
        // Method to refresh the table after a user is added, edited, or deleted
        public void refreshUserTable() {
            loadUsersData();
        }
    }
    