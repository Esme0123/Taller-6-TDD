package com.taller.tictactoe;

/**
 * Kata del Tres en Raya (Tic-Tac-Toe) desarrollada con TDD.
 *
 * Reglas implementadas:
 *  - Tablero de 3x3.
 *  - Jugador 'X' siempre inicia la partida.
 *  - Los jugadores alternan 'X' y '+'.
 *  - No se permite jugar en posiciones fuera de rango (X/Y).
 *  - No se permite jugar en una posicion ya ocupada.
 *  - Victoria por linea horizontal, vertical o diagonal.
 */
public class TicTacToe {

    private static final int SIZE = 3;

    private final char[][] board = new char[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean finished = false;
    private char winner;

    public TicTacToe() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Realiza una jugada en la posicion indicada.
     *
     * @param x fila (coordenada X) 0..2
     * @param y columna (coordenada Y) 0..2
     * @return true si la jugada fue valida, false en caso contrario
     */
    public boolean play(int x, int y) {
        if (finished) {
            return false;
        }
        if (isOutOfRange(x, y) || board[x][y] != ' ') {
            return false;
        }
        board[x][y] = currentPlayer;
        if (hasWon(currentPlayer)) {
            winner = currentPlayer;
            finished = true;
        } else if (isBoardFull()) {
            finished = true;
        } else {
            currentPlayer = (currentPlayer == 'X') ? '+' : 'X';
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getCell(int x, int y) {
        if (isOutOfRange(x, y)) {
            throw new IndexOutOfBoundsException(
                    "Posicion fuera de rango: (" + x + ", " + y + ")");
        }
        return board[x][y];
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean hasWinner() {
        return winner != '\0';
    }

    public char getWinner() {
        return winner;
    }

    private boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasWon(char player) {
        return hasHorizontalLine(player)
                || hasVerticalLine(player)
                || hasMainDiagonal(player)
                || hasAntiDiagonal(player);
    }

    private boolean hasHorizontalLine(char player) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] == player
                    && board[row][1] == player
                    && board[row][2] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVerticalLine(char player) {
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] == player
                    && board[1][col] == player
                    && board[2][col] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMainDiagonal(char player) {
        return board[0][0] == player
                && board[1][1] == player
                && board[2][2] == player;
    }

    private boolean hasAntiDiagonal(char player) {
        return board[0][2] == player
                && board[1][1] == player
                && board[2][0] == player;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                sb.append(board[row][col]);
                if (col < SIZE - 1) {
                    sb.append('|');
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Kata del Tres en Raya - TDD con JUnit 5");
        System.out.println(game);
    }
}
