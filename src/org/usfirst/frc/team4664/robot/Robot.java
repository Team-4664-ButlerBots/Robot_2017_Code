package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.Commands.Commands;
import org.usfirst.frc.team4664.Subsystem.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
public class Robot extends IterativeRobot {
	public static Sana flowery;
	public static DriveTrain driveTrain;
    final int rangeFinderPort = 1;
    public static Commands torisetsu;
    public Robot() {
    	flowery = new Sana(0);
    	driveTrain = new DriveTrain();
    	torisetsu = new Commands();
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
		if(torisetsu.tenshit != torisetsu.commandChooser.getSelected()){
			torisetsu.tenshit = torisetsu.commandChooser.getSelected();
			torisetsu.tenshit.start();
		}
	}
    @Override
	public void autonomousInit(){
    	torisetsu.tenshit = torisetsu.commandChooser.getSelected();
    	if(torisetsu.tenshit != null){
    		torisetsu.tenshit.start();
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