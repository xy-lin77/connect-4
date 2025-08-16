package testConnectFourGame;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import connectFourGame.CmdList;
import connectFourGame.GamaGo;
import connectFourGame.Grid;

public class TestGamaGo{
	@Test
	public void testMakeMove1() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();
		test.update(0, 'x');
		test.update(1, 'x');
		test.update(2, 'x');
		
		assertEquals(3, gamaGo.makeMove(test));
	}
	
	@Test
	public void testMakeMove2() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();
		test.update(0, 'x');
		test.update(0, 'o');
		test.update(0, 'x');
		test.update(0, 'o');
		test.update(0, 'x');
		test.update(0, 'o');
		test.update(1, 'o');
		test.update(1, 'o');
		test.update(1, 'o');
		
		
		assertEquals(1, gamaGo.makeMove(test));
	}
	
	@Test
	public void testMakeMove3() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();
		test.update(3, 'o');
		test.update(4, 'x');
		test.update(4, 'o');
		test.update(5, 'x');
		test.update(5, 'o');
		test.update(5, 'o');
		test.update(6, 'x');
		test.update(6, 'x');
		
		assertEquals(3, gamaGo.makeMove(test));
	}
	
	@Test
	public void testMakeMove4() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();		
		
		test.update(3, 'o');
		test.update(3, 'x');
		test.update(4, 'x');
		test.update(4, 'x');
		test.update(5, 'o');
		test.update(5, 'o');
		test.update(5, 'x');
		test.update(6, 'o');
		test.update(6, 'o');
		test.update(6, 'x');
		
		assertEquals(4, gamaGo.makeMove(test));
	}
	
	@Test
	public void testMakeMove5() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();		
		test.update(0, 'x');
		test.update(0, 'o');
		test.update(0, 'x');
		test.update(0, 'o');
		test.update(0, 'x');
		
		test.update(3, 'x');
		test.update(3, 'o');
		test.update(4, 'o');
		test.update(4, 'o');
		test.update(5, 'x');
		test.update(5, 'x');
		test.update(5, 'o');
		test.update(6, 'x');
		test.update(6, 'x');
		test.update(6, 'o');
		
		assertEquals(4, gamaGo.makeMove(test));
	}
	
	@Test
	public void testMakeMove6() {
		GamaGo gamaGo = new GamaGo('x');
		GamaGo oppoent = new GamaGo('o');
		Grid test = new Grid();		
		
		test.update(3, 'x');
		test.update(3, 'o');
		test.update(4, 'o');
		test.update(4, 'o');
		test.update(5, 'x');
		test.update(5, 'x');
		test.update(5, 'o');
		test.update(6, 'o');
		test.update(6, 'x');
		
		assertEquals(4, gamaGo.makeMove(test));
	}
	
	@Test
	public void testInputAI() {
		GamaGo gamaGo = new GamaGo('x');
		Grid test = new Grid();		
		
		test.update(3, 'x');
		test.update(3, 'o');
		test.update(4, 'o');
		test.update(4, 'o');
		test.update(5, 'x');
		test.update(5, 'x');
		test.update(5, 'o');
		test.update(6, 'o');
		test.update(6, 'x');
		
		assertEquals(4, gamaGo.InputAI(test, 'x', 'o'));
	}
	
}