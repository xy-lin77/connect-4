package connectFourGame;

public class CmdMove {
    int x, y;
    Grid grid;
    char symbol;

    public CmdMove(char asymbol, Grid agrid, int ax) {
        symbol = asymbol;
        grid = agrid;
        x = ax;
        y = agrid.update(ax, asymbol);
    }

    public void undoOneCmd() {
        grid.withdraw(x);
    }

    public void redoOneCmd() {
        grid.update(x, symbol);
    }

    public Move getMove() {
        return new Move(x, y, symbol);
    }
}
