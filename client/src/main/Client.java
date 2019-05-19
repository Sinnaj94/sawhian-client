/**
 *
 * GT1(PÜ) AI for Games and Interactive Systems
 * SoSe 2019
 * Prof. Dr. Tobias Lenz
 *
 * Jannis Jahr (565638)
 * David Müller (565664)
 *
 */



package main;

import lenz.htw.sawhian.Move;
import lenz.htw.sawhian.net.NetworkClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Client {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedImage logo = ImageIO.read(new File("client/resources/circle.png"));
        NetworkClient client = new NetworkClient(null, "Gewinner", logo);

        int myPlayerNumber = client.getMyPlayerNumber();

        Board board = new Board();
        Minimax m = new Minimax(7, board);

        while (true) {
            Move move = client.receiveMove();
            if (move == null) {
                move = m.nextMove(myPlayerNumber);
                client.sendMove(move);
            } else {
                board.integrate(move);
            }
        }
    }
}