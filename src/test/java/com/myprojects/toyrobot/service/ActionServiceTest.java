package com.myprojects.toyrobot.service;

import com.myprojects.toyrobot.model.Direction;
import com.myprojects.toyrobot.model.RobotAction;
import com.myprojects.toyrobot.model.RobotStatus;
import com.myprojects.toyrobot.model.ToyRobot;
import org.junit.Test;

import java.awt.Robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ActionServiceTest {

    private ActionService classUnderTest = new ActionService();

    @Test
    public void placeRobot_ProperValues_ReturnsRobotPlaced() {
        classUnderTest.removeRobot();
        RobotStatus status = classUnderTest.placeRobot(1,1, "WEST");
        assertEquals(RobotStatus.PLACED, status);
    }

    @Test
    public void placeRobot_WrongDirection_ReturnsInvalidDirection() {
        classUnderTest.removeRobot();
        RobotStatus status = classUnderTest.placeRobot(1,1, "SKY");
        assertEquals(RobotStatus.INVALID_DIRECTION, status);
    }

    @Test
    public void placeRobot_ProperValues_ReturnsRobotAlreadyPlaced() {
        classUnderTest.removeRobot();
        classUnderTest.placeRobot(1,1, "WEST");
        RobotStatus status = classUnderTest.placeRobot(1,1, "WEST");
        assertEquals(RobotStatus.ALREADY_PLACED, status);
    }

    @Test
    public void placeRobot_OutOfBoundayValues_ReturnsInvalidDirection() {
        classUnderTest.removeRobot();
        RobotStatus status = classUnderTest.placeRobot(6,1, "EAST");
        assertEquals(RobotStatus.INVALID_POSITION, status);
    }

    @Test
    public void moveRobot_ReturnsRobotNotAvailable() {
        classUnderTest.removeRobot();
        RobotStatus status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.NOT_AVAILABLE, status);
    }

    @Test
    public void moveRobot_ReturnsRobotNotMoved() {
        RobotStatus status;
        classUnderTest.removeRobot();
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
        classUnderTest.removeRobot();
        classUnderTest.placeRobot(4,4, "NORTH");
        status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.MOVED, status);
    }

    @Test
    public void setDirection_LEFT_ReturnsRobotNotAvailable() {
        classUnderTest.removeRobot();
        RobotStatus status = classUnderTest.setDirection(RobotAction.LEFT);
        assertEquals(RobotStatus.NOT_AVAILABLE, status);
    }

    @Test
    public void setDirection_LEFT_ReturnsDirectionChanged() {
        RobotStatus status;
        classUnderTest.removeRobot();
        classUnderTest.placeRobot(1,1,"NORTH");
        status = classUnderTest.setDirection(RobotAction.LEFT);
        assertEquals(RobotStatus.DIRECTION_CHANGED, status);
        status = classUnderTest.setDirection(RobotAction.RIGHT);
        assertEquals(RobotStatus.DIRECTION_CHANGED, status);
    }

    @Test
    public void getRobot_ReturnsNull() {
        classUnderTest.removeRobot();
        assertNull(classUnderTest.getToyRobot());
    }

    @Test
    public void getRobot_ReturnsToyRobot() {
        classUnderTest.removeRobot();
        classUnderTest.placeRobot(1, 4, "SOUTH");
        ToyRobot toyRobot = classUnderTest.getToyRobot();
        assertEquals(1, toyRobot.getxValue());
        assertEquals(4, toyRobot.getyValue());
        assertTrue(Direction.SOUTH.equals(toyRobot.getDirection()));
    }

    @Test
    public void getRobot_ActionSequence1_ReturnsToyRobot() {
        RobotStatus status;
        ToyRobot toyRobot;
        classUnderTest.removeRobot();

        status = classUnderTest.placeRobot(2, 1, "WEST");
        assertEquals(RobotStatus.PLACED, status);
        classUnderTest.moveRobot();
        classUnderTest.moveRobot();
        status = classUnderTest.moveRobot();
        assertEquals(RobotStatus.NOT_MOVED, status);

        classUnderTest.setDirection(RobotAction.RIGHT);
        classUnderTest.moveRobot();
        classUnderTest.moveRobot();
        classUnderTest.moveRobot();
        classUnderTest.moveRobot();
        toyRobot = classUnderTest.getToyRobot();
        assertEquals(0, toyRobot.getxValue());
        assertEquals(5, toyRobot.getyValue());
        assertTrue(Direction.NORTH.equals(toyRobot.getDirection()));

        classUnderTest.setDirection(RobotAction.RIGHT);
        classUnderTest.moveRobot();
        classUnderTest.setDirection(RobotAction.RIGHT);
        classUnderTest.moveRobot();
        classUnderTest.setDirection(RobotAction.LEFT);
        toyRobot = classUnderTest.getToyRobot();
        assertEquals(1, toyRobot.getxValue());
        assertEquals(4, toyRobot.getyValue());
        assertTrue(Direction.EAST.equals(toyRobot.getDirection()));
    }
}
