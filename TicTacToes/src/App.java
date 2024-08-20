import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
        GameBoard gameBoard = new GameBoard();
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Integer> player = new ArrayList<Integer>();
        ArrayList<Integer> bot = new ArrayList<Integer>();

        int pos, turns = 0;

        gameBoard.display();
        
        while(true) {
            // Player Turn Loop
            while (true) {
                System.out.print("Pos (1-9): ");
                pos = input.nextInt();

                if(pos <= 0 || pos >= 10) {
                    System.out.println("\nCell out of bounds!\n");
                    continue;
                }

                if(isValid(gameBoard, pos, "player")) {
                    break;
                }
            }         

            gameBoard.play('x', pos);
            player.add(pos);
            checkWin(player, "Player");
            
            turns++;

            Thread.sleep(1500);
            // Bot Generated Turn Loop
            
            if(turns >= 8) {
                turns = 0; 
                resetGame(gameBoard, player, bot);
            }

            do {
                pos = random.nextInt(9) + 1;
            } while(isValid(gameBoard, pos, "bot") != true);

            gameBoard.play('o', pos);
            bot.add(pos);
            checkWin(bot, "Computer");

            turns++;
            

        }
    }

    public static boolean isValid(GameBoard gameBoard, int pos, String player) {
        if(gameBoard.record[pos-1] != ' ') {
            if (player.equals("player")) System.out.println("\nThat cell is already occupied!\n");
            return false;
        } else {
            return true;
        }
    }

    public static void checkWin(ArrayList<Integer> player, String user) {
        
        ArrayList<List> winningCombinations = new ArrayList<List>();
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(3, 5, 7);

        winningCombinations.add(topRow);
        winningCombinations.add(middleRow);
        winningCombinations.add(bottomRow);
        winningCombinations.add(leftColumn);
        winningCombinations.add(middleColumn);
        winningCombinations.add(rightColumn);
        winningCombinations.add(leftDiagonal);
        winningCombinations.add(rightDiagonal);

        for(List list : winningCombinations) {
            if(player.containsAll(list) != false) {
                System.out.printf("%s wins!", user);
                System.exit(0);
            }
        }
    }
    
    public static void resetGame(GameBoard gameBoard, ArrayList<Integer> playerRecord, ArrayList<Integer> botRecord) {
        for(int i = 0; i < gameBoard.record.length ; i++) {
            gameBoard.record[i] = ' ';
        }

        playerRecord.clear();
        botRecord.clear();
        
        System.out.println("\nNew Game:\n");
    }
}
