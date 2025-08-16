package testConnectFourGame;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.Test;

import connectFourGame.CmdList;
import connectFourGame.CmdMove;
import connectFourGame.Grid;
import systemClass.ProjectScanner;

public class TestCmdList{
	// CmdList.createCmdMove
	@Test
	public void testCreateCmdMove() {
		CmdList test = new CmdList();
		Grid tGrid = new Grid();
		CmdMove expected = new CmdMove('!', new Grid(), 2);
		CmdMove result = test.createCmdMove('!', tGrid, 2);
		assertArrayEquals(new Object[]{expected.getMove().getX(), expected.getMove().getY(), expected.getMove().getSymbol()}
		, new Object[] {result.getMove().getX(), result.getMove().getY(), result.getMove().getSymbol()});
	}
	
	// CmdList.undocmd and CmdList.redocmd
	@Test
	public void testUndoRedo() {
		CmdList test = new CmdList();
		Grid tGrid = new Grid();
		
		test.createCmdMove('x', tGrid, 3);
		
		assertTrue(test.undocmd());
		char[][] outcome = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' '}};
		assertArrayEquals(outcome, tGrid.getGrid());
		
		assertTrue(test.redocmd());
		outcome[5][3] = 'x';
		assertArrayEquals(outcome, tGrid.getGrid());
	
	}
	
	// CmdList.allowUndo and CmdList.allowRedo
	@Test
	public void testAllowUndoRedo() {
		CmdList test = new CmdList();
		Grid tGrid = new Grid();
		assertFalse(test.allowUndo());
		assertFalse(test.allowRedo());
		
		test.createCmdMove('x', tGrid, 3);
		test.createCmdMove('!', tGrid, 4);
		
		assertTrue(test.allowUndo());
		test.undocmd(); test.undocmd();
		assertTrue(test.allowRedo());
	}
	
	// CmdList.traverse
	@Test
	public void testTraverse() {
		CmdList test = new CmdList();
		Grid tGrid = new Grid();
		test.createCmdMove('!', tGrid, 3); test.createCmdMove('?', tGrid, 0);
		test.createCmdMove('!', tGrid, 3); test.createCmdMove('?', tGrid, 0);
		test.createCmdMove('!', tGrid, 3); test.createCmdMove('?', tGrid, 0);
		test.createCmdMove('!', tGrid, 3);
		
        provideInputStream("ah\n0\n1\n2\n1\n2\n2\n2\n2\n0\n2\n0\n2\n2\n2\n6\n0\n-1\n");
        PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));
			
			test.traverse();
        
			String output = outputStream.toString();
			assertEquals("  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
					+ "Please enter a valid Integer! \r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "BetaGo, the AI player, is thinking...\r\n"
                    + "BetaGo's suggestion: 3\r\n"
                    + "GamaGo, the AI player, is thinking...\r\n"
                    + "GamaGo's suggestion: 3\r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
					+ "It's already the first move. \r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
					+ "\r\n"
					+ "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|       !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|       !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|       !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "BetaGo, the AI player, is thinking...\r\n"
                    + "BetaGo's suggestion: 3\r\n"
                    + "GamaGo, the AI player, is thinking...\r\n"
                    + "GamaGo's suggestion: 3\r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "|       !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "BetaGo, the AI player, is thinking...\r\n"
                    + "BetaGo's suggestion: 3\r\n"
                    + "GamaGo, the AI player, is thinking...\r\n"
                    + "GamaGo's suggestion: 3\r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|               |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "\r\n"
                    + "  --------------\r\n"
            		+ "|               |\r\n"
                    + "|               |\r\n"
                    + "|       !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "| ?     !       |\r\n"
                    + "  --------------\r\n"
                    + "  0 1 2 3 4 5 6 \r\n"
                    + "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "It's already the last move. \r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
                    + "It's already the last move. \r\n"
					+ "Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: "
					,output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
    }

	
    private void provideInputStream(String input) {
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Use reflection to set the scanner field
        try {
            Field scannerField = ProjectScanner.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(ProjectScanner.getInstance(), new Scanner(System.in));
        } 
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.setIn(sysInBackup); // Restore original System.in
    }

    private void restoreSystemIn() {
        // Restore original System.in
        System.setIn(new FileInputStream(FileDescriptor.in));
    }
}