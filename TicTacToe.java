import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("Ungültige Position!");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("Feld ist bereits belegt!");
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void printBoard() {
        System.out.println("\nAktuelles Spielfeld:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----------");
        }
        System.out.println();
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean checkWin() {
        // Zeilen und Spalten prüfen
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {
                return true;
            }
        }
        // Diagonalen prüfen
        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen bei Tic Tac Toe!");

        boolean playAgain;
        do {
            TicTacToe game = new TicTacToe();
            game.printBoard();

            while (true) {
                System.out.print("Spieler " + game.getCurrentPlayer() + ", Zeile (1-3): ");
                int row = scanner.nextInt() - 1;
                System.out.print("Spieler " + game.getCurrentPlayer() + ", Spalte (1-3): ");
                int col = scanner.nextInt() - 1;

                if (game.makeMove(row, col)) {
                    game.printBoard();

                    if (game.checkWin()) {
                        System.out.println("Spieler " + game.getCurrentPlayer() + " hat gewonnen!");
                        break;
                    } else if (game.isBoardFull()) {
                        System.out.println("Unentschieden! Das Spielfeld ist voll.");
                        break;
                    }

                    game.switchPlayer();
                } else {
                    System.out.println("Ungültiger Zug, versuche es erneut.");
                }
            }

            System.out.print("Möchtest du nochmal spielen? (j/n): ");
            String antwort = scanner.next();
            playAgain = antwort.equalsIgnoreCase("j");
        } while (playAgain);

        System.out.println("Danke fürs Spielen!");
    }
}
