package testSystemClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import systemClass.ProjectScanner;
import systemClass.SysConfiguration;

public class TestSysConfiguration {
	
	@Test
	public void testGetSysConfig1() {
        String result = SysConfiguration.getSysConfig("abcd");
        assertEquals("", result);
	}
	
	@Test
	public void testGetSysConfig2() {
        String result = SysConfiguration.getSysConfig("allowedUndo");
        assertEquals("1", result);
	}
	
	@Test
	public void testChangeSysConfig1() {
		provideInputStream("abc\n-1\n5\n0\n");
		
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));
	         
	        SysConfiguration.changeSysConfig();
    		
	        String output = outputStream.toString();
	        assertEquals("\r\n"
	        		+ "+-------+-------------+-------+\r\n"
	        		+ "| Index | Setting     | Value |\r\n"
	        		+ "+-------+-------------+-------+  \r\n"
	        		+ "|   1   | allowedUndo |   1   |  \r\n"
	        		+ "|   2   | allowedHelp |   2   |  \r\n"
	        		+ "+-------+-------------+-------+  \r\n"
	        		+ "Which setting would you like to change the quantity for?\r\n"
	        		+ "Enter the corresponding index number or enter 0 to exit: Please enter a valid integer! \r\n"
	        		+ "Enter the corresponding index number or enter 0 to exit: Please enter a valid index! The index should be from 1 to 2.\r\n"
	        		+ "Enter the corresponding index number or enter 0 to exit: Please enter a valid index! The index should be from 1 to 2.\r\n"
	        		+ "Enter the corresponding index number or enter 0 to exit: ", output);
    	} 
    	finally {
            System.setOut(originalOut);
            restoreSystemIn();
	    }
	}
	
	@Test
	public void testChangeSysConfig2() {
		provideInputStream("1\n-1\n1\n");
		
		PrintStream originalOut = System.out;
    	try {
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outputStream));
	         
	        SysConfiguration.changeSysConfig();
    		
	        String output = outputStream.toString();
	        assertEquals("\r\n"
	        		+ "+-------+-------------+-------+\r\n"
	        		+ "| Index | Setting     | Value |\r\n"
	        		+ "+-------+-------------+-------+  \r\n"
	        		+ "|   1   | allowedUndo |   1   |  \r\n"
	        		+ "|   2   | allowedHelp |   2   |  \r\n"
	        		+ "+-------+-------------+-------+  \r\n"
	        		+ "Which setting would you like to change the quantity for?\r\n"
	        		+ "Enter the corresponding index number or enter 0 to exit: What value do you want to change to? \r\n"
	        		+ "Please enter a non-negative integer! \r\n"
	        		+ "Change Successfully! Change allowedUndo to 1\r\n", output);
    	} 
    	finally {
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