import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class StaffUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db";
    private static final String DB_USER = "jmehta2";
    private static final String DB_PASSWORD = "COSC*9fmdy";

    private JTable table;

    public StaffUI() {
        // Window settings
        setTitle("Staff Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Welcome label
        JLabel label = new JLabel("Staff Contact Information", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        // Table for displaying results
        table = new JTable(new DefaultTableModel());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Fetch button
        JButton fetchContactButton = new JButton("Retrieve School Contact Information");
        fetchContactButton.addActionListener(e -> fetchContactInfo());
        add(fetchContactButton, BorderLayout.SOUTH);

        // Make the UI visible
        setVisible(true);
    }

    private void fetchContactInfo() {
        String query = """
                SELECT 
                    T.first_Name, 
                    T.last_Name, 
                    T.phone_Number, 
                    T.email, 
                    T.department AS role
                FROM 
                    Teacher T

                UNION

                SELECT 
                    G.first_Name, 
                    G.last_Name, 
                    G.phone_Number, 
                    G.email, 
                    'Guardian' AS role
                FROM 
                    Guardian G

                UNION

                SELECT 
                    A.first_Name, 
                    A.last_Name, 
                    A.phone_Number, 
                    A.email, 
                    A.position AS role
                FROM 
                    Admin A;
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
        SwingUtilities.invokeLater(() -> new StaffUI());
    }
}
