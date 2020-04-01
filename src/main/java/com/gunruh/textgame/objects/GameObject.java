package com.gunruh.textgame.objects;

import com.gunruh.textgame.objects.items.containers.Container;
import com.gunruh.textgame.utils.IOUtils;

import java.util.List;

public abstract class GameObject {
    protected static final GameObject EMPTY_GAME_OBJECT = new GameObject(null, null) {};
    private int health = 100;
    private final String name;
    private final String description;
    private String nickName;
    private boolean isPermanentFixture;
    private List<GameObject> parentContainer;

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
        IOUtils.display(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this) + " does not seem to respond."));
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

    public void insertInto(GameObject receivingObject) {
        if (receivingObject instanceof Container) {
            ((Container) receivingObject).receiveInsertInto(this);
        }
        else {
            IOUtils.displayWithinAsterisks("Items cannot be placed inside " + IOUtils.getNickNameOrNameWithArticle(receivingObject));
        }
    }

    public void removeFrom(GameObject receivingObject) {
        if (receivingObject instanceof Container) {
            ((Container) receivingObject).receiveRemove(this);
        }
        else {
            IOUtils.displayWithinAsterisks("Nothing can be taken from inside " + IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(receivingObject)) + ". Everything in there is still a part of " + IOUtils.getNickNameOrNameWithArticle(receivingObject) + ".");
        }
    }

    public List<GameObject> getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(List<GameObject> parentContainer) {
        this.parentContainer = parentContainer;
    }
}
