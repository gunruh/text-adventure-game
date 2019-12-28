package com.gunruh.textgame.objects;

import com.gunruh.textgame.utils.IOUtils;

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
    	this.takeDamage(actingObject.getEffectivenessAsBlaster());
    }

    public void takeDamage(int amount) {
        if (amount == 0) {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " takes no damage.");
            return;
        }

        if (this.health - amount >= 0) {
            this.health -= amount;
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " takes damage.");
        }
        else {
            this.health = 0;
        }

        if (this.health <= 0) {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " has been destroyed. " + IOUtils.getRandomDestroyString());
            // When health reaches zero, the GameObject disappears (this default behavior may be overridden in extended classes)
            Player.getInstance().getInventory().remove(this);
            Player.getInstance().getCurrentRoom().getAvailableObjects().remove(this);
        }
    }
}
