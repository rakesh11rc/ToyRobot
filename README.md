This is a springboot application to simulate the movement of the robot on a square table top of 5 X 5 units.

It is assumed that the table surface is free of obstacles and the robot can move freely.

Robot placing and movement:
  1. Robot can be placed in one of the NORTH, EAST, WEST or SOUTH directions. 
  2. Robot is placed with in the boundaries of the table.
  3. Robot is moved by 1 unit in the direction that it faces. Any movement that makes if fall off from the table is ignored.
  4. Robot can perform one of the following four actions: MOVE, LEFT, RIGHT, REMOVE
  
APIs:
  1. localhost:8085/toyrobot/place
     POST request.
     Form Parameters:
     x: x axis value
     y: y axis value
     direction: facing direction of robot. One of the above mentioned directions.
  2. localhost:8085/toyrobot/action
     POST request.
     Form parameter:
     robotAction: Performs the provided action on the robot. Provide one of the above mentioned actions. 
  3. localhost:8085/toyrobot/report
     GET request
     returs the current position and direction of the robot.