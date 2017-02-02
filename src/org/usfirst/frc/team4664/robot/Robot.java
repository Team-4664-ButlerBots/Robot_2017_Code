package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.Commands.Commands;
import org.usfirst.frc.team4664.Subsystem.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
public class Robot extends IterativeRobot {
	public static Gyro skyLine;
	public static DriveTrain driveTrain;
    public static Commands commandList;
    public static RangeFinder headset;
    public static Camera possibleWorlds;
    final int rangeFinderPort = 1;
    public Robot() {
    	skyLine = new Gyro(0);
    	driveTrain = new DriveTrain();
    	commandList = new Commands();
    	headset = new RangeFinder(1, 1);
    	possibleWorlds = new Camera();
    }
	@Override
	public void disabledInit() {

	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
    public void robotInit(){
    }
    public void robotPeriodic(){
		if(commandList.command != commandList.commandChooser.getSelected()){		//checks for change on SmartDashboard CommandList
			commandList.command = commandList.commandChooser.getSelected();			//sets command to the selected command.
			commandList.command.start();											//starts command
		}
		else if(IO.getActiveButtons() != 0){										//checks for a button being depressed
			commandList.switchButtons();
		}
		possibleWorlds.run();
	}
    @Override
	public void autonomousInit(){
    	commandList.command = commandList.commandChooser.getSelected();
    	if(commandList.command != null){
    		commandList.command.start();
    	}
    }
    @Override
    public void autonomousPeriodic(){
    	Scheduler.getInstance().run();
    }
    @Override
    public void teleopInit(){
    }
    public void teleopPeriodic(){
    	Scheduler.getInstance().run();
    }
	public void testPeriodic() {
		LiveWindow.run();
	}
}