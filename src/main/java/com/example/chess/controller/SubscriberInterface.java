package com.example.chess.controller;

public interface SubscriberInterface<T> {

    void notify(T what);

}
