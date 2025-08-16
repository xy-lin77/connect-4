package testConnectFourGame;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import connectFourGame.Grid;

public class TestGrid{
	// Grid.getGrid
	@Test
	public void testGridGetGrid() {
		Grid test = new Grid();
		char[][] outcome = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '}};
		assertArrayEquals(outcome, test.getGrid());
	}
	
	// Grid.update
	@Test
	public void testGridUpdateQ1() {
		Grid test = new Grid();
		int updateReceive = -1, updateTimes = 0;
		while(updateTimes < 2) {
			updateReceive = test.update(0, '!');
			updateTimes += 1;
		}
		assertEquals(2, updateTimes);
		assertEquals(4, updateReceive);
	}
	@Test
	public void testGridUpdateQ2() {
		Grid test = new Grid();
		int updateReceive = -1, updateTimes = 0;
		while(updateTimes < 6) {
			updateReceive = test.update(1, '!');
			updateTimes += 1;
		}
		assertEquals(6, updateTimes);
		assertEquals(0, updateReceive);
	}
	@Test
	public void testGridUpdateQ3() {
		Grid test = new Grid();
		int updateReceive = -1, updateTimes = 0;
		while(updateTimes < 10) {
			updateReceive = test.update(1, '!');
			updateTimes += 1;
		}
		assertEquals(10, updateTimes);
		assertEquals(0, updateReceive);
	}
		
	// Grid.withdraw
	@Test
	public void testGridWithdrawQ1() {
		Grid test = new Grid();
		test.withdraw(4);
		char[][] outcome = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '}};
		assertArrayEquals(outcome, test.getGrid());
	}
	@Test
	public void testGridWithdrawQ2() {
		Grid test = new Grid();
		test.update(5, '!');
		test.update(5, '!');
		test.update(5, '!');
		test.withdraw(5);
		char[][] outcome = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', '!', ' '},
				{' ', ' ', ' ', ' ', ' ', '!', ' '}};
		assertArrayEquals(outcome, test.getGrid());
	}
	
	// Grid.printGrid
	@Test
	public void testGridPrint() {
    	Grid test = new Grid();
    	for(int k = 0; k < 3; k++) {
    		for (int i = 0; i < 7; i++) {
	    		test.update(i, '!');
	   		}
	   	}
	   	test.withdraw(2);
	   	
    	PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));
	         
	        test.printGrid();
	        String output = outputStream.toString();
	        assertEquals("  --------------\r\n"
	            		+ "|               |\r\n"
	                    + "|               |\r\n"
	                    + "|               |\r\n"
	                    + "| ! !   ! ! ! ! |\r\n"
	                    + "| ! ! ! ! ! ! ! |\r\n"
	                    + "| ! ! ! ! ! ! ! |\r\n"
	                    + "  --------------\r\n"
	                    + "  0 1 2 3 4 5 6 \r\n", output);
    	} 
    	finally {
	            System.setOut(originalOut);
	    }
    }
	
	// Grid.clone
	@Test
	public void testGridClone() throws CloneNotSupportedException {
		Grid origin = new Grid();
		Grid clone = origin.clone();
		
		assertNotSame(origin,clone);
		assertArrayEquals(origin.getGrid(), clone.getGrid());
	}
}