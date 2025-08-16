package connectFourGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamaGo extends Player implements AIPlayer {
    private int[] weight = {2, 5, 7, 8, 6, 4, 3}; // weight for each column

    public GamaGo(char symbol) {
        super("GamaGo", symbol);
    }

    /**
     * the function to check what case it is and then return the x to make move accordingly
     * check through the following cases:
     * 1. Assume it's the computer's move, check if it can form four in a row
     * 2. Assume it's the player's move, check if it can form four in a row
     * 3. Check if making this move would set up the opponent for a win
     * 4. If the AI places a piece here, there are two places where it can form a four in a row
     * 5. If the player places a piece here, there are two places where they can form a four in a row
     * 6. If the player places a piece here, there is one place where they can form a four in a row and multiple places where they can form a three in a row, which is relatively easy to win 
     * (due to the incomplete algorithm, we need to consider this relatively dangerous situation)
     * 7. Normal case, move to the more likely positions in a normal game
     * 
     * @param grid the grid now
     * @param acmd a command list(not important)
     * @return the move it want
     */
    @Override
    public int makeMove(Grid grid) {
        Grid aGrid = grid.clone();
        System.out.println("GamaGo, the AI player, is thinking...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        int[] potentialMove = new int[7];
        for (int i = 0; i < 7; i++) {
            if (aGrid.getGrid()[0][i] != ' ') {
                potentialMove[i] = -100; // Non-movable cells have the smallest value of -100
                continue;
            }

            // 1. Assume it's the computer's move, check if it can form four in a row
            int temp = checkIfWin(this, aGrid, i);
            if(temp != -1) { return temp; }

            // 2. Assume it's the player's move, check if it can form four in a row
            temp = checkIfWin(this.switchPlayer(), aGrid, i);
            if(temp != -1) { return temp; }

            // 3. Check if making this move would set up the opponent for a win
            if (YCoordinate(aGrid, i) > 0) {
                aGrid.update(i, getSymbol());
                aGrid.update(i, switchPlayer().getSymbol());
                if (Referee.checkWinner(aGrid, i, YCoordinate(aGrid, i) + 1, false) != ' ') {
                    potentialMove[i] = -90; // Set as the second smallest value -90, only move here if necessary
                    aGrid.withdraw(i);
                    aGrid.withdraw(i);
                    continue;
                }
                aGrid.withdraw(i);
                aGrid.withdraw(i);
            }

            // 4. If the AI places a piece here, there are two places where it can form a four in a row
            aGrid.update(i, getSymbol());
            int[] result = Referee.findLongest(aGrid, i, YCoordinate(aGrid, i) + 1);
            if (result[0] == 2) {
                potentialMove[i] = 90; // Very likely to win, try to move here
                aGrid.withdraw(i);
                continue;
            }
            aGrid.withdraw(i);

            // 5. If the player places a piece here, there are two places where they can form a four in a row
            aGrid.update(i, switchPlayer().getSymbol());
            result = Referee.findLongest(aGrid, i, YCoordinate(aGrid, i) + 1);
            if (result[0] == 2) {
                potentialMove[i] = 80; // The opponent is very likely to win if they move here, try to move here
                aGrid.withdraw(i);
                continue;
            }
            aGrid.withdraw(i);

            // 6. If the player places a piece here, there is one place where they can form
            // a four in a row and multiple places where they can form a three in a row,
            // which is relatively easy to win
            aGrid.update(i, switchPlayer().getSymbol());
            result = Referee.findLongest(aGrid, i, YCoordinate(aGrid, i) + 1);
            if (result[0] == 1 && result[1] > 1) {
                potentialMove[i] = 60; // Moving here will put me at some risk, try to move here
                aGrid.withdraw(i);
                continue;
            }
            aGrid.withdraw(i);

            // 7. In other cases, move to the more likely positions in a normal game
            aGrid.update(i, getSymbol());
            result = Referee.findLongest(aGrid, i, YCoordinate(aGrid, i) + 1);
            potentialMove[i] = result[0] * 30 + result[1] * 10; // Evaluate this position
            aGrid.withdraw(i);
        }

        // Centralize processing, give slightly higher weight to the middle columns so
        // that they can be selected first among the same level, but not across levels
        // (ensure the weight is <10)
        for(int i = 0; i < 7; i++)
        {
            potentialMove[i] += weight[i];
            //System.out.println(potentialMove[i]);
        }

        int max = potentialMove[0]; // Save the maximum value
        List<Integer> maxIndices = new ArrayList<>(); // Save all the indices of the maximum value
        for (int i = 0; i < potentialMove.length; i++) {
            if (potentialMove[i] > max) {
                max = potentialMove[i];
                maxIndices.clear(); // Clear the previous maximum value indices
                maxIndices.add(i);
            } else if (potentialMove[i] == max) {
                maxIndices.add(i); // There are multiple maximum values, add the indices to the list
            }
        }

        // Randomly select an index from the list of maximum value indices
        Random random = new Random();
        int randomIndex = maxIndices.get(random.nextInt(maxIndices.size()));
        // System.out.println("The evaluate value: " + max);

        return randomIndex;
    }
    
    //check if player will win if go there in next move
    private int checkIfWin(Player aPlayer, Grid aGrid, int x)
    {
        int result;
        aGrid.update(x, aPlayer.getSymbol());
        if (Referee.checkWinner(aGrid, x, YCoordinate(aGrid, x) + 1, false) != ' ') {
            result = x;
        }
        else
        {
            result = -1;
        }
        aGrid.withdraw(x);
        return result;
    }

    @Override
    public int InputAI(Grid grid, char turn, char opponent) {
        this.setSymbol(turn);
        Player fakeTest = new HumanPlayer("fakeTest", opponent);
        int result = makeMove(grid);
        fakeTest.removePlayer();
        this.removePlayer();
        return result;
    }

}