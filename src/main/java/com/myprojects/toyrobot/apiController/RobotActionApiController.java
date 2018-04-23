package com.myprojects.toyrobot.apiController;

import com.myprojects.toyrobot.api.RobotActionApi;
import com.myprojects.toyrobot.model.RobotAction;
import com.myprojects.toyrobot.model.RobotStatus;
import com.myprojects.toyrobot.model.ToyRobot;
import com.myprojects.toyrobot.service.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import java.util.Objects;

@Component
public class RobotActionApiController extends RobotActionApi {

    @Autowired
    private ActionService actionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotActionApiController.class);

    @Override
    public ResponseEntity placeRobot(@FormParam("x") int xValue, @FormParam("y") int yValue, @FormParam("direction") String direction) {
        return ResponseEntity.ok(actionService.placeRobot(xValue, yValue, direction).toString());
    }

    @Override
    public ResponseEntity performRobotAction(@FormParam("action") String action) {
        RobotAction robotAction = null;
        try {
            robotAction = RobotAction.valueOf(action);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid robot action {}", action);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Action");

        }
        if (robotAction.equals(RobotAction.MOVE)) {
            return ResponseEntity.ok(actionService.moveRobot().toString());
        } else if (robotAction.equals(RobotAction.REMOVE)) {
            return ResponseEntity.ok(actionService.removeRobot().toString());
        } else {
            return ResponseEntity.ok(actionService.setDirection(robotAction).toString());
        }

    }

    @Override
    public ResponseEntity getRobot() {
        ToyRobot toyRobot = actionService.getToyRobot();
        if (Objects.isNull(toyRobot)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Robot");
        }
        return ResponseEntity.ok(actionService.getToyRobot());
    }
}
