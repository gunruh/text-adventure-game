package com.gunruh.textgame;

import com.gunruh.textgame.utils.IOUtils;

public class GameOutput {
    StringBuffer outputBuffer;

    public GameOutput() {
        this.outputBuffer = new StringBuffer();
    }

    public GameOutput(String string) {
        this.outputBuffer = new StringBuffer(string);
    }

    public void append(String string) {
        outputBuffer.append(IOUtils.controlWidth(string));
    }

    public void appendln(String string) {
        outputBuffer.append(IOUtils.controlWidth(string)).append("\n");
    }

    public String print() {
        String output = outputBuffer.toString();
        clear();
        return output;
    }

    public void clear() {
        outputBuffer.setLength(0);
    }

    public String toString() {
        return outputBuffer.toString();
    }
}
