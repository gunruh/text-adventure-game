package com.gunruh.textgame.objects;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.enumerations.Direction;

public class Statement {
    public static final Statement EMPTY_STATEMENT = new Statement(null, null, null);

    private Action action;
    private GameObject actingObject;
    private GameObject receivingObject;
    private Direction direction;
    private String remainingString;

    public Statement(GameObject actingObject, Action action, GameObject receivingObject) {
        this(actingObject, action, receivingObject, null);
    }

    public Statement(GameObject actingObject, Action action, GameObject receivingObject, Direction direction) {
        this.actingObject = actingObject;
        this.action = action != null ? action : Action.UNKNOWN_ACTION;
        this.receivingObject = receivingObject;
        this.direction = direction;
        this.remainingString = null;
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
    
    public String getRemainingString() {
    	return remainingString;
    }
    
    public void setRemainingString(String remainingString) {
    	this.remainingString = remainingString;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
