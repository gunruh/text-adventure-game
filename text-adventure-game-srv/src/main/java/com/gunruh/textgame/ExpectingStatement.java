package com.gunruh.textgame;

public class ExpectingStatement {
    public boolean inputString;
    public boolean action;
    public boolean actingObject;
    public boolean receivingObject;
    public boolean direction;
    public boolean remainingString;

    public boolean isExpecting() {
        return inputString || action || actingObject || receivingObject || direction || remainingString;
    }

}
