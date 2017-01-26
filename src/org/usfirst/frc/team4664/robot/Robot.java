package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.Commands.GoBackward;
import org.usfirst.frc.team4664.Commands.GoForward;
import org.usfirst.frc.team4664.Commands.JoyDrive;
import org.usfirst.frc.team4664.Commands.TurnLeft;
import org.usfirst.frc.team4664.Commands.TurnRight;
import org.usfirst.frc.team4664.Subsystem.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
public class Robot extends IterativeRobot {
	public static GyroM sana;
	public static DriveTrain driveTrain;
    final int rangeFinderPort = 1;
    SendableChooser<Command> commandChooser;
    Command heavensWrit;
    IO PSVista;
    public Robot() {
    	sana = new GyroM(0);
    	driveTrain = new DriveTrain();
    	commandChooser = new SendableChooser<>();
    }
	@Override
	public void disabledInit() {

	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
    public void robotInit(){
    	commandChooser.addDefault("Default Drive", new JoyDrive());
    	commandChooser.addObject("Go Forward", new GoForward());
    	commandChooser.addObject("Go Backward", new GoBackward());
    	commandChooser.addObject("Turn Left", new TurnLeft());
    	commandChooser.addObject("Turn Right", new TurnRight());
    	SmartDashboard.putData("Commands", commandChooser);
    }
    
    void AutonomousInit(){
    	heavensWrit = commandChooser.getSelected();
    	while(true){
    		heavensWrit = commandChooser.getSelected();
    	if(heavensWrit != null){
    		heavensWrit.start();
    	}
    }}
    @Override
	public void autonomousInit(){
    	while(true){
    		heavensWrit = commandChooser.getSelected();
    		if(heavensWrit != null){
    			heavensWrit.start();
    		}
    	}
    }
    @Override
    public void autonomousPeriodic(){
    	Scheduler.getInstance().run();
    }
    @Override
    public void teleopInit(){
    	if(heavensWrit != commandChooser.getSelected()){
    		heavensWrit.cancel();
    		heavensWrit = commandChooser.getSelected();
    		heavensWrit.start();
    	}
    	else{
    		for(int i = 0; i < PSVista.getActiveButtons().length; i++){
        		switch(PSVista.getActiveButtons()[i]){
        			case 0:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new GoForward();
        	    		heavensWrit.start();
        				break;
        			case 1:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new GoBackward();
        	    		heavensWrit.start();
        				break;
        			case 2:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new TurnLeft();
        	    		heavensWrit.start();
        				break;
        			case 3:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new TurnRight();
        	    		heavensWrit.start();
        				break;
        			case 4:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new JoyDrive();
        	    		heavensWrit.start();
        				break;
        			default:
        	    		heavensWrit.cancel();
        	    		heavensWrit = new JoyDrive();
        	    		heavensWrit.start();
        				break;
        		}
    		}
    	}
    }
    public void teleopPeriodic(){
    	Scheduler.getInstance().run();
    }
	public void testPeriodic() {
		LiveWindow.run();
	}
}