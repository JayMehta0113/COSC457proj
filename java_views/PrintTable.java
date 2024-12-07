import java.sql.*;

public class PrintTable {

    private static final String DB_URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db";
    private static final String DB_USER = "jmehta2"; 
    private static final String DB_PASSWORD = "COSC*9fmdy"; 

    public static void main(String[] args) {
        printTable("Student");
    }

    public static void printTable(String tableName) {
        String query = "SELECT * FROM " + tableName; // Query to get all rows from the table

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error accessing database: " + e.getMessage());
        }
    }
}
