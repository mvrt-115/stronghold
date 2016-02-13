package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Code for the motors that control the angle of the shooter.
 *
 *@author Ben Cuan, Nolan Nguyen, and Heather Baker
 */


public class Angler extends Subsystem {

	private CANTalon[] shooterAngler = new CANTalon[2];
	private final int LEFT = 0;
	private final int RIGHT = 1;

	private RobotDrive angler;
	private Encoder encoderLeft;
	private Encoder encoderRight;

	private AnalogInput bottomHall, topHall, shootHall;

	private final double HALL_EFFECT_ACTIVE = 3.00;
	private final double TICKS_PER_DEGREE = 1024/360;

	public Angler() {
		shooterAngler[LEFT] = new CANTalon(RobotMap.SHOOTER_ANGLER_LEFT_MOTOR);
		shooterAngler[RIGHT] = new CANTalon(RobotMap.SHOOTER_ANGLER_RIGHT_MOTOR);

		encoderLeft = new Encoder(RobotMap.ENCODER_ANGLER_LEFT_A, RobotMap.ENCODER_ANGLER_LEFT_B, false, Encoder.EncodingType.k4X);
		encoderRight = new Encoder(RobotMap.ENCODER_ANGLER_RIGHT_A, RobotMap.ENCODER_ANGLER_RIGHT_B, false, Encoder.EncodingType.k4X);

		angler = new RobotDrive(shooterAngler[LEFT], shooterAngler[RIGHT]);

		bottomHall = new AnalogInput(RobotMap.ANGLER_BOTTOM_HALL);
		shootHall = new AnalogInput(RobotMap.ANGLER_SHOOT_HALL);
		topHall = new AnalogInput(RobotMap.ANGLER_TOP_HALL);

		resetEncoders();
	}

	public void angle(double speed) {
		angler.arcadeDrive(speed, 0);

	}

	public void stop() {
		angle(0);
	}

	public void resetEncoders() {
		encoderLeft.reset();
		encoderRight.reset();
	}

	public double getAngle() {
		return ((encoderRight.getDistance() + encoderLeft.getDistance()) / (2 * TICKS_PER_DEGREE));
	}

	public boolean isShootHall() {
		return (shootHall.getVoltage() >= HALL_EFFECT_ACTIVE);
			
	}

	public boolean isBottomHallTrue() {
		return (bottomHall.getVoltage() >= HALL_EFFECT_ACTIVE);
	}

	public boolean isTopHallTrue() {
		return (topHall.getVoltage() >= HALL_EFFECT_ACTIVE);
	}

	public void initDefaultCommand() {

	}
}

