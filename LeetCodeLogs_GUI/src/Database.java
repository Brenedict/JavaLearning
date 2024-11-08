import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Driver;
import java.sql.*;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/leetUI_Tracker"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Commented Out
    private static final String DB_PASSWORD = "Benedict#28"; // Commented out
    private static final String TABLE_NAME = "credentials";

    Connection connection = null;

    private String userID;
    private String userPassword;

    Database(String userID, String userPassword) throws SQLException, ClassNotFoundException {
        this.userID = userID;
        this.userPassword = userPassword;

        connect();
        String trackerKey;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE userID = ? AND userPassword = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result;

        statement.setString(1, this.userID);
        statement.setString(2, this.userPassword);
        result = statement.executeQuery();

        while(result.next()) {
            trackerKey = result.getString("trackerKey");
        }

    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        System.out.println(connection.isClosed());
    }

}
