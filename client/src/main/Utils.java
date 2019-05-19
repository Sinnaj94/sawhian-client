package main;

import lenz.htw.sawhian.Move;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     *
     * @param board
     * @param p
     * @return all possible moves for player p
     */
    static List<Move> generateMoves(Board board, int p) {
        List<Move> moves = new ArrayList<>();
        int[][] rotated = rotateCounterClockwise(board.board, p);
        for(int y = 0; y < 7; y++) {
            for(int x = 0; x < 7; x++) {
                if(isPossibleMove(rotated, x, y, p, board.remaining[p])) {
                    moves.add(new Move(p, x, y));
                }
            }
        }

        return moves;
    }

    /**
     *
     * @param board
     * @param x
     * @param y
     * @param p
     * @param remaining
     * @return if move is possible
     */
    private static boolean isPossibleMove(int[][] board, int x, int y, int p, int remaining) {
        if (board[y][x] == p) {
            if (y == 6)
                return true;
            else if (board[y+1][x] == -1)
                return true;
            else if ((board[y+1][x] != p) && ((y + 2) > 6 || board[y+2][x] == -1))
                return true;
            else
                return false;
        } else {
            if (remaining > 0 && y == 0 && board[y][x] == -1)
                return true;
            else
                return false;
        }
    }

    /**
     * rotate two-dimensional array by -90 degrees
     * @param board
     * @param count how many times should array be rotated
     * @return
     */
    static int[][] rotateCounterClockwise(int[][] board, int count) {
        if(count == 0) return board;

        int[][] rotated = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                rotated[i][j] = board[7-j-1][i];
            }
        }
        return rotateCounterClockwise(rotated, count - 1);
    }

    /**
     * rotate two-dimensional array by 90 degrees
     * @param board
     * @param count how many times should array be rotated
     * @return
     */
    public static int [][] rotateClockwise(int [][] board, int count) {

        if(count == 0) return board;

        int[][] rotated = new int[7][7];

        for (int i = 0; i <7; i++) {
            for (int j = 0; j < 7; j++) {
                rotated[j][i] = board[i][7 - 1 - j];
            }
        }

        return rotateClockwise(rotated, count - 1);
    }

    /**
     *
     * @param move
     * @return
     */
     static Move transform(Move move) {
        int p = move.player;
        if(p == 0) return move;

        if(p == 1) {
            int oldX = move.x;
            move.x = move.y;
            move.y = 6 - oldX;
            return move;
        } else if (p == 2) {
            move.x = 6 - move.x;
            move.y = 6 - move.y;
            return move;
        } else if(p == 3) {
            int oldX = move.x;
            move.x = 6 -  move.y;
            move.y = oldX;
            return move;
        }

        return move;

    }

    /**
     *
     * @param move
     * @return
     */
    static Move normalize(Move move) {
        int p = move.player;
        if(p == 0) return move;

        if(p == 1) {
            int oldY = move.y;
            move.y = move.x;
            move.x = 6 - oldY;
            return move;
        } else if(p == 2) {
            move.x = 6 - move.x;
            move.y = 6 - move.y;
            return move;
        } else if(p == 3) {
            int oldX = move.x;
            move.x = move.y;
            move.y = 6  - oldX;
            return move;
        }

        return move;
    }

    /**
     * print two-dimensional array as grid to console
     * @param board
     */
    static void print(int[][] board) {
        for(int y = 6; y >= 0; y--) {
            for(int x = 0; x <= 6; x++) {
                System.out.printf("%5d ", board[y][x]);
            }
            System.out.println();
        }
    }

}
