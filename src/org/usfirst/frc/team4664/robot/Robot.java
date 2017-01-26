package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.Commands.Commands;
import org.usfirst.frc.team4664.Subsystem.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
public class Robot extends IterativeRobot {
	public static GyroM sana;
	public static DriveTrain driveTrain;
    final int rangeFinderPort = 1;
    public static Commands init;
    public Robot() {
    	sana = new GyroM(0);
    	driveTrain = new DriveTrain();
    }
	@Override
	public void disabledInit() {

	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
    public void robotInit(){
    	init = new Commands();
    }
    public void robotPeriodic(){
		if(init.heavensWrit != init.commandChooser.getSelected()){
			init.heavensWrit = init.commandChooser.getSelected();
			init.heavensWrit.start();
		}
	}
    @Override
	public void autonomousInit(){
    	init.heavensWrit = init.commandChooser.getSelected();
    	if(init.heavensWrit != null){
    		init.heavensWrit.start();
    	}
    }
    @Override
    public void autonomousPeriodic(){
    	Scheduler.getInstance().run();
    }
    @Override
    public void teleopInit(){
    	/*else{
    		for(int i = 1; i < PSVista.getActiveButtons().length; i++){
        		switch(PSVista.getActiveButtons()[i]){
        			case 0:
        	    		heavensWrit = new GoForward();
        				break;
        			case 1:
        	    		heavensWrit = new GoBackward();
        				break;
        			case 2:
        	    		heavensWrit = new TurnLeft();
        				break;
        			case 3:
        	    		heavensWrit = new TurnRight();
        				break;
        			case 4:
        	    		heavensWrit = new JoyDrive();
        				break;
        			default:
        	    		heavensWrit = new JoyDrive();
        				break;
        		}
    		}
    		}*/
    }
    public void teleopPeriodic(){
    	Scheduler.getInstance().run();
    }
	public void testPeriodic() {
		LiveWindow.run();
	}
}