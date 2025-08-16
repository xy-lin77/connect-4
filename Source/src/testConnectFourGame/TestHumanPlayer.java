package testConnectFourGame;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import exception.CanNotRedoException;
import exception.CanNotUndoException;
import connectFourGame.CmdList;
import connectFourGame.Grid;
import connectFourGame.HumanPlayer;
import exception.NoMoreSuggestionException;
import exception.NoMoreUndoException;
import systemClass.ProjectScanner;
import systemClass.SysConfiguration;

public class TestHumanPlayer{	
	@Test
	public void testMakeMove1() {
		provideInputStream("abc 1");
		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));
			
//			HumanPlayer test = new HumanPlayer("a", 'x');
//			test.getInput();
			
			HumanPlayer humanPlayer = new HumanPlayer("test", 'x');
			humanPlayer.makeMove(new Grid());
			
			String output = outputStream.toString();
			assertEquals("test, it's your turn.\r\n" + "Please select an option from the following list: \r\n"
	                    + "    0-6: Place A New Piece At The Input Position \r\n"
						+ "     -1: To Undo The Last Step (" + SysConfiguration.getSysConfig("allowedUndo") + " more times) \r\n"
	                    + "     -2: Get AI suggestion (" + SysConfiguration.getSysConfig("allowedHelp") + " more times) \r\n"
	                    + "     -3: Redo The Last Step \r\n"
	                    + "Please enter your selection: "
	                    + "Please enter an integer number! \r\n"
	                    + "test, it's your turn.\r\n" + "Please select an option from the following list: \r\n"
	                    + "    0-6: Place A New Piece At The Input Position \r\n"
	                    + "     -1: To Undo The Last Step (" + SysConfiguration.getSysConfig("allowedUndo") + " more times) \r\n"
	                    + "     -2: Get AI suggestion (" + SysConfiguration.getSysConfig("allowedHelp") + " more times) \r\n"
	                    + "     -3: Redo The Last Step \r\n"
	                    + "Please enter your selection: "
	                    , output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
	}
	
	@Test
	public void testMakeUndo() {
		provideInputStream("1 1 1 1 ");
		
		//String scannerContent = getScannerContent();
        //System.out.println("Scanner content: " + scannerContent);
		//provideInputStream("abc\n1\n");
		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));

//			HumanPlayer test = new HumanPlayer("a", 'x');
//			test.getInput();
			
			HumanPlayer humanPlayer = new HumanPlayer("test", 'x');
			HumanPlayer humanPlayer2 = new HumanPlayer("test2", 'o');
			Grid grid = new Grid();
			CmdList cmdList = new CmdList();
			try {
				humanPlayer.makeUndo(grid, cmdList);	
			}
			catch(CanNotUndoException e) {}
			catch(NoMoreUndoException e) {}
			cmdList.createCmdMove(humanPlayer.getSymbol(), grid, 1);
			cmdList.createCmdMove(humanPlayer2.getSymbol(), grid, 1);
			try {
				humanPlayer.makeUndo(grid, cmdList);
			}
			catch(CanNotUndoException e) {}
			catch(NoMoreUndoException e) {}
			cmdList.createCmdMove(humanPlayer.getSymbol(), grid, 1);
			cmdList.createCmdMove(humanPlayer2.getSymbol(), grid, 1);
			try {
				humanPlayer.makeUndo(grid, cmdList);	
			}
			catch(CanNotUndoException e) {}
			catch(NoMoreUndoException e) {}
			
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
	                    , output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
	}
	
	@Test
	public void testMakeRedo() {
		provideInputStream("abc 1 1 ");
		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));
			
			HumanPlayer humanPlayer = new HumanPlayer("test", 'x');
			HumanPlayer humanPlayer2 = new HumanPlayer("test2", 'o');
			Grid grid = new Grid();
			CmdList cmdList = new CmdList();
			try {
				humanPlayer.makeRedo(grid, cmdList);	
			}
			catch(CanNotRedoException e) {}
			cmdList.createCmdMove(humanPlayer.getSymbol(), grid, 1);
			cmdList.createCmdMove(humanPlayer2.getSymbol(), grid, 1);
			try {
				humanPlayer.makeUndo(grid, cmdList);
				humanPlayer.makeRedo(grid, cmdList);	
			}
			catch(CanNotUndoException e) {}
			catch(NoMoreUndoException e) {}
			catch(CanNotRedoException e) {}
			
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
	                    + "  --------------\r\n"
	            		+ "|               |\r\n"
	                    + "|               |\r\n"
	                    + "|               |\r\n"
	                    + "|               |\r\n"
	                    + "|   o           |\r\n"
	                    + "|   x           |\r\n"
	                    + "  --------------\r\n"
	                    + "  0 1 2 3 4 5 6 \r\n"
	                    , output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
	}
	
	@Test
	public void testMakeSuggestion() {
		provideInputStream("1 1 1 1 ");
		
		//String scannerContent = getScannerContent();
        //System.out.println("Scanner content: " + scannerContent);
		//provideInputStream("abc\n1\n");
		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));

//			HumanPlayer test = new HumanPlayer("a", 'x');
//			test.getInput();
			
			HumanPlayer humanPlayer = new HumanPlayer("test", 'x');
			HumanPlayer humanPlayer2 = new HumanPlayer("test2", 'o');
			try {
				humanPlayer.makeSuggestion(new Grid());
			}
			catch (NoMoreSuggestionException e) {}
			try {
				humanPlayer.makeSuggestion(new Grid());
			}
			catch (NoMoreSuggestionException e) {}
			try {
				humanPlayer.makeSuggestion(new Grid());
			}
			catch (NoMoreSuggestionException e) {}
			
			String output = outputStream.toString();
			assertEquals("BetaGo, the AI player, is thinking...\r\n"
	            		+ "BetaGO's suggestion: 3\r\n"
	                    + "GamaGo, the AI player, is thinking...\r\n"
	                    + "GamaGo's suggestion: 3\r\n"
	                    + "BetaGo, the AI player, is thinking...\r\n"
	            		+ "BetaGO's suggestion: 3\r\n"
	                    + "GamaGo, the AI player, is thinking...\r\n"
	                    + "GamaGo's suggestion: 3\r\n"
	                    , output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
	}
	
    private void provideInputStream(String input) {
    	//System.out.print(input);
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Use reflection to set the scanner field
        try {
            Field scannerField = ProjectScanner.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(ProjectScanner.getInstance(), new Scanner(System.in));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.setIn(sysInBackup); // Restore original System.in
    }
    
    private void restoreSystemIn() {
        // Restore original System.in
        System.setIn(new FileInputStream(FileDescriptor.in));
    }
    
//    private String getScannerContent() {
//        InputStream sysInBackup = System.in;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            System.out.println(line);
//        }
//
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//        System.setIn(sysInBackup);
//
//        return out.toString();
//    }
}