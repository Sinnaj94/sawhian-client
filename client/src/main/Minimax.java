package main;

import lenz.htw.sawhian.Move;

import java.util.ArrayList;
import java.util.List;

public class Minimax {

    private final int depth;
    private final Board board;

    Minimax(int depth, Board board) {
        this.depth = depth;
        this.board = board;
    }

    /**
     *
     * @param moves
     * @param p
     * @param depth
     * @param alpha
     * @param beta
     * @return
     */
    private float max(List<Move> moves,
                      int p,
                      int depth,
                      float alpha,
                      float beta) {
        if (depth > this.depth) {
            return Heuristic.evaluate(board, moves, p);
        }

        List<Move> possible = Utils.generateMoves(board, p);
        if (possible.size() == 0) return 0;

        float score;

        float bestValue = Float.NEGATIVE_INFINITY;
        for (Move move : possible) {
            moves.add(move);
            score = min(moves, p < 3 ? p + 1 : 0, depth + 1, alpha, beta);
            moves.remove(moves.size() - 1);

            if (score > bestValue) {
                bestValue = score;
            }

            if (bestValue > alpha) {
                alpha = bestValue;
            }
            if (alpha >= beta) {
                break;
            }
        }

        return bestValue;
    }

    /**
     *
     * @param moves
     * @param p
     * @param depth
     * @param alpha
     * @param beta
     * @return
     */
    private float min(List<Move> moves,
                      int p,
                      int depth,
                      float alpha,
                      float beta) {
        if (depth > this.depth) {
            return Heuristic.evaluate(board, moves, p);
        }

        List<Move> possible = Utils.generateMoves(board, p);
        if (possible.size() == 0) return 0;

        float score;

        float bestValue = Float.POSITIVE_INFINITY;
        for (Move move : possible) {
            moves.add(move);
            // TODO: board.integrate?
            score = max(moves, p < 3 ? p + 1 : 0, depth + 1, alpha, beta);
            moves.remove(moves.size() - 1);

            if (score < bestValue) {
                bestValue = score;
            }

            if (bestValue < beta) {
                beta = bestValue;
            }
            if (alpha >= beta) {
                break;
            }
        }

        return bestValue;
    }

    /**
     *
     * @param p
     * @return best possible move by using alpha-beta pruning
     */
     Move nextMove(int p) {
        List<Move> possible = Utils.generateMoves(board, p);
        float[] score = new float[possible.size()];
        Thread[] thread = new Thread[score.length];

        float bestValue = Float.NEGATIVE_INFINITY;
        Move bestMove = possible.get(0);

        for (int i = 0; i < score.length; ++i) {
            int iteration = i;
            List<Move> tmp = new ArrayList<>();
            tmp.add(possible.get(i));

            thread[i] = new Thread(() -> score[iteration] = min(
                    tmp,
                    p < 3 ? p + 1 : 0,
                    1,
                    Float.NEGATIVE_INFINITY,
                    Float.POSITIVE_INFINITY));
            thread[i].start();

        }

        for (int i = 0; i < score.length; ++i) {
            System.out.println(score[i]);
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (score[i] > bestValue) {
                bestValue = score[i];
                bestMove = possible.get(i);
            }
        }
        return Utils.transform(bestMove);
    }
}
