package Matrix_Calculator;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Thomas Castonguay-Gagnon
 * @codePerm CAST10059303
 */
public class Matrix implements IMatrix {

    private int numColumns;
    private int numLines;
    private ArrayList<Double> elements;

    public Matrix(int numLines, int numColumns, double value) throws MatrixException {
        if (numLines < 1 || numColumns < 1) {
            throw new MatrixException();
        } else {
            this.numLines = numLines;
            this.numColumns = numColumns;
            int max = this.numColumns * this.numLines;
            this.elements = new ArrayList<Double>(max);
            for (int i = 0; i < max; i++) {
                elements.add(value);
            }
        }

    }

    public Matrix(int numLines, int numColumns, double[] elements) throws MatrixException {
        if (numLines < 1 || numLines < 1 || elements == null || elements.length != (numLines * numColumns)) {
            throw new MatrixException();
        } else {
            this.numLines = numLines;
            this.numColumns = numColumns;
            int max = this.numColumns * this.numLines;
            this.elements = new ArrayList<Double>(max);
            for (int i = 0; i < (numLines * numColumns); i++) {
                this.elements.add(elements[i]);
            }
        }
    }

    public Matrix(Matrix otherMatrix) {
        this.numColumns = otherMatrix.numColumns;
        this.numLines = otherMatrix.numLines;
        int max = numColumns * numLines;
        this.elements = new ArrayList<Double>(max);
        for (int i = 0; i < max; i++) {
            this.elements.add(otherMatrix.elements.get(i));
        }
    }

