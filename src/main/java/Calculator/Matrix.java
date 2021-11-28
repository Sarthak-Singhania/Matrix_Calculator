package Calculator;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class Matrix implements IMatrix {

    private int numColumns;
    private int numLines;
    private final ArrayList<Double> elements;

    public Matrix(int numLines, int numColumns, double value) throws MatrixException {
        if (numLines < 1 || numColumns < 1) {
            throw new MatrixException();
        } else {
            this.numLines = numLines;
            this.numColumns = numColumns;
            int max = this.numColumns * this.numLines;
            this.elements = new ArrayList<>(max);
            for (int i = 0; i < max; i++) {
                elements.add(value);
            }
        }

    }

    public Matrix(int numLines, int numColumns, double[] elements) throws MatrixException {
        if (numLines < 1 || elements == null || elements.length != (numLines * numColumns)) {
            throw new MatrixException();
        } else {
            this.numLines = numLines;
            this.numColumns = numColumns;
            int max = this.numColumns * this.numLines;
            this.elements = new ArrayList<>(max);
            for (int i = 0; i < (numLines * numColumns); i++) {
                this.elements.add(elements[i]);
            }
        }
    }

    public Matrix(Matrix otherMatrix) {
        this.numColumns = otherMatrix.numColumns;
        this.numLines = otherMatrix.numLines;
        int max = numColumns * numLines;
        this.elements = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            this.elements.add(otherMatrix.elements.get(i));
        }
    }

    /*
     * Returns a string representation of this
     * Matrix. Example of the representation of a Matrix of 3 lines by 2
     * columns:
     * [9.0 5.0] [6.0 7.0] [7.0 4.0]
     * NOTE: This method works for Matrices with all elements
     * as a string contain no more than 7 characters. Items are rounded to one decimal place.
     * @return a string representation of this Matrix.
     */
    @Override
    public String toString() {
        final DecimalFormat DEC_FORMAT = new DecimalFormat("0.0");
        final int ESP = 8;
        int num;
        StringBuilder sTmp;
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < (numLines * numColumns); i++) {
            //extend i on ESP columns
            sTmp = new StringBuilder();
            num = ESP - DEC_FORMAT.format(elements.get(i)).length();
            sTmp.append(" ".repeat(Math.max(0, num)));
            sTmp.append(DEC_FORMAT.format(elements.get(i)));

            if (i != 0 && i % numColumns == 0) {
                s.append("  ]\n[").append(sTmp);
            } else {
                s.append(sTmp);
            }
        }
        s.append("  ]");
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean output = true;
        if (this.getClass() == obj.getClass()) {
            Matrix input = (Matrix) obj;
            if (this.numLines == input.numLines && this.numColumns == input.numColumns) {
                int i = 0;
                int max = input.numLines * input.numColumns;
                boolean exit = false;
                while (i < max && !exit) {
                    if (this.elements.get(i).equals(input.elements.get(i))) {
                        i++;
                    } else {
                        exit = true;
                        output = false;
                    }
                }
            } else {
                output = false;
            }
        } else {
            output = false;
        }
        return output;
    }

    /*
     * Returns the number of lines of this Matrix.
     * @return the number of lines of this Matrix.
     */
    public int getNumLines() {
        return numLines;
    }

    /*
     * Returns the number of columns of this Matrix.
     * @return the number of columns of this Matrix.
     */
    public int getNumColumns() {
        return numColumns;
    }

    /*
     * Returns the Matrix element to position noLine, noCol.
     * @param noLine the number of the line in this Matrix.
     * @param noCol the number of the column in this Matrix.
     * @return the element of the Matrix to position noLine, noCol.
     * @throws MatrixException if: noLine or noCol is not a valid index in this Matrix.
     */
    public double getElement(int noLine, int noCol) throws MatrixException {
        if (noCol < 0 || noCol >= numColumns || noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position = noLine * numColumns + noCol;
            return elements.get(position);
        }
    }

    /*
     * Modifies the element to position noLine, noCol by the element passed in parameter.
     * @param noLine the number of the line in this Matrix.
     * @param noCol the number of the column in this Matrix.
     * @param element the newel element to insert at position noLine, noCol in this Matrix.
     * @throws MatrixException if noLine or noCol is not a valid index in this Matrix.
     */
    public void setElement(int noLine, int noCol, double element) throws MatrixException {
        if (noCol < 0 || noCol >= numColumns || noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position = noLine * numColumns + noCol;
            elements.set(position, element);
        }
    }

    /*
     * Returns the line of this Matrix specified by noLine.
     * @param noLine the number of the line to return.
     * @return the line of this Matrix specified by noLine.
     * @throws MatrixException if noLine is not a valid line in this Matrix.
     */
    public double[] getLine(int noLine) {
        if (noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position;
            double[] result = new double[numColumns];
            for (int i = 0; i < numColumns; i++) {
                position = noLine * numColumns + i;
                result[i] = elements.get(position);
            }
            return result;
        }
    }

    /*
     * Returns the column of this Matrix specified by noCol.
     * @param noCol the number of the column to return.
     * @return the column of this Matrix specified by noCol.
     * @throws MatrixException if noCol is not a valid column in this Matrix.
     */
    public double[] getColumn(int noCol) {
        if (noCol >= numColumns || noCol < 0) {
            throw new MatrixException();
        } else {
            int position;
            double[] result = new double[numLines];
            for (int i = 0; i < numLines; i++) {
                position = i * numColumns + noCol;
                result[i] = elements.get(position);
            }
            return result;
        }
    }

    /*
     * Replaces the line specified by noLine of this Matrix by the line given in parameter.
     * @param noLine the number of the line to replace.
     * @param line the new line
     * @throws Matrix Exception if: - noLine is not a valid line in
     * this Matrix. - line is null or does not exactly contain getNumColumns () values.
     */
    @Override
    public void replaceLine(int noLine, double[] line) {
        if (noLine < 0 || noLine >= numLines || line == null || line.length != numColumns) {
            throw new MatrixException();
        } else {
            int position;
            for (int i = 0; i < numColumns; i++) {
                position = noLine * numColumns + i;
                elements.set(position, line[i]);
            }
        }
    }

    /*
     * Replace the column specified by noCol of this Matrix by the column given in parameter.
     * @param noCol the number of the column to replace.
     * @param column the new column
     * @throws MatrixException if: - noCol is not a valid column in
     * this Matrix. - column is null or does not exactly contain getNumLines () values.
     */
    @Override
    public void replaceColumn(int noCol, double[] column) {
        if (noCol >= numColumns || noCol < 0 || column == null || column.length != numLines) {
            throw new MatrixException();
        } else {
            int position;
            for (int i = 0; i < numLines; i++) {
                position = i * numColumns + noCol;
                elements.set(position, column[i]);
            }
        }
    }

    /*
     * Add to this Matrix the line given to the given noLine. After this add, the
     * Matrix contains one more line and the new line is at the index
     * noLine (the number of the following lines will have increased by 1).
     * @param noLine the index of the new line after the add.
     * @param line the line to add in this Matrix, at the index noLine.
     * @throws MatrixException if: - noLine is not between 0 and getNumLines ()
     * inclusive - line is null or does not exactly contain getNumColumns () values.
     */
    @Override
    public void addLine(int noLine, double[] line) {
        if (noLine < 0 || noLine > numLines || line == null || line.length != numColumns) {
            throw new MatrixException();
        } else {
            int position;
            numLines++;
            for (int i = 0; i < numColumns; i++) {
                position = noLine * numColumns + i;
                elements.add(position, line[i]);
            }
        }
    }

    /*
     * Add to this Matrix the column given to the given noCol. After this add,
     * the Matrix contains one more column and the new column is at
     * the noCol index (the number of the following columns will have increased by 1).
     * @param noCol the index of the new column after the add.
     * @param column the column has add.
     * @throws MatrixException if: - noCol is not between 0 and getNumColumns ()
     * inclusive. - column is null or does not exactly contain getNumLines () values.
     */
    @Override
    public void addColumn(int noCol, double[] column) {
        if (noCol > numColumns || noCol < 0 || column == null || column.length != numLines) {
            throw new MatrixException();
        } else {
            int position;
            numColumns++;
            for (int i = 0; i < numLines; i++) {
                position = i * numColumns + noCol;
                elements.add(position, column[i]);
            }
        }
    }

    /*
     * Delete the line of this Matrix corresponding to the given noLine. After
     * calling this method, this Matrix contains one line less. Note:
     * you cannot delete a line in a Matrix which contains only one line.
     * @param noLine the number of the line to delete.
     * @return the deleted line.
     * @throws MatrixException if: - noLine is not a valid line in
     * this Matrix or - if this Matrix contains only one line before deletion.
     */
    public double[] deleteLine(int noLine) throws MatrixException {
        if (numLines == 1 || noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position;
            double[] result = new double[numColumns];
            for (int i = 0; i < numColumns; i++) {
                position = noLine * numColumns;
                result[i] = elements.remove(position);
            }
            numLines--;
            return result;
        }
    }

    /*
     * Remove the column from this Matrix corresponding to the given noCol. After
     * calling this method, this Matrix contains one column less.
     * Note: you cannot delete a column in a Matrix which contains only one column.
     * @param noCol the number of the column to delete.
     * @return the deleted column.
     * @throws MatrixException if: - noCol is not a valid column in
     * this Matrix - if this Matrix contains only one column before deletion.
     */
    public double[] deleteColumn(int noCol) throws MatrixException {
        if (numColumns == 1 || noCol >= numColumns || noCol < 0) {
            throw new MatrixException();
        } else {
            int position;
            double[] result = new double[numLines];
            for (int i = 0; i < numLines; i++) {
                position = i * numColumns + noCol - i;
                result[i] = elements.remove(position);
            }
            numColumns--;
            return result;
        }
    }

    /*
     * Performs the sum of this Matrix with otherMatrix given as a parameter.
     * @param otherMatrix the Matrix to be added with this Matrix.
     * @return the Matrix resulting from the sum of this Matrix with otherMatrix.
     * @throws MatrixException if otherMatrix is null or not the same
     * dimension as this Matrix (same number of Rows & Columns).
     */
    @Override
    public IMatrix sum(IMatrix otherMatrix) throws MatrixException {
        if (otherMatrix == null || otherMatrix.getNumLines() != numLines || otherMatrix.getNumColumns() != numColumns) {
            System.out.println("ICI");
            throw new MatrixException();
        } else {
            IMatrix Matrix = new Matrix(this);
            double add;
            for (int i = 0; i < Matrix.getNumLines(); i++) {
                for (int j = 0; j < Matrix.getNumColumns(); j++) {
                    add = Matrix.getElement(i, j) + otherMatrix.getElement(i, j);
                    Matrix.setElement(i, j, add);
                }
            }
            return Matrix;
        }
    }

    /*
     * Performs the subtraction of this Matrix with otherMatrix given as a parameter.
     * @param otherMatrix the Matrix to be subtracted with this Matrix.
     * @return the Matrix resulting from the difference of this Matrix with otherMatrix.
     * @throws MatrixException if otherMatrix is null or not the same
     * dimension as this Matrix (same number of Rows & Columns).
     */
    @Override
    public IMatrix diff(IMatrix otherMatrix) throws MatrixException {
        if (otherMatrix == null || otherMatrix.getNumLines() != numLines || otherMatrix.getNumColumns() != numColumns) {
            System.out.println("ICI");
            throw new MatrixException();
        } else {
            IMatrix Matrix = new Matrix(this);
            double diff;
            for (int i = 0; i < Matrix.getNumLines(); i++) {
                for (int j = 0; j < Matrix.getNumColumns(); j++) {
                    diff = Matrix.getElement(i, j) - otherMatrix.getElement(i, j);
                    Matrix.setElement(i, j, diff);
                }
            }
            return Matrix;
        }
    }
    /*
     * Calculate the product of this Matrix by the given value.
     * @param values the multiplication value of this Matrix.
     * @return the Matrix resulting from the product of this Matrix by the given value.
     */
    @Override
    public IMatrix product(double value) {
        IMatrix Matrix = new Matrix(this);
        double mult;
        for (int i = 0; i < Matrix.getNumLines(); i++) {
            for (int j = 0; j < Matrix.getNumColumns(); j++) {
                mult = Matrix.getElement(i, j) * value;
                Matrix.setElement(i, j, mult);
            }
        }
        return Matrix;
    }

    /*
     * Calculate the product of this Matrix (A) by otherMatrix (B) = A X B.
     * @param otherMatrix the Matrix to multiply with this Matrix.
     * @return the Matrix resulting from the product of this Matrix by otherMatrix.
     * The dimension of the resulting Matrix will be this.getNumLines () X
     * otherMatrix.getNumColumns ().
     * @throws MatrixException if: - otherMatrix is null - the number of
     * columns of this Matrix is not equal to the number of lines of
     * otherMatrix.
     */
    @Override
    public IMatrix product(IMatrix otherMatrix) throws MatrixException {
        if (otherMatrix == null || numColumns != otherMatrix.getNumLines()) {
            throw new MatrixException();
        } else {
            IMatrix mult = new Matrix(numLines, otherMatrix.getNumColumns(), 0);
            for (int i = 0; i < mult.getNumColumns(); i++) {
                for (int j = 0; j < mult.getNumLines(); j++) {
                    mult.setElement(j, i, multTab(this.getLine(j), otherMatrix.getColumn(i)));
                }
            }
            return mult;
        }
    }

    /*
     * Builds the transpose of this Matrix.
     * @return the transpose of this Matrix.
     */
    @Override
    public IMatrix transpose() {
        IMatrix Matrix = new Matrix(this.numColumns, this.numLines, 0);
        double mult;
        for (int i = 0; i < Matrix.getNumLines(); i++) {
            for (int j = 0; j < Matrix.getNumColumns(); j++) {
                mult = this.getElement(j, i);
                Matrix.setElement(i, j, mult);
            }
        }
        return Matrix;
    }

    private double multTab(double[] tab1, double[] tab2) {
        double result = 0;
        for (int i = 0; i < tab1.length; i++) {
            result += tab1[i] * tab2[i];
        }
        return result;
    }
}
