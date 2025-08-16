package testConnectFourGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import connectFourGame.CmdMove;
import connectFourGame.Grid;
import connectFourGame.Move;

class TestCmdMove {
	// Test CmdMove.getMove
	@Test
	public void testCmdMoveQ1() {
		Grid tGrid = new Grid();
		// assume the first chess is dropped in slot 0
		CmdMove test = new CmdMove('x', tGrid, 0);
		Move expected = new Move(0, 5, 'x');
		assertArrayEquals(new Object[]{expected.getX(), expected.getY(), expected.getSymbol()}
		, new Object[]{test.getMove().getX(), test.getMove().getY(), test.getMove().getSymbol()});
	}
	
	@Test
	public void testCmdMoveQ2() {
		Grid tGrid = new Grid();
		CmdMove test = new CmdMove('?', tGrid, 0);
		char[][] outcome = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'?', ' ', ' ', ' ', ' ', ' ', ' '}};
		assertArrayEquals(outcome, tGrid.getGrid());
		
		char[][] emptyGrid = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '}};
		test.undoOneCmd();
		assertArrayEquals(emptyGrid, tGrid.getGrid());
		test.redoOneCmd();
		assertArrayEquals(outcome, tGrid.getGrid());
	}
}