import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class databaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/leettracker"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Commented Out
    private static final String DB_PASSWORD = "Benedict#28"; // Commented out
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
        System.out.println("\n===================================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date\t\t\t   Details");
        while(result.next()) {
            printAllColumnValues(result);
        }
        System.out.println("\n===================================================================================\n");
        statement.close();
        result.close();
    }

    public void displayDataDifficulty() throws SQLException {
        String sql = "Select * FROM " + DB_NAME +" ORDER BY ID;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        ArrayList<difficultyResults> easyDiff = new ArrayList<difficultyResults>();
        ArrayList<difficultyResults> mediumDiff = new ArrayList<difficultyResults>();
        ArrayList<difficultyResults> hardDiff = new ArrayList<difficultyResults>();

        int ID;
        String attempt, difficulty, attemptDate, details;

        while (result.next()) {
            ID = result.getInt(1);
            attempt = result.getString(2);
            difficulty = result.getString(3);
            attemptDate = result.getString(4);
            details = result.getString(5);

            difficultyResults TEMP = new difficultyResults(ID, attempt, difficulty, attemptDate, details);

            if(result.getString("Difficulty").equalsIgnoreCase("easy")) {
                easyDiff.add(TEMP);
            }
            else if(result.getString("Difficulty").equalsIgnoreCase("medium")) {
                mediumDiff.add(TEMP);
            }
            else {
                hardDiff.add(TEMP);
            }
        }

        System.out.println("\n===================================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date\t\t\t   Details");

        String temp;

        for (difficultyResults value : easyDiff) {
            ID = value.getID();
            attempt = value.getAttempt();
            difficulty = value.getDifficulty();
            attemptDate = value.getAttemptDate();
            temp = value.getDetails();
            details = (temp.length()<15)?temp:temp.substring(0, 15) + "...";

            System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n", ID, attempt, difficulty, attemptDate, details);
        }

        for (difficultyResults value : mediumDiff) {
            ID = value.getID();
            attempt = value.getAttempt();
            difficulty = value.getDifficulty();
            attemptDate = value.getAttemptDate();
            temp = value.getDetails();
            details = (temp.length()<15)?temp:temp.substring(0, 15) + "...";

            System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n", ID, attempt, difficulty, attemptDate, details);
        }

        for (difficultyResults value : hardDiff) {
            ID = value.getID();
            attempt = value.getAttempt();
            difficulty = value.getDifficulty();
            attemptDate = value.getAttemptDate();
            temp = value.getDetails();
            details = (temp.length()<15)?temp:temp.substring(0, 15) + "...";

            System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n", ID, attempt, difficulty, attemptDate, details);
        }

        System.out.println("\n===================================================================================\n");
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
        System.out.println("\n===================================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date\t\t\t   Details");
        while(result.next()) {
            printAllColumnValues(result);
        }
        System.out.println("\n===================================================================================\n");
        result.close();
        preparedStatement.close();

    }

    public void displayDataDate() throws SQLException {
        String sql = "SELECT * FROM " + DB_NAME + " ORDER BY AttemptDate";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();

        System.out.println("\n===================================================================================\n");
        System.out.println("Question ID\t\tDifficulty\t\tAttempt\t\t\t   Date\t\t\t   Details");
        while(result.next()) {
            printAllColumnValues(result);
        }
        System.out.println("\n===================================================================================\n");
        result.close();
        preparedStatement.close();
    }
    private void printAllColumnValues(ResultSet result) throws SQLException {
        int ID = result.getInt(1);
        String attempt = result.getString(2);
        String difficulty = result.getString(3);
        String attemptDate = result.getString(4);
        String temp = result.getString(5);
        String details = (temp.length()<15)?temp:temp.substring(0, 15) + "...";
        System.out.printf("   %4d\t\t\t%s\t\t\t%s\t\t\t%s\t\t%s\n", ID, attempt, difficulty, attemptDate, details);
    }
    public void insertNewRow(int ID, String difficulty, String attempt, int offset, String details) throws SQLException{
        String sql = "INSERT INTO " + DB_NAME + " VALUES(?, ?, ?, DATE_SUB(CURRENT_DATE(), INTERVAL ? DAY), ?);";

        PreparedStatement prepStatement = connection.prepareStatement(sql);

        prepStatement.setInt(1, ID);
        prepStatement.setString(2, difficulty);
        prepStatement.setString(3, attempt);
        prepStatement.setInt(4, offset);
        prepStatement.setString(5, details);

        prepStatement.executeUpdate();
        System.out.printf("\nRow successfully added ID#%d\n\n", ID);
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

    public long totalRow() throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + DB_NAME + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        long res = 0;
        while(resultSet.next()) {
            res = resultSet.getLong(1);
        }
        preparedStatement.close();
        resultSet.close();
        return res;
    }

    public void disconnect() throws SQLException {
        connection.close();
        System.out.println("Connection successfully disconnected...");
    }

}

class difficultyResults {
    private int ID;
    private String attempt;
    private String difficulty;
    private String attemptDate;
    private String details;

    difficultyResults(int ID, String attempt, String difficulty, String attemptDate, String details) {
        this.ID = ID;
        this.attempt = attempt;
        this.difficulty = difficulty;
        this.attemptDate = attemptDate;
        this.details = details;
    }

    public int getID() { return this.ID; }
    public String getAttempt() { return this.attempt; }
    public String getDifficulty() { return this.difficulty; }
    public String getAttemptDate() { return this.attemptDate; };
    public String getDetails() { return this.details; }
}