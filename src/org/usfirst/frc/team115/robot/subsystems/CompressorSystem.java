package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Nolan Nguyen
 * @edited Rithvik Chuppala
 *
 */
public class CompressorSystem extends Subsystem{
	private Compressor compressor;
	public CompressorSystem() {
		compressor = new Compressor(RobotMap.PCM);
		compressor.setClosedLoopControl(true);
		compressor.start();
	}

	public void log() {
		SmartDashboard.putBoolean("Compressor Enabled? ", compressor.enabled());
		SmartDashboard.putNumber("Compressor Current: ", compressor.getCompressorCurrent());
		SmartDashboard.putBoolean("Pressure Switch Value: ", compressor.getPressureSwitchValue());
	}

	protected void initDefaultCommand() {
	}
}
