package org.usfirst.frc.team4664.robot;

public interface Constants {
	final int lsMotor	    = 0;
    final int rsMotor	    = 1;
//joystick 2 buttons
    final int latticeUpB    = 3;
    final int latticeDownB 	= 2;
    final int winchOutB   	= 4;
    int winchInB     		= 5;
//speed variables
    final double armSpeedVal   = 0.25;
    final double winchOut      = 1.0;
    final double winchIn       = -.7;
    final double latticeUp     = 0.8;
    final double latticeDown   = -.5;
//dead band variables
    final double driveDb    = 0.5;
    final double armTorqueDb = 0.2;
//Laptop ports
    final int gamepadPort	= 1;
    final int joystickPort  = 0;
//Sensor Variables
    final int rangeFinder   = 0;
    final int gyroSense   = 1;
//Scale Factors
    final double maxSpeed   = 0.85;

}