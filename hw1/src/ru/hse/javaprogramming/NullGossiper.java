package ru.hse.javaprogramming;

public class NullGossiper extends Gossiper {

    public NullGossiper(String name) {
        super(name);
    }

    @Override
    public void doGossipAction() {
        for (String gossipMessage : gossipMessages) {
            System.out.println(this.name + ", message number = " + currentMessageN + ", message: \"" + gossipMessage + "\"");
        }
    }
}
