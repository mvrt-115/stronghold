package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlyWheels extends Subsystem {

  private CANTalon[] flyWheels = new CANTalon[2];
  private Encoder[] flyEncoders = new Encoder[2];
  private PIDController[] controllers = new PIDController[2];
  private final int LEFT = 0, RIGHT = 1;
  
  private final double TICKS_PER_DEGREE = 0;
  private final double MAX_SPEED = 0.75;
  private final double INTAKE_SPEED = -0.7;
  private final int P = 0, I = 0, D = 0, F = 0;
  
  public FlyWheels() {
    flyWheels[LEFT] = new CANTalon(RobotMap.SHOOTER_INTAKE_LEFT_MOTOR);
    flyWheels[RIGHT] = new CANTalon(RobotMap.SHOOTER_INTAKE_RIGHT_MOTOR);
    
    flyEncoders[LEFT] = new Encoder(RobotMap.FLYWHEEL_LEFT_ENCODER_A, RobotMap.FLYWHEEL_LEFT_ENCODER_B, false, Encoder.EncodingType.k4X);
    flyEncoders[RIGHT] = new Encoder(RobotMap.FLYWHEEL_RIGHT_ENCODER_A, RobotMap.FLYWHEEL_RIGHT_ENCODER_B, false, Encoder.EncodingType.k4X);
    
    controllers[LEFT] = new PIDController(P, I, D, F, flyEncoders[LEFT], flyWheels[LEFT]);
    controllers[RIGHT] = new PIDController(P, I, D, F, flyEncoders[RIGHT], flyWheels[RIGHT]);
    
    flyWheels[RIGHT].setInverted(true);
    
    for (PIDController controller : controllers) {
      controller.setInputRange(-MAX_SPEED, MAX_SPEED);
      controller.setOutputRange(-MAX_SPEED, MAX_SPEED);
    }
    
    resetEncoders();
  }
  
  public void drive(double speed) {
    for (CANTalon flyWheel : flyWheels) {
      flyWheel.set(speed);
    }
  }
  
  public void intake() {
    drive(INTAKE_SPEED);
  }
  
  public void stop() {
    drive(0);
  }
  
  public void resetEncoders() {
    for (Encoder flyEncoder : flyEncoders) {
      flyEncoder.reset();
    }
  }
  
  public double getAngle(int side) {
    return (flyEncoders[side].get() / (2 * TICKS_PER_DEGREE));
  }
  
  public double getAngle() {
    return (getAngle(LEFT) + getAngle(RIGHT))/2;
  }
  
  public void startPID(double speed) {
    for (PIDController controller : controllers) {
      controller.setSetpoint(speed);
      controller.enable();
    }
  }
  
  public void resetPID() {
    for (PIDController controller : controllers) {
      controller.reset();
    }
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub
    
  }

}
