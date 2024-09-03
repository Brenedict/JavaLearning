import javax.xml.transform.Result;
import java.sql.*;

public class databaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/leettracker"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Benedict#28"; // Replace with your MySQL password
    private static final String DB_NAME = "leet_tracker_sheet";
    private Connection connection = null;

    public void connect() throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD));
        System.out.println("Connection established...");
    }

    public void displayData() throws SQLException {
        String sql = "Select * FROM " + DB_NAME +" ORDER BY ID ASC;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println("\n================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date");
        while (result.next()) {
            printAllColumnValues(result);
        }
        System.out.println("\n================================================================\n");
        statement.close();
        result.close();
    }

    public void displayData(String column, String value_search) throws SQLException {
        String sql = "SELECT * FROM " + DB_NAME + " WHERE " + column + " = ? ORDER BY ID ASC;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet result;
        if(column.equals("ID")) {
            int TEMP = Integer.parseInt(value_search);
            preparedStatement.setInt(1, TEMP);
            result = preparedStatement.executeQuery();
        } else {
            preparedStatement.setString(1, value_search);
            result = preparedStatement.executeQuery();
        }
        while(result.next()) {
            printAllColumnValues(result);
        }

    }


    private void printAllColumnValues(ResultSet result) throws SQLException {
        int ID = result.getInt(1);
        String attempt = result.getString(2);
        String difficulty = result.getString(3);
        String attemptDate = result.getString(4);
        String details = result.getString(5);
        System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\n", ID, attempt, difficulty, attemptDate);
    }
    public void insertNewRow(int ID, String difficulty, String attempt, String details) throws SQLException{
        String sql = "INSERT INTO " + DB_NAME + " VALUES(?, ?, ?, CURRENT_DATE(), ?);";
        PreparedStatement prepStatement = connection.prepareStatement(sql);
        prepStatement.setInt(1, ID);
        prepStatement.setString(2, difficulty);
        prepStatement.setString(3, attempt);
        prepStatement.setString(4, details);

        prepStatement.executeUpdate();
        System.out.printf("Row successfully added ID#%d\n", ID);
        prepStatement.close();
    }

    public void deleteLog(int ID) throws SQLException {
        String sql = "DELETE FROM " + DB_NAME + " WHERE ID = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, ID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public boolean exist(int ID) throws SQLException {
        String sql = "SELECT * FROM " + DB_NAME + " WHERE ID = ?";
        PreparedStatement prepStatement = connection.prepareStatement(sql);
        prepStatement.setInt(1, ID);
        ResultSet result = prepStatement.executeQuery();

        while(result.next()) {
            if(result.getInt(1) != 0) {
                return true;
            }
        }
        return false;
    }


    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Connection successfully disconnected...");
    }

}

