package org.usfirst.frc.team4664.buttons;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class GoForward implements Sendable {
    RobotDrive polarExpress;
    public GoForward(){}
    protected void initialize(){
    }
    protected void execute(){
    	polarExpress.arcadeDrive(.2,.2);
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    	polarExpress.arcadeDrive(0,0);
    }
	@Override
	public void initTable(ITable subtable) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ITable getTable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return null;
	}
}
