package com.mvrt.lib;

import edu.wpi.first.wpilibj.util.BoundaryException;

/**
 * Does all computation synchronously (i.e. the calculate() function must be called by the user from
 * his own thread)
 *
 * @author Siddharth Gollapudi
 */
public class SynchronousPid {

  private double kP;
  private double kI;
  private double kD;
  private double maxOutput; //max output
  private double minOutput;
  private double maxInput;
  private double minInput;
  private boolean continuous = false;
  private double previousError = 0.0;
  private double totalError = 0.0;
  private double setpoint = 0.0;
  private double error = 0.0;
  private double result = 0.0;
  private double previousInput = Double.NaN;

  /**
   * Allocate a PID object with the given constants for P, I, D.
   *
   * @param kP the proportional coefficient
   * @param kI the integral coefficient
   * @param kD the derivative coefficient
   */
  public SynchronousPid(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }

  public String getType() {
    return "PIDController";
  }


  /**
   * Read the input, calculate the output accordingly, and write to the output. This should be
   * called at a constant rate by the user (ex. in a timed thread).
   *
   * @param input the input
   * @return the calculated output
   */
  public double calculate(double input) {
    previousInput = input;
    error = setpoint - input;
    if (continuous) {
      if (Math.abs(error) > (maxInput - minInput) / 2) {
        if (error > 0) {
          error -= maxInput + minInput;
        } else {
          error += maxInput - minInput;
        }
      }
    }

    if ((error * kP < maxOutput) && (error * kP > minOutput)) {
      totalError += error;
    } else {
      totalError = 0;
    }

    result = (kP * error + kI * totalError + kD * (error - previousError));
    previousError = error;

    if (result > maxOutput) {
      result = maxOutput;
    } else if (result < minOutput) {
      result = minOutput;
    }
    return result;
  }

  /**
   * Set the PID controller gain parameters. Set the proportional, integral, and differential
   * coefficients.
   *
   * @param kP Proportional coefficient
   * @param kI Integral coefficient
   * @param kD Differential coefficient
   */
  public void setPid(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }

  /**
   * Get the Proportional coefficient.
   *
   * @return proportional coefficient
   */
  public double getP() {
    return kP;
  }

  /**
   * Get the Integral coefficient.
   *
   * @return integral coefficient
   */
  public double getI() {
    return kI;
  }

  /**
   * Get the Differential coefficient.
   *
   * @return differential coefficient
   */
  public double getD() {
    return kD;
  }

  /**
   * Return the current PID result This is always centered on zero and constrained the the max and
   * min outs.
   *
   * @return the latest calculated output
   */
  public double retrieve() {
    return result;
  }

  /**
   * Set the PID controller to consider the input to be continuous, Rather then using the max and
   * min in as constraints, it considers them to be the same point and automatically calculates the
   * shortest route to the setpoint.
   *
   * @param continuous Set to true turns on continuous, false turns off continuous
   */
  public void setContinuous(boolean continuous) {
    this.continuous = continuous;
  }

  /**
   * Set the PID controller to consider the input to be continuous, Rather then using the max and
   * min in as constraints, it considers them to be the same point and automatically calculates the
   * shortest route to the setpoint.
   */
  public void setContinuous() {
    this.setContinuous(true);
  }

  /**
   * Sets the maximum and minimum values expected from the input.
   *
   * @param minimumInput the minimum value expected from the input
   * @param maximumInput the maximum value expected from the output
   */
  public void setInputRange(double minimumInput, double maximumInput) {
    if (minimumInput > maximumInput) {
      throw new BoundaryException("Lower bound is greater than upper bound");
    }
    this.minInput = minimumInput;
    this.maxInput = maximumInput;
    setSetpoint(setpoint);
  }

  /**
   * Sets the minimum and maximum values to write.
   *
   * @param minimumOutput the minimum value to write to the output
   * @param maximumOutput the maximum value to write to the output
   */
  public void setOutputRange(double minimumOutput, double maximumOutput) {
    if (minimumOutput > maximumOutput) {
      throw new BoundaryException("Lower bound is greater than upper bound");
    }
    this.minOutput = minimumOutput;
    this.maxOutput = maximumOutput;
  }

  /**
   * Set the setpoint for the PID controller.
   *
   * @param setpoint the desired setpoint
   */
  public void setSetpoint(double setpoint) {
    if (maxInput > minInput) {
      if (setpoint > maxInput) {
        this.setpoint = maxInput;
      } else if (setpoint < minInput) {
        this.setpoint = minInput;
      } else {
        this.setpoint = setpoint;
      }
    } else {
      this.setpoint = setpoint;
    }
  }

  /**
   * Return true if the error is within the tolerance.
   *
   * @param tolerance check if on target within a tolerance
   * @return true if the error is less than the tolerance
   */
  public boolean onTarget(double tolerance) {
    return previousInput != Double.NaN && Math.abs(previousInput - setpoint) < tolerance;
  }

  /**
   * Returns the current setpoint of the PID controller.
   *
   * @return the current setpoint
   */
  public double getSetpoint() {
    return setpoint;
  }

  /**
   * Returns the current difference of the input from the setpoint.
   *
   * @return the current error
   */
  public double getError() {
    return error;
  }

  /**
   * Reset all internal terms.
   */
  public void reset() {
    previousInput = Double.NaN;
    previousError = 0;
    totalError = 0;
    result = 0;
    setpoint = 0;
  }

  /**
   * Reset the integrator term to 0.
   */
  public void resetIntegrator() {
    totalError = 0;
  }

  @Override
  public String toString() {
    String lState = "";

    lState += "Proportional Constant: " + kP + "\n";
    lState += "Integral Constant: " + kI + "\n";
    lState += "Derivative Constant: " + kD + "\n";

    return lState;
  }

}

