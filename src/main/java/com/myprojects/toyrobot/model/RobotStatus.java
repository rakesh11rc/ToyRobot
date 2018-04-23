package com.myprojects.toyrobot.model;

public enum RobotStatus {

    PLACED("Robot is placed"),
    ALREADY_PLACED("Robot is already placed"),
    MOVED("Robot is moved"),
    NOT_MOVED("Robot movement not possible in the given direction"),
    NOT_AVAILABLE("Robot is not avaiable"),
    DIRECTION_CHANGED("Robot direction is changed"),
    INVALID_POSITION("Robot position is invalid"),
    INVALID_DIRECTION("Invalid direction provided"),
    REMOVED("Robot is removed from table");

    private String status;

    private RobotStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return this.status;
    }
}
