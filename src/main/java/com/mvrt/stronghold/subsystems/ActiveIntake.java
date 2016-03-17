package com.mvrt.stronghold.subsystems;

import com.mvrt.stronghold.Constants;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by Ishan on 3/14/2016.
 */
public class ActiveIntake extends Subsystem{

  private CANTalon intakeMotor;

  public ActiveIntake(){
    intakeMotor = new CANTalon(Constants.kIntakeTalonId);
  }

  public void setOutput(double speed){
    intakeMotor.set(speed);
  }

  @Override
  protected void initDefaultCommand() {

  }
}
