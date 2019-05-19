package main;

import lenz.htw.sawhian.Move;

import java.util.Arrays;

class Board {
    int[][] board;
    int[] remaining;
    int[] finished;

    Board() {
        board = new int[7][7];
        for (int[] row: board) Arrays.fill(row, -1);

        remaining = new int[]{ 7, 7, 7, 7};
        finished = new int[4];
    }

    void integrate(Move move) {

        System.out.println("board before:");
        Utils.print(board);

        int p = move.player;
        Move m = Utils.normalize(move);
        int x = m.x;
        int y = m.y;
        System.out.println("move(normalized): " + m);

        int[][] b = Utils.rotateCounterClockwise(board, p);
        System.out.println("b before: ");
        Utils.print(b);

        // region [integration]
        if(y == 6) {
            b[y][x] = -1;
            finished[p]++;
        } else if (y == 0) {
            if (b[y][x] == p) {
                b[y][x] = -1;
                if(b[1][x] == -1)
                    b[1][x] = p;
                else {
                    for(int i = 2; i <= 6; i++) {
                        if((b[i-1][x] != p && b[i-1][x] != -1) && b[i][x] == -1) {
                            System.out.println("JUMP! --> " + m);
                            b[i-2][x] = -1;
                            b[i][x] = p;
                        } else
                            break;
                    }
                }
            } else {
                b[y][x] = p;
                remaining[p]--;
            }
        } else {
            if (b[y+1][x] == -1) {
                b[y+1][x] = p;
                b[y][x] = -1;
            } else {
                for (int i = 2; (y+i) <= 8; i = i + 2) {
                    if(y+i >= 7) {
                        if(y+i == 7 && b[y+i-1][x] != p && b[y+i-1][x] != -1) {
                            b[y+i-2][x] = -1;
                            finished[p]++;
                        }
                        break;
                    } else {
                        if (b[y + i][x] == -1 && (b[y+i-1][x] != p && b[y+i-1][x] != -1)) {
                            System.out.println("b["+(y+i-1)+"]["+x+"]"+" is " + (b[y+i-1][x]) + " | JUMP! --> " + m);
                            b[y+i-2][x] = -1;
                            b[y+i][x] = p;
                        } else
                            break;
                    }
                }

            }

        }
        // endregion

        System.out.println("b after: ");
        Utils.print(b);
        System.out.println("--- rotating clockwise " + p +  " times ---");
        board = Utils.rotateClockwise(b, p);
        System.out.println("original after: ");
        Utils.print(board);
    }

}
