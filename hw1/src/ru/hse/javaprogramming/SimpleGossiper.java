package ru.hse.javaprogramming;

public class SimpleGossiper extends Gossiper {

    public SimpleGossiper(String name) {
        super(name);
    }

    @Override
    public void doGossipAction() {
        for (String gossipMessage : gossipMessages) {
            System.out.println(this.name + ", message number = " + currentMessageN + ", message: \"" + gossipMessage + "\"");

            sendMessage(gossipMessage);
        }
    }
}
