
public class GameBoard {
    char[][] gameboard = {
        {'.', '|', '.', '|', '.'},
        {'-', '+', '-', '+', '-'},
        {'.', '|', '.', '|', '.'},
        {'-', '+', '-', '+', '-'},
        {'.', '|', '.', '|', '.'}
    };
    char [] record = {
        ' ', ' ', ' ',
        ' ', ' ', ' ',
        ' ', ' ', ' '
    };

    void display() {
        int currentRecordIndex = 0;
        for(int i = 0;i < 5;i++) {
            for(int j = 0;j < 5;j++) {
                if(gameboard[i][j] != '.') {
                    System.out.print(gameboard[i][j]);
                    continue;
                } else {
                    System.out.print(record[currentRecordIndex]);
                    currentRecordIndex++;
                }
            }
            System.out.println();
        }   
        System.out.println("\n\n"); 
    }

    void play(char player, int location) {
        record[location - 1] = player;
        display();
    } 

    // public static void main(String[] args) {
    //     GameBoard game = new GameBoard();
    //     game.display();
    // }
}
