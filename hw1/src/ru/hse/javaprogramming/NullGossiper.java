package ru.hse.javaprogramming;

public class NullGossiper extends Gossiper {

    @Override
    public void doGossipAction() {
        for (String gossipMessage : gossipMessages) {
            System.out.println(getName() + ", message number = " + currentMessageN + ", message: \"" + gossipMessage + "\"");
        }
    }
}
