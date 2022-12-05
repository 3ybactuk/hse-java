package ru.hse.javaprogramming;

public class CensorGossiper extends Gossiper {

    public CensorGossiper(String name) {
        super(name);
    }

    /**
     * Prints name, number of a gossip, gossip message.
     * Only spreads gossips that contain "Java" in any case
     */
    @Override
    public void doGossipAction() {
        for (String gossipMessage : gossipMessages) {
            System.out.println(this.name + ", message number = " + currentMessageN + ", message: \"" + gossipMessage + "\"");

            if (gossipMessage.toLowerCase().contains("java")) {
                sendMessage(gossipMessage);
            }
        }
    }

}
