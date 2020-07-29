package com.interviewcake.datastructures;

public class Rectangle {

    // coordinates of bottom left corner
    private int leftX;
    private int bottomY;

    // dimensions
    private int width;
    private int height;

    public Rectangle() {}

    public Rectangle(int leftX, int bottomY, int width, int height) {
        this.leftX = leftX;
        this.bottomY = bottomY;
        this.width  = width;
        this.height = height;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getBottomY() {
        return bottomY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean equals(Object other) {
        if(!(other instanceof Rectangle))
            return false;
        Rectangle rectangleOther = (Rectangle)other;
        return this.getLeftX() == rectangleOther.getLeftX() &&
            this.getBottomY() == rectangleOther.getBottomY() &&
            this.getWidth() == rectangleOther.getWidth() &&
            this.getHeight() == rectangleOther.getHeight();

    }

    public String toString() {
        return "leftx=" + leftX + ", bottomY=" + bottomY + ", width=" + width + ", height=" + height;
    }
}