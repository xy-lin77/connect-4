package testConnectFourGame;

import org.junit.jupiter.api.Test;

import connectFourGame.AIPlayer;
import connectFourGame.Grid;

import static org.junit.Assert.assertEquals;

public class TestAIPlayer{
	@Test
	public void testAIPlayer() {
		class stubAIPlayer implements AIPlayer {

			@Override
			public int InputAI(Grid grid, char turn, char opponent) {
				return 0;
			}
			
		}
		AIPlayer test = new stubAIPlayer();
		int result = test.InputAI(new Grid(), 'x', 'o');
		assertEquals(0, result);
	}
}