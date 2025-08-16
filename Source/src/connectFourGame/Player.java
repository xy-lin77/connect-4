package connectFourGame;

import java.util.ArrayList;

public abstract class Player {
    private String name;
    private char symbol;
    static ArrayList<Player> playersList = new ArrayList<>();

    public Player(String aname, char asymbol) {
        this.name = aname;
        this.symbol = asymbol;
        // timer = new PlayerTimer();
        playersList.add(this);
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char asymbol) {
        symbol = asymbol;
    }

    public Player switchPlayer() {
    	Player result = playersList.get(0);
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i) == this) {
                if (i % 2 == 0) {
                    result = playersList.get(i + 1);
                } else {
                    result = playersList.get(i - 1);
                }
            }
        }
        return result;
    }

    public void removePlayer() {
        playersList.remove(this);
    }

    public int YCoordinate(Grid grid, int x) {
        for (int i = 5; i >= 0; i--) {
            if (grid.getGrid()[i][x] == ' ') {
                return i;
            }
        }
        return 0;
    }

    public abstract int makeMove(Grid grid);
}
