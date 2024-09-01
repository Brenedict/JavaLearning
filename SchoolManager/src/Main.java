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
            System.out.println(" (2) Insert New Log");
            System.out.println(" (3) Delete Existing Log");
            System.out.print("\n> ");
            menuConf = input.nextInt();

            switch(menuConf) {
                case 1:
                    database.displayData();
                    break;
                case 2:
                    newEntry();
                    break;
                case 3:
                    deleteEntry();
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

    public static void deleteEntry() throws SQLException {
        int ID;
        String conf = "Y";
        do {
            try {
                System.out.print("Search by Id: ");
                ID = input.nextInt();
                input.nextLine();
                if(!database.exist(ID)) {
                    System.out.println("ID doesn't exist!");
                    continue;
                }

                database.displayDataByID(ID);
                System.out.print("\n\nAre you sure to delete this log (Y/N) or (Q to quit):\n\n> ");
                conf = input.nextLine();

                switch (conf.toUpperCase()) {
                    case "N":
                        break;
                    case "Y":
                        database.deleteLog(ID);
                        System.out.printf("\n\nID #%d successfully deleted from the DataBase!\n", ID);
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("\n\n!!Not an option!!\n");
                }
            }
            catch(Exception e) {
                input.nextLine();
                System.out.println("\n\n!!Input not an integer!!\n");
            }
        } while(!conf.equals("Q"));
    }
}