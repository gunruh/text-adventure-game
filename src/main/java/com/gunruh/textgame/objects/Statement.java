package com.gunruh.textgame.objects;

public class Statement {
    private Action action;
    private GameObject actingObject;
    private GameObject receivingObject;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public GameObject getActingObject() {
        return actingObject;
    }

    public void setActingObject(GameObject actingObject) {
        this.actingObject = actingObject;
    }

    public GameObject getReceivingObject() {
        return receivingObject;
    }

    public void setReceivingObject(GameObject receivingObject) {
        this.receivingObject = receivingObject;
    }
}
