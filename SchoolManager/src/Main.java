import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    static databaseUtil database = new databaseUtil();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int menuConf = 0;
        database.connect();
        do {
            System.out.println("LeetCode Log (Benedict)");
            System.out.println(" (1) Display All Logs");
            System.out.println(" (2) Insert New Log\n");

            System.out.print("> ");
            menuConf = input.nextInt();

            switch(menuConf) {
                case 1:
                    database.displayData();
                    break;
                case 2:
                    newEntry();
                    break;
                default:
                    System.out.println("\n\n!!Input off-range!!\n");
            }
        } while(true);


//        database.insertNewRow(4018, "Medium", "Correct", "Random Bull");


    }

    public static void newEntry() throws SQLException {
        int ID;
        String difficulty, attempt, details;

        System.out.print("Enter Question ID No. #");
        ID = input.nextInt();
        input.nextLine();

        System.out.print("Enter Question Difficulty (Easy-Medium-Hard): ");
        difficulty = input.nextLine();

        System.out.print("Attempt (Correct/Wrong): ");
        attempt = input.nextLine();

        System.out.println("Enter Question Description:");
        details = input.nextLine();

        database.insertNewRow(ID, difficulty, attempt, details);
    }
}