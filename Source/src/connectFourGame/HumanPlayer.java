package connectFourGame;

import systemClass.*;

import java.util.InputMismatchException;
//import java.util.Scanner;

import exception.CanNotRedoException;
import exception.CanNotUndoException;
import exception.NoMoreSuggestionException;
import exception.NoMoreUndoException;

public class HumanPlayer extends Player {
    private int allowedUndo;
    private int allowedHelp;
    //private static Scanner scanner = ProjectScanner.getInstance();

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
        allowedUndo = Integer.parseInt(SysConfiguration.getSysConfig("allowedUndo"));
        allowedHelp = Integer.parseInt(SysConfiguration.getSysConfig("allowedHelp"));
    }

    private void inputPrompt() {
        System.out.println(getName() + ", it's your turn.");
        System.out.println("Please select an option from the following list: ");
        System.out.println("    0-6: Place A New Piece At The Input Position ");
        System.out.println("     -1: To Undo The Last Step (" + allowedUndo + " more times) ");
        System.out.println("     -2: Get AI suggestion (" + allowedHelp + " more times) ");
        System.out.println("     -3: Redo The Last Step ");
        System.out.print("Please enter your selection: ");
    }

    public void makeUndo(Grid grid, CmdList aCmd) throws CanNotUndoException, NoMoreUndoException {
        if (!aCmd.allowUndo()) {
            throw new CanNotUndoException();
        } else if (allowedUndo == 0) {
            throw new NoMoreUndoException();
        } else {
            aCmd.undocmd();
            aCmd.undocmd();
            allowedUndo--;
            grid.printGrid();
        }
    }

    public void makeRedo(Grid grid, CmdList aCmd) throws CanNotRedoException{
        if(!aCmd.allowRedo()){
            throw new CanNotRedoException();
        }
        aCmd.redocmd();
        aCmd.redocmd();
        allowedUndo++;
        grid.printGrid();
    }

    public void makeSuggestion(Grid grid) throws NoMoreSuggestionException {
        if (allowedHelp == 0) {
            throw new NoMoreSuggestionException();
        } else {
            char opponent = switchPlayer().getSymbol();
            AIPlayer BetaGO = new BetaGo(getSymbol());
            System.out.println("BetaGO's suggestion: "
                    + Integer.toString(BetaGO.InputAI(grid, getSymbol(), opponent)));
            AIPlayer GamaGo = new GamaGo(getSymbol());
            System.out.println("GamaGo's suggestion: "
                    + Integer.toString(GamaGo.InputAI(grid, getSymbol(), opponent)));
            //System.out.println(Player.playersList.size());
            allowedHelp--;
        }
    }

    @Override
    public int makeMove(Grid grid) {
        int x;

        while(true)
        {
            try
            {
                inputPrompt();
                x = ProjectScanner.getInstance().nextInt();
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter an integer number! ");
                ProjectScanner.getInstance().next();
            }

        }

        return x;
    }
}
