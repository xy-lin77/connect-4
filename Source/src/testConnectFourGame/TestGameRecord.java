package testConnectFourGame;

import org.junit.jupiter.api.Test;

import connectFourGame.GameRecord;
import exception.NoGameRecordException;
import systemClass.ProjectScanner;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TestGameRecord{
	@Test
	public void testGetInstance() {
		GameRecord gameRecord = GameRecord.getInstance();
		GameRecord gameRecord2 = GameRecord.getInstance();
		assertSame(gameRecord, gameRecord2);
	}
	
	@Test
	public void testViewRecord1() {
		GameRecord gameRecord = GameRecord.getInstance();
		boolean exception = false;
		try {
			gameRecord.viewRecord();
		} catch (NoGameRecordException e) {
			exception = true;
		}
		assertEquals(true, exception);
	}
	
	@Test
	public void testViewRecord2() {
		provideInputStream("3\n2\nSONG\no\n6\n6\n6\nabc\n2\n1\n-1\n");

		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));
			
			GameRecord gameRecord = GameRecord.getInstance();
			
			LocalTime currentTime = LocalTime.now();
			
			gameRecord.addGame();
			try {
				gameRecord.viewRecord();
			} catch (NoGameRecordException e) {
				e.printStackTrace();
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			String output = outputStream.toString();
			assertEquals("Please select game mode: \r\n"
					+ "   1. Human vs Human \r\n"
					+ "   2. Human vs AI \r\n"
					+ "   3. AI vs Human \r\n"
					+ "Enter the index number to indicate the game mode: Please select the AI player you want to play against: \r\n"
					+ "   1. GamaGo (Easy) \r\n"
					+ "   2. BetaGO (Hard) \r\n"
					+ "Enter the index number to indicate the AI Model to play against: Please type in Player 1's name: BetaGo\r\n"
					+ "Please type in a char to represent BetaGo's chess (e.g. 'o', 'x'): x\r\n"
					+ "Please type in Player 2's name: Please type in a char represent to SONG's chess (e.g. 'o', 'x'): \r\n"
					+ "New game started! The symbol for BetaGo is 'x', for SONG is 'o'.\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "'x' has got 4 Vertical Adjacencies.\r\n"
					+ "Congrats to BetaGo on the win!\r\n"
					+ "\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "| Records Number|   Time   | Player1(?) vs. Player2(?)  |\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "|       1       | "+currentTime.format(formatter)+" | BetaGo(x) vs. SONG(o) |\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "Which game record would you like to check?\r\n"
					+ "Enter the corresponding reference number or enter 0 to exit: Please enter a valid integer! \r\n"
					+ "Which game record would you like to check?\r\n"
					+ "Enter the corresponding reference number or enter 0 to exit: Please enter a valid index! The index should be from 1 to 1.\r\n"
					+ "Which game record would you like to check?\r\n"
					+ "Enter the corresponding reference number or enter 0 to exit: \r\n"
					+ "You are now traversing the game in "+currentTime.format(formatter)+" between Player1, BetaGo(x),  and Player2, SONG(o). \r\n"
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
                    , output);
		}
		finally
    	{
    		System.setOut(originalOut);
    		restoreSystemIn();
    	}
	}
	
	@Test
	public void testViewRecord3() {
		provideInputStream("3\n2\nSONG\no\n6\n6\n6\n-1\n0\n");

		PrintStream originalOut = System.out;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outputStream));
			
			GameRecord gameRecord = GameRecord.getInstance();
			
			LocalTime currentTime = LocalTime.now();
			
			gameRecord.addGame();
			try {
				gameRecord.viewRecord();
			} catch (NoGameRecordException e) {
				e.printStackTrace();
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			String output = outputStream.toString();
			assertEquals("Please select game mode: \r\n"
					+ "   1. Human vs Human \r\n"
					+ "   2. Human vs AI \r\n"
					+ "   3. AI vs Human \r\n"
					+ "Enter the index number to indicate the game mode: Please select the AI player you want to play against: \r\n"
					+ "   1. GamaGo (Easy) \r\n"
					+ "   2. BetaGO (Hard) \r\n"
					+ "Enter the index number to indicate the AI Model to play against: Please type in Player 1's name: BetaGo\r\n"
					+ "Please type in a char to represent BetaGo's chess (e.g. 'o', 'x'): x\r\n"
					+ "Please type in Player 2's name: Please type in a char represent to SONG's chess (e.g. 'o', 'x'): \r\n"
					+ "New game started! The symbol for BetaGo is 'x', for SONG is 'o'.\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "SONG, it's your turn.\r\n"
					+ "Please select an option from the following list: \r\n"
					+ "    0-6: Place A New Piece At The Input Position \r\n"
					+ "     -1: To Undo The Last Step (1 more times) \r\n"
					+ "     -2: Get AI suggestion (2 more times) \r\n"
					+ "     -3: Redo The Last Step \r\n"
					+ "Please enter your selection:   --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "BetaGo, the AI player, is thinking...\r\n"
					+ "  --------------\r\n"
					+ "|               |\r\n"
					+ "|               |\r\n"
					+ "|       x       |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "|       x     o |\r\n"
					+ "  --------------\r\n"
					+ "  0 1 2 3 4 5 6 \r\n"
					+ "'x' has got 4 Vertical Adjacencies.\r\n"
					+ "Congrats to BetaGo on the win!\r\n"
					+ "\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "| Records Number|   Time   | Player1(?) vs. Player2(?)  |\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "|       1       | "+currentTime.format(formatter)+" | BetaGo(x) vs. SONG(o) |\r\n"
					+ "|       2       | "+currentTime.format(formatter)+" | BetaGo(x) vs. SONG(o) |\r\n"
					+ "+---------------+----------+----------------------------+\r\n"
					+ "Which game record would you like to check?\r\n"
					+ "Enter the corresponding reference number or enter 0 to exit: Please enter a valid index! The index should be from 1 to 2.\r\n"
					+ "Which game record would you like to check?\r\n"
					+ "Enter the corresponding reference number or enter 0 to exit: "
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
}