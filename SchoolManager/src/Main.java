import java.sql.SQLException;
import java.time.LocalDate;
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
            try {

                System.out.println("LeetCode Log (Benedict - " + database.totalRow() + ")");
                System.out.println(" (1) Display All Logs");
                System.out.println(" (2) Insert New Log");
                System.out.println(" (3) Delete Existing Log");
                System.out.println(" (4) Search Logs With Parameters");
                System.out.println(" (5) Disconnect");
                System.out.print("\n> ");
                menuConf = input.nextInt();
                input.nextLine();
                switch (menuConf) {
                    case 1:
                        database.displayData();
                        fullDisplay();
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
                    case 5:
                        database.disconnect();
                        return;
                    default:
                        System.out.println("\n\n!!Input off-range!!\n\n");
                }
            }
            catch(Exception e) {
                input.nextLine();
                System.out.println("\n\n!!Input not an integer!!\n\n");
            }
        } while (true);
    }

    public static void newEntry() throws SQLException {
        int ID, offset = 0;
        String difficulty, attempt, details, menu = "Y";
        do {

            try {
                System.out.print("Enter Question ID No. #");
                ID = input.nextInt();
                input.nextLine();

                if(database.exist(ID)) {
                    System.out.println("\n\n!! That question already exists in the record !!\n\n");
                    continue;
                }


                System.out.print("Enter Question Difficulty (Easy-Medium-Hard): ");
                difficulty = input.nextLine();

                List diffs = Arrays.asList("EASY", "MEDIUM", "HARD");
                if (!diffs.contains(difficulty.toUpperCase())) {
                    System.out.println("\n\n!! Difficulty does not exist !!\n\n");
                    continue;
                }

                System.out.print("Attempt (Correct/Wrong): ");
                attempt = input.nextLine();

                if (!attempt.equalsIgnoreCase("correct") && !attempt.equalsIgnoreCase("wrong")) {
                    System.out.println("\n\n!! There is no such attempt parameter !!\n\n");
                    continue;
                }

                System.out.print("Date offset: ");
                offset = input.nextInt();
                input.nextLine();

                System.out.println("Enter Question Description:");
                details = input.nextLine();

                database.insertNewRow(ID, difficulty.toUpperCase(), attempt.toUpperCase(), Math.abs(offset), details);

                System.out.print("Repeat operation (Y to repeat): ");
                menu = input.nextLine();
            }

            catch(Exception e) {
                input.nextLine();
                System.out.println("\n\n!!Input not an integer!!\n\n");
            }
        } while(menu.equalsIgnoreCase("Y"));
    }

    public static void deleteEntry() throws SQLException {
        int ID;
        String conf = "Y";
        do {
            try {
                System.out.print("Search by Id: ");
                ID = input.nextInt();
                input.nextLine();
                if (!database.exist(ID)) {
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
                        System.out.printf("\n\nID #%d successfully deleted from the DataBase!\n\n", ID);
                        conf = "Q";
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("\n\n!!Not an option!!\n\n");
                }
            } catch (Exception e) {
                input.nextLine();
                System.out.println("\n\n!!Input not an integer!!\n\n");
            }
        } while (!conf.equalsIgnoreCase("Q"));
    }

    public static void searchAll() throws SQLException {
        do {
            String menu = "N";
            int conf;
            System.out.println("Indicate search parameter by:");
            System.out.println(" (1) ID Number");
            System.out.println(" (2) Attempt");
            System.out.println(" (3) Difficulty");
            System.out.println(" (4) Date Log");
            System.out.print(" (5) Quit to Menu\n\n> ");

            conf = input.nextInt();
            input.nextLine();
            if (conf == 1) {
                do {
                    try {
                        int ID;
                        System.out.print("Input ID #");
                        ID = input.nextInt();
                        input.nextLine();

                        if (!database.exist(ID)) {
                            System.out.println("ID doesn't exist!");
                            continue;
                        }
                        database.displayData("ID", Integer.toString(ID));

                    }

                    catch (Exception e) {
                        input.nextLine();
                        System.out.println("\n\n!! Input not an integer!!\n\n");
                    }

                    System.out.print("Repeat operation (Y to repeat): ");
                    menu = input.nextLine();

                } while (menu.equalsIgnoreCase("Y"));
            }

            else if (conf == 2) {
                do {
                    String attempt;
                    System.out.print("Search by attempt (correct/wrong): ");
                    attempt = input.nextLine();

                    if (attempt.equalsIgnoreCase("correct") || attempt.equalsIgnoreCase("wrong")) {
                        database.displayData("Attempt", attempt);
                    } else {
                        System.out.println("\n\n!! There is no such attempt parameter !!\n\n");
                        continue;
                    }

                    System.out.print("Repeat operation (Y to repeat): ");
                    menu = input.nextLine();

                } while (menu.equalsIgnoreCase("Y"));

            }

            else if (conf == 3) {
                do {
                    String difficulty;
                    List diffs = Arrays.asList("EASY", "MEDIUM", "HARD");
                    System.out.print("Search by difficulty (Easy | Medium | Hard): ");
                    difficulty = input.nextLine();

                    if (diffs.contains(difficulty.toUpperCase())) {
                        database.displayData("Difficulty", difficulty);
                    } else {
                        System.out.println("\n\n!! Difficulty does not exist !!\n\n");
                        continue;
                    }

                    System.out.print("Repeat operation (Y to repeat): ");
                    menu = input.nextLine();

                } while (menu.equalsIgnoreCase("Y"));
            }

            else if (conf == 4) {
                try {
                    List days_31 = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
                    List days_30 = Arrays.asList(4, 6, 9, 11);

                    int month, day, year;
                    do {
                        System.out.print("Enter month number: ");
                        month = input.nextInt();

                        if(month <= 0 || month > 12) {
                            System.out.println("\n\n!! Month does not exist !!\n\n");
                            continue;
                        }

                        System.out.print("Enter day: ");
                        day = input.nextInt();

                        if(days_31.contains(month) && (day <= 0 || day > 31)) {
                            System.out.println("\n\n!! Month only has 31 days !!\n\n");
                            continue;
                        }

                        else if(days_30.contains(month) && (day <= 0 || day > 30)) {
                            System.out.println("\n\n!! Month only has 30 days !!\n\n");
                            continue;
                        }

                        else if(month == 2 && (day <= 0 || day > 29)) {
                            System.out.println("\n\n!! Month only has 29 days !!\n\n");
                            continue;
                        }

                        System.out.print("Enter year number: ");
                        year = input.nextInt();
                        input.nextLine();

                        int currentYear = LocalDate.now().getYear();
                        if(year > currentYear) {
                            System.out.println("\n\n!! The year is only " + currentYear + " !!\n\n");
                            continue;
                        }

                        String date_search = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
                        database.displayData("AttemptDate", date_search);

                        System.out.print("Repeat operation (Y to repeat): ");
                        menu = input.nextLine();
                    } while(menu.equalsIgnoreCase("Y"));

                    return;
                }

                catch (Exception e) {
                    System.out.println("\n\n!! Invalid input/s! !!\n\n");
                    input.nextLine();
                }
            }
            else if(conf == 5) {
                return;
            }
        } while (true);
    }

    public static void fullDisplay() throws SQLException{
        String argument, conf = "Y";
        do {
            System.out.print("Display options: ['NORMAL' | 'DIFFICULTY' | 'DATE' | 'X' to quit ]\n> ");
            argument = input.nextLine();
            if(argument.equalsIgnoreCase("X")) { return; }

            else if(argument.equalsIgnoreCase("DIFFICULTY")) {
                database.displayDataDifficulty();
            }
            else if(argument.equalsIgnoreCase("NORMAL")) {
                database.displayData();
            }
            else if(argument.equalsIgnoreCase("DATE")) {
                database.displayDataDate();
            }
            else {
                System.out.println("\n\n!!Input not in the display options!!\n\n");
            }
        } while(conf.equalsIgnoreCase("Y"));
    }
}