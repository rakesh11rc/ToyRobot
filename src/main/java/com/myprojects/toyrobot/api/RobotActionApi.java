package com.myprojects.toyrobot.api;

import com.myprojects.toyrobot.apiController.RobotActionApiController;
import com.myprojects.toyrobot.model.RobotStatus;
import com.myprojects.toyrobot.model.ToyRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/toyrobot")
public class RobotActionApi {

    @Autowired
    private RobotActionApiController delegate;

    @Path("/place")
    @Produces("application/json")
    @POST
    public ResponseEntity placeRobot(@FormParam("x") int xValue, @FormParam("y") int yValue, @FormParam("direction") String direction) {
        return delegate.placeRobot(xValue, yValue, direction);
    }

    @Path("/action")
    @Produces("application/json")
    @POST
    public ResponseEntity performRobotAction(@FormParam("robotAction") String robotAction) {
        return delegate.performRobotAction(robotAction);
    }


    @Path("/report")
    @Produces("application/json")
    @GET
    public ResponseEntity getRobot() {
        return delegate.getRobot();
    }

}