    /**
     * Retourne une representation sous forme de chaine de caracteres de cette
     * Matrix. Exemple de la representation d'une Matrix de 3 lines par 2
     * columns :
     *
     * [ 9,0 5,0 ] [ 6,0 7,0 ] [ 7,0 4,0 ]
     *
     * NOTE : Cette methode fonctionne pour les Matrices dont tous les elements
     * sous forme de chaine de caracteres ne contiennent pas plus de 7
     * caracteres. Les elements sont arrondis a une decimale.
     *
     * @return une representation sous forme de chaine de caracteres de cette
     * Matrix.
     */
    @Override
    public String toString() {
        final DecimalFormat DEC_FORMAT = new DecimalFormat("0.0");
        final int ESP = 8;
        int num;
        String sTmp;
        String s = "[";
        for (int i = 0; i < (numLines * numColumns); i++) {
            //etendre i sur ESP columns
            sTmp = "";
            num = ESP - DEC_FORMAT.format(elements.get(i)).length();
            for (int j = 0; j < num; j++) {
                sTmp = sTmp + " ";
            }
            sTmp = sTmp + DEC_FORMAT.format(elements.get(i));

            if (i != 0 && i % numColumns == 0) {
                s = s + "  ]\n[" + sTmp;
            } else {
                s = s + sTmp;
            }
        }
        s = s + "  ]";
        return s;
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
                    if (this.elements.get(i) == input.elements.get(i)) {
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

    /**
     * Retourne le nombre de lines de cette Matrix.
     *
     * @return le nombre de lines de cette Matrix.
     */
    public int getNumLines() {
        return numLines;
    }

    /**
     * Retourne le nombre de columns de cette Matrix.
     *
     * @return le nombre de columns de cette Matrix.
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Retourne l'element de la Matrix a la position noLine, noCol.
     *
     * @param noLine le numero de la line dans cette Matrix.
     * @param noCol le numero de la column dans cette Matrix.
     * @return l'element de la Matrix a la position noLine, noCol.
     * @throws MatrixException si : noLine ou noCol n'est pas un indice valide
     * dans cette Matrix.
     */
    public double getElement(int noLine, int noCol) throws MatrixException {
        if (noCol < 0 || noCol >= numColumns || noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position = noLine * numColumns + noCol;
            return elements.get(position);
        }
    }

    /**
     * Modifie l'element a la position noLine, noCol par l'element passe en
     * parametre.
     *
     * @param noLine le numero de la line dans cette Matrix.
     * @param noCol le numero de la column dans cette Matrix.
     * @param element le newel element a inserer a la position noLine, noCol
     * dans cette Matrix.
     * @throws MatrixException si noLine ou noCol n'est pas un indice valide
     * dans cette Matrix.
     */
    public void setElement(int noLine, int noCol, double element) throws MatrixException {
        if (noCol < 0 || noCol >= numColumns || noLine < 0 || noLine >= numLines) {
            throw new MatrixException();
        } else {
            int position = noLine * numColumns + noCol;
            elements.set(position, element);
        }
    }

    /**
     * Retourne la line de cette Matrix specifiee par noLine.
     *
     * @param noLine le numero de la line a retourner.
     * @return la line de cette Matrix specifiee par noLine.
     * @throws MatrixException si noLine n'est pas une line valide dans cette
     * Matrix.
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

    /**
     * Retourne la column de cette Matrix specifiee par noCol.
     *
     * @param noCol le numero de la column a retourner.
     * @return la column de cette Matrix specifiee par noCol.
     * @throws MatrixException si noCol n'est pas une column valide dans cette
     * Matrix.
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

    /**
     * Remplace la line specifiee par noLine de cette Matrix par la line donnee
     * en parametre.
     *
     * @param noLine le numero de la line a replace.
     * @param line la newelle line
     * @throws Matrix Exception si : - noLine n'est pas une line valide dans
     * cette Matrix. - line est null ou ne contient pas exactement
     * getNumColumns() values.
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

    /**
     * Remplace la column specifiee par noCol de cette Matrix par la column
     * donnee en parametre.
     *
     * @param noCol le numero de la column a replace.
     * @param column la newelle column
     * @throws MatrixException si : - noCol n'est pas une column valide dans
     * cette Matrix. - column est null ou ne contient pas exactement
     * getNumLines() values.
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

    /**
     * Ajoute a cette Matrix la line donnee au noLine donne. Apres cet add, la
     * Matrix contient une line de plus et la newelle line se trouve a l'indice
     * noLine (le numero des lines suivantes aura augmente de 1).
     *
     * @param noLine l'indice de la newelle line apres l'add.
     * @param line la line a add dans cette Matrix, a l'indice noLine.
     * @throws MatrixException si : - noLine n'est pas entre 0 et getNumLines()
     * inclusivement - line est null ou ne contient pas exactement
     * getNumColumns() values.
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

    /**
     * Ajoute a cette Matrix la column donnee au noCol donne. Apres cet add,
     * la Matrix contient une column de plus et la newelle column se trouve a
     * l'indice noCol (le numero des columns suivantes aura augmente de 1).
     *
     * @param noCol l'indice de la newelle column apres l'add.
     * @param column la column a add.
     * @throws MatrixException si : - noCol n'est pas entre 0 et getNumColumns()
     * inclusivement. - column est null ou ne contient pas exactement
     * getNumLines() values.
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

    /**
     * Supprime la line de cette Matrix correspondant au noLine donne. Apres
     * l'appel de cette methode, cette Matrix contient une line de moins. Note :
     * on ne peut pas delete une line dans une Matrix qui ne contient qu'une
     * seule line.
     *
     * @param noLine le numero de la line a delete.
     * @return la line supprimee.
     * @throws MatrixException si : - noLine n'est pas une line valide dans
     * cette Matrix ou - si cette Matrix ne contient qu'une seule line avant la
     * suppression.
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

    /**
     * Supprime la column de cette Matrix correspondant au noCol donne. Apres
     * l'appel de cette methode, cette Matrix contient une column de moins.
     * Note : on ne peut pas delete une column dans une Matrix qui ne contient
     * qu'une seule column.
     *
     * @param noCol le numero de la column a delete.
     * @return la column supprimee.
     * @throws MatrixException si : - noCol n'est pas une column valide dans
     * cette Matrix - si cette Matrix ne contient qu'une seule column avant la
     * suppression.
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

    /**
     * Effectue la sum de cette Matrix avec otherMatrix donnee en parametre.
     *
     * @param otherMatrix la Matrix a additionner avec cette Matrix.
     * @return la Matrix resultante de la sum de cette Matrix avec
     * otherMatrix.
     * @throws MatrixException si otherMatrix est null ou n'est pas de meme
     * dimension que cette Matrix(meme nombre de lines et meme nombre de
     * columns).
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

    /**
     * Calcul le product de cette Matrix par la value donnee.
     *
     * @param value la value de multiplication de cette Matrix.
     * @return la Matrix resultante du product de cette Matrix par la value
     * donnee.
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

    /**
     * Calcule le product de cette Matrix (A) par otherMatrix (B) = A X B.
     *
     * @param otherMatrix la Matrix a multiplier avec cette Matrix.
     * @return la Matrix resultante du product de cette Matrix par otherMatrix.
     * La dimension de la Matrix resultante sera this.getNumLines() X
     * otherMatrix.getNumColumns().
     * @throws MatrixException si : - otherMatrix est null - le nombre de
     * columns de cette Matrix n'est pas egal au nombre de lines de
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

    /**
     * Construit la transpose de cette Matrix.
     *
     * @return la transpose de cette Matrix.
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
