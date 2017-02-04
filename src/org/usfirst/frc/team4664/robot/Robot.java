package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	//Drive System Init
	RobotDrive driveSystem = new RobotDrive(0, 1);
	
	//Joystick Declaration
	Joystick gamepad = new Joystick(0);
	Joystick stick   = new Joystick(1);
	
	//RobotStuff
	public Robot() {
		driveSystem.setExpiration(0.1);
	}

	//Init Stuff
	public void robotInit() {
	}

	//Autonomous, activated in Autonomous mode
	public void autonomous() {
	}

	//Operator mode, activated in TeleOperated mode
	public void operatorControl() {
		//TODO Test actual code
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			if(gamepad.getRawButton(9)){
				driveSystem.tankDrive(0.0, 0.0);
				System.out.println("Emergency disabled for 5 seconds");
				Timer.delay(5);
			}
			//driveSystem.tankDrive(speedStepL.SpeedStepper(gamepadr.getY()), speedStepR.SpeedStepper(gamepad.getTwist()));
			driveSystem.tankDrive(gamepad.getY()*0.9, gamepad.getRawAxis(3)*0.9);
		}
	}
}