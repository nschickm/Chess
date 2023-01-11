package com.example.chess.controller;

import javafx.scene.control.ChoiceBox;

public class SettingsController {
    public ChoiceBox choiceBoxTime;

    public void initialize(){

        System.out.println(choiceBoxTime.getValue());

    }
}
