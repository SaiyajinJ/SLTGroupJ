import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    public void setUp() {
        game = new TicTacToe();
    }

    // Tests für resetBoard()
    @Test
    public void testResetBoard_AllFieldsEmpty() {
        game.makeMove(0, 0);
        game.resetBoard();
        assertFalse(game.isBoardFull());
    }

    @Test
    public void testResetBoard_CurrentPlayerIsX() {
        game.switchPlayer();
        game.resetBoard();
        assertEquals('X', game.getCurrentPlayer());
    }

    // Tests für makeMove()
    @Test
    public void testMakeMove_ValidMove() {
        boolean result = game.makeMove(0, 0);
        assertTrue(result);
    }

    @Test
    public void testMakeMove_InvalidOccupiedCell() {
        game.makeMove(1, 1);
        boolean result = game.makeMove(1, 1);
        assertFalse(result);
    }

    // Tests für switchPlayer()
    @Test
    public void testSwitchPlayer_Once() {
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer());
    }

    @Test
    public void testSwitchPlayer_TwiceBackToX() {
        game.switchPlayer();
        game.switchPlayer();
        assertEquals('X', game.getCurrentPlayer());
    }

    // Tests für getCurrentPlayer()
    @Test
    public void testGetCurrentPlayer_DefaultIsX() {
        assertEquals('X', game.getCurrentPlayer());
    }

    @Test
    public void testGetCurrentPlayer_AfterSwitch() {
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer());
    }

    public void testCheckWin_RowWin() {
        assertTrue(game.makeMove(0, 0));
        game.switchPlayer();  // Spieler O dran

        assertTrue(game.makeMove(1, 0));
        game.switchPlayer();  // Spieler X dran

        assertTrue(game.makeMove(0, 1));
        game.switchPlayer();  // Spieler O dran

        assertTrue(game.makeMove(1, 1));
        game.switchPlayer();

        assertTrue(game.makeMove(0, 2));

        assertTrue(game.checkWin());
    }

    @Test
    public void testCheckWin_NoWin() {
        assertFalse(game.checkWin());
    }

    // Tests für isBoardFull()
    @Test
    public void testIsBoardFull_True() {
        char player = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.makeMove(i, j);
                game.switchPlayer();
            }
        }
        assertTrue(game.isBoardFull());
    }

    @Test
    public void testIsBoardFull_False() {
        game.makeMove(0, 0);
        assertFalse(game.isBoardFull());
    }
}
