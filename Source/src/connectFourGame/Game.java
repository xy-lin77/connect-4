package connectFourGame;

import systemClass.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.CanNotRedoException;
import exception.CanNotUndoException;
import exception.NoMoreSuggestionException;
import exception.NoMoreUndoException;

public class Game {
    private CmdList cmd;
    private Grid grid;
    private Player player1;
    private Player player2;
    private boolean playerOnesTurn; // who goes next. true for player1 and false for player2
    private LocalTime currentTime;
    Scanner scanner;

    public Game() {
        cmd = new CmdList();
        grid = new Grid();
        playerOnesTurn = true;
        currentTime = LocalTime.now();
        scanner = ProjectScanner.getInstance();
        this.start();
    }

    private void start() {
        int gameMode = 0;
        while (gameMode < 1 
        		|| gameMode > 3) {
            System.out.println("Please select game mode: ");
            System.out.println("   1. Human vs Human ");
            System.out.println("   2. Human vs AI ");
            System.out.println("   3. AI vs Human ");
            System.out.print("Enter the index number to indicate the game mode: ");
            try{
                gameMode = scanner.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid integer number!");
                scanner.next();
            }
        }
        createPlayers(gameMode);
        System.out.println();
        System.out.println("New game started! The symbol for " + player1.getName() + " is '" + player1.getSymbol()
                + "', for " + player2.getName() + " is '" + player2.getSymbol() + "'.");
        grid.printGrid();

        gameLoop();
    }

    private void createPlayers(int gameMode) {
    	if (gameMode == 1) {
            player1 = PlayerFactory.createPlayer(1);
            player2 = PlayerFactory.createPlayer(2);
            while (player1.getSymbol() == player2.getSymbol() || player1.getName().equals(player2.getName())) {
                System.out.println(
                        "The two players cannot have the same name or symbol! Please set the second player's the name and symbol again.");
                player2.removePlayer();
                player2 = PlayerFactory.createPlayer(2);
            }
        } else if (gameMode == 2) {
            player1 = PlayerFactory.createPlayer(1);
            player2 = PlayerFactory.createAI(2, player1.getSymbol());
        } else {
            player1 = PlayerFactory.createAI(1, 'o');
            player2 = PlayerFactory.createPlayer(2);
            while (player2.getSymbol() == player1.getSymbol()) {
                System.out.println(
                        "The two players cannot have the same name or symbol! Please set the second player's the name and symbol again.");
                player2.removePlayer();
                player2 = PlayerFactory.createPlayer(2);
            }
        }
    }
    
    private void gameLoop() {

        // Scanner scanner = new Scanner(System.in);
        // int x, y;
        Player currentPlayer;
        // char symbol;
        CmdMove latestMove;

        do {
            currentPlayer = playerOnesTurn ? player1 : player2;
            // symbol = currentPlayer.getSymbol();

            int x = -3;
            do
            {
                try{
                    x = currentPlayer.makeMove(grid);

                    if(x == -1)
                    {
                        ((HumanPlayer)currentPlayer).makeUndo(grid, cmd);
                    }
                    else if(x == -2)
                    {
                        ((HumanPlayer)currentPlayer).makeSuggestion(grid);
                    }
                    else if(x == -3)
                    {
                        ((HumanPlayer)currentPlayer).makeRedo(grid, cmd);
                    }
                }
                catch (CanNotUndoException e1)
                {
                    System.out.println(e1.getMessage());
                }
                catch (CanNotRedoException e1)
                {
                    System.out.println(e1.getMessage());
                }
                catch (NoMoreUndoException e2)
                {
                    System.out.println(e2.getMessage());
                }
                catch (NoMoreSuggestionException e3)
                {
                    System.out.println(e3.getMessage());
                }
            }while(x == -1 || x == -2 || x == -3 || !Referee.isValidMove(grid, x));

            latestMove = cmd.createCmdMove(currentPlayer.getSymbol(), grid, x);

            // x = currentPlayer.makeMove(grid, scanner);
            // y = grid.update(x, symbol);

            grid.printGrid();
            playerOnesTurn = !playerOnesTurn;
        } while (!Referee.isFullGrid(grid)
                && Referee.checkWinner(grid, latestMove.getMove().getX(), latestMove.getMove().getY(), true) == ' ');

        System.out.println("Congrats to " + currentPlayer.getName() + " on the win!");
        // scanner.close();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter) + " | " +
                player1.getName() + "(" + player1.getSymbol() + ") vs. " +
                player2.getName() + "(" + player2.getSymbol() + ")";
    }

    public void viewGame() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println();
        System.out.println("You are now traversing the game in " + currentTime.format(formatter) + " between" +
                " Player1, " + player1.getName() + "(" + player1.getSymbol() + "), " +
                " and Player2, " + player2.getName() + "(" + player2.getSymbol() + "). ");

        cmd.traverse();
    }
}
