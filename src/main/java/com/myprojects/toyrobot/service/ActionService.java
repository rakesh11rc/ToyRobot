package com.myprojects.toyrobot.service;

import com.myprojects.toyrobot.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ActionService {

    private static ToyRobot toyRobot;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionService.class);

    public RobotStatus placeRobot(int xValue, int yValue, String direction) {
        Direction placingDirection;
        if(Objects.nonNull(toyRobot)) {
            LOGGER.error("The robot is already placed. Cannot place it again.");
            return RobotStatus.ALREADY_PLACED;
        }
        try {
            placingDirection = Direction.valueOf(direction);
        } catch (IllegalArgumentException e) {
            LOGGER.error("The provided direction is invalid. Please choose from EAST, WEST, NORTH or SOUTH");
            return RobotStatus.INVALID_DIRECTION;
        }
        if (checkPosition(xValue, yValue)) {

            toyRobot = ToyRobot.getToyRobot();
            toyRobot.setxValue(xValue);
            toyRobot.setyValue(yValue);
            toyRobot.setDirection(placingDirection);
            return RobotStatus.PLACED;
        }
        return RobotStatus.INVALID_POSITION;
    }

    public RobotStatus moveRobot() {
        if(Objects.isNull(toyRobot)) {
            return RobotStatus.NOT_AVAILABLE;
        }
        if(!checkPositionAndMoveRobot()) {
            LOGGER.info("Robot not moved. Check the current position of the robot");
            return RobotStatus.NOT_MOVED;
        } else {
            return RobotStatus.MOVED;
        }
    }

    public RobotStatus setDirection(RobotAction action) {
        Direction placingDirection = null;

        if(Objects.isNull(toyRobot)) {
            return RobotStatus.NOT_AVAILABLE;
        }

        if(action.equals(RobotAction.LEFT)) {
            placingDirection = Direction.valueOf(toyRobot.getDirection().getLeft());
        } else if(action.equals(RobotAction.RIGHT)) {
            placingDirection = Direction.valueOf(toyRobot.getDirection().getRight());
        }


        toyRobot.setDirection(placingDirection);
        LOGGER.info("Robot direction changed to {}", placingDirection.toString());
        return RobotStatus.DIRECTION_CHANGED;

    }

    public ToyRobot getToyRobot() {
        return toyRobot;
    }

    public RobotStatus removeRobot() {
        if(Objects.isNull(toyRobot)) {
            return RobotStatus.NOT_AVAILABLE;
        }
        toyRobot = null;
        return RobotStatus.REMOVED;
    }

    private boolean checkPosition(int xValue, int yValue) {
        if(xValue < TableBoundary.X_MIN.getBoundaryValue() || xValue > TableBoundary.X_MAX.getBoundaryValue()) {
            return false;
        }
        if(yValue < TableBoundary.Y_MIN.getBoundaryValue() || yValue > TableBoundary.Y_MAX.getBoundaryValue()) {
            return false;
        }
        return true;
    }

    private boolean checkPositionAndMoveRobot() {

        Direction direction = toyRobot.getDirection();
        int xValue = toyRobot.getxValue();
        int yValue = toyRobot.getyValue();

        switch (direction) {
            case NORTH:
                int yMax = TableBoundary.Y_MAX.getBoundaryValue();
                if ((yValue + 1) > yMax) {
                    return false;
                } else {
                     yValue += 1;
                    toyRobot.setyValue(yValue);
                    break;
                }
            case SOUTH:
                int yMin = TableBoundary.Y_MIN.getBoundaryValue();
                if ((yValue - 1) < yMin) {
                    return false;
                } else {
                    yValue -= 1;
                    toyRobot.setyValue(yValue);
                    break;
                }
            case EAST:
                int xMax = TableBoundary.X_MAX.getBoundaryValue();
                if ((xValue + 1) > xMax) {
                    return false;
                } else {
                    xValue += 1;
                    toyRobot.setxValue(xValue);
                    break;
                }
            case WEST:
                int xMin = TableBoundary.X_MIN.getBoundaryValue();
                if ((xValue - 1) < xMin) {
                    return false;
                } else {
                    xValue -= 1;
                    toyRobot.setxValue(xValue);
                    break;
                }
        }
        return true;
    }

}
