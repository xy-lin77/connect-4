package testConnectFourGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import connectFourGame.Move;

class TestMove {

    @Test
    void testGetX() {
        Move move = new Move(1, 2, 'X');
        assertEquals(1, move.getX());
    }

    @Test
    void testGetY() {
        Move move = new Move(1, 2, 'X');
        assertEquals(2, move.getY());
    }

    @Test
    void testGetSymbol() {
        Move move = new Move(1, 2, 'X');
        assertEquals('X', move.getSymbol());
    }

}
