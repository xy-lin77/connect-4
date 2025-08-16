package testConnectFourGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import connectFourGame.Grid;
import connectFourGame.HumanPlayer;
import connectFourGame.Player;
import connectFourGame.BetaGo;
import connectFourGame.GamaGo;

class TestPlayer {
	@Test
	void testGetName() {
		Player human = new HumanPlayer("Xiu", '1');
		Player human1 = new HumanPlayer("Shawn", '|');
		Player Beta = new BetaGo('Q');
		Player Gama = new GamaGo('W');
	    assertEquals("Xiu", human.getName());
	    assertEquals("Shawn", human1.getName());
	    assertEquals("BetaGo", Beta.getName());
	    assertEquals("GamaGo", Gama.getName());
	}
	
	@Test
	void testGetSymbol() {
		Player human = new HumanPlayer("Xiu", '2');
		Player human1 = new HumanPlayer("Shawn", '|');
		Player Beta = new BetaGo('E');
		Player Gama = new GamaGo('R');
	    assertEquals('2', human.getSymbol());
		assertEquals('|', human1.getSymbol());
	    assertEquals('E', Beta.getSymbol());
	    assertEquals('R', Gama.getSymbol());
	}
	
	@Test
	void testSwitchPlayer() {
		Player human1 = new HumanPlayer("Xiu", '3');
		Player Beta = new BetaGo('T');
		Player human2 = new HumanPlayer("Rui", '\\');
		Player Gama = new GamaGo('Y');
	    assertEquals(Beta, human1.switchPlayer());
	    assertEquals(human1, Beta.switchPlayer());
	    assertEquals(Gama, human2.switchPlayer());
	    assertEquals(human2, Gama.switchPlayer());
	}
	
	@Test
    void testSetSymbol() {
		Player human = new HumanPlayer("Xiu", '4');
        assertEquals('4', human.getSymbol());

        // Set a new symbol
        human.setSymbol('?');
        assertEquals('?', human.getSymbol());
        
        Player Gama = new GamaGo('x');
        assertEquals('x', Gama.getSymbol());

        // Set a new symbol
        Gama.setSymbol('o');
        assertEquals('o', Gama.getSymbol());
    }
	
	
	@Test
	void testRemovePlayer() {
		Player human1 = new HumanPlayer("Song", '!');
		Player Beta = new BetaGo('?');
	    assertEquals(Beta, human1.switchPlayer());

	    Beta.removePlayer();
	    
	    Player Gama = new GamaGo('+');
	    assertEquals(Gama, human1.switchPlayer());
	}
	
	@Test
	void testYCoordinate() {
	    // Assuming the grid is empty initially
		Grid grid = new Grid();
		Player human1 = new HumanPlayer("Xiu", '!');
		Player Gama = new BetaGo('?');
	    assertEquals(5, human1.YCoordinate(grid, 0));
	    assertEquals(5, Gama.YCoordinate(grid, 0));
	    grid.update(0, '!');
	    grid.update(4, '?');
	    assertEquals(4, Gama.YCoordinate(grid, 4));
	    assertEquals(4, human1.YCoordinate(grid, 0));
	    for (int i = 0; i < 6; i++) {
            grid.update(1,'!');
        }
	    assertEquals(0, human1.YCoordinate(grid, 1));
	}
}
