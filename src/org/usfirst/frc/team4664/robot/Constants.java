package org.usfirst.frc.team4664.robot;

public interface Constants {
	/*
	// ROBO RIO PORTS
	*/
	
	////MOTOR PORTS
	final int lsMotor	    = 0;//These numbers should probably never have to be changed
    final int rsMotor	    = 1;
    
    
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
    final int latticeUpB    = 3;//These could be changed for the joystick buttons
    final int latticeDownB 	= 2;
    final int winchOutB   	= 4;
    int winchInB     		= 5;
    
    
    /*
    //DEAD BAND VALUES
    */
    
    final double driveXDb    = 0.3;//  dead band variables to only adjust when there's serious problems
    final double driveYDb    = 0.3;// with false outputs on the controller
    final double armTorqueDb = 0.2;// values from 0.00 to 1.00 again

    
    /*
    //LAPTOP PORTS
    */
   
    final int gamepadPort	= 1;//  ports for USB controllers on the laptop, should never need to be adjusted
    final int joystickPort  = 0;

}
