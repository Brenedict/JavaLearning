import java.util.*;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        GameBoard gameBoard = new GameBoard();
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Integer> player = new ArrayList<Integer>();
        ArrayList<Integer> bot = new ArrayList<Integer>();

        int pos, turns = 0;
        
        while(true) {
            // Player Turn Loop
            do {
                System.out.print("Where to place: ");
                pos = input.nextInt();
            } while(isValid(gameBoard, pos) != true);         
            gameBoard.play('x', pos);
            player.add(pos);
            checkWin(player);
            
            Thread.sleep(1500);
            // Bot Generated Turn Loop
            do {
                pos = random.nextInt(9) + 1;
            } while(isValid(gameBoard, pos) != true);
            gameBoard.play('o', pos);
            bot.add(pos);

            System.out.flush();
            turns++;    
            checkWin(bot);

        }
    }

    public static boolean isValid(GameBoard gameBoard, int pos) {
        if(gameBoard.record[pos-1] != ' ') {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkWin(ArrayList<Integer> player) {
        
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
                System.out.println("WINN");
                return true;
            }
        }
        return false;
    }

}
