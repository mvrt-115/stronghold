package com.mvrt.lib;

import java.util.Arrays;

public class DoubleMatrix {
  private double[] data;
  private int width;
  private int height;

  /**
   * Construct a DoubleMatrix instance of the given size.
   *
   * @param width  the width of the new matrix
   * @param height the height of the new matrix
   */
  public DoubleMatrix(int width, int height) {
    this.width = width;
    this.height = height;
    data = new double[width * height];
    Arrays.fill(data, 0.0);
  }

  /**
   * Construct a new DoubleMatrix with the given single array data.
   *
   * @param height the height of the matrix
   * @param width  the width of the matrix
   * @param data   the 1D array representing the data
   */
  public DoubleMatrix(int width, int height, double[] data) {
    this.width = width;
    this.height = height;
    this.data = Arrays.copyOf(data, data.length);
  }

  /**
   * Construct a new DoubleMatrix from a 2D array. For example: {{ 0, 1 , 2, 1}, { 1, 3, 4, 0},
   * {5, 1, 6, 5}}
   * <p>
   * would get
   * </p>
   * 0 1 2 1 1 3 4 0 5 1 6 5
   *
   * @param data the 2D array containing the matrix data
   */
  public DoubleMatrix(double[][] data) {
    this.height = data.length;
    this.width = data[0].length;
    this.data = Arrays.stream(data).flatMapToDouble(Arrays::stream).toArray();
  }

  /**
   * Construct a new DoubleMatrix of the given size, initialized to 0.
   *
   * @param width  the width of the matrix
   * @param height the height of the matrix
   * @return the new DoubleMatrix
   */
  public static DoubleMatrix zeroes(int width, int height) {
    double[][] data = new double[height][width];
    for (double[] datum : data) {
      Arrays.fill(datum, 0);
    }
    return new DoubleMatrix(data);
  }

  /**
   * Construct a square DoubleMatrix of 0.
   *
   * @param dimension the dimension of the matrix
   * @return the created DoubleMatrix
   */
  public static DoubleMatrix zeroes(int dimension) {
    return zeroes(dimension, dimension);
  }

