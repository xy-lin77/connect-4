package connectFourGame;

public class Grid implements Cloneable {

    private char[][] grid;

    public char[][] getGrid() {
        return grid;
    }

    // Create a 6x8 empty board, let ' ' represent empty cell
    public Grid() {
        grid = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public int update(int x, char symbol) {
    	int result = 0;
        for (int i = 5; i > 0; i--) {
            if (grid[i][x] == ' ') {
                result = i;
                break;
            }
        }
        grid[result][x] = symbol;
        return result;
    }

    public void withdraw(int x) {
    	int result = 5;
        for (int i = 0; i < 5; i++) {
            if (grid[i][x] != ' ') {
            	result = i;
                break;
            }
        }
        grid[result][x] = ' ';
    }

    public void printGrid() {
        System.out.print("  ");
        for (int i = 0; i < 14; i++) {
            System.out.print('-');
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) {
                System.out.print("" + grid[i][j] + ' ');
            }
            System.out.println('|');
        }
        System.out.print("  ");
        for (int i = 0; i < 14; i++) {
            System.out.print('-');
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 7; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Override
    public Grid clone() {
        try {
            Grid clonedGrid = (Grid) super.clone();
            clonedGrid.grid = new char[6][7];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    clonedGrid.grid[i][j] = this.grid[i][j];
                }
            }
            return clonedGrid;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
