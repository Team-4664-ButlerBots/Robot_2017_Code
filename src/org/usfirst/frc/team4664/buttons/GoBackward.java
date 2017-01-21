package org.usfirst.frc.team4664.buttons;

import edu.wpi.first.wpilibj.RobotDrive;

public class GoBackward {
    RobotDrive polarExpress;
    protected void initialize(){
    }
    protected void execute(){
    	polarExpress.arcadeDrive(-.2,-.2);
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    	polarExpress.arcadeDrive(0,0);;
    }
}
