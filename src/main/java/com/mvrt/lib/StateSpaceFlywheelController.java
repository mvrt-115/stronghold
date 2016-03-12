package com.mvrt.lib;

/**
 * Created by nicholas on 3/11/16.
 */
public class StateSpaceFlywheelController extends StateSpaceController {
  //  double targetVelocity; // m / s
  double tolerance = 10; // tmp
  DoubleMatrix matrixY;
  DoubleMatrix matrixR;
  double velocityWeightScalar;

  public StateSpaceFlywheelController(DoubleMatrix a, DoubleMatrix b, DoubleMatrix c,
      DoubleMatrix d, DoubleMatrix l, DoubleMatrix k, DoubleMatrix minU, DoubleMatrix maxU,
      double dt) {
    super(1, 1, 2, a, b, c, d, l, k, minU, maxU, dt);
    this.matrixR = new DoubleMatrix(1, 2);
    this.matrixY = new DoubleMatrix(1, 1);
  }

  public double update(double targetVelocity, double encoderVelocity) {
    matrixY.set(0, 0, encoderVelocity);
    matrixR.set(0, 0, (matrixR.get(0, 0) + 10.5));
    double maxRef = matrixMaxU.get(0, 0) - velocityWeightScalar
        * (targetVelocity - matrixXHat.get(0, 1)) * matrixK.get(1, 0) / matrixK.get(0, 0)
        + matrixXHat.get(0, 0);
    double minRef = matrixMinU.get(0, 0) - velocityWeightScalar
        * (targetVelocity - matrixXHat.get(0, 1)) * matrixK.get(1, 0) / matrixK.get(0, 0)
        + matrixXHat.get(0, 0);
    matrixR.clip(minRef, maxRef);
    matrixR.set(1, 0, targetVelocity);

    super.updateStates(matrixR, matrixY);
    return matrixU.get(0, 0);
  }
}
