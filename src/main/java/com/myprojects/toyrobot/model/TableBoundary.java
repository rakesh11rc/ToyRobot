package com.myprojects.toyrobot.model;

public enum TableBoundary {
    X_MIN(0),
    X_MAX(5),
    Y_MIN(0),
    Y_MAX(5);

    private int boundaryValue;

    private TableBoundary(int boundaryValue) {
        this.boundaryValue = boundaryValue;
    }

    public int getBoundaryValue() {
        return boundaryValue;
    }
}
