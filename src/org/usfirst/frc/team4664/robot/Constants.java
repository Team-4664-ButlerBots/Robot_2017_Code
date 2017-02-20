package org.usfirst.frc.team4664.robot;

public interface Constants {
	final int lsMotor	    = 1;
    final int rsMotor	    = 0;
    
    final int collectMPort= 2;
    final int shootMPort= 3;
    
//joystick 2 buttons
    final int collectStartB = 3;
    final int collectStopB = 2;
    final int shootB = 1; //(uses method GetTrigger)
//speed variables
    final double armSpeedVal   = 0.25;
    final double winchOut      = 1.0;
    final double winchIn       = -.7;
    final double latticeUp     = 0.8;
    final double latticeDown   = -.5;
//dead band variables
    final double driveDb    = 0.2;
    final double armDb = 0.2;
//Laptop ports
    final int gamepadPort	= 1;
    final int joystickPort  = 0;
//Sensor Variables
    final int rangeFinder   = 0;
    final int gyroSense   = 1;
//Scale Factors
    final double maxSpeed   = 0.75;//negative to flip direction

}