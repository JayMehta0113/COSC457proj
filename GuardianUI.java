import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class GuardianUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db";
    private static final String DB_USER = "jmehta2";
    private static final String DB_PASSWORD = "COSC*9fmdy";

    private JTable table;
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField startDateField;
    private JTextField endDateField;

    public GuardianUI() {
        setTitle("Guardian Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Welcome label
        JLabel label = new JLabel("Guardian Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        // Input and button panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        studentIdField = new JTextField();
        courseIdField = new JTextField();
        startDateField = new JTextField();
        endDateField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Course ID:"));
        inputPanel.add(courseIdField);
        inputPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        inputPanel.add(startDateField);
        inputPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        inputPanel.add(endDateField);

        JButton fetchGradesButton = new JButton("View Child Grades");
        JButton fetchScheduleButton = new JButton("View Schedule");
        JButton fetchAttendanceButton = new JButton("View Attendance");
        JButton fetchAnnouncementsButton = new JButton("View Announcements");

        inputPanel.add(fetchGradesButton);
        inputPanel.add(fetchScheduleButton);
        inputPanel.add(fetchAttendanceButton);
        inputPanel.add(fetchAnnouncementsButton);

        add(inputPanel, BorderLayout.CENTER);

        // Table for displaying results
        table = new JTable(new DefaultTableModel());
        add(new JScrollPane(table), BorderLayout.SOUTH);

        // Button listeners
        fetchGradesButton.addActionListener(e -> fetchChildGrades());
        fetchScheduleButton.addActionListener(e -> fetchChildSchedule());
        fetchAttendanceButton.addActionListener(e -> fetchChildAttendance());
        fetchAnnouncementsButton.addActionListener(e -> fetchSchoolAnnouncements());

        setVisible(true);
    }

    private void fetchChildGrades() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.");
            return;
        }

        String query = """
                SELECT 
                    C.course_Name, 
                    G.grade_Value, 
                    G.term, 
                    G.comments
                FROM 
                    Grade G
                JOIN 
                    Course C ON G.course_Id = C.course_Id
                WHERE 
                    G.student_Id = ?;
                """;

        executeQuery(query, studentId);
    }

    private void fetchChildSchedule() {
        String studentId = studentIdField.getText(); // Get student ID input
    
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Student ID.");
            return;
        }
    
        String query = """
                SELECT 
                    SC.schedule_Date, 
                    SC.start_Time, 
                    SC.end_Time, 
                    C.course_Name, 
                    CL.room_Number
                FROM 
                    Enrollments E
                JOIN 
                    Schedule SC ON E.course_Id = SC.course_Id
                JOIN 
                    Course C ON E.course_Id = C.course_Id
                JOIN 
                    Classroom CL ON SC.classroom_Id = CL.classroom_Id
                WHERE 
                    E.student_Id = ?;
                """;
    
        // Execute the query using the helper method
        executeQuery(query, studentId);
    }
    

    private void fetchChildAttendance() {
        String studentId = studentIdField.getText();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.");
            return;
        }

        String query = """
                SELECT 
                    A.date, 
                    A.status,
                    A.remarks
                FROM 
                    Attendance A
                WHERE 
                    A.student_Id = ?;
                """;

        executeQuery(query, studentId);
    }

    private void fetchSchoolAnnouncements() {
        String query = """
                SELECT 
                    created_on, 
                    title, 
                    content
                FROM 
                    Announcements
                ORDER BY 
                    created_on DESC;
                """;

        executeQuery(query);
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
            JOptionPane.showMessageDialog(this, "Error executing query.");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuardianUI());
    }
}
