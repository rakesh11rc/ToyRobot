package com.myprojects.toyrobot.model;

public enum Direction {
    EAST("NORTH", "SOUTH"),
    WEST("SOUTH", "NORTH"),
    NORTH("WEST", "EAST"),
    SOUTH("EAST", "WEST");

    private String left;

    private String right;

    private Direction(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
