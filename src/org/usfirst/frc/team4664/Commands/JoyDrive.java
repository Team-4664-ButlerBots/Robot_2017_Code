package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoyDrive extends Command{
    final int joy1Port	= 0;
    final int joy2Port  = 1;
    public Joystick joy1, joy2;
	public JoyDrive(){
    	requires(Robot.driveTrain);
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
	}
    protected void initialize(){
    }
    protected void execute(){
    	Robot.driveTrain.drive(joy1.getX(), joy1.getY());
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    }
}