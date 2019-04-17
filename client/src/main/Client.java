package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import lenz.htw.sawhian.Move;
import lenz.htw.sawhian.net.NetworkClient;

public class Client {

    public static void main(String[] args) throws IOException {
        BufferedImage logo = ImageIO.read(new File("client/resources/circle.png"));
        NetworkClient client = new NetworkClient(null, "CLIENT -1", logo);

        client.getMyPlayerNumber();
        client.getTimeLimitInSeconds();
        client.getExpectedNetworkLatencyInMilliseconds();

        while (true) {
            Move move = client.receiveMove();
            if (move == null) {
                // move = meinTotalClevererZug();
                //uncleverer Zug: new Move(client.getMyPlayerNumber(),0,0);
                client.sendMove(move);
            } else {
                // moveInMeinSpielfeldIntegrieren(move);
            }
        }
    }
}