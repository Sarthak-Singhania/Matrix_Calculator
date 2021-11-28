package Calculator;


/*
 Interface offering methods for the simple manipulation of Matrices whose elements are real numbers.
 */
public interface IMatrix {

    /*
     * Returns the number of lines of this Matrix.
     * @return the number of lines of this Matrix.
     */
    int getNumLines();

    /**
     * Returns the number of columns of this Matrix.
     * @return the number of columns of this Matrix.
     */
    int getNumColumns();

    /*
     * Returns the Matrix element to position noLine, noCol.
     * @param noLine the number of the line in this Matrix.
     * @param noCol the number of the column in this Matrix.
     * @return the element of the Matrix to position noLine, noCol.
     * @throws MatrixException if: noLine or noCol is not a valid index in this Matrix.
     */
    double getElement(int noLine, int noCol);

    /*
     * Modifies the element to position noLine, noCol by the element passed in parameter.
     * @param noLine the number of the line in this Matrix.
     * @param noCol the number of the column in this Matrix.
     * @param element the newel element to insert at position noLine, noCol in this Matrix.
     * @throws MatrixException if noLine or noCol is not a valid index in this Matrix.
     */
    void setElement(int noLine, int noCol, double element);

    /*
     * Returns the line of this Matrix specified by noLine.
     * @param noLine the number of the line to return.
     * @return the line of this Matrix specified by noLine.
     * @throws MatrixException if noLine is not a valid line in this Matrix.
     */
    double[] getLine(int noLine);

    /*
     * Returns the column of this Matrix specified by noCol.
     * @param noCol the number of the column to return.
     * @return the column of this Matrix specified by noCol.
     * @throws MatrixException if noCol is not a valid column in this Matrix.
     */
    double[] getColumn(int noCol);

    /*
     * Replaces the line specified by noLine of this Matrix by the line given in parameter.
     * @param noLine the number of the line to replace.
     * @param line the new line
     * @throws Matrix Exception if: - noLine is not a valid line in this Matrix. - line is null or does not exactly
     * contain getNumColumns () values.
     */
    void replaceLine(int noLine, double[] line);

    /*
     * Replace the column specified by noCol of this Matrix by the column given in parameter.
     * @param noCol the number of the column to replace.
     * @param column the new column
     * @throws MatrixException if: - noCol is not a valid column in this Matrix. - column is null or does not exactly
     * contain getNumLines () values.
     */
    void replaceColumn(int noCol, double[] column);

    /*
     * Add to this Matrix the line given to the given noLine. After this add, the Matrix contains one more line and
     * the new line is at the index noLine (the number of the following lines will have increased by 1).
     * @param noLine the index of the new line after the add.
     * @param line the line to add in this Matrix, at the index noLine.
     * @throws MatrixException if: - noLine is not between 0 and getNumLines () inclusive - line is null or does not
     * exactly contain getNumColumns () values.
     */
    void addLine(int noLine, double[] line);

    /*
     * Add to this Matrix the column given to the given noCol. After this add, the Matrix contains one more column and
     * the new column is at the index noCol (the number of the following columns will have increased by 1).
     * @param noCol the index of the new column after the add.
     * @param column the column has add.
     * @throws MatrixException if: - noCol is not between 0 and getNumColumns () inclusive. - column is null or does
     * not exactly contain getNumLines () values.
     */
    void addColumn(int noCol, double[] column);

    /*
     * Delete the line of this Matrix corresponding to the given noLine. After calling this method, this Matrix contains
     * one line less. Note: you cannot delete a line in a Matrix which contains only one line.
     * @param noLine the number of the line to delete.
     * @return the deleted line.
     * @throws MatrixException if: - noLine is not a valid line in this Matrix or - if this Matrix contains only one
     * line before deletion.
     */
    double[] deleteLine(int noLine);

    /*
     * Remove the column from this Matrix corresponding to the given noCol. After calling this method, this Matrix
     * contains one column less.
     * Note: you cannot delete a column in a Matrix which contains only one column.
     * @param noCol the number of the column to delete.
     * @return the deleted column.
     * @throws MatrixException if: - noCol is not a valid column in this Matrix - if this Matrix contains only one
     * column before deletion.
     */
    double[] deleteColumn(int noCol);

    /*
     * Performs the sum of this Matrix with otherMatrix given as a parameter.
     * @param otherMatrix the Matrix to be added with this Matrix.
     * @return the Matrix resulting from the sum of this Matrix with otherMatrix.
     * @throws MatrixException if otherMatrix is null or is not of the same dimension as this Matrix(Same Rows & Columns)
     */
    IMatrix sum(IMatrix otherMatrix);

    /*
     * Performs the subtraction of this Matrix with otherMatrix given as a parameter.
     * @param otherMatrix the Matrix to be subtracted with this Matrix.
     * @return the Matrix resulting from the difference of this Matrix with otherMatrix.
     * @throws MatrixException if otherMatrix is null or is not of the same dimension as this Matrix(Same Rows & Columns)
     */
    IMatrix diff(IMatrix otherMatrix);

    /*
     * Calculate the product of this Matrix by the given value.
     *
     * @param values the multiplication value of this Matrix.
     * @return the Matrix resulting from the product of this Matrix by the value given.
     */
    IMatrix product(double value);

    /*
     * Calculate the product of this Matrix (A) by otherMatrix (B) = A X B.
     * @param otherMatrix the Matrix to multiply with this Matrix.
     * @return the Matrix resulting from the product of this Matrix by otherMatrix.
     * The dimension of the resulting Matrix will be this.getNumLines () X otherMatrix.getNumColumns ().
     * @throws MatrixException if: - otherMatrix is null - the number of columns of this Matrix is not equal to the
     * number of lines of otherMatrix.
     */
    IMatrix product(IMatrix otherMatrix);

    /*
     * Builds the transpose of this Matrix.
     * @return the transpose of this Matrix.
     */
    IMatrix transpose();

}
