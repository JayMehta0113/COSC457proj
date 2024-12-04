package ui.admin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DC {
    private static final String URL = "jdbc:mysql://triton2.towson.edu:3360/jmehta2db"; // Update with your DB URL
    private static final String USERNAME = "jmehta2"; // Update with your DB username
    private static final String PASSWORD = "COSC*9fmdy"; // Update with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

