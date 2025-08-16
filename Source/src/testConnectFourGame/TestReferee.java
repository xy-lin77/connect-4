package testConnectFourGame;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import connectFourGame.Grid;
import connectFourGame.Referee;

public class TestReferee{
	// Referee.isValidMove
	@Test
	public void testRefereeIsValidMove1(){
		Grid test = new Grid();
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals(false, Referee.isValidMove(test, -10));
	        String output = outputStream.toString();
	        assertEquals("Input is out of range 0-6. Please try again.\r\n", output);
    	} 
    	finally {
    		System.setOut(originalOut);
	    }
	}
	@Test
	public void testRefereeIsValidMove2(){
		Grid test = new Grid();
		test.update(6, '?');
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals(false, Referee.isValidMove(test, 8));
	        String output = outputStream.toString();
	        assertEquals("Input is out of range 0-6. Please try again.\r\n", output);
    	} 
    	finally {
	    	System.setOut(originalOut);
	    	test.printGrid();
	    }
	}
	@Test
	public void testRefereeIsValidMove3(){
		Grid test = new Grid();
		int idx = -1;
		while (idx != 0)idx = test.update(3, '!');
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals(false, Referee.isValidMove(test, 3));
	        String output = outputStream.toString();
	        assertEquals("Column 3 is already full. Please try again.\r\n", output);
    	} 
    	finally {
	            System.setOut(originalOut);
	            test.printGrid();
	    }
	}
	@Test
	public void testRefereeIsValidMove4(){
		Grid test = new Grid();
	    assertEquals(true, Referee.isValidMove(test, 3));
	    test.printGrid();
	}
	
	// Referee.isFullGrid
	@Test
	public void testRefereeIsFullGrid1() {
		Grid test = new Grid();
		test.update(2, '!');
		assertEquals(false, Referee.isFullGrid(test));
	}
	@Test
	public void testRefereeIsFullGrid2() {
		Grid test = new Grid();
		for (int i = 0; i < 7; i++) {
			int idx = -1;
			while(idx != 0)idx = test.update(i, '!');
		}
		
		assertEquals(true, Referee.isFullGrid(test));
	}
	
	// Referee.checkWinner
	@Test
	public void testRefereecheckWinner1() {
		Grid test = new Grid();
		test.update(1, '!'); test.update(1, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(3, '!'); test.update(0, 'x');
		test.update(3, '!'); test.update(1, 'x');
		test.update(3, '!'); test.update(2, 'x');
		test.update(2, '!'); test.update(3, 'x');
		test.update(5, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(6, 'x');
		test.update(4, '!'); test.update(5, 'x');
		test.update(6, '!');
  		assertEquals('x', Referee.checkWinner(test, 2, 3, false));
	}
	@Test
	public void testRefereecheckWinner2() {
		Grid test = new Grid();
		test.update(1, '!'); test.update(5, 'x');
		test.update(2, '!'); test.update(6, 'x');
		test.update(3, '!'); test.update(2, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(3, '!'); test.update(3, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(0, '!');
  		assertEquals('!', Referee.checkWinner(test, 1, 5, false));
	}
	@Test
	public void testRefereecheckWinner3() {
		Grid test = new Grid();
		test.update(1, '!'); test.update(1, 'x');
		test.update(0, '!'); test.update(0, 'x');
		test.update(6, '!'); test.update(6, 'x');
		test.update(0, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(2, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(0, '!'); test.update(1, 'x');
		test.update(0, '!'); test.update(3, 'x');
		test.update(0, '!'); test.update(3, 'x');
		test.update(3, '!'); test.update(3, 'x');
  		assertEquals('!', Referee.checkWinner(test, 0, 1, false));
	}
	@Test
	public void testRefereecheckWinner4() {
		Grid test = new Grid();
		test.update(2, '!'); test.update(0, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(5, '!'); test.update(6, 'x');
		test.update(6, '!'); test.update(5, 'x');
		test.update(6, '!'); test.update(3, 'x');
		test.update(1, '!'); test.update(3, 'x');
		test.update(3, '!'); test.update(3, 'x');
		test.update(2, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(4, '!'); 
 		assertEquals('x', Referee.checkWinner(test, 5, 4, false));
	}
	@Test
	public void testRefereecheckWinner5() {
		Grid test = new Grid();
		test.update(2, '!'); test.update(3, 'x');
		test.update(2, '!'); test.update(3, 'x');
		test.update(3, '!'); test.update(2, 'x');
		test.update(1, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(2, '!'); test.update(5, 'x');
		test.update(0, '!'); test.update(5, 'x');
		test.update(5, '!'); test.update(3, 'x');
		test.update(5, '!'); test.update(5, 'x');
		test.update(0, '!'); test.update(5, 'x');
		test.update(6, '!');
  		assertEquals(' ', Referee.checkWinner(test, 0, 4, false));
	}

	@Test
	public void testRefereecheckWinner6() {
		Grid test = new Grid();
		test.update(1, '!'); test.update(1, 'x');
		test.update(2, '!'); test.update(1, 'x');
		test.update(3, '!'); test.update(4, 'x');
		test.update(0, '!'); 
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals('!', Referee.checkWinner(test, 0, 5, true));
	        String output = outputStream.toString();
	        assertEquals("\'!\' has got 4 Horizontal Adjacencies.\r\n", output);
    	} 
    	finally {
	        System.setOut(originalOut);
	    }
	}
	@Test
	public void testRefereecheckWinner7() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(2, '!');
		test.update(3, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(2, '!');
		test.update(3, 'x'); test.update(2, '!');

		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals('!', Referee.checkWinner(test, 2, 2, true));
	        String output = outputStream.toString();
	        assertEquals("\'!\' has got 4 Vertical Adjacencies.\r\n", output);
    	} 
    	finally {
	        System.setOut(originalOut);
	    }
	}
	@Test
	public void testRefereecheckWinner8() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(3, '!');
		test.update(3, 'x'); test.update(2, '!');
		test.update(1, 'x'); test.update(1, '!');
		test.update(2, 'x'); test.update(0, '!');
		test.update(1, 'x');
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals('x', Referee.checkWinner(test, 1, 2, true));
	        String output = outputStream.toString();
	        assertEquals("\'x\' has got 4 Left-Top to Right-Bottom Diagonal Adjacencies.\r\n", output);
    	} 
    	finally {
	        System.setOut(originalOut);
	    }
	}
	@Test
	public void testRefereecheckWinner9() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(2, '!');
		test.update(2, 'x'); test.update(3, '!');
		test.update(4, 'x'); test.update(3, '!');
		test.update(3, 'x'); test.update(4, '!');
		test.update(5, 'x'); test.update(4, '!');
		test.update(4, 'x');
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));

	        assertEquals('x', Referee.checkWinner(test, 4, 2, true));
	        String output = outputStream.toString();
	        assertEquals("\'x\' has got 4 Left-Bottom to Right-Top Diagonal Adjacencies.\r\n", output);
    	} 
    	finally {
	        System.setOut(originalOut);
	    }
	}
	
	// Referee.findLongest
	@Test
	public void testRefereeFindLongest1() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(3, '!');
		test.update(2, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(3, '!');
		test.update(6, 'x'); test.update(3, '!');
		test.update(6, 'x'); test.update(4, '!');
		test.update(6, 'x'); test.update(4, '!');
		assertArrayEquals(new int[] {1, 2},Referee.findLongest(test, 3, 3));
	}
	@Test
	public void testRefereeFindLongest2() {
		Grid test = new Grid();
		test.update(4, 'x'); test.update(2, '!');
		test.update(5, 'x'); test.update(6, '!');
		test.update(5, 'x'); test.update(5, '!');
		test.update(6, 'x'); test.update(5, '!');
		test.update(6, 'x');
		assertArrayEquals(new int[] {0, 0},Referee.findLongest(test, 6, 3));
	}
	@Test
	public void testRefereeFindLongest3() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(3, '!');
		test.update(2, 'x'); test.update(4, '!');
		test.update(6, 'x'); test.update(5, '!');
		test.update(2, 'x'); test.update(3, '!');
		test.update(4, 'x'); test.update(6, '!');
		test.update(5, 'x'); test.update(2, '!'); 
		test.update(3, 'x'); test.update(5, '!'); 
		test.update(6, 'x'); test.update(4, '!'); 
		test.update(2, 'x'); test.update(3, '!');
		test.update(5, 'x'); test.update(5, '!');
		test.update(4, 'x'); test.update(5, '!');
		test.update(6, 'x'); test.update(0, '!');
		test.update(6, 'x'); test.update(6, '!');
		test.update(4, 'x'); test.update(4, '!');

		assertArrayEquals(new int[] {1, 0},Referee.findLongest(test, 4, 0));
	}
	@Test
	public void testRefereeFindLongest4() {
		Grid test = new Grid();
		test.update(0, 'x'); test.update(4, '!');
		test.update(3, 'x'); test.update(3, '!');
		test.update(5, 'x'); test.update(6, '!');
		test.update(6, 'x'); test.update(5, '!');
		test.update(6, 'x'); test.update(4, '!');
		test.update(5, 'x'); test.update(4, '!');
		test.update(0, 'x'); test.update(3, '!');
		assertArrayEquals(new int[] {2, 1},Referee.findLongest(test, 4, 3));
	}
	
	// Referee.TraverseGrid with stub class
	@Test
	public void testRefereeTraverseGrid1() {
		Grid test = new Grid();
		test.update(2, '!'); test.update(0, 'x');
		test.update(2, '!'); test.update(2, 'x');
		test.update(5, '!'); test.update(6, 'x');
		test.update(6, '!'); test.update(5, 'x');
		test.update(6, '!'); test.update(3, 'x');
		test.update(1, '!'); test.update(3, 'x');
		test.update(3, '!'); test.update(3, 'x');
		test.update(2, '!'); test.update(4, 'x');
		test.update(4, '!'); test.update(4, 'x');
		test.update(4, '!'); 
		assertEquals('!', Referee.TraverseGrid(test));
	}
	@Test
	public void testRefereeTraverseGrid2() {
		Grid test = new Grid();
		test.update(1, '!'); test.update(1, 'x');
		test.update(2, '!'); test.update(1, 'x');
		test.update(3, '!'); test.update(4, 'x');
		test.update(0, '!'); 
		assertEquals('!', Referee.TraverseGrid(test));
	}
	@Test
	public void testRefereeTraverseGrid3() {
		Grid test = new Grid();
		test.update(3, 'x'); test.update(6, '!');
		test.update(3, 'x'); test.update(4, '!');
		test.update(1, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(2, '!');
		test.update(5, 'x'); test.update(4, '!');
		test.update(3, 'x'); test.update(5, '!');
		test.update(1, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(2, '!');
		assertEquals('!', Referee.TraverseGrid(test));
	}
	@Test
	public void testRefereeTraverseGrid4() {
		Grid test = new Grid();
		test.update(1, 'x'); test.update(2, '!');
		test.update(3, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(2, '!');
		test.update(3, 'x'); 
		assertEquals(' ', Referee.TraverseGrid(test));
	}
	@Test
	public void testRefereeTraverseGrid5() {
		Grid test = new Grid();
		test.update(3, 'x'); test.update(6, '!');
		test.update(3, 'x'); test.update(4, '!');
		test.update(2, 'x'); test.update(2, '!');
		test.update(4, 'x'); test.update(3, '!');
		test.update(5, 'x'); test.update(4, '!');
		test.update(2, 'x'); test.update(4, '!');
		test.update(5, 'x'); test.update(1, '!');
		assertEquals('!', Referee.TraverseGrid(test));
	}
}