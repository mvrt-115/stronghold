package com.mvrt.lib;

/**
 * Controller to make the robot turn in place.
 *
 * @author Lee Mracek
 */
public class TurnInPlaceController {
  private final TrajectoryFollowingPositionController1D controller;
  private final DriveState setpointRelativeDriveState;

  public TurnInPlaceController(DriveState priorState, double destHeading, double velocity,
      double dt, double maxAcceleration, double maxVelocity, PidConstants pid, double kV, double kA,
      double onTargetError) {
    TrajectoryFollower1D.TrajectoryConfig config = new TrajectoryFollower1D.TrajectoryConfig();
    config.dt = dt;
    config.maxVelocity = maxVelocity;
    config.maxAcceleration = maxAcceleration;
    controller =
        new TrajectoryFollowingPositionController1D(pid.kP, pid.kI, pid.kD, kV, kA, onTargetError,
            config);
    TrajectoryFollower1D.TrajectorySetpoint initialSetpoint =
        new TrajectoryFollower1D.TrajectorySetpoint();
    initialSetpoint.position = priorState.getHeading();
    initialSetpoint.velocity = priorState.getHeadingVelocity();

    setpointRelativeDriveState = priorState;
  }

  public DriveSignal update(DriveState currentState) {
    controller.update(currentState.getHeading(), currentState.getHeadingVelocity());
    double turn = controller.retrieve();
    return new DriveSignal(turn, -turn);
  }

  public DriveState getCurrentState() {
    TrajectoryFollower1D.TrajectorySetpoint setpoint = controller.getSetpoint();

    // this is gross and technically incorrect
    return new DriveState(setpointRelativeDriveState.getLeftDistance(),
        setpointRelativeDriveState.getRightDistance(), setpointRelativeDriveState.getLeftVelocity(),
        setpointRelativeDriveState.getRightVelocity(), setpoint.position, setpoint.velocity);
  }

  public boolean isOnTarget() {
    return controller.isOnTarget();
  }

  public double getHeadingGoal() {
    return controller.getGoal();
  }
}
