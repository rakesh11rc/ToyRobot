package com.myprojects.toyrobot.service;

import com.myprojects.toyrobot.model.RobotAction;
import com.myprojects.toyrobot.model.RobotStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActionServiceTest {

    private ActionService classUnderTest = new ActionService();

    @Test
    public void placeRobot_ProperValues_ReturnsRobotPlaced() {
        RobotStatus status = classUnderTest.placeRobot(1,1, "WEST");
        assertEquals(RobotStatus.PLACED, status);
    }

    @Test
    public void placeRobot_WrongDirection_ReturnsInvalidDirection() {
        RobotStatus status = classUnderTest.placeRobot(1,1, "SKY");
        assertEquals(RobotStatus.INVALID_DIRECTION, status);
    }

    @Test
    public void placeRobot_ProperValues_ReturnsRobotAlreadyPlaced() {
        classUnderTest.placeRobot(1,1, "WEST");
        RobotStatus status = classUnderTest.placeRobot(1,1, "WEST");
        assertEquals(RobotStatus.ALREADY_PLACED, status);
    }

    @Test
    public void placeRobot_OutOfBoundayValues_ReturnsInvalidDirection() {
        RobotStatus status = classUnderTest.placeRobot(6,1, "EAST");
        assertEquals(RobotStatus.INVALID_POSITION, status);
    }

    @Test
    public void moveRobot_ReturnsRobotNotAvailable() {
        RobotStatus status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.NOT_AVAILABLE, status);
    }

    @Test
    public void moveRobot_ReturnsRobotNotMoved() {
        RobotStatus status;
        classUnderTest.placeRobot(0,0, "SOUTH");
        status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.NOT_MOVED, status);
        classUnderTest.setDirection(RobotAction.RIGHT);
        status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.NOT_MOVED, status);
    }

    @Test
    public void moveRobot_ReturnsRobotMoved() {
        RobotStatus status;
        classUnderTest.placeRobot(4,4, "NORTH");
        status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.MOVED, status);
    }
}
