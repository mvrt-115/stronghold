package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class CompressorSystem extends Subsystem{
	private Compressor compressor;
	public CompressorSystem() {
		compressor = new Compressor(RobotMap.PCM);
		compressor.setClosedLoopControl(true);
		compressor.start();
	}

	public void log() {
		SmartDashboard.putBoolean("Compressor Enabled?", compressor.enabled());
	}

	protected void initDefaultCommand() {
	}
}
