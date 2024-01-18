import java.util.*;

public class main{

    private static final char ROWS = 3;
    private static final char COLS = 3;
    private static char[][] board = new char[ROWS][COLS];
    private static char player = 'O';
    public static void main(String[] args) {
        createBoard();
        Scanner scanner =  new Scanner(System.in);

        displayBoard();
        boolean isWin = false;
        boolean isFull = false;

        while(!isWin && !isFull) {
            int row = getRow();
            int col = getColumn();
            playerPlays(row, col);

            isWin = checkWinner();
            isFull = checkFull();

            displayBoard();
            switchPlayer();
        }

        scanner.close();

        if(isWin){
            switchPlayer();
            System.out.println("Player " +player+ " Wins!!!");
        }
        else{
            System.out.println("No winner in that round...Rematch?");
        }
        
    }

    private static char[][] createBoard(){  //creates an empty tic-tac-toe board.
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                board[i][j] = ' ';
            }
        }

        return board;
    }

    private static void displayBoard(){   //displays the board.
        System.out.println("-----------------");
        for(int i = 0; i < ROWS; i++){
            System.out.print(i+1);
            System.out.print(" | ");
            for(int j = 0; j < COLS; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("   1    2    3  ");
        System.out.println("-----------------");
    }

    private static int getRow(){  //prompts the user to select a row.
        int row;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Enter the row(horizontal) to place your mark(1-3): ");
            System.out.println();
            row = scanner.nextInt()-1;
        }while(row < 0 || row > ROWS);

        return row;
    }

    private static int getColumn(){   //prompts the user to select a column.
        int column;

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Enter the column(vertical) to place your mark(1-3): ");
            System.out.println();
            column = scanner.nextInt()-1;
        }
        while(column < 0 || column > COLS);

        return column;
    }

    private static char[][] playerPlays(int row, int col){   //initializes the current players play.
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(board[row][col] == ' '){
                    board[row][col] = player;
                }
            }
        }

        return board;
    }

    private static void switchPlayer(){   //switches the players concurrently.
        player = (player == 'O') ? 'X' : 'O';
    }

    private static boolean checkWinner(){
        int horizontalCount = 0;
        for(int i = 0; i < ROWS; i++){   //checks for matching played marks in a horizontal position.
            for(int j = 0; j < COLS; j++){
                if(board[i][j] == player){
                    horizontalCount++;
                    if(horizontalCount == 3) return true;
                }
            }
            horizontalCount = 0;
        }

        int verticalCount = 0;
        for(int j = 0; j < COLS; j++){   //checks for matching played marks in a vertical position.
            for(int i = 0; i < ROWS; i++){
                if(board[i][j] == player){
                    verticalCount++;
                    if(verticalCount == 3) return true;
                }
            }
            verticalCount = 0;
        }

        int diagonalCount = 0;
        if(board[1][1] == player){ 
            for(int i = 0; i < ROWS; i++){   //checks for matching played marks in a diagonal position(left-right).
                int j = i;
                if(board[i][j] == player){
                    diagonalCount++;
                    if(diagonalCount == 3) return true;
                }
            }
            diagonalCount = 0;
            
            int j = COLS-1;   //checks for matching played marks in a diagonal position(right-left).
            for(int i = 0; i < ROWS; i++){
                if(board[i][j] == player){
                    diagonalCount++;
                    if(diagonalCount == 3) return true;
                }
                j--;
            }
            diagonalCount = 0;
        }

        return false;
    }

    private static boolean checkFull(){   //checks if the board is full.
        int count = 0;

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(board[i][j] == ' ') count++;
            }
        }

        if(count > 0){
            count = 0;
            return false;
        }
        else return true;
    }
}