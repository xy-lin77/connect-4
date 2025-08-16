package connectFourGame;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.IndexOutOfRangeException;
import systemClass.ProjectScanner;

public interface PlayerFactory {
    public static Player createPlayer(int pos) {
        Scanner scanner = ProjectScanner.getInstance();
        String name;
        char chess;
        System.out.print(String.format("Please type in Player %d's name: ", pos));
        scanner.nextLine(); // clear the \n because scanner.next() will not take \n
        name = scanner.nextLine();
        System.out.print(String.format("Please type in a char represent to %s's chess (e.g. 'o', 'x'): ", name));
        chess = scanner.next().charAt(0);
        return new HumanPlayer(name, chess);
    }

    public static Player createAI(int pos, char opponent) {
        Scanner scanner = ProjectScanner.getInstance();
        String AIname;
        System.out.println("Please select the AI player you want to play against: ");
        System.out.println("   1. GamaGo (Easy) ");
        System.out.println("   2. BetaGO (Hard) ");
        System.out.print("Enter the index number to indicate the AI Model to play against: ");
        int input = 0;
        boolean validIndex = false;
        while (!validIndex) {
            try{
                input = scanner.nextInt();
                if(input == 1 || input == 2)
                {
                    validIndex = true;
                }
                else
                {
                    throw new IndexOutOfRangeException(1, 2);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid integer! ");
                scanner.next();
            }
            catch (IndexOutOfRangeException e)
            {
                System.out.println(e.getMessage());
            }
            
        }
        if (input == 1) {
            AIname = "GamaGo";
        } else {
            AIname = "BetaGo";
        }

        Player result;
        System.out.println(String.format("Please type in Player " + pos + "'s name: " + AIname));
        if (opponent != 'x') {
            if (AIname.equals("GamaGo")) {
                result = new GamaGo('x');
            } else {
                result = new BetaGo('x');
            }
            System.out.println(
                    String.format("Please type in a char to represent " + AIname + "'s chess (e.g. 'o', 'x'): x"));
        } else {
            if (AIname.equals("GamaGo")) {
                result = new GamaGo('o');
            } else {
                result = new BetaGo('o');
            }
            System.out.println(
                    String.format("Please type in a char to represent " + AIname + "'s chess (e.g. 'o', 'x'): o"));
        }
        return result;
    }
    
}
