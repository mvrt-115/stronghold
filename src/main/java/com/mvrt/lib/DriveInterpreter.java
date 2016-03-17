package com.mvrt.lib;


import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Class which interprets drive commands into actual motor output.
 * <br>
 * Can be used for a multitude of drive styles.
 *
 * @author Lee Mracek
 */
public class DriveInterpreter {

  private static final double SENSITIVITY = 0.75;

  /**
   * Stop the {@link RobotDrive}.
   */
  public static DriveSignal stop() {
    return DriveSignal.NEUTRAL;
  }

  /**
   * Drive the {@link RobotDrive} in an Arcade drive style.
   *
   * @param driveSpeed the forward drive speed signal
   * @param turnSpeed  the turn speed signal
   */
  public static DriveSignal arcade(double driveSpeed, double turnSpeed) {
    return arcade(driveSpeed, turnSpeed, true);
  }


  /**
   * Drive the {@link RobotDrive} in an Arcade drive style.
   *
   * @param driveSpeed    the forward drive speed signal
   * @param turnSpeed     the turn speed signal
   * @param squaredInputs whether or not the inputs should be squared for linearity
   */
  public static DriveSignal arcade(double driveSpeed, double turnSpeed, boolean squaredInputs) {
    double leftMotorSpeed;
    double rightMotorSpeed;

    if (squaredInputs) {
      squareInputs(driveSpeed);

      squareInputs(turnSpeed);
    }

    if (driveSpeed > 0.0) {
      if (turnSpeed > 0.0) {
        leftMotorSpeed = driveSpeed - turnSpeed;
        rightMotorSpeed = Math.max(driveSpeed, turnSpeed);
      } else {
        leftMotorSpeed = Math.max(driveSpeed, -turnSpeed);
        rightMotorSpeed = driveSpeed + turnSpeed;
      }
    } else {
      if (turnSpeed > 0.0) {
        leftMotorSpeed = -Math.max(-driveSpeed, turnSpeed);
        rightMotorSpeed = driveSpeed + turnSpeed;
      } else {
        leftMotorSpeed = driveSpeed - turnSpeed;
        rightMotorSpeed = -Math.max(-driveSpeed, -turnSpeed);
      }
    }

    return new DriveSignal(leftMotorSpeed, rightMotorSpeed);
  }

  private static double squareInputs(double speed) {
    return Math.signum(speed) * speed * speed;
  }

  /**
   * Drive the {@link RobotDrive} in a tank configuration.
   *
   * @param leftSpeed     the left signal for the drive
   * @param rightSpeed    the right signal of the drive
   * @param squaredInputs whether or not the drive inputs should be squared
   */
  public static DriveSignal tank(double leftSpeed, double rightSpeed, boolean squaredInputs) {
    if (squaredInputs) {
      leftSpeed = squareInputs(leftSpeed);

      rightSpeed = squareInputs(rightSpeed);
    }

    return tank(leftSpeed, rightSpeed);
  }

  /**
   * Drive the {@link RobotDrive} in a tank configuration.
   *
   * @param leftSpeed  the left signal for the drive
   * @param rightSpeed the right signal for the drive
   */
  public static DriveSignal tank(double leftSpeed, double rightSpeed) {
    return new DriveSignal(leftSpeed, rightSpeed);
  }

  private static double oldWheel = 0.0;
  private static double negativeInertiaAccumulator = 0.0;
  private static double quickStopAccumulator = 0.0;

  /**
   * Drive the {@link RobotDrive} in the Austin/Cheesy style. Originally pioneered by Austin
   * Schuh in 2008, this style of drive has been stolen 254's public repos by practically every
   * single team.
   *
   * @param throttle  the throttle to drive with
   * @param wheel     the wheel to drive with
   * @param quickturn whether or not to apply quickturn
   */
  public static DriveSignal austinDrive(double throttle, double wheel, boolean quickturn) {
    double negativeInertia = wheel - oldWheel;
    oldWheel = wheel;

    double wheelNonLinearity = 0.6; // tune this
    wheel = dampen(wheel, wheelNonLinearity);
    wheel = dampen(wheel, wheelNonLinearity);

    double leftPwm, rightPwm, overPower = 0;
    double sensitivity = SENSITIVITY;

    double angularPower;
    double linearPower;

    double negativeInertiaScalar;

    if (wheel * negativeInertia > 0) {
      negativeInertiaScalar = 2.5;
    } else {
      if (Math.abs(wheel) > 0.65) {
        negativeInertiaScalar = 5.0;
      } else {
        negativeInertiaScalar = 3.0;
      }
    }

    double negativeInertiaPower = negativeInertia * negativeInertiaScalar;
    negativeInertiaAccumulator += negativeInertiaPower;

    wheel += negativeInertiaPower;

    if (negativeInertiaAccumulator > 1) {
      negativeInertiaAccumulator -= 1;
    } else if (negativeInertiaAccumulator < -1) {
      negativeInertiaAccumulator += 1;
    } else {
      negativeInertiaAccumulator = 0;
    }

    linearPower = throttle;

    if (quickturn) {
      if (Math.abs(linearPower) > 0.2) {
        double alpha = 0.1;
        quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * wheel * 5;
      }
      overPower = 1.0;
      sensitivity = 1.0;
      angularPower = wheel;
    } else {
      overPower = 0.0;
      angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
      if (quickStopAccumulator > 1) {
        quickStopAccumulator -= 1.0;
      } else if (quickStopAccumulator < -1) {
        quickStopAccumulator += 0.0;
      } else {
        quickStopAccumulator = 0.0;
      }
    }

    rightPwm = leftPwm = linearPower;
    leftPwm += angularPower;
    rightPwm -= angularPower;

    if (leftPwm > 1.0) {
      rightPwm -= overPower * (leftPwm - 1.0);
      leftPwm = 1.0;
    } else if (rightPwm > 1.0) {
      leftPwm -= overPower * (rightPwm - 1.0);
      rightPwm = 1.0;
    } else if (leftPwm < -1.0) {
      rightPwm += overPower * (-1.0 - leftPwm);
      leftPwm = -1.0;
    } else if (rightPwm < -1.0) {
      leftPwm += overPower * (-1.0 - rightPwm);
      rightPwm = -1.0;
    }

    return new DriveSignal(leftPwm, rightPwm);
  }

  private static double dampen(double wheel, double wheelNonLinearity) {
    double factor = Math.PI * wheelNonLinearity;
    return Math.sin(factor * wheel) / Math.sin(factor);
  }
}
