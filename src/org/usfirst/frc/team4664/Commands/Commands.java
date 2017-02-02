package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.IO;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Commands {
    public SendableChooser<Command> commandChooser;
    public Command command;
    public Commands(){
    	commandChooser = new SendableChooser<>();
    	display();
    }
    public void display(){
    	SmartDashboard.putNumber("Distance", 0.00);
    	commandChooser.addDefault("Default Drive", new JoyDrive());
    	commandChooser.addObject("Go Forward", new GoForward());
    	commandChooser.addObject("Go Backward", new GoBackward());
    	commandChooser.addObject("Turn Left", new TurnLeft());
    	commandChooser.addObject("Turn Right", new TurnRight());
    	commandChooser.addObject("Update Sensitivity", new UpdateSensitivityFactor(SmartDashboard.getNumber("Distance", 0.00)));
    	SmartDashboard.putData("Commands", commandChooser);
    }
    public boolean checkForChange(int buttonNumber){
    	if(Robot.commandList.command != Robot.commandList.commandChooser.getSelected()){
    		return true;										//checks for change on the SmartDashboard
    	}
    	else if(IO.getActiveButtons() != 0 && IO.getActiveButtons() != buttonNumber){
    		return true;										//checks for a button not corresponding to the command button being depressed
    	}
    	else{
    		return false;
    	}
    }
    public void switchButtons(){
		switch(IO.getActiveButtons()){
			case 1:
				Robot.commandList.command = new JoyDrive();
				break;
			case 2:
				Robot.commandList.command = new GoForward();
				break;
			case 3:
				Robot.commandList.command = new GoBackward();
				break;
			case 4:
				Robot.commandList.command = new TurnLeft();
				break;
			case 5:
				Robot.commandList.command = new TurnRight();
				break;
			case 6:
				Robot.commandList.command = new UpdateSensitivityFactor(SmartDashboard.getNumber("Distance", 0.00));
				break;
			default:
				break;
		}
    }
}
