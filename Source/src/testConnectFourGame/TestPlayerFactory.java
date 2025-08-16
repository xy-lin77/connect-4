package testConnectFourGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import connectFourGame.BetaGo;
import connectFourGame.GamaGo;
import connectFourGame.HumanPlayer;
import connectFourGame.Player;
import connectFourGame.PlayerFactory;
import systemClass.ProjectScanner;

class TestPlayerFactory {

    @Test
    void testCreatePlayer() {

    	provideInputStream("\nJohn\nx\n");

        Player player = PlayerFactory.createPlayer(1);

        assertTrue(player instanceof HumanPlayer);
        assertEquals("John", player.getName());
        assertEquals('x', player.getSymbol());

        // Restore original System.in
        restoreSystemIn();
    }

    @Test
    void testCreateAI1() {
    	
        provideInputStream("abc\n1\n");

        Player aiPlayer = PlayerFactory.createAI(2, 'o');

        assertTrue(aiPlayer instanceof GamaGo); 
        assertEquals('x', aiPlayer.getSymbol());

        // Restore original System.in
        restoreSystemIn();
    }
    
    @Test
    void testCreateAI2() {
    	
        provideInputStream("2\n");

        Player aiPlayer = PlayerFactory.createAI(2, 'o');

        assertTrue(aiPlayer instanceof BetaGo); 
        assertEquals('x', aiPlayer.getSymbol());

        // Restore original System.in
        restoreSystemIn();
    }
    
    @Test
    void testCreateAI3() {
    	
        provideInputStream("1\n");

        Player aiPlayer = PlayerFactory.createAI(2, 'x');

        assertTrue(aiPlayer instanceof GamaGo); 
        assertEquals('o', aiPlayer.getSymbol());

        // Restore original System.in
        restoreSystemIn();
    }
        
    @Test
    void testCreateAI4() {
    	
        provideInputStream("3\n2\n");

        Player aiPlayer = PlayerFactory.createAI(2, 'x');

        assertTrue(aiPlayer instanceof BetaGo); 
        assertEquals('o', aiPlayer.getSymbol());

        // Restore original System.in
        restoreSystemIn();
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
