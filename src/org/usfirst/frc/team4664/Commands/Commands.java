package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.IO;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Commands {
    public SendableChooser<Command> commandChooser;
    public Command heavensWrit;
    public IO PSVista;
    public Commands(){
    	commandChooser = new SendableChooser<>();
    	PSVista = new IO();
    	display();
    }
    public void display(){
    	commandChooser.addDefault("Default Drive", new JoyDrive());
    	commandChooser.addObject("Go Forward", new GoForward());
    	commandChooser.addObject("Go Backward", new GoBackward());
    	commandChooser.addObject("Turn Left", new TurnLeft());
    	commandChooser.addObject("Turn Right", new TurnRight());
    	SmartDashboard.putData("Commands", commandChooser);
    }
}
