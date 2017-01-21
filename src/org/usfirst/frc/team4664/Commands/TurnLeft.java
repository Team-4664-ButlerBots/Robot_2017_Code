package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.GyroM;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class TurnLeft extends Command{
    GyroM gyro = new GyroM(0);
    protected void initialize(){
    	requires(Robot.driveTrain);
    }
    protected void execute(){
    	Robot.driveTrain.drive(-.2,.2);
    }
    protected boolean isFinished(){
    	double placeholder = gyro.getAngle();
    	return Math.abs(placeholder - gyro.getAngle()) < 90;
    }
    protected void end(){
    	Robot.driveTrain.drive(0,0);
    }
}
