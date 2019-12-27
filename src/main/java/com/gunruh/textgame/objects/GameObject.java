package com.gunruh.textgame.objects;

public abstract class GameObject {
    private int health = 100;
    private final String name;
    private final String description;
    private String nickName;

    public int getEffectivenessAsBlaster() {
        return 0;
    }

    protected GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDescription() {
        return description;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public void shoot(GameObject receivingObject) {
    	receivingObject.receiveShoot(this);
    }
    
    public void receiveShoot(GameObject actingObject) {
    	this.health -= actingObject.getEffectivenessAsBlaster();
    }
}
