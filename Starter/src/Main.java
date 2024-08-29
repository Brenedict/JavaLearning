import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        String username, password;

        System.out.println("Login System\n");

        System.out.print("Enter Username: ");
        username = input.nextLine();

        System.out.print("Enter Password: ");
        password = input.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Benedict#28");
            statement = connection.createStatement();

            result = statement.executeQuery("SELECT password FROM login WHERE username = '" + username + "';");
            if(result.next()) {
                if(Objects.equals(result.getString(1), password)) {
                    System.out.println("YOU'RE IN!");
                }
                else {
                    System.out.println("Password is incorrect!");
                }
            }
            else {
                System.out.printf("Username @%s not found", username);
            }
            statement.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}