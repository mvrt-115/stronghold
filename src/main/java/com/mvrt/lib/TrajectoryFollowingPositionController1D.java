package com.mvrt.lib;

public class TrajectoryFollowingPositionController1D {

  private TrajectoryFollower1D follower;
  double goal, error, onTargetDelta, result = 0;

  public TrajectoryFollowingPositionController1D(double kP, double kI, double kD, double kV,
      double kA, double onTargetDelta, TrajectoryFollower1D.TrajectoryConfig config) {
    follower = new TrajectoryFollower1D();
    follower.initialize(kP, kI, kD, kV, kA, config);
    this.onTargetDelta = onTargetDelta;
  }

  /**
   * Set the goal of the TrajectoryFollower1D.
   *
   * @param currentState the current state of the robot
   * @param goal the goal position of the robot
   */
  public void setGoal(TrajectoryFollower1D.TrajectorySetpoint currentState, double goal) {
    this.goal = goal;
    follower.setGoal(currentState, goal);
  }

  /**
   * Retrieve the current goal.
   *
   * @return the current position goal
   */
  public double getGoal() {
    return follower.getGoal();
  }

  /**
   * Retrieve the configuration of the TrajectoryFollower1D.
   *
   * @return the configuration
   */
  public TrajectoryFollower1D.TrajectoryConfig getConfig() {
    return follower.getConfig();
  }

  /**
   * Update the calculated motor command (called at some constant period).
   *
   * @param position the position of the robot
   * @param velocity the velocity of the robot
   */
  public void update(double position, double velocity) {
    error = goal - position;
    result = follower.calculate(position, velocity);
  }

  public TrajectoryFollower1D.TrajectorySetpoint getSetpoint() {
    return follower.getCurrentSetpoint();
  }

  public void reset() {
    result = 0;
    error = 0;
    follower.setGoal(follower.getCurrentSetpoint(), goal);
  }

  /**
   * Retrieve the corresponding method from the TrajectoryFollower1D.
   *
   * @return true if the TrajectoryFollower1D has completed the trajectory
   */
  public boolean isFinishedTrajectory() {
    return follower.isFinishedTrajectory();
  }

  /**
   * Retrieve the calculated command value.
   *
   * @return the calculated motor output
   */
  public double retrieve() {
    return result;
  }

  public boolean isOnTarget() {
    return follower.isFinishedTrajectory() && Math.abs(error) < onTargetDelta;
  }
}
