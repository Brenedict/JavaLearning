import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
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
                case 4:
                    searchAll();
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

        database.insertNewRow(ID, difficulty.toUpperCase(), attempt.toUpperCase(), details);
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

                database.displayData("ID", Integer.toString(ID));
                System.out.print("\n\nAre you sure to delete this log (Y/N) or (Q to quit):\n\n> ");
                conf = input.nextLine();

                switch (conf.toUpperCase()) {
                    case "N":
                        break;
                    case "Y":
                        database.deleteLog(ID);
                        System.out.printf("\n\nID #%d successfully deleted from the DataBase!\n", ID);
                        conf = "Q";
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
        } while(!conf.equalsIgnoreCase("Q"));
    }

    public static void searchAll() throws SQLException{
        int conf;
        System.out.println("Indicate search parameter by:");
        System.out.println(" (1) ID Number");
        System.out.println(" (2) Attempt");
        System.out.println(" (3) Difficulty");
        System.out.println(" (4) Date Log\n> ");

        conf = input.nextInt();
        input.nextLine();

        do {

            if(conf == 1) {
                try {
                    int ID;
                    System.out.print("Input ID #");
                    ID = input.nextInt();
                    input.nextLine();

                    if(!database.exist(ID)) {
                        System.out.println("ID doesn't exist!");
                        continue;
                    }
                    database.displayData("ID", Integer.toString(ID));
                }
                catch (Exception e) {
                    input.nextLine();
                    System.out.println("\n\n!! Input not an integer!!\n");
                }
            }
            else if(conf == 2) {
                String attempt;
                System.out.print("Search by attempt (correct/wrong): ");
                attempt = input.nextLine();

                if(attempt.equalsIgnoreCase("correct") || attempt.equalsIgnoreCase("wrong")) {
                    database.displayData("Attempt", attempt);
                } else {
                    System.out.println("\n\n!! There is no such attempt paramete !!\n");
                    continue;
                }
            }
            else if (conf == 3) {
                String difficulty;
                List diffs = Arrays.asList("EASY", "MEDIUM", "HARD");
                System.out.print("Search by difficulty (Easy | Medium | Hard): ");
                difficulty = input.nextLine();
                if(diffs.contains(difficulty.toUpperCase())) {
                    database.displayData("Difficulty", difficulty);
                }
                else {
                    System.out.println("\n\n!! Difficulty does not exist !!\n");
                }
            }
        } while(true);
    }
}