package Calculator;


/**
 * Interface offrant des methodes pour la manipulation simple de Matrices dont
 * les elements sont des nombres reels.
 *
 * @author Melanie lord
 * @version janvier 2015 (TP1 INF2120 H15)
 */
public interface IMatrix {

    /**
     * Retourne le nombre de lines de cette Matrix.
     *
     * @return le nombre de lines de cette Matrix.
     */
    int getNumLines();

    /**
     * Retourne le nombre de columns de cette Matrix.
     *
     * @return le nombre de columns de cette Matrix.
     */
    int getNumColumns();

    /**
     * Retourne l'element de la Matrix a la position noLine, noCol.
     *
     * @param noLine le numero de la line dans cette Matrix.
     * @param noCol le numero de la column dans cette Matrix.
     * @return l'element de la Matrix a la position noLine, noCol.
     * @throws MatrixException si : noLine ou noCol n'est pas un indice valide
     * dans cette Matrix.
     */
    double getElement(int noLine, int noCol);

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
    void setElement(int noLine, int noCol, double element);

    /**
     * Retourne la line de cette Matrix specifiee par noLine.
     *
     * @param noLine le numero de la line a retourner.
     * @return la line de cette Matrix specifiee par noLine.
     * @throws MatrixException si noLine n'est pas une line valide dans cette
     * Matrix.
     */
    public double[] getLine(int noLine);

    /**
     * Retourne la column de cette Matrix specifiee par noCol.
     *
     * @param noCol le numero de la column a retourner.
     * @return la column de cette Matrix specifiee par noCol.
     * @throws MatrixException si noCol n'est pas une column valide dans cette
     * Matrix.
     */
    double[] getColumn(int noCol);

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
    void replaceLine(int noLine, double[] line);

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
    void replaceColumn(int noCol, double[] column);

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
    void addLine(int noLine, double[] line);

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
    void addColumn(int noCol, double[] column);

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
    double[] deleteLine(int noLine);

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
    double[] deleteColumn(int noCol);

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
    IMatrix sum(IMatrix otherMatrix);

    /**
     * Calcul le product de cette Matrix par la value donnee.
     *
     * @param value la value de multiplication de cette Matrix.
     * @return la Matrix resultante du product de cette Matrix par la value
     * donnee.
     */
    IMatrix product(double value);

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
    IMatrix product(IMatrix otherMatrix);

    /**
     * Construit la transpose de cette Matrix.
     *
     * @return la transpose de cette Matrix.
     */
    IMatrix transpose();

}
