package org.usfirst.frc.team4664.robot;

public interface Constants {
	final int lsMotor	    = 0;
    final int rsMotor	    = 1;
    
    final int collectMPort= 5;//correct
    final int shootMPort =  3;//correct
    final int hopperPort =  4;//correct
    final int climbMPort =  2;//correct
    
//joystick 2 buttons
    final int hopperB = 11;
    final int collectInB = 4;
    final int collectOutB = 5;
    final int collectStopB = 3;
    final int shootB = 1; // Uses method .getTrigger()
//speed variables
    final double armSpeedVal   = 0.25;
    final double winchOut      = 1.0;
    final double winchIn       = -.7;
    final double latticeUp     = 0.8;
    final double latticeDown   = -.5;
//dead band variables
    final double driveDb    = -0.2;
    final double climbDb = 0.3;
//Laptop ports
    final int gamepadPort	= 1;
    final int joystickPort  = 0;
//Sensor Variables
    final int rangeFinder   = 0;
    final int gyroSense   = 1;
//Scale Factors
    final double maxSpeedDrive   = 0.8;//negative to flip direction

}