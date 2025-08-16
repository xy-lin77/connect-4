package connectFourGame;

import java.util.ArrayList;
import java.util.List;

public class BetaGo extends Player implements AIPlayer {
    public BetaGo(char symbol) {
        super("BetaGo", symbol);
    }

    @Override
    public int makeMove(Grid grid) {
        System.out.println("BetaGo, the AI player, is thinking...");
        int greedy = greedy(grid);
        if (greedy != -1) {
            return greedy;
        } else {
            return MiniMax(grid, 8, 0, -100000, 100000, getSymbol(), this.switchPlayer().getSymbol())[1];
        }
    }

    private static List<Integer> validMoves(Grid state) {
        List<Integer> res = new ArrayList<>();
        int[] considerSequence = { 3, 2, 4, 1, 5, 0, 6 };
        for (int i : considerSequence) {
            if (state.getGrid()[0][i] == ' ') {
                res.add(i);
            }
        }
        return res;
    }

    private int greedyWin(Grid grid) {
        char ai = getSymbol();
        for (int x : validMoves(grid)) {
            int y = YCoordinate(grid, x);
            Grid newGrid = grid.clone();
            newGrid.getGrid()[y][x] = ai;
            if (Referee.checkWinner(newGrid, x, y, false) == ai) {
                return x;
            }
        }
        return -1;
    }

    private int greedyDefend(Grid grid) {
        char human = this.switchPlayer().getSymbol();
        for (int x : validMoves(grid)) {
            int y = YCoordinate(grid, x);
            Grid newGrid = grid.clone();
            newGrid.getGrid()[y][x] = human;
            if (Referee.checkWinner(newGrid, x, y, false) == human) {
                return x;
            }
        }
        return -1;
    }

    private int greedy(Grid grid) {
        if (greedyWin(grid) != -1) {
            return greedyWin(grid);
        } else if (greedyDefend(grid) != -1) {
            return greedyDefend(grid);
        }
        return -1;
    }

    private static Grid newMove(Grid state, char symbol, int x, int y) {
        Grid newState = state.clone();
        newState.getGrid()[y][x] = symbol;
        return newState;
    }

    private static int evaluate(Grid grid, char symbol) {

        char winner = Referee.TraverseGrid(grid);
        if (winner != ' ') {
            return -1000;
        }
        return 0;
    }

    private int[] MiniMax(Grid state, int maxDepth, int currentDepth, int a, int b, char turn, char oppo) {

        // Check if the recursion is done
        if (Referee.TraverseGrid(state) != ' ' || currentDepth == maxDepth || Referee.isFullGrid(state)) {
            // System.out.println("depth: " + currentDepth + " score: " + evaluate(state,
            // player.getSymbol()));
            return new int[] { evaluate(state, turn), -1 };
        }

        // Bubble up value from below
        int bestMove = -1;
        int bestScore = -100000;

        // Go through each possible move
        for (int x : validMoves(state)) {
            int y = YCoordinate(state, x);
            Grid newState = newMove(state, turn, x, y);

            // Recurse
            int[] recursion = MiniMax(newState, maxDepth, currentDepth + 1, -b, -Math.max(a, bestScore), oppo, turn);
            int currentScore = -recursion[0];

            // Update the best score
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestMove = x;

                // Prunning
                if (bestScore >= b) {
                    return new int[] { bestScore, bestMove };
                }
            }
        }
        return new int[] { bestScore, bestMove };
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
