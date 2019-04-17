package main;


public class Configuration {
    private int[][] field;
    private int[] pointsPerPlayer;
    private int[] stack;

    /**
     *
     * @param field Spielfeld
     * @param pointsPerPlayer Punkte pro Spieler
     * @param stack Reststapel pro Spieler
     */
    public Configuration(int[][] field, int[] pointsPerPlayer, int[] stack) {
        this.field = field;
        this.pointsPerPlayer = pointsPerPlayer;
        this.stack = stack;
    }
}
