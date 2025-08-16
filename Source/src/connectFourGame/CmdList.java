package connectFourGame;

import systemClass.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CmdList implements Cloneable {
    private ArrayList<CmdMove> undo;
    private ArrayList<CmdMove> redo;

    public CmdList() {
        undo = new ArrayList<>();
        redo = new ArrayList<>();
    }

    protected void addundo(CmdMove acmd) {
        undo.add(acmd);
    }

    protected void addredo(CmdMove acmd) {
        redo.add(acmd);
    }

    public boolean undocmd() {
        undo.get(undo.size() - 1).undoOneCmd();
        addredo(undo.get(undo.size() - 1));
        undo.remove(undo.size() - 1);
        return true;
    }

    public boolean redocmd() {
        redo.get(redo.size() - 1).redoOneCmd();
        addundo(redo.get(redo.size() - 1));
        redo.remove(redo.size() - 1);
        return true;
    }

    public CmdMove createCmdMove(char symbol, Grid agrid, int ax)
    {
        CmdMove cmdRealMove = new CmdMove(symbol, agrid, ax);
        addundo(cmdRealMove);
        clearRedo();
        return cmdRealMove;
    }

    public boolean allowUndo() {
        if (undo.size() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean allowRedo() {
        if (redo.size() < 2) {
            return false;
        } else {
            return true;
        }
    }

    protected void clearRedo() {
        redo = new ArrayList<>();
    }

    public void traverse() {
        ArrayList<CmdMove> list = undo;
        Grid grid = new Grid();
        Scanner scanner = ProjectScanner.getInstance();
        grid.printGrid();
        int input;
        int index = 0;
        while (true) {
            System.out.print("Enter 1 for the last move, 2 for the next move, 0 for AI suggestion for the next move, -1 for exit: ");
            try{
                input = scanner.nextInt();
                if (input == 1) {
                    if (index == 0) {
                        System.out.println("It's already the first move. ");
                    } else {
                        index--;
                        grid.withdraw(list.get(index).getMove().getX());
                        System.out.println();
                        grid.printGrid();
                    }
                } else if (input == 2) {
                    if (index == list.size()) {
                        System.out.println("It's already the last move. ");
                    } else {
                        grid.update(list.get(index).getMove().getX(), list.get(index).getMove().getSymbol());
                        System.out.println();
                        grid.printGrid();
                        index++;
                    }
                } else if (input == 0) {
                    if(index == list.size())
                        System.out.println("It's already the last move. ");
                    else
                    {
                        char turn = list.get(index).getMove().getSymbol();
                        char opponent;
                        if (index == 0) {
                            opponent = list.get(index + 1).getMove().getSymbol();
                        } else {
                            opponent = list.get(index - 1).getMove().getSymbol();
                        }
                        System.out.println();
                        AIPlayer BetaGO = new BetaGo(turn);
                        System.out.println("BetaGo's suggestion: " + Integer.toString(BetaGO.InputAI(grid, turn, opponent)));
                        AIPlayer GamaGo = new GamaGo(turn);
                        System.out.println("GamaGo's suggestion: " + Integer.toString(GamaGo.InputAI(grid, turn, opponent)));
                    }
                } else if (input == -1) {
                    break;
                }
            
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid Integer! ");
                scanner.next();
            }
        }
    }
}