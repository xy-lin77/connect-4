package systemClass;

import java.util.Scanner;

// to make sure there will be only one scanner
public class ProjectScanner {
    private static Scanner scanner = new Scanner(System.in);

    private ProjectScanner() {}
    
    public static Scanner getInstance() {
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
