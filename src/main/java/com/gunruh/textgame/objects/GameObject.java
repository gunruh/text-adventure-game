package com.gunruh.textgame.objects;

import com.gunruh.textgame.utils.IOUtils;

public abstract class GameObject {
    private int health = 100;
    private final String name;
    private final String description;
    private String nickName;
    private boolean isPermanentFixture;

    // use this to determine whether the object can be taken by the player.
    public boolean isPermanentFixture() {
        return isPermanentFixture;
    }

    public int getEffectivenessAsBlaster() {
        return 0;
    }

    protected GameObject(String name, String description) {
        this(name, description, false);
    }

    protected GameObject(String name, String description, boolean isPermanentFixture) {
        this.name = name;
        this.description = description;
        this.isPermanentFixture = isPermanentFixture;
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

    public void talkTo(GameObject receivingObject) {
        receivingObject.receiveTalkTo(this);
    }

    public void receiveTalkTo(GameObject actingObject) {
        IOUtils.display(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this) + " has nothing to say."));
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

        if (this.health - amount > 0) {
            this.health -= amount;
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " takes damage.");
        }
        else {
            this.health = 0;

            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " has been destroyed. " + IOUtils.getRandomDestroyString());
            // When health reaches zero, the GameObject disappears (this default behavior may be overridden in extended classes)
            Player.getInstance().getInventory().remove(this);
            Player.getInstance().getCurrentRoom().getAvailableObjects().remove(this);
        }
    }
}
