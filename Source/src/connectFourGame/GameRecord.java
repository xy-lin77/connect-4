package connectFourGame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.IndexOutOfRangeException;
import exception.NoGameRecordException;
import systemClass.ProjectScanner;

public class GameRecord {
    private static ArrayList<Game> gameList;

    private GameRecord() {
        gameList = new ArrayList<Game>();
    }

    private static GameRecord instance = new GameRecord();

    public static GameRecord getInstance() {
        return instance;
    }

    public void addGame() {
        Game aGame = new Game();
        gameList.add(aGame);
    }

    public void viewRecord() throws NoGameRecordException {
        if (gameList.size() == 0) {
            throw new NoGameRecordException();
        }

        System.out.println();
        System.out.println("+---------------+----------+----------------------------+");
        System.out.println("| Records Number|   Time   | Player1(?) vs. Player2(?)  |");
        System.out.println("+---------------+----------+----------------------------+");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.print("|       " + (i+1) + "       | ");
            System.out.println(gameList.get(i).toString() + " |");
        }
        System.out.println("+---------------+----------+----------------------------+");

        while (true) {
            System.out.println("Which game record would you like to check?");
            System.out.print("Enter the corresponding reference number or enter 0 to exit: ");
            int input;
            
            Scanner scanner = ProjectScanner.getInstance();
            try
            {
                input = scanner.nextInt();
                if (input == 0) {
                    break;
                } else if (input > gameList.size() || input < 0) {
                    throw new IndexOutOfRangeException(1, gameList.size());
                } else {
                    gameList.get(input - 1).viewGame();
                    break;
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
    }
}
