package testSystemClass;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;
import systemClass.ProjectScanner;

public class TestProjectScanner{
	@Test
	public void testSingletonScanner() {
		String testInput = "test input";
        ByteArrayInputStream testIn = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(testIn);
        
        Scanner scanner = ProjectScanner.getInstance();
        String testReceive = scanner.nextLine();
        assertEquals(testInput, testReceive);
        ProjectScanner.closeScanner();
        
        System.setIn(System.in);
	}
}