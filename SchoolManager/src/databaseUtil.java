import java.sql.*;

public class databaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/leettracker"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Benedict#28"; // Replace with your MySQL password
    private Connection connection = null;

    public void connect() throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD));
        System.out.println("Connection established...");
    }

    public void displayData() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("Select * FROM leet_tracker_sheet;");
        System.out.println("\n================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date");
        while (result.next()) {
            int ID = result.getInt(1);
            String attempt = result.getString(2);
            String difficulty = result.getString(3);
            String attemptDate = result.getString(4);
            String details = result.getString(5);
            System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\n", ID, attempt, difficulty, attemptDate);
        }
        System.out.println("\n================================================================\n");
        statement.close();
        result.close();
    }

    public void insertNewRow(int ID, String difficulty, String attempt, String details) throws SQLException{
        PreparedStatement prepStatement = connection.prepareStatement("INSERT INTO leet_tracker_sheet VALUES(?, ?, ?, CURRENT_DATE(), ?);");
        prepStatement.setInt(1, ID);
        prepStatement.setString(2, difficulty);
        prepStatement.setString(3, attempt);
        prepStatement.setString(4, details);

        prepStatement.executeUpdate();
        System.out.printf("Row successfully added ID#%d\n", ID);
        prepStatement.close();
    }

    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Connection successfully disconnected...");
    }

}

