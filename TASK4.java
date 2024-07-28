import java.util.Scanner;

public class TASK4 {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';
    private char[][] board = new char[ROWS][COLS];

    public TASK4() {
        // Initialize the board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    public boolean makeMove(int col, char player) {
        if (col < 0 || col >= COLS) {
            System.out.println("Column out of bounds!");
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = player;
                return true;
            }
        }

        System.out.println("Column is full!");
        return false;
    }

    public boolean isWinningMove(char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row][col + 1] == player &&
                    board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == player && board[row + 1][col] == player &&
                    board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player && board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TASK4 game = new TASK4();
        char currentPlayer = PLAYER1;
        boolean gameWon = false;

        while (true) {
            game.printBoard();
            System.out.println("Player " + currentPlayer + ", enter a column (0-6): ");
            int col = scanner.nextInt();

            if (game.makeMove(col, currentPlayer)) {
                if (game.isWinningMove(currentPlayer)) {
                    game.printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameWon = true;
                    break;
                }

                if (game.isBoardFull()) {
                    game.printBoard();
                    System.out.println("The game is a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1;
            }
        }

        if (!gameWon) {
            System.out.println("Game over. No winner.");
        }
    }
}