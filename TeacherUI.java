import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Random;

public class TeacherUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db";
    private static final String DB_USER = "jmehta2"; 
    private static final String DB_PASSWORD = "COSC*9fmdy"; 

    private JTable table;
    private JTextField teacherIdField;
    private JTextField courseIdField;
    private JTextField studentIdField;
    private JTextField assignmentTitleField;

    public TeacherUI() {
        // Window setup
        setTitle("Teacher UI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Welcome Label
        JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        teacherIdField = new JTextField();
        courseIdField = new JTextField();
        studentIdField = new JTextField();
        assignmentTitleField = new JTextField();

        inputPanel.add(new JLabel("Teacher ID:"));
        inputPanel.add(teacherIdField);
        inputPanel.add(new JLabel("Course ID:"));
        inputPanel.add(courseIdField);
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Assignment Title:"));
        inputPanel.add(assignmentTitleField);

        add(inputPanel, BorderLayout.CENTER);

        // Buttons for Queries
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton fetchRosterButton = new JButton("Fetch Roster");
        JButton viewProfileButton = new JButton("View Profile");
        JButton editGradeButton = new JButton("Edit Grade");
        JButton viewScheduleButton = new JButton("View Schedule");
        JButton postAssignmentButton = new JButton("Post Assignment");

        buttonPanel.add(fetchRosterButton);
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(editGradeButton);
        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(postAssignmentButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Table for displaying results
        table = new JTable(new DefaultTableModel());
        add(new JScrollPane(table), BorderLayout.EAST);

        // Button Actions
        fetchRosterButton.addActionListener(e -> fetchRoster());
        viewProfileButton.addActionListener(e -> viewProfile());
        editGradeButton.addActionListener(e -> editGrade());
        viewScheduleButton.addActionListener(e -> viewSchedule());
        postAssignmentButton.addActionListener(e -> postAssignment());

        setVisible(true);
    }

    private void fetchRoster() {
        String teacherId = teacherIdField.getText();
        String courseId = courseIdField.getText();

        String query = """
                SELECT 
                    S.student_Id, 
                    S.first_Name, 
                    S.grade_Level, 
                    G.first_Name AS Guardians_First_Name, 
                    G.last_Name AS Guardian_Last_Name, 
                    G.phone_Number AS Guardian_Contact, 
                    T.first_Name AS Homeroom_Teacher_First_Name, 
                    T.last_Name AS Homeroom_Teacher_Last_Name
                FROM 
                    Teaches TE
                JOIN 
                    Student S ON S.homeroom_Teacher_Id = TE.teacher_Id
                JOIN 
                    Guardian G ON G.guardian_Id = S.guardian_Id
                JOIN 
                    Teacher T ON T.teacher_Id = S.homeroom_Teacher_Id
                WHERE 
                    TE.teacher_Id = ? AND TE.course_Id = ?;
                """;

        executeQuery(query, teacherId, courseId);
    }

    private void viewProfile() {
        String studentId = studentIdField.getText();
        String courseId = courseIdField.getText();

        String query = """
                SELECT 
                    S.student_Id, 
                    S.first_Name, 
                    S.grade_Level, 
                    G.first_Name AS Guardian_First_Name, 
                    G.last_Name AS Guardian_Last_Name, 
                    G.phone_Number AS Guardian_Contact, 
                    A.status AS Attendance_Status, 
                    GR.grade_Value, 
                    GR.comments
                FROM 
                    Student S
                JOIN 
                    Guardian G ON G.guardian_Id = S.guardian_Id
                LEFT JOIN 
                    Attendance A ON A.student_Id = S.student_Id
                LEFT JOIN 
                    Grade GR ON S.student_Id = GR.student_Id
                WHERE 
                    S.student_Id = ? AND GR.course_Id = ?;
                """;

        executeQuery(query, studentId, courseId);
    }

    private void editGrade() {
        String studentId = studentIdField.getText();
        String courseId = courseIdField.getText();
        String gradeValue = JOptionPane.showInputDialog("Enter Grade:");
        String term = JOptionPane.showInputDialog("Enter Term:");
        String comments = JOptionPane.showInputDialog("Enter Comments:");

        String query = """
                INSERT INTO Grade (student_Id, course_Id, grade_Value, term, comments)
                VALUES (?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE 
                    grade_Value = ?, 
                    comments = ?;
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            stmt.setString(2, courseId);
            stmt.setString(3, gradeValue);
            stmt.setString(4, term);
            stmt.setString(5, comments);
            stmt.setString(6, gradeValue);
            stmt.setString(7, comments);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Grade updated successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating grade.");
        }
    }

    private void viewSchedule() {
        String teacherId = teacherIdField.getText();

        String query = """
                SELECT 
                    SC.schedule_Date, 
                    SC.start_Time, 
                    SC.end_Time, 
                    C.room_Number,
                    SC.course_Id,
                    CO.course_Name
                FROM 
                    Schedule SC
                JOIN 
                    Classroom C ON C.classroom_Id = SC.classroom_Id
                JOIN 
                    Course CO ON SC.course_Id = CO.course_Id
                WHERE 
                    SC.course_Id IN (
                        SELECT course_Id 
                        FROM Teaches 
                        WHERE teacher_Id = ?
                    );
                """;

        executeQuery(query, teacherId);
    }

    private void postAssignment() {
        String teacherId = teacherIdField.getText();
        String courseId = courseIdField.getText();
        String title = assignmentTitleField.getText();
        String description = JOptionPane.showInputDialog("Enter Description:");
        String dueDate = JOptionPane.showInputDialog("Enter Due Date (YYYY-MM-DD):");
        String status = JOptionPane.showInputDialog("Enter Status:");

        Random random = new Random();
        int assignmentId = 10000 + random.nextInt(90000);

        String query = """
                INSERT INTO Assignments (assignment_Id, teacher_Id, course_Id, title, description, due_Date, status)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, assignmentId);
            stmt.setString(2, teacherId);
            stmt.setString(3, courseId);
            stmt.setString(4, title);
            stmt.setString(5, description);
            stmt.setString(6, dueDate);
            stmt.setString(7, status);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Assignment posted successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error posting assignment.");
        }
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
        SwingUtilities.invokeLater(TeacherUI::new);
    }
}
