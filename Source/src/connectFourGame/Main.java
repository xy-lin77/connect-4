package connectFourGame;

import systemClass.*;

import java.util.Scanner;

import exception.NoGameRecordException;

public class Main {
	private Main() {}
    public static void main(String[] args) {

        Scanner scanner = ProjectScanner.getInstance();
        boolean exit = false;

        while (!exit) {
        	System.out.println();
            System.out.println("Please select an option from the following list: ");
            System.out.println("    1: Start A New Game ");
            System.out.println("    2: Review Game Record ");
            System.out.println("    3: Settings ");
            System.out.println("    exit: Exit the Program ");
            System.out.print("Please enter your selection: ");
            String func = scanner.next();
            switch (func) {
                case "1":
                    GameRecord.getInstance().addGame();
                    break;
                case "2":
                    try {
                        GameRecord.getInstance().viewRecord();
                    } catch (NoGameRecordException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    SysConfiguration.changeSysConfig();
                    break;
                case "exit":
                    System.out.println("Are you sure to exit? Your game record will be deleted. ");
                    System.out.println("Please press 1 to confirm. Others to cancel. ");
                    String ensure = scanner.next();
                    if (ensure.equals("1")) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Please enter a valid index number! ");
                    break;
            }
        }
        ProjectScanner.closeScanner();
    }
}