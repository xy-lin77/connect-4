package connectFourGame;

public interface Referee {

    public static boolean isValidMove(Grid grid, int x) {
        if (x < 0 || x > 6) {
            System.out.println("Input is out of range 0-6. Please try again.");
            return false;
        }
        if (grid.getGrid()[0][x] != ' ') {
            System.out.println("Column " + x + " is already full. Please try again.");
            return false;
        }
        return true;
    }

    public static boolean isFullGrid(Grid grid) {
        for (int i = 0; i < 7; i++) {
            if (grid.getGrid()[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }
    
    private static int[] countHorizontal(char symbol, int x, int y, Grid grid) {
        int count = 0;
        int px = x;
        int potential = 0;
        while (px - 1 >= 0 && grid.getGrid()[y][px - 1] == symbol) {
            count++;
            px--;
        }
        if (px - 1 >= 0 && grid.getGrid()[y][px - 1] == ' ')
        {
            potential++;
        }
        px = x;
        while (px + 1 <= 6 && grid.getGrid()[y][px + 1] == symbol) {
            count++;
            px++;
        }
        if (px + 1 <= 6 && grid.getGrid()[y][px + 1] == ' ')
        {
            potential++;
        }
        int[] result = {count, potential};
        return result;
    }

    private static int[] countVertical(char symbol, int x, int y, Grid grid) {
        int count = 0;
        int py = y;
        int potential = 0;
        while (py - 1 >= 0 && grid.getGrid()[py - 1][x] == symbol) {
            count++;
            py--;
        }
        if (py - 1 >= 0 && grid.getGrid()[py - 1][x] == ' ')
        {
            potential++;
        }
        py = y;
        while (py + 1 <= 5 && grid.getGrid()[py + 1][x] == symbol) {
            count++;
            py++;
        }
        int[] result = {count, potential};
        return result;
    }

    private static int[] countLeftTopToRightBottom(char symbol, int x, int y, Grid grid) {
        int count = 0;
        int px = x;
        int py = y;
        int potential = 0;
        while (px - 1 >= 0 && py - 1 >= 0 && grid.getGrid()[py - 1][px - 1] == symbol) {
            count++;
            px--;
            py--;
        }
        if (px - 1 >= 0 && py - 1 >= 0 && grid.getGrid()[py - 1][px - 1] == ' ')
        {
            potential++;
        }
        px = x;
        py = y;
        while (px + 1 <= 6 && py + 1 <= 5 && grid.getGrid()[py + 1][px + 1] == symbol) {
            count++;
            px++;
            py++;
        }
        if (px + 1 <= 6 && py + 1 <= 5 && grid.getGrid()[py + 1][px + 1] == ' ')
        {
            potential++;
        }
        int[] result = {count, potential};
        return result;
    }

    private static int[] countLeftBottomtoRightTop(char symbol, int x, int y, Grid grid) {
        int count = 0;
        int px = x;
        int py = y;
        int potential = 0;
        while (px - 1 >= 0 && py + 1 <= 5 && grid.getGrid()[py + 1][px - 1] == symbol) {
            count++;
            px--;
            py++;
        }
        if (px - 1 >= 0 && py + 1 <= 5 && grid.getGrid()[py + 1][px - 1] == ' ')
        {
            potential++;
        }
        px = x;
        py = y;
        while (px + 1 <= 6 && py - 1 >= 0 && grid.getGrid()[py - 1][px + 1] == symbol) {
            count++;
            px++;
            py--;
        }
        if (px + 1 <= 6 && py - 1 >= 0 && grid.getGrid()[py - 1][px + 1] == ' ')
        {
            potential++;
        }
        int[] result = {count, potential};
        return result;
    }

    // Check if 4 pieces are connected and return the symbol ('o'/'x'/...) of the
    // winner
    public static char checkWinner(Grid grid, int x, int y, boolean output) {
        char symbol = grid.getGrid()[y][x];

        // Check Horizontal Adjacency
        if (countHorizontal(symbol, x, y, grid)[0] >= 3) {
            if(output)
                System.out.println("'" + symbol + "' has got 4 Horizontal Adjacencies.");
            return symbol;
        }

        // Check Vertical Adjacency
        if (countVertical(symbol, x, y, grid)[0] >= 3) {
            if(output)
                System.out.println("'" + symbol + "' has got 4 Vertical Adjacencies.");
            return symbol;
        }

        // Check Left-Top to Right-Bottom Diagonal Adjacency
        if (countLeftTopToRightBottom(symbol, x, y, grid)[0] >= 3) {
            if(output)
                System.out.println("'" + symbol + "' has got 4 Left-Top to Right-Bottom Diagonal Adjacencies.");
            return symbol;
        }

        // Check Left-Bottom to Right-Top Diagonal Adjacency
        if (countLeftBottomtoRightTop(symbol, x, y, grid)[0] >= 3) {
            if(output)
                System.out.println("'" + symbol + "' has got 4 Left-Bottom to Right-Top Diagonal Adjacencies.");
            return symbol;
        }

        // No 4 Adjacent Symbols Found
        return ' ';
    }

    //Find out how many pieces can be connected after this step
    //For AI's evaluate
    public static int[] findLongest(Grid grid, int x, int y) {
        char symbol = grid.getGrid()[y][x];
        int[] result = {0, 0};
        int[] temp = new int[2];

        // Check Horizontal Adjacency
        temp = countHorizontal(symbol, x, y, grid);
        if(temp[0] == 2)
            result[0] += temp[1];
        else if(temp[0] == 1 && temp[1] == 2)
            result[1]++;

        // Check Vertical Adjacency
        temp = countVertical(symbol, x, y, grid);
        if(temp[0] == 2)
            result[0] += temp[1];

        // Check Left-Top to Right-Bottom Diagonal Adjacency
        temp = countLeftTopToRightBottom(symbol, x, y, grid);
        if(temp[0] == 2)
            result[0] += temp[1];
        else if(temp[0] == 1 && temp[1] == 2)
            result[1]++;

        // Check Left-Bottom to Right-Top Diagonal Adjacency
        temp = countLeftBottomtoRightTop(symbol, x, y, grid);
        if(temp[0] == 2)
            result[0] += temp[1];
        else if(temp[0] == 1 && temp[1] == 2)
            result[1]++;

        return result;
    }
    
    // traverse the whole grid to check whether the game is over
    public static char TraverseGrid(Grid grid) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid.getGrid()[i][j] != ' ') {
                    char symbol = grid.getGrid()[i][j];
                    if (anyWinner(symbol, j, i, grid)){
                    	return symbol;
                    }
                }
            }
        }
        return ' ';
    }
    private static boolean anyWinner(char symbol, int j, int i, Grid grid) {
    	if (countHorizontal(symbol, j, i, grid)[0] >= 3 
    			|| countVertical(symbol, j, i, grid)[0] >= 3
                || countLeftBottomtoRightTop(symbol, j, i, grid)[0] >= 3
                || countLeftTopToRightBottom(symbol, j, i, grid)[0] >= 3) {
            return true;
    	}
    	return false;
    }
}
