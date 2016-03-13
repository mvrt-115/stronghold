package com.mvrt.lib;

import com.mvrt.lib.Values;

/**
 * Helpful code to calculate the motor command value for a given position and velocity.
 *
 * @author Lee Mracek
 */
public class TrajectoryFollower1D {

  /**
   * Class containing configuration information for a TrajectoryFollower1D.
   */
  public static class TrajectoryConfig {
    /**
     * The time period (in seconds).
     */
    public double dt;

    /**
     * The maximum acceleration (in units per second^2).
     */
    public double maxAcceleration;

    /**
     * The maximum velocity (in units per second).
     */
    public double maxVelocity;

    @Override
    public String toString() {
      return "dt: " + dt + ", Max Acc: " + maxAcceleration + ", Max Vel: " + maxVelocity;
    }
  }


  /**
   * Class representing a simple setpoint as part of a trajectory.
   */
  public static class TrajectorySetpoint {
    /**
     * The current position.
     */
    public double position;

    /**
     * The current velocity.
     */
    public double velocity;

    /**
     * The current acceleration.
     */
    public double acceleration;

    @Override
    public String toString() {
      return "Position: " + position + ", Velocity: " + velocity + " , Acceleration: "
          + acceleration;
    }
  }

  private double kP, kI, kD, kV, kA, lastError, errorSum;
  private double maxOutput = 1.0, minOutput = -1.0;

  private boolean reset = true;
  private double lastTimestamp;
  private TrajectorySetpoint nextState = new TrajectorySetpoint();

  private TrajectoryConfig config = new TrajectoryConfig();
  private double goalPosition;
  private TrajectorySetpoint currentState = new TrajectorySetpoint();

  /**
   * Initialize a TrajectoryFollower1D with the given constants.
   *
   * @param kP the proportional constant
   * @param kI the integral constant
   * @param kD the derivative constant
   * @param kV the velocity constant
   * @param kA the acceleration constant
   * @param config the given {@link TrajectoryConfig}.
   */
  public void initialize(double kP, double kI, double kD, double kV, double kA,
      TrajectoryConfig config) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kV = kV;
    this.kA = kA;
    this.config = config;
  }

  /**
   * Set the goal for this TrajectoryFollower1D.
   *
   * @param currentState the current setpoint of the robot in motion
   * @param goalPosition the goal position of the robot
   */
  public void setGoal(TrajectorySetpoint currentState, double goalPosition) {
    this.goalPosition = goalPosition;
    this.currentState = currentState;
    reset = true;
    errorSum = 0F;
  }

  /**
   * Retrieve the current goal position of the follower.
   *
   * @return the goal position as a double.
   */
  public double getGoal() {
    return goalPosition;
  }

  /**
   * Set the configuration of the follower.
   *
   * @param config the configuration to set it to.
   */
  public void setConfig(TrajectoryConfig config) {
    this.config = config;
  }

  /**
   * Calculate the motor command based on the position and velocity.
   *
   * @param position the current position
   * @param velocity the current velocity
   * @return the calculated motor command
   */
  public double calculate(double position, double velocity) {
    double dt = config.dt;

    if (isFinishedTrajectory()) {
      currentState.position = position;
      currentState.velocity = 0;
      currentState.acceleration = 0;
    } else {
      double distanceToGo = goalPosition - currentState.position;
      double currentVelocity = currentState.velocity;
      double currentVelocitySqr = currentVelocity * currentVelocity;
      boolean inverted = false;

      if (distanceToGo < 0) {
        inverted = true;
        distanceToGo *= -1;
        currentVelocity *= -1;
      }

      double maxReachableVelocityDisc =
          currentVelocitySqr / 2.0 + config.maxAcceleration * distanceToGo;
      double minReachableVelocityDisc =
          currentVelocitySqr / 2.0 - config.maxAcceleration * distanceToGo;

      double cruiseVelocity = currentVelocity;

      if (minReachableVelocityDisc < 0 || cruiseVelocity < 0) {
        cruiseVelocity = Math.min(config.maxVelocity, Math.sqrt(maxReachableVelocityDisc));
      }

      double tStart = (cruiseVelocity - currentVelocity) / config.maxAcceleration;
      double xStart = currentVelocity * tStart + .5 * config.maxAcceleration * tStart * tStart;

      double tEnd = Math.abs(cruiseVelocity / config.maxAcceleration);
      double xEnd = cruiseVelocity * tEnd - 0.5 * config.maxAcceleration * tEnd * tEnd;

      double xCruise = Math.max(0, distanceToGo - xStart - xEnd);
      double tCruise = Math.abs(xCruise / cruiseVelocity);

      if (tStart >= dt) {
        nextState.position = currentVelocity * dt + .5 * config.maxAcceleration * dt * dt;
        nextState.velocity = currentVelocity + config.maxAcceleration * dt;
        nextState.acceleration = config.maxAcceleration;
      } else if (tStart + tCruise >= dt) {
        nextState.position = xStart + cruiseVelocity * (dt - tStart);

        nextState.velocity = cruiseVelocity;

        nextState.acceleration = 0;
      } else if (tStart + tCruise + tEnd >= dt) {
        double deltaT = dt - tStart - tCruise;
        nextState.position = xStart + xCruise + cruiseVelocity * deltaT
            - 0.5 * config.maxAcceleration * deltaT * deltaT;
        nextState.velocity = cruiseVelocity - config.maxAcceleration * deltaT;
        nextState.acceleration = -config.maxAcceleration;
      } else {
        nextState.position = distanceToGo;
        nextState.velocity = 0;
        nextState.acceleration = 0;
      }

      if (inverted) {
        nextState.position *= -1;
        nextState.velocity *= -1;
        nextState.acceleration *= -1;
      }

      currentState.position += nextState.position;
      currentState.velocity = nextState.velocity;
      currentState.acceleration = nextState.acceleration;
    }

    double error = currentState.position - position;
    if (reset) {
      reset = false;
      lastError = error;
      errorSum = error;
    }

    double output = kP * error + kD * ((error - lastError) / dt - currentState.velocity) + (
        kV * currentState.velocity + kA * currentState.acceleration);
    if (output < maxOutput && output > minOutput) {
      errorSum += error * dt;
    }

    output += kI * errorSum;

    lastError = error;
    return output;
  }

  /**
   * Retrieve the current setpoint.
   *
   * @return the current setpoint
   */
  public TrajectoryFollower1D.TrajectorySetpoint getCurrentSetpoint() {
    return this.currentState;
  }

  /**
   * Retrieve the configuration of this TrajectoryFollower1D.
   *
   * @return the config
   */
  public TrajectoryConfig getConfig() {
    return config;
  }

  /**
   * Has the TrajectoryFollower1D completed the trajectory.
   *
   * @return true if the trajectory is completed
   */
  public boolean isFinishedTrajectory() {
    return Values.fuzzyCompare(currentState.position - goalPosition, 0, 10) == 0
        && Values.fuzzyCompare(currentState.velocity, 0, 6) == 0;
  }
}
