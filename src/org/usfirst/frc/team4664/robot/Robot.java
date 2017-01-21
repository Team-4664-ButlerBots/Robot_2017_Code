package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.Commands.GoBackward;
import org.usfirst.frc.team4664.Commands.GoForward;
import org.usfirst.frc.team4664.Commands.JoyDrive;
import org.usfirst.frc.team4664.Commands.TurnLeft;
import org.usfirst.frc.team4664.Commands.TurnRight;
import org.usfirst.frc.team4664.Subsystem.DriveTrain;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
public class Robot extends SampleRobot {
	public GyroM sana;
	public static DriveTrain driveTrain;
    final int rangeFinderPort = 1;
    SendableChooser<Command> commandChooser;
    Command heavensWrit;
    public Robot() {
    	sana = new GyroM(0);
    	driveTrain = new DriveTrain();
    	commandChooser = new SendableChooser<>();
    }
    public void robotInit(){
    	commandChooser.addDefault("Default Drive", new JoyDrive());
    	commandChooser.addObject("Go Forward", new GoForward());
    	commandChooser.addObject("Go Backward", new GoBackward());
    	commandChooser.addObject("Turn Left", new TurnLeft());
    	commandChooser.addObject("Turn Right", new TurnRight());
    	SmartDashboard.putData("Commands", commandChooser);
    }
    public void operatorControl() {
        while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", sana.cantabile.getAngle());
        	sana.updateGyro();
        	/*Rangefinder getMeOutOfHere = new Rangefinder(0,1);
    		SmartDashboard.putDouble("Distance: ", getMeOutOfHere.getDistance());
			double sensitivityFactorMeasurements = 0;
			double distance = 12;
    		for(int i = 0; i < 1000; i++){
    			sensitivityFactorMeasurements += getMeOutOfHere.getDistance();
    			SmartDashboard.putDouble("Current Sensitivity Factor at " + distance + " inches",  sensitivityFactorMeasurements / (double)(i));
    		}*/
        	if(heavensWrit != commandChooser.getSelected()){
        		heavensWrit.cancel();
        		heavensWrit = commandChooser.getSelected();
        		heavensWrit.start();
        	}
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles  
        }
    }
    
    void Autonomous(){
    	heavensWrit = commandChooser.getSelected();
    	if(heavensWrit != null){
    		heavensWrit.start();
    	}
    }
    public void autonomousPeriodic(){
    	Scheduler.getInstance().run();
    }
    public void teleopPeriodic(){
    	Scheduler.getInstance().run();
    }
    void Test(){
	}
}