package main;


import lenz.htw.sawhian.Move;

import java.util.List;

public class Heuristic {

    /**
     *
     * @param board
     * @param moves
     * @param p
     * @return utility function:
     */
    public static float evaluate(Board board,
                               List<Move> moves,
                               int p) {

        float score = 0.0f;
        score += 8.5 * board.finished[p];
        score += 2 * getGoodMoves(moves);
        score -= 1.0 * weightedDistance(board.board, p);
        score -= 1.5 * board.remaining[p];
        return score;
    }

    /**
     *
     * @param board
     * @param p
     * @return distance to finish line for every token
     */
    private static double weightedDistance(int[][] board, int p) {
        int distance = 0;
        for (int y = 0; y < 7; y++) {
            for(int x = 0; x < 7; x++) {
                if(board[y][x] == p)
                    distance += (7 - y);
            }

        }
        return distance;
    }

    /**
     *
     * @param moves
     * @return moves that are closer to the finish (y>5)
     */
    private static double getGoodMoves(List<Move> moves) {
        return moves
                .stream()
                .filter(m -> m.y > 5)
                .count();
    }
}
