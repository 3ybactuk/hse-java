package ru.hse.javaprogramming;

import java.util.Collection;

public class DeduplicatorGossiper extends SimpleGossiper {
    protected Collection<String> gossipMessages;

    public DeduplicatorGossiper(String name) {
        super(name);
    }

//    @Override
//    public void getGossipMessage(String gossipMessage) {
//        ++currentMessageN;
//        gossipMessages.add(gossipMessage);
//    }
}
