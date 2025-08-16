package testConnectFourGame;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import connectFourGame.CmdList;
import connectFourGame.GamaGo;
import connectFourGame.BetaGo;
import connectFourGame.Grid;

public class TestBetaGo{
	@Test // To cover GreedyWin() branch
	public void testMakeMove1() {
		BetaGo betaGo = new BetaGo('x');
		BetaGo opponent = new BetaGo('o');
		Grid test = new Grid();
		test.update(0, 'x');
		test.update(1, 'x');
		test.update(2, 'x');
		assertEquals(3, betaGo.makeMove(test));
	}
	
	@Test // To cover GreedyDefend() branch
	public void testMakeMove2() {
		BetaGo betaGo = new BetaGo('x');
		BetaGo opponent = new BetaGo('o');
		Grid test = new Grid();
		test.update(0, 'o');
		test.update(1, 'o');
		test.update(2, 'o');
		assertEquals(3, betaGo.makeMove(test));
	}

	@Test // To cover the recursive function MiniMax()
	public void testMakeMove4() {
		BetaGo betaGo = new BetaGo('x');
		BetaGo opponent = new BetaGo('o');
		Grid test = new Grid();
		assertEquals(3 , betaGo.makeMove(test));
	}
	
	@Test // To cover the case where Referee.isFullGrid() = True
	public void testMakeMove5() {
		BetaGo betaGo = new BetaGo('x');
		BetaGo opponent = new BetaGo('o');
		Grid test = new Grid();
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (i % 2 == 1) {
					test.update(j, 'o');
					test.update(j+3, 'x');
				}
				else {
					test.update(j, 'x');
					test.update(j+3, 'o');
				}
			}
		}
		for (int i = 0; i < 2; i++) {
			test.update(6, 'o');
			test.update(6, 'x');
		}
		for (int i = 0; i < 6; i+=2) {
			test.update(i, 'o');
			test.update(i+1, 'x');
		}
		test.update(6, 'o');
		assertEquals(6 , betaGo.makeMove(test));
	}
	
	@Test // To cover InputAI()
	public void testMakeMove6() {
		BetaGo betaGo = new BetaGo('x');
		Grid test = new Grid();		
		
		test.update(0, 'x');
		test.update(1, 'x');
		test.update(2, 'x');
		
		assertEquals(3, betaGo.InputAI(test, 'x', 'o'));
	}
}