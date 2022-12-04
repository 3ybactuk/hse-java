package ru.hse.javaprogramming;


import java.util.ArrayList;

public abstract class Gossiper {
    private String name;
    private ArrayList<Gossiper> listeners;
    protected int maxMessagesN;
    protected int currentMessageN = 0;
    protected ArrayList<String> gossipMessages;

    /**
     * @return string containing the name of a gossiper
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a gossiper to listeners
     * @param gossiper the Gossiper object
     */
    public void addListener(Gossiper gossiper) {
        listeners.add(gossiper);
    }

    /**
     * Prints a tired message containing a gossiper's name.
     */
    public void printTiredMessage() {
        System.out.println(name + " is tired");
    }

    /**
     * Sends a gossip message to listeners
     * @param gossipMessage contains the text of a gossip message
     */
    public void sendMessage(String gossipMessage) {
        for (Gossiper listener : listeners) {
            listener.getGossipMessage(gossipMessage);
        }
    }

    /**
     * Remembers a gossip
     * @param gossipMessage contains the text of a gossip message
     */
    public void getGossipMessage(String gossipMessage) {
        if (currentMessageN > maxMessagesN) {
            printTiredMessage();
        } else {
            ++currentMessageN;
            gossipMessages.add(gossipMessage);
        }
    }


    /**
     * If gossiper isn't tired, spreads the gossip
     */
    public void talkGossip() {
        if (currentMessageN > maxMessagesN) {
            printTiredMessage();
        } else {
            doGossipAction();
        }
    }

    /**
     * Prints name, number of a gossip, gossip message.
     * Spreads the gossip to listeners
     */
    public abstract void doGossipAction();
}
