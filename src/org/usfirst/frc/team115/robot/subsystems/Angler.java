package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan, Nolan Nguyen, and Heather Baker
 */


public class Angler extends Subsystem {
	private static final double HALL_EFFECT_ACTIVE = 3.00;
	private static final double TICKS_PER_DEGREE = 1.00;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;	

	private CANTalon[] shooterAngler = new CANTalon[2];
	private RobotDrive angler;
	private AnalogInput bottomHall, topHall, shootHall;

	public static final double PRESET_INTAKE = 0;
	public static final double PRESET_SHOOT_BATTER = 90 * TICKS_PER_DEGREE;
	public static final double PRESET_LOW_BAR = 45 * TICKS_PER_DEGREE;
	public static final double[] presets = {PRESET_INTAKE, PRESET_SHOOT_BATTER, PRESET_LOW_BAR};

	public Angler() {
		shooterAngler[LEFT] = new CANTalon(RobotMap.SHOOTER_ANGLER_LEFT_MOTOR);
		shooterAngler[RIGHT] = new CANTalon(RobotMap.SHOOTER_ANGLER_RIGHT_MOTOR);

		angler = new RobotDrive(shooterAngler[LEFT], shooterAngler[RIGHT]);

		bottomHall = new AnalogInput(RobotMap.ANGLER_BOTTOM_HALL);
		shootHall = new AnalogInput(RobotMap.ANGLER_SHOOT_HALL);
		topHall = new AnalogInput(RobotMap.ANGLER_TOP_HALL);

		for(CANTalon sa: shooterAngler) {
			sa.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}

		resetEncoders();
	}

	public void setOutput(double speed) {
		angler.arcadeDrive(speed, 0);
	}

	public void stop() {
		setOutput(0);
	}

	public void resetEncoders() {
		for(CANTalon m:shooterAngler) {
			m.reset();
		}
	}

	public double getAngle() {
		return ((shooterAngler[LEFT].getPosition() + shooterAngler[RIGHT].getPosition()) / (2 * TICKS_PER_DEGREE));
	}

	public boolean isShootHall() {
		return shootHall.getVoltage() >= HALL_EFFECT_ACTIVE;
	}

	public boolean isBottomHallTrue() {
		return bottomHall.getVoltage() >= HALL_EFFECT_ACTIVE;
	}

	public boolean isTopHallTrue() {
		return topHall.getVoltage() >= HALL_EFFECT_ACTIVE;
	}

	public void initDefaultCommand() {}
}

