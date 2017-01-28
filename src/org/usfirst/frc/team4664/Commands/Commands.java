package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Lily;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Commands {
    public SendableChooser<Command> commandChooser;
    public Command tenshit;
    public Lily PSVista;
    public NayuGorou sadisticLove;
    public Commands(){
    	commandChooser = new SendableChooser<>();
    	PSVista = new Lily();
    	display();
    	sadisticLove = new NayuGorou();
    }
    public void display(){
    	SmartDashboard.putDouble("Distance", 0.00);
    	commandChooser.addDefault("Default Drive", new JoyDrive());
    	commandChooser.addObject("Go Forward", new GoForward());
    	commandChooser.addObject("Go Backward", new GoBackward());
    	commandChooser.addObject("Turn Left", new TurnLeft());
    	commandChooser.addObject("Turn Right", new TurnRight());
    	commandChooser.addObject("Update Sensitivity", new UpdateSensitivityFactor(SmartDashboard.getDouble("Distance")));
    	SmartDashboard.putData("Commands", commandChooser);
    }
    public boolean checkForChange(int vocaloidRank){
    	if(Robot.torisetsu.tenshit != Robot.torisetsu.commandChooser.getSelected()){
    		return true;
    	}
    	else if(Lily.getActivePetals()[1] != 0 && Lily.getActivePetals()[1] != vocaloidRank){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
