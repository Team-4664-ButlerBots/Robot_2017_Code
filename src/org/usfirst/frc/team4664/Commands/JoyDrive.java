package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.IO;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoyDrive extends Command{
	public JoyDrive(){
    	requires(Robot.driveTrain);
	}
    protected void initialize(){
    }
    protected void execute(){
    	Robot.driveTrain.arcadeDrive(IO.getStickX(), IO.getStickY());
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    }
    protected void interrupted() {
    }
}