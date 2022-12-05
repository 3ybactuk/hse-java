package ru.hse.javaprogramming;


import java.util.ArrayList;
import java.util.SortedSet;

public abstract class Gossiper {
    public final String name;
    public SortedSet<Gossiper> listeners;
    protected int maxMessagesN;
    protected int currentMessageN = 0;
    protected ArrayList<String> gossipMessages;

    public Gossiper(String name) {
        this.name = name;
    }


    /**
     * Adds a gossiper to listeners
     * @param gossiper the Gossiper object
     */
    public void addListener(Gossiper gossiper) {
        if (listeners.contains(gossiper)) {
            System.out.println("Error: \"" + gossiper.name + "\" is already a listener of \"" + this.name + "\".");
            return;
        }
        listeners.add(gossiper);
    }

    /**
     * Removes a gossiper from listeners
     * @param gossiper the Gossiper object
     */
    public void removeListener(Gossiper gossiper) {
        if (!listeners.contains(gossiper)) {
            System.out.println("Error: \"" + gossiper.name + "\" isn't a listener of \"" + this.name + "\".");
            return;
        }

        listeners.remove(gossiper);
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
