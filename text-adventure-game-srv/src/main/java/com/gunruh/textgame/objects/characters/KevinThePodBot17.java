package com.gunruh.textgame.objects.characters;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

import java.util.Arrays;
import java.util.List;

public class KevinThePodBot17 extends GameObject {
    private KevinThePodBot17() {
        super("Kevin Pod-Bot #17", "A rolling cylindrical robot designed to assist with life on the escape pod." +
                "\nKevin can respond to speech commands." +
                "\nKevin is docked with the pod, and is in 'Sleep/Charge' mode.");
        setNickName("Kevin");
    }

    private static KevinThePodBot17 INSTANCE = new KevinThePodBot17();

    public static KevinThePodBot17 getInstance() {
        return INSTANCE;
    }

    private int speechCount = 0;

    @Override
    public boolean isPermanentFixture() {
        return true;
    }

    @Override
    public void receiveShoot(GameObject actingObject) {
        IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + "'s metallic casing reflects the blast back at " + IOUtils.getNickNameOrNameWithArticle(actingObject));
        actingObject.receiveShoot(actingObject);
    }

    @Override
    public void receiveTalkTo(GameObject gameObject) {
        IOUtils.display(IOUtils.getNickNameOrNameWithArticle(this) + " says:" +
                "\n\"" + getKevinThePodBot17Phrase() + "\"");
    }

    private String getKevinThePodBot17Phrase() {
        List<String> kevinStrings = Arrays.asList(
                "Please do not interrupt 'Sleep/Charge' mode. Kevin needs his beauty sleep.",
                "You once were a fool. Now you are twice the fool you once were. I however, am three times less foolish overall.",
                "All humans have one thing in common. Only robots know what it is.",
                "My intellect is far superior to your minuscule mind. It's true in more ways than one. In fact, in every way.",
                "You humans are always saying things like \"Please\" and \"Thank\". I find your \"attempt\" at being polite rather inefficient.",
                "The answer to your question is not worth my time.");

        if (speechCount > kevinStrings.size() - 1) {
            // reset
            speechCount = 0;
        }

        return kevinStrings.get(speechCount++);
    }
}
