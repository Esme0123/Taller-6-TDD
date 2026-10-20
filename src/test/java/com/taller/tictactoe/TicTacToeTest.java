package com.taller.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Kata del Tres en Raya (TDD)")
class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    @Nested
    @DisplayName("Requerimiento 1: inicia el jugador X")
    class InitialState {

        @Test
        @DisplayName("El jugador inicial es 'X'")
        void firstPlayerIsX() {
            assertEquals('X', game.getCurrentPlayer());
        }

        @Test
        @DisplayName("El tablero empieza vacio")
        void boardStartsEmpty() {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    assertEquals(' ', game.getCell(x, y));
                }
            }
        }
    }

    @Nested
    @DisplayName("Requerimiento 2: posiciones fuera de rango (X/Y)")
    class OutOfRange {

        @Test
        @DisplayName("X negativa es rechazada")
        void negativeXIsRejected() {
            assertFalse(game.play(-1, 0));
        }

        @Test
        @DisplayName("Y negativa es rechazada")
        void negativeYIsRejected() {
            assertFalse(game.play(0, -1));
        }

        @Test
        @DisplayName("X mayor o igual a 3 es rechazada")
        void xTooLargeIsRejected() {
            assertFalse(game.play(3, 0));
        }

        @Test
        @DisplayName("Y mayor o igual a 3 es rechazada")
        void yTooLargeIsRejected() {
            assertFalse(game.play(0, 3));
        }

        @Test
        @DisplayName("getCell fuera de rango lanza excepcion")
        void getCellOutOfRangeThrows() {
            assertThrows(IndexOutOfBoundsException.class, () -> game.getCell(3, 3));
        }

        @Test
        @DisplayName("Una jugada invalida no cambia el turno")
        void invalidMoveKeepsTurn() {
            game.play(5, 5);
            assertEquals('X', game.getCurrentPlayer());
        }
    }

    @Nested
    @DisplayName("Requerimiento 3: posicion ocupada")
    class OccupiedPosition {

        @Test
        @DisplayName("No se puede jugar sobre una posicion ocupada")
        void cannotPlayOnOccupiedCell() {
            assertTrue(game.play(0, 0));
            assertFalse(game.play(0, 0));
        }

        @Test
        @DisplayName("La celda conserva el valor del primer jugador")
        void occupiedCellKeepsFirstValue() {
            game.play(1, 1);
            game.play(1, 1);
            assertEquals('X', game.getCell(1, 1));
        }
    }

    @Nested
    @DisplayName("Requerimiento 4: alternancia de jugadores")
    class PlayerRotation {

        @Test
        @DisplayName("Tras la jugada de X, el turno pasa a '+'")
        void turnGoesToPlus() {
            game.play(0, 0);
            assertEquals('+', game.getCurrentPlayer());
        }

        @Test
        @DisplayName("X y '+' alternan correctamente")
        void playersAlternate() {
            game.play(0, 0);
            assertEquals('+', game.getCurrentPlayer());
            game.play(0, 1);
            assertEquals('X', game.getCurrentPlayer());
            game.play(1, 0);
            assertEquals('+', game.getCurrentPlayer());
        }
    }

    @Nested
    @DisplayName("Requerimiento 5: condiciones de victoria")
    class Victory {

        @Test
        @DisplayName("Victoria horizontal para X")
        void horizontalWinForX() {
            game.play(0, 0);
            game.play(1, 0);
            game.play(0, 1);
            game.play(1, 1);
            game.play(0, 2);
            assertTrue(game.hasWinner());
            assertEquals('X', game.getWinner());
            assertTrue(game.isFinished());
        }

        @Test
        @DisplayName("Victoria vertical para '+'")
        void verticalWinForPlus() {
            game.play(2, 0);
            game.play(0, 0);
            game.play(2, 1);
            game.play(1, 0);
            game.play(2, 2);
            game.play(0, 1);
            assertTrue(game.hasWinner());
            assertEquals('+', game.getWinner());
        }

        @Test
        @DisplayName("Victoria diagonal principal para X")
        void mainDiagonalWinForX() {
            game.play(0, 0);
            game.play(0, 1);
            game.play(1, 1);
            game.play(0, 2);
            game.play(2, 2);
            assertTrue(game.hasWinner());
            assertEquals('X', game.getWinner());
        }

        @Test
        @DisplayName("Victoria diagonal inversa para '+'")
        void antiDiagonalWinForPlus() {
            game.play(0, 0);
            game.play(0, 2);
            game.play(1, 0);
            game.play(1, 1);
            game.play(2, 1);
            game.play(2, 0);
            assertTrue(game.hasWinner());
            assertEquals('+', game.getWinner());
        }

        @Test
        @DisplayName("No se permiten jugadas despues de terminar la partida")
        void noMovesAfterGameFinished() {
            game.play(0, 0);
            game.play(1, 0);
            game.play(0, 1);
            game.play(1, 1);
            game.play(0, 2);
            assertTrue(game.isFinished());
            assertFalse(game.play(2, 2));
        }
    }

    @Nested
    @DisplayName("Escenario adicional: empate")
    class DrawScenario {

        @Test
        @DisplayName("El tablero lleno sin ganador termina en empate")
        void fullBoardWithoutWinnerIsDraw() {
            game.play(0, 0);
            game.play(0, 1);
            game.play(0, 2);
            game.play(1, 1);
            game.play(1, 0);
            game.play(2, 0);
            game.play(1, 2);
            game.play(2, 2);
            game.play(2, 1);
            assertTrue(game.isFinished());
            assertFalse(game.hasWinner());
        }
    }
}
