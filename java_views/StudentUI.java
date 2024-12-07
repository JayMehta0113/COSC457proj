import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class StudentUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db";
    private static final String DB_USER = "jmehta2";
    private static final String DB_PASSWORD = "COSC*9fmdy";

    private JTable table;
    private JTextField studentIdField;

    public StudentUI() {
        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Header label
        JLabel headerLabel = new JLabel("Student Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Input panel for student ID
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        studentIdField = new JTextField();
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        add(inputPanel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        addButton(buttonPanel, "View Medical Record", e -> fetchMedicalRecord());
        addButton(buttonPanel, "View Clubs and Directors", e -> fetchClubs());
        addButton(buttonPanel, "View Awards", e -> fetchAwards());
        addButton(buttonPanel, "Check Out Book", e -> checkOutBook());
        addButton(buttonPanel, "View Grades", e -> fetchGrades());
        addButton(buttonPanel, "View Schedule", e -> fetchSchedule());
        add(buttonPanel, BorderLayout.WEST);

        // Table to display results
        table = new JTable(new DefaultTableModel());
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addButton(JPanel panel, String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void fetchMedicalRecord() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            showError("Student ID is required.");
            return;
        }

        String query = """
                SELECT 
                    allergies, 
                    medical_Conditions, 
                    emergency_Contact_name 
                FROM Medical_Record 
                WHERE student_Id = ?;
                """;
        executeQuery(query, studentId);
    }

    private void fetchClubs() {
        String query = """
                SELECT 
                    C.club_name, 
                    T.first_Name AS club_director_first_name, 
                    T.last_Name AS club_director_last_name 
                FROM Clubs C 
                JOIN Teacher T ON C.club_director = T.teacher_Id;
                """;
        executeQuery(query);
    }

    private void fetchAwards() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            showError("Student ID is required.");
            return;
        }

        String query = """
                SELECT 
                    award_name 
                FROM Awards 
                WHERE award_winner = ?;
                """;
        executeQuery(query, studentId);
    }

    private void checkOutBook() {
        String studentId = studentIdField.getText();
        String bookId = JOptionPane.showInputDialog("Enter Book ID to Check Out:");
        if (studentId.isEmpty() || bookId == null || bookId.isEmpty()) {
            showError("Student ID and Book ID are required.");
            return;
        }

        String query = """
                INSERT INTO Library_Transactions (book_Id, student_Id, checkout_Date) 
                VALUES (?, ?, CURDATE());
                """;
        executeUpdate(query, bookId, studentId);
    }

    private void fetchGrades() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            showError("Student ID is required.");
            return;
        }

        String query = """
                SELECT 
                    C.course_Name, 
                    G.grade_Value, 
                    G.term, 
                    G.comments 
                FROM Grade G 
                JOIN Course C ON G.course_Id = C.course_Id 
                WHERE G.student_Id = ?;
                """;
        executeQuery(query, studentId);
    }

    private void fetchSchedule() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            showError("Student ID is required.");
            return;
        }

        String query = """
                SELECT 
                    SC.schedule_Date, 
                    SC.start_Time, 
                    SC.end_Time, 
                    C.course_Name, 
                    CL.room_Number 
                FROM Schedule SC 
                JOIN Course C ON SC.course_Id = C.course_Id 
                JOIN Classroom CL ON CL.classroom_Id = SC.classroom_Id 
                JOIN Enrollments E ON E.course_Id = C.course_Id 
                WHERE E.student_Id = ?;
                """;
        executeQuery(query, studentId);
    }

    private void executeQuery(String query, String... params) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }

            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = buildTableModel(rs);
            table.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            showError("Error executing query.");
        }
    }

    private void executeUpdate(String query, String... params) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }

            int rowsAffected = stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, rowsAffected + " rows affected.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            showError("Error executing update.");
        }
    }

    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();

        // Add column names
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        // Add rows
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            model.addRow(row);
        }

        return model;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentUI::new);
    }
}
