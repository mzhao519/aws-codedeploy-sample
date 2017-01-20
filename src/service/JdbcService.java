package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcService {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    static final String DB_URL = "jdbc:mysql://myccvdb.ccl23f4ztirg.ap-southeast-2.rds.amazonaws.com:3306/aws_dev_connectcv";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "Simon8481";

    public static String findTestResult() {
        Connection conn = null;
        Statement stmt = null;
        String result = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT ID, NAME FROM tbll_country where ID<=10";
            ResultSet rs = stmt.executeQuery(sql);
            // STEP 5: Extract data from result set

            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", NAME: " + name);
                result = "ID: " + id + ", NAME: " + name;
                break;
            }
            rs.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
        return result;
    }// end main
}
