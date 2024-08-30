import java.sql.*;

public class databaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/schooldata"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Benedict#28"; // Replace with your MySQL password
    private Connection connection = null;


    public void connect() throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        System.out.println("Connection established...");


    }

    public void insertRow() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO student_tracker VALUES(1, 'John Benedict', 'Binas');");
    }



}

