package com.example.chess.controller;

public interface PublisherInterface<T> {

    public void addSubscriber(SubscriberInterface<T> sub);

    public void removeSubscriber(SubscriberInterface<T> sub);

    public void notifyAllSubscribers(T what);

}
