package com.gunruh.textgame.objects;

public class Statement {
    public static final Statement EMPTY_STATEMENT = new Statement(null, null, null);

    private Action action;
    private GameObject actingObject;
    private GameObject receivingObject;
    private Direction direction;

    public Statement(GameObject actingObject, Action action, GameObject receivingObject) {
        this.actingObject = actingObject;
        this.action = action;
        this.receivingObject = receivingObject;
        this.direction = null;
    }

    public Statement(GameObject actingObject, Action action, GameObject receivingObject, Direction direction) {
        this.actingObject = actingObject;
        this.action = action;
        this.receivingObject = receivingObject;
        this.direction = direction;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
