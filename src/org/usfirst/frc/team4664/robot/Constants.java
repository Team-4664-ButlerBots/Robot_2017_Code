package org.usfirst.frc.team4664.robot;

public interface Constants {
	/*
	// ROBO RIO PORTS
	*/
	
	////MOTOR PORTS
	final int lsMotor	    = 0;//These numbers should probably never have to be changed
    final int rsMotor	    = 1;
    final int collectMPort  = 5;//correct
    final int shootMPort    =  3;//correct
    final int hopperPort    =  4;//correct
    final int climbMPort    =  2;//correct
    
    ////SENSOR VARIABLES
    final int rangeFinder   = 0;
    final int gyroSense   = 1;
    /*
    // SPEED CONTROLS
    */
    
    final double armSpeedVal   = 0.25;//  speed in percentage, from a range of 0.00% power to 1.00% power
    final double winchOut      = 1.0; // make sure to set them in between 0 and 1
    final double winchIn       = -.7;
    final double latticeUp     = 0.8;
    final double latticeDown   = -.5;
    
    
    /*
    // CONTROLLER BUTTONS
    */
    
    ////controller 1 buttons
    final int exampleButton  = 0;
    final int exampleButton2 = 1;
    
    ////joystick 2 buttons
    final int hopperB = 11;
    final int collectInB = 4;
    final int collectOutB = 5;
    final int collectStopB = 3;
    final int shootB = 1; // Uses method .getTrigger()
    
    
    /*
    //ASSORTED VALUES
    */
    
    final double driveDb    = -0.2;//  dead band variables to only adjust when there's serious problems
    final double climbDb = 0.3;// with false outputs on the controller
    final double armTorqueDb = 0.2;// values from 0.00 to 1.00 again
    final double maxSpeedDrive   = 0.75;//negative to flip direction

    
    /*
    //LAPTOP PORTS
    */
   
    final int gamepadPort	= 1;//  ports for USB controllers on the laptop, should never need to be adjusted
    final int joystickPort  = 0;
}