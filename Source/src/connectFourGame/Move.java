package connectFourGame;

public class Move {
    // a object to pass the information of a move between classes
    private int x;
    private int y;
    private char symbol;

    public Move(int ax, int ay, char asymbol) {
        x = ax;
        y = ay;
        symbol = asymbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }
}
