package com.mvrt.lib;

/**
 * An abstract representation of a state-space controller.
 *
 * @author Nicholas Liskij
 */
public abstract class StateSpaceController {
  public int numInputs;
  public int numOutputs;
  public int numStates;
  boolean init = false;
  public double deltaT;

  // All matrices calculated with scipy and slycot in python
  // There's also a lot of extras for when observers and other fancy things exist
  protected DoubleMatrix matrixA;
  protected DoubleMatrix matrixB;
  protected DoubleMatrix matrixC;
  protected DoubleMatrix matrixD;
  protected DoubleMatrix matrixL;
  protected DoubleMatrix matrixK;
  protected DoubleMatrix matrixX;
  protected DoubleMatrix matrixXHat;
  protected DoubleMatrix matrixU;
  protected DoubleMatrix matrixRawU;
  protected DoubleMatrix matrixMaxU;
  protected DoubleMatrix matrixMinU;

  public StateSpaceController(int inputCount, int outputCount, int stateCount, DoubleMatrix a,
      DoubleMatrix b, DoubleMatrix c, DoubleMatrix d, DoubleMatrix l, DoubleMatrix k,
      DoubleMatrix minU, DoubleMatrix maxU, double dt) {
    numInputs = inputCount;
    numOutputs = outputCount;
    numStates = stateCount;
    deltaT = dt;

    // Sets sizes for the matrices
    matrixA = new DoubleMatrix(numStates, numStates, a.getArray());
    matrixB = new DoubleMatrix(numOutputs, numStates, b.getArray());
    matrixC = new DoubleMatrix(numStates, numOutputs, c.getArray());
    matrixD = new DoubleMatrix(numOutputs, numOutputs, d.getArray());
    matrixL = new DoubleMatrix(numOutputs, numStates, l.getArray());
    matrixK = new DoubleMatrix(numStates, numOutputs, k.getArray());
    matrixX = new DoubleMatrix(1, numStates);
    matrixXHat = new DoubleMatrix(1, numStates);
    matrixU = new DoubleMatrix(1, numOutputs);
    matrixRawU = new DoubleMatrix(1, numOutputs, a.getArray());
    matrixMinU = new DoubleMatrix(1, numOutputs, minU.getArray());
    matrixMaxU = new DoubleMatrix(1, numOutputs, maxU.getArray());
  }

  public void updateStates(DoubleMatrix matrixR, DoubleMatrix matrixY) {
    DoubleMatrix distanceFromGoal = DoubleMatrix.subtract(matrixR, matrixXHat);
    matrixU = DoubleMatrix.multiply(matrixK, distanceFromGoal);
    matrixU.clip(matrixMinU.get(0, 0),
        matrixMaxU.get(0, 0)); // Only for 1x1 input, but good enough for now

    DoubleMatrix coefficientXHatToY =
        DoubleMatrix.subtract(matrixC, DoubleMatrix.multiply(matrixD, matrixK));
    DoubleMatrix predictedY = DoubleMatrix.multiply(coefficientXHatToY, matrixXHat);
    DoubleMatrix scaledError =
        DoubleMatrix.multiply(matrixL, DoubleMatrix.subtract(matrixY, predictedY));
    DoubleMatrix coefficientXHat =
        DoubleMatrix.subtract(matrixA, DoubleMatrix.multiply(matrixB, matrixK));
    matrixXHat = DoubleMatrix.add(DoubleMatrix.multiply(coefficientXHat, matrixXHat), scaledError);
  }
}