  /**
   * Construct and identity matrix with a given height and width.
   *
   * @param width  the width of the matrix
   * @param height the height of the matrix
   * @return the newly created identity matrix
   */
  public static DoubleMatrix identity(int width, int height) {
    double[][] data = new double[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        data[i][j] = j == i ? 1 : 0;
      }
    }
    return new DoubleMatrix(data);
  }

  /**
   * Construct a square identity matrix with the given dimension.
   *
   * @param dimensions the dimensions of the matrix
   * @return the new square identity matrix
   */
  public static DoubleMatrix identity(int dimensions) {
    return identity(dimensions, dimensions);
  }

  /**
   * Get the internal 1D representation of the matrix.
   *
   * @return the 1D array representation
   */
  public double[] getArray() {
    return data;
  }

  /**
   * Get a 2D representation of the matrix.
   *
   * @return a constructed 2D array representing the matrix
   */
  public double[][] get2dArray() {
    double[][] d2data = new double[height][width];
    for (int i = 0, z = 0; i < height; i++) {
      for (int j = 0; j < width; j++, z++) {
        d2data[i][j] = data[z];
      }
    }
    return d2data;
  }

  /**
   * Get a value from the matrix based on x and y position of the value.
   *
   * @param x the x coordinate of the value
   * @param y the y coordinate of the value
   * @return the value at the given coordinate pair
   */
  public double get(int x, int y) {
    if (x >= width || y >= height) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return data[x + y * width];
  }

  /**
   * Get a value from the matrix based on its internal data position.
   *
   * @param i the index at which the value is located
   * @return the value at the given index
   */
  public double get(int i) {
    return data[i];
  }

  /**
   * Set a value in the matrix based on the x and y position of that value.
   *
   * @param x     the x position of the value
   * @param y     the y position of the value
   * @param value the new value for that position
   */
  public void set(int x, int y, double value) {
    if (x >= width || y >= height) {
      throw new ArrayIndexOutOfBoundsException();
    }
    data[x + y * width] = value;
  }

  /**
   * Set a value in the matrix based on its internal data position.
   *
   * @param i     the index of the value
   * @param value the new value
   */
  public void set(int i, double value) {
    data[i] = value;
  }

  /**
   * Retrieve a row of the matrix as a 1D array of doubles.
   *
   * @param y the location of the row
   * @return the 1D array representation of the row
   */
  public double[] getRow(int y) {
    if (y >= height) {
      throw new ArrayIndexOutOfBoundsException();
    }
    double[] row = new double[width];
    for (int i = 0; i < width; i++) {
      row[i] = this.get(i, y);
    }
    return row;
  }

  /**
   * Retreive a column of the matrix as a 1D array of doubles.
   *
   * @param x the location of the column
   * @return the 1D array representation of the matrix.
   */
  public double[] getColumn(int x) {
    if (x >= width) {
      throw new ArrayIndexOutOfBoundsException();
    }
    double[] column = new double[height];
    for (int i = 0; i < height; i++) {
      column[i] = get(x, i);
    }

    return column;
  }

  /**
   * Sets the data of the array to a new 1D data matrix.
   * <p>
   * Clones the array to avoid reference errors.
   * </p>
   *
   * @param data the new data for the matrix
   */
  public void flash(double[] data) {
    this.data = data.clone();
  }

  /**
   * Sets the data of the array to a new 2D matrix.
   * <p>
   * Clones the array to avoid reference errors.
   * </p>
   *
   * @param data the double array to flash
   */
  public void flash(double[][] data) {
    this.data = Arrays.stream(data).flatMapToDouble(Arrays::stream).toArray().clone();
  }

  /**
   * Get the width of this matrix.
   *
   * @return the width of the matrix
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the height of this matrix.
   *
   * @return the height of the matrix
   */
  public int getHeight() {
    return height;
  }

  /**
   * Check if this matrix is the same size as another.
   *
   * @param matrix the other matrix
   * @return true if the matrices are the same size
   */
  public boolean sameSize(DoubleMatrix matrix) {
    return (getWidth() == matrix.getWidth() && getHeight() == matrix.getHeight());
  }

  /**
   * Multiply this matrix by another matrix.
   * <p>
   * This matrix is the pre-multiplied matrix, and the parameter is the post-multiplied.
   * <p>
   * A.multiply(B) -- AB
   * NOT
   * A.multiply(B) -- BA
   *
   * @param matrix the matrix to multiply with
   * @return the product of the two matrices
   */
  public DoubleMatrix multiply(DoubleMatrix matrix) {
    return multiply(this, matrix);
  }

  /**
   * Multiply this matrix by a scalar.
   *
   * @param scalar the scalar to multiply the matrix by
   * @return the scaled DoubleMatrix
   */
  public DoubleMatrix multiply(double scalar) {
    double[] data = this.data.clone();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        data[i] *= scalar;
      }
    }

    return new DoubleMatrix(this.width, this.height, data);
  }

  /**
   * Alias for {@link #multiply(DoubleMatrix)}.
   *
   * @param matrix the matrix to multiply
   * @return the product of the matrices
   */
  public DoubleMatrix premultiplyBy(DoubleMatrix matrix) {
    return matrix.multiply(this);
  }

  /**
   * Multiply this matrix by another matrix.
   * <p>
   * This matrix is the post-multiplied matrix, and the parameter is the pre-multiplied.
   * <p>
   * A.multiply(B) -- BA
   *
   * @param matrix the matrix to multiply with
   * @return the product of the two matrices
   */
  public DoubleMatrix postmultiplyBy(DoubleMatrix matrix) {
    return this.multiply(matrix);
  }

  /**
   * Safely copy a matrix without the possibility of shared pointers.
   *
   * @return a copy of the original matrix
   */
  @Override
  public DoubleMatrix clone() {
    return new DoubleMatrix(this.getHeight(), this.getWidth(), this.data.clone());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof DoubleMatrix)) {
      return false;
    }

    DoubleMatrix m = (DoubleMatrix) o;

    if (!sameSize(m)) {
      return false;
    }

    if (Arrays.equals(data, m.data)) {
      return true;
    }
    return false;
  }

  /**
   * Determine if a matrix is square.
   *
   * @return true if the height equals the width
   */
  public boolean isSquare() {
    return height == width;
  }

  /**
   * Returns true if the matrix is singular (the determinant is 0). Singular matrices cannot be
   * inverted.
   *
   * @return true if the matrix is singular
   */
  public boolean isSingular() {
    return determinant() == 0;
  }

  /**
   * Get the determinant of a DoubleMatrix.
   *
   * @return the determinant of the matrix
   */
  public double determinant() {
    if (!isSquare()) {
      throw new IllegalArgumentException("Determinant Matrices must be square!");
    }
    return determinant(get2dArray(), getHeight());
  }

  @Override
  public String toString() {
    String s = height + " * " + width + " matrix\n";
    for (int i = 0; i < height; i++) {
      String build = "";
      for (int j = 0; j < width; j++) {
        build += "\t" + get(j, i);
      }
      build = build.substring(1);
      s += build;
      if (i != height - 1) {
        s += "\n";
      }
    }
    return s;
  }

  public void clip(double lowerBound, double upperBound) {
    // Only works for 1x1
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (this.get(j, i) > upperBound) {
          this.set(j, i, upperBound);
        } else if (this.get(j, i) < lowerBound) {
          this.set(j, i, lowerBound);
        }
      }
    }
  }

  /**
   * Add the two matrices together.
   *
   * @param a the first matrix
   * @param b the second matrix
   * @return the sum of the two matrices
   */
  public static DoubleMatrix add(DoubleMatrix a, DoubleMatrix b) {
    if (!a.sameSize(b)) {
      throw new IllegalArgumentException("Matrices must be the same size!");
    }
    double[] update = a.data.clone();
    for (int i = 0; i < b.getArray().length; i++) {
      update[i] += b.data[i];
    }
    return new DoubleMatrix(a.width, a.height, update);
  }

  /**
   * Subtract the two matrices.
   *
   * @param a the first matrix
   * @param b the second matrix
   * @return the difference of the two matrices
   */
  public static DoubleMatrix subtract(DoubleMatrix a, DoubleMatrix b) {
    if (!a.sameSize(b)) {
      throw new IllegalArgumentException("Matrices must be the same size!");
    }
    double[] update = a.data.clone();
    for (int i = 0; i < b.getArray().length; i++) {
      update[i] -= b.data[i];
    }
    return new DoubleMatrix(a.width, a.height, update);
  }

  /**
   * Multiply the two matrices together.
   *
   * @param a the first matrix
   * @param b the second matrix
   * @return the product of the two matrices
   */
  public static DoubleMatrix multiply(DoubleMatrix a, DoubleMatrix b) {
    if (a.getWidth() != b.getHeight()) {
      throw new IllegalArgumentException("Matrix A's width not equal to Matrix B's height");
    }

    int destHeight = a.getHeight();
    int destWidth = b.getWidth();

    double[] update = new double[destHeight * destWidth];

    int pMax = a.getWidth();
    int width1 = a.getWidth();
    int width2 = b.getWidth();
    for (int i = 0; i < destWidth; i++) {
      for (int j = 0; j < destHeight; j++) {
        double tmp = 0.0;
        for (int p = 0; p < pMax; p++) {
          tmp += b.data[i + width2 * p] * a.data[p + width1 * j];
        }
        update[i + destWidth * j] = tmp;
      }
    }
    return new DoubleMatrix(destWidth, destHeight, update);
  }

  /**
   * Get the determinant of a DoubleMatrix.
   *
   * @param m the matrix to get the determinant of
   * @return the determinant of the matrix
   */
  public static double determinant(DoubleMatrix m) {
    if (!m.isSquare()) {
      throw new IllegalArgumentException("Determinant Matrices must be square!");
    }
    return determinant(m.get2dArray(), m.getHeight());
  }

  private static double determinant(double[][] m, int rows) {
    double det = 0;
    int p = 0, q = 0, sign = 1;

    int n = rows;

    if (n == 1) {
      det = m[0][0];
    } else {
      double[][] b = new double[n - 1][n - 1];
      for (int x = 0; x < n; x++) {
        p = 0;
        q = 0;
        for (int i = 1; i < n; i++) {
          for (int j = 0; j < n; j++) {
            if (j != x) {
              b[p][q++] = m[i][j];
              if (q % (n - 1) == 0) {
                p++;
                q = 0;
              }
            }
          }
        }
        det = det + m[0][x] * determinant(b, n - 1) * sign;
        sign = -sign;
      }
    }
    return det;
  }
}
