package com.myprojects.toyrobot.model;

import java.util.Objects;

public class ToyRobot {

    private int xValue;

    private int yValue;

    private Direction direction;

    private static ToyRobot toyRobot;

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public static ToyRobot getToyRobot() {
        if(Objects.equals(toyRobot, null)) {
            toyRobot = new ToyRobot();
        }
        return toyRobot;
    }

    public static void setToyRobot(ToyRobot robot) {
        toyRobot = robot;
    }

    private ToyRobot() {
    }


}
