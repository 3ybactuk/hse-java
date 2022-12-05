package ru.hse.javaprogramming;

import java.util.Random;

public class SpammerGossiper extends Gossiper {

    public SpammerGossiper(String name) {
        super(name);
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    @Override
    public void doGossipAction() {
        for (String gossipMessage : gossipMessages) {
            System.out.println(this.name + ", message number = " + currentMessageN + ", message: \"" + gossipMessage + "\"");

            for (int i = 0; i < randInt(2, 5); i++) {
                sendMessage(gossipMessage);
            }
        }
    }
}
