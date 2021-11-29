package Calculator;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Class implementing the IMatrix interface that performs operations on a
 * Matrix implemented with a linked list.

 */
public class TP3 extends WindowAdapter implements ActionListener {

    /**
     * **********************************
     * CLASS CONSTANTS
	 ***********************************
     */
    //We Define Width & Height of Our Screen
    public final static int LARGE_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final static int HIGH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().height;
    //The Width & Height of the Main Window
    public final static int LARGE_WINDOW = 1000;
    public final static int HIGH_WINDOW = 630;

    //File where the Entered Matrices are Stored
    public final static String FIC_Matrices = "Matrices.txt";

    //Defining the Title of Our Window
    public final static String TITLE_WINDOW = "Matrix Calculator";

    // Instructions
    private final static String INSTRUCTIONS = "Create a new Matrix by clicking on the [New] button below or choose an existing Matrix from the drop-down list above.";

    /**
     * **********************************
     * INSTANCE VARIABLES
	 ***********************************
     */
    //Matrix List & Names
    private ArrayList<Object> Matrices;
    private String nameMatrixZone1;
    private Matrix MatrixZone1;
    private String nameMatrixZone2;
    private Matrix MatrixZone2;
    private String nameMatrixZone4;
    private Matrix MatrixZone4;

    private int nColZone1;
    private int nColZone2;
    private int nLinesZone1;
    private int nLinesZone2;

    /**
     * **********************************
     * GRAPHIC COMPONENTS
	 ***********************************
     */
    //Main Window
    private JFrame window;
    //Window Contents
    private Container contentsWindow;
    //Matrix on Left
    private JPanel zone1;
    //Matrix on Right
    private JPanel zone2;
    //Operations space between Matrices
    private JPanel zone3;
    //Result Area
    private JPanel zone4;
    //Buttons for Left Matrix
    private JPanel panel1ButtonsZone1;
    private JPanel panel2ButtonsZone1;
    //Buttons for Right Matrix
    private JPanel panel1ButtonsZone2;
    private JPanel panel2ButtonsZone2;
    //Left Matrix Selector
    private JPanel selectorZone1;
    private JComboBox selectMatrixZone1;
    //Right Matrix Selector
    private JPanel selectorZone2;
    private JComboBox selectMatrixZone2;
    //Deleting the Left Matrix
    private JButton deleteZone1;
    //Deleting the Right Matrix
    private JButton deleteZone2;
    // Add & Mul Buttons
    private JButton addition;
    private JButton subtraction;
    private JButton multiplication;
    private JButton[] buttonsTabZone1;
    private JButton[] buttonsTabZone2;
    private JLabel multLabelZone1;
    private JLabel multLabelZone2;
    private JTextField multScalarZone1;
    private JTextField multScalarZone2;
    private JTextArea instructionsZone1;
    private JTextArea instructionsZone2;

    private JPanel panelMatrixZone1;
    private JPanel panelMatrixZone2;
    private JTextField[][] fieldsMatrixZone1;
    private JTextField[][] fieldsMatrixZone2;

    private JPanel resultZone4;
    private JTextArea nameZone4;
    private JTextArea MatrixAffZone4;
    private JScrollPane scrollPane;
    private JButton saveZone4;

    // Matrix in Edit Mode
    private boolean saveZone1;
    private boolean saveZone2;
    private boolean opZone1;
    private boolean opZone2;

    /**
     * ***************************************************************
     * New Matrix
	 ****************************************************************
     */
    // Zone 1 (Left Matrix)
    private JLabel newMatrixLabelLinesZone1;
    private JComboBox newMatrixBoxLinesZone1;
    private JLabel newMatrixLabelColumnsZone1;
    private JComboBox newMatrixBoxColumnsZone1;
    private JPanel newMatrixPanelZone1;
    private JPanel newMatrixUnderPanelZone1;
    private JButton newMatrixButtonZone1;

    // Zone 2 (Right Matrix)
    private JLabel newMatrixLabelLinesZone2;
    private JComboBox newMatrixBoxLinesZone2;
    private JLabel newMatrixLabelColumnsZone2;
    private JComboBox newMatrixBoxColumnsZone2;
    private JPanel newMatrixPanelZone2;
    private JPanel newMatrixUnderPanelZone2;
    private JButton newMatrixButtonZone2;

    /**
     * Initialising the Application
     */
    public TP3() {
        init();
    }

    /*
    Initialising the Graphic Components
     */
    private void init() {
        fileScanner();
        //JFrame Window
        window = new JFrame(TITLE_WINDOW);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        Image icon=Toolkit.getDefaultToolkit().getImage("matrix.png");
        window.setIconImage(icon);  
        window.setLayout(null);
        //Putting the Main Window in the Center of Screen
        window.setBounds(LARGE_SCREEN / 2 - LARGE_WINDOW / 2, HIGH_SCREEN / 2 - HIGH_WINDOW / 2, LARGE_WINDOW, HIGH_WINDOW);

        contentsWindow = window.getContentPane();

        /*
		 * Initialising the Zones
         */
        zone1 = zone(0, 0, 460, 375);
        zone3 = zone(460, 0, 80, 375);
        zone2 = zone(540, 0, 460, 375);
        zone4 = zone(0, 375, 1000, 255);

        /*
		 * Initialising the Buttons
         */
        initButtonsZone1();
        initButtonsZone2();
        initSelectionZone1();
        initSelectionZone2();
        initInstructionsZone1();
        initInstructionsZone2();
        addition = button("+", 15, 110, 50, 25);
        addition.setEnabled(false);
        addition.addActionListener(this);
        subtraction = button("-", 15, 160, 50, 25);
        subtraction.setEnabled(false);
        subtraction.addActionListener(this);
        multiplication = button("X", 15, 210, 50, 25);
        multiplication.setEnabled(false);
        multiplication.addActionListener(this);

        /*
         * **********************************
         * Adding Components to the Zones
         ***********************************
         */
        zone1.add(selectorZone1);
        zone1.add(panel1ButtonsZone1);
        zone1.add(panel2ButtonsZone1);

        zone2.add(selectorZone2);
        zone2.add(panel1ButtonsZone2);
        zone2.add(panel2ButtonsZone2);

        zone3.add(addition);
        zone3.add(subtraction);
        zone3.add(multiplication);

        initZone4();

        /*
		 * Adding Zones to Frame
         */
        contentsWindow.add(zone1);
        contentsWindow.add(zone3);
        contentsWindow.add(zone2);
        contentsWindow.add(zone4);

        //Leave this instruction at the end of the initialization of the graphics components.
        window.setVisible(true);

        /*
         * ***********************
         * Listeners
		 ************************
         */
        //Adding Listener to Window
        window.addWindowListener(this); // See the WindowClosing Method

    }

    /**
     * Action listener
     */
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        /*For testing now */
        if (event == buttonsTabZone1[0]) {
            initNewMatrixZone1();
        } else if (event == buttonsTabZone1[1] && saveZone1) {
            saveZone1();
        } else if (event == buttonsTabZone1[1]) {
            editionZone1();
        } else if (event == buttonsTabZone2[0]) {
            initNewMatrixZone2();
        } else if (event == buttonsTabZone2[1] && saveZone2) {
            saveZone2();
        } else if (event == buttonsTabZone2[1]) {
            editionZone2();
        } else if (event == selectMatrixZone1) {
            selectMatrixZone1();
        } else if (event == selectMatrixZone2) {
            selectMatrixZone2();
        } else if (event == deleteZone1) {
            deleteMatrixZone1();
        } else if (event == deleteZone2) {
            deleteMatrixZone2();
        } else if (event == newMatrixButtonZone1) {
            creNewMatrixZone1();
        } else if (event == newMatrixButtonZone2) {
            creNewMatrixZone2();
        } else if (event == buttonsTabZone1[2]) {
            addLineZone1();
        } else if (event == buttonsTabZone2[2]) {
            addLineZone2();
        } else if (event == buttonsTabZone1[3]) {
            addColumnZone1();
        } else if (event == buttonsTabZone2[3]) {
            addColumnZone2();
        } else if (event == buttonsTabZone1[4]) {
            suppLineZone1();
        } else if (event == buttonsTabZone2[4]) {
            suppLineZone2();
        } else if (event == buttonsTabZone1[5]) {
            suppColumnZone1();
        } else if (event == buttonsTabZone2[5]) {
            suppColumnZone2();
        } else if (event == multScalarZone1) {
            multValueZone1();
        } else if (event == multScalarZone2) {
            multValueZone2();
        } else if (event == buttonsTabZone1[6]) {
            transposeZone1();
        } else if (event == buttonsTabZone2[6]) {
            transposeZone2();
        } else if (event == addition) {
            addMatrix();
        } else if (event == subtraction) {
            subMatrix();
        }else if (event == multiplication) {
            multMatrix();
        } else if (event == saveZone4) {
            saveZone4();
        }

    }

    /*
     * When closing the window, save all the Matrices in the file FIC_Matrices.
     * @param the window closing event.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        try {
            FileWriter fw = new FileWriter(FIC_Matrices);
            BufferedWriter br = new BufferedWriter(fw);
            String line = "";
            Matrix Matrix;
            for (int d = 0; d < Matrices.size(); d = d + 2) {
                line += Matrices.get(d);
                line += ":";
                Matrix = (Matrix) Matrices.get(d + 1);
                line += Matrix.getNumLines();
                line += ";";
                line += Matrix.getNumColumns();
                line += ";";
                for (int i = 0; i < Matrix.getNumLines(); i++) {
                    for (int j = 0; j < Matrix.getNumColumns(); j++) {
                        line += Matrix.getElement(i, j);
                        line += ";";
                    }
                }
                br.write(line);
                line = "";
                br.newLine();
            }
            br.close();
            fw.close();
        } catch (IOException e1) {
        }
    }

    public static void main(String[] args) {
        new TP3();
    }

    /*
     * Reading the matrices.txt file
     */
    private void fileScanner() {
        try {
            FileReader fr = new FileReader(FIC_Matrices);
            BufferedReader br = new BufferedReader(fr);
            Matrices = new ArrayList<>();
            String readLine;
            while (br.ready()) {
                readLine = br.readLine();
                treatLine(readLine);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            Matrices = new ArrayList<>();
            // A delete
            System.out.println("FNF Error");
        } catch (IOException e) {
            System.out.println("IO ERROR");
        }

    }

    /**
     * Method which processes the line read from the text file. The method adds
     * the name of the Matrix in an even position in the Arraylist Matrices. The
     * Matrix object is added in odd position.
     *
     * @param readLine in the text file
     */
    private void treatLine(String readLine) {
        String name = "";
        double[] values = null;
        int nbCol = -1;
        int nbLines = -1;
        Matrix Matrix;
        String[] bufferName = readLine.split(":");
        name = bufferName[0];
        String[] buffer = bufferName[1].split(";");
        values = new double[buffer.length - 2];
        for (int i = 0; i < buffer.length; i++) {
            if (i == 0) {
                nbLines = Integer.parseInt(buffer[i]);
            } else if (i == 1) {
                nbCol = Integer.parseInt(buffer[i]);
            } else {
                values[i - 2] = Double.parseDouble(buffer[i]);
            }
        }
        Matrix = new Matrix(nbLines, nbCol, values);
        Matrices.add(name);
        Matrices.add(Matrix);
    }

    /*
     Construct a panel with null layout and written positions and dimensions
     * @param x panel position in X
     * @param y panel position in Y
     * @param width Panel width
     * @param height Panel height
     * @return a JPanel according to the sizes and the position in parameter.
     */
    private JPanel zone(int x, int y, int width, int height) {
        JPanel panel = new JPanel(null);
        panel.setSize(width, height);
        panel.setLocation(x, y);
        return panel;
    }

    /*
     * Create a button with the given label.
     *
     * @param name Label of the button
     * @param x Position in x of the button
     * @param y Position in y of the button
     * @param width Width of the button
     * @param height Button height
     * @return A JButton with the given parameters.
     */
    private JButton button(String name, int x, int y, int width, int height) {
        JButton button = new JButton(name);
        button.setSize(width, height);
        button.setLocation(x, y);
        return button;
    }

    /**
     * Initialising Buttons in Zone 1 (Left-Side)
     */
    private void initButtonsZone1() {
        panel1ButtonsZone1 = zone(0, 300, 460, 40);
        panel2ButtonsZone1 = zone(0, 335, 460, 40);
        panel1ButtonsZone1.setLayout(new FlowLayout());
        panel2ButtonsZone1.setLayout(new FlowLayout());

        //Buttons Array
        buttonsTabZone1 = new JButton[7];
        // New Matrix Button
        buttonsTabZone1[0] = new JButton("New");
        panel1ButtonsZone1.add(buttonsTabZone1[0]);

        // Save/Edit Button
        buttonsTabZone1[1] = new JButton("Edit");
        buttonsTabZone1[1].setEnabled(false);
        panel1ButtonsZone1.add(buttonsTabZone1[1]);

        // Add New Row Button
        buttonsTabZone1[2] = new JButton("+ Line");
        buttonsTabZone1[2].setEnabled(false);

        // Add New Column Button
        panel1ButtonsZone1.add(buttonsTabZone1[2]);
        buttonsTabZone1[3] = new JButton("+ Column");
        buttonsTabZone1[3].setEnabled(false);
        panel1ButtonsZone1.add(buttonsTabZone1[3]);

        // Delete a Row Button
        buttonsTabZone1[4] = new JButton("- Line");
        buttonsTabZone1[4].setEnabled(false);
        panel2ButtonsZone1.add(buttonsTabZone1[4]);

        // Delete a Column Button
        buttonsTabZone1[5] = new JButton("- Column");
        buttonsTabZone1[5].setEnabled(false);
        panel2ButtonsZone1.add(buttonsTabZone1[5]);

        // Transpose Operation Button
        buttonsTabZone1[6] = new JButton("Transpose");
        buttonsTabZone1[6].setEnabled(false);
        panel2ButtonsZone1.add(buttonsTabZone1[6]);

        // Scalar Multiplication Operation Button
        multLabelZone1 = new JLabel("Mult. through");
        multLabelZone1.setEnabled(false);
        panel2ButtonsZone1.add(multLabelZone1);
        multScalarZone1 = new JTextField(4);
        multScalarZone1.setEnabled(false);
        panel2ButtonsZone1.add(multScalarZone1);

        // Action listener
        multScalarZone1.addActionListener(this);
        for (int i = 0; i < buttonsTabZone1.length; i++) {
            buttonsTabZone1[i].addActionListener(this);
        }
    }

    /*
     * Initialising Button in Zone 3
     */
    private void initButtonsZone2() {
        panel1ButtonsZone2 = zone(0, 300, 460, 40);
        panel2ButtonsZone2 = zone(0, 335, 460, 40);
        panel1ButtonsZone2.setLayout(new FlowLayout());
        panel2ButtonsZone2.setLayout(new FlowLayout());

        // Array of Buttons
        buttonsTabZone2 = new JButton[7];

        // New Button
        buttonsTabZone2[0] = new JButton("New");
        panel1ButtonsZone2.add(buttonsTabZone2[0]);

        // Save/Edit Button
        buttonsTabZone2[1] = new JButton("Edit");
        buttonsTabZone2[1].setEnabled(false);
        panel1ButtonsZone2.add(buttonsTabZone2[1]);

        // Add Row Button
        buttonsTabZone2[2] = new JButton("+ Line");
        buttonsTabZone2[2].setEnabled(false);
        panel1ButtonsZone2.add(buttonsTabZone2[2]);

        // Add Column Button
        buttonsTabZone2[3] = new JButton("+ Column");
        buttonsTabZone2[3].setEnabled(false);
        panel1ButtonsZone2.add(buttonsTabZone2[3]);

        // Delete Row Button
        buttonsTabZone2[4] = new JButton("- Line");
        buttonsTabZone2[4].setEnabled(false);
        panel2ButtonsZone2.add(buttonsTabZone2[4]);

        // Delete Column Button
        buttonsTabZone2[5] = new JButton("- Column");
        buttonsTabZone2[5].setEnabled(false);
        panel2ButtonsZone2.add(buttonsTabZone2[5]);

        // Transpose Operation Button
        buttonsTabZone2[6] = new JButton("Transpose");
        buttonsTabZone2[6].setEnabled(false);
        panel2ButtonsZone2.add(buttonsTabZone2[6]);

        // Scalar Multiplication Button
        multLabelZone2 = new JLabel("Mult. through");
        multLabelZone2.setEnabled(false);
        panel2ButtonsZone2.add(multLabelZone2);
        multScalarZone2 = new JTextField(4);
        multScalarZone2.setEnabled(false);
        panel2ButtonsZone2.add(multScalarZone2);

        // Action listener
        multScalarZone2.addActionListener(this);
        for (int i = 0; i < buttonsTabZone1.length; i++) {
            buttonsTabZone2[i].addActionListener(this);
        }
    }

    /*
    Initializes the Matrix selector in zone1 according to the Matrices present in the arraylist
     */
    private void initMatrixSelectZone1() {
        selectMatrixZone1 = new JComboBox();
        selectMatrixZone1.setLocation(135, 12);
        selectMatrixZone1.setSize(100, 25);
        selectMatrixZone1.addItem("");
        selectMatrixZone1.setSelectedItem("");
        for (int i = 0; i < Matrices.size(); i++) {
            if (i % 2 == 0) {
                selectMatrixZone1.addItem(Matrices.get(i));
            }
        }
        selectMatrixZone1.addActionListener(this);
    }

    private void refreshSelectorALL() {
        selectorZone1.setVisible(false);
        selectorZone2.setVisible(false);
        initSelectionZone1();
        initSelectionZone2();
        zone1.add(selectorZone1);
        zone2.add(selectorZone2);
        window.revalidate();
    }

    /*
     Initializes the selection of the Matrix in zone1. Delete button + Selector
     */
    private void initSelectionZone1() {
        selectorZone1 = zone(0, 0, 460, 49);
        deleteZone1 = button("Delete", 250, 12, 95, 25);
        deleteZone1.setEnabled(false);
        deleteZone1.addActionListener(this);
        initMatrixSelectZone1();
        selectorZone1.add(deleteZone1);
        selectorZone1.add(selectMatrixZone1);
        selectorZone1.revalidate();
        zone1.revalidate();
    }

    /*Initializes the Matrix selector in zone2 according to the Matrices present in the arraylist
     */
    private void initMatrixSelectZone2() {
        selectMatrixZone2 = new JComboBox();
        selectMatrixZone2.setLocation(135, 12);
        selectMatrixZone2.setSize(100, 25);
        selectMatrixZone2.addItem("");
        selectMatrixZone2.setSelectedItem("");
        for (int i = 0; i < Matrices.size(); i++) {
            if (i % 2 == 0) {
                selectMatrixZone2.addItem(Matrices.get(i));
            }
        }
        selectMatrixZone2.addActionListener(this);
    }

    /*
    Initializes the selection of the Matrix in zone1. Delete button + Selector
     */
    private void initSelectionZone2() {
        selectorZone2 = zone(0, 0, 460, 49);
        deleteZone2 = button("Delete", 250, 12, 95, 25);
        deleteZone2.setEnabled(false);
        deleteZone2.addActionListener(this);
        initMatrixSelectZone2();
        selectorZone2.add(deleteZone2);
        selectorZone2.add(selectMatrixZone2);
        selectorZone2.revalidate();
        zone2.revalidate();
    }

    /*Initialising Instructions in Zone 1
     */
    private void initInstructionsZone1() {
        instructionsZone1 = new JTextArea();
        instructionsZone1.setText(INSTRUCTIONS);
        instructionsZone1.setEditable(false);
        instructionsZone1.setLineWrap(true);
        instructionsZone1.setWrapStyleWord(true);
        Border border = BorderFactory.createEmptyBorder(5, 25, 5, 25);
        instructionsZone1.setBorder(border);
        instructionsZone1.setBounds(80, 125, 300, 80);
        zone1.add(instructionsZone1);
        zone1.revalidate();
    }

    /**
     * Initialising Instructions in Zone 2
     */
    private void initInstructionsZone2() {
        instructionsZone2 = new JTextArea();
        instructionsZone2.setText(INSTRUCTIONS);
        instructionsZone2.setEditable(false);
        instructionsZone2.setLineWrap(true);
        instructionsZone2.setWrapStyleWord(true);
        Border border = BorderFactory.createEmptyBorder(5, 25, 5, 25);
        instructionsZone2.setBorder(border);
        instructionsZone2.setBounds(80, 125, 300, 80);
        zone2.add(instructionsZone2);
        zone2.revalidate();
    }

    /*
     * *******************************************************************************
     * Matrix 1
	 ********************************************************************************
     */
    /*
     * Initialising the Graphic representation of Matrix in Zone 1
     */
    private void initMatrixZone1() {
        if (panelMatrixZone1 != null) {
            resetMatrixZone1();
        }
        int width = 55 * nColZone1;
        int height = 30 * nLinesZone1;
        int positionX = 5 + ((440 - width) / 2);
        int positionY = 50 + ((240 - height) / 2);
        panelMatrixZone1 = new JPanel(new GridLayout(nLinesZone1, nColZone1, 5, 5));
        panelMatrixZone1.setBounds(positionX, positionY, width, height);
        panelMatrixZone1.setVisible(true);
        fieldsMatrixZone1(nColZone1, nLinesZone1);
        panelMatrixZone1.repaint();
        zone1.repaint();
    }

    /*
     Initialising the Fields & their respective values
     */
    private void fieldsMatrixZone1(int nCol, int nLines) {
        fieldsMatrixZone1 = new JTextField[nLines][nCol];
        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nCol; j++) {
                fieldsMatrixZone1[i][j] = new JTextField("" + MatrixZone1.getElement(i, j));
                fieldsMatrixZone1[i][j].setEditable(false);
                fieldsMatrixZone1[i][j].setBackground(Color.white);
                panelMatrixZone1.add(fieldsMatrixZone1[i][j]);

            }
        }
    }

    /*
     * *******************************************************************************
     * Matrix 2
	 ********************************************************************************
     */
    /*
    Initialising the Graphic representation of Matrix in Zone 2
     */
    private void initMatrixZone2() {
        if (panelMatrixZone2 != null) {
            resetMatrixZone2();
        }
        int width = 55 * nColZone2;
        int height = 30 * nLinesZone2;
        int positionX = 5 + ((440 - width) / 2);
        int positionY = 50 + ((240 - height) / 2);
        panelMatrixZone2 = new JPanel(new GridLayout(nLinesZone2, nColZone2, 5, 5));
        panelMatrixZone2.setBounds(positionX, positionY, width, height);
        fieldsMatrixZone2(nColZone2, nLinesZone2);
        panelMatrixZone2.revalidate();
        zone2.revalidate();
    }

    /*
      Initialising the Fields & their respective values
     */
    private void fieldsMatrixZone2(int nCol, int nLines) {
        fieldsMatrixZone2 = new JTextField[nLines][nCol];
        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nCol; j++) {
                fieldsMatrixZone2[i][j] = new JTextField("" + MatrixZone2.getElement(i, j));
                fieldsMatrixZone2[i][j].setEditable(false);
                fieldsMatrixZone2[i][j].setBackground(Color.white);
                panelMatrixZone2.add(fieldsMatrixZone2[i][j]);
            }
        }
    }

    /*
     * *******************************************************************************
     * Actions
	 ********************************************************************************
     */
    /*
     Action when a Matrix is selected in zone 1
     */
    private void selectMatrixZone1() {
        if (newMatrixUnderPanelZone1 != null) {
            resetNewMatrixZone1();
        }
        String select = (String) selectMatrixZone1.getSelectedItem();
        if (select.equals("")) {
            instructionsZone1.setVisible(false);
            MatrixZone1 = null;
            nameMatrixZone1 = null;
            deleteZone1.setEnabled(false);
            resetMatrixZone1();
            initInstructionsZone1();
            resetOperationZone1();
            resetEditionZone1();
        } else {
            for (int i = 0; i < Matrices.size(); i++) {
                if (select.equals(Matrices.get(i))) {
                    nameMatrixZone1 = (String) Matrices.get(i);
                    MatrixZone1 = (Matrix) Matrices.get(i + 1);
                    nColZone1 = MatrixZone1.getNumColumns();
                    nLinesZone1 = MatrixZone1.getNumLines();
                    deleteZone1.setEnabled(true);
                    operationZone1();
                }
            }
        }
    }

    /*
    Action when a Matrix is selected in zone 2
     */
    private void selectMatrixZone2() {
        if (newMatrixUnderPanelZone2 != null) {
            resetNewMatrixZone2();
        }
        String select = (String) selectMatrixZone2.getSelectedItem();
        if (select.equals("")) {
            instructionsZone2.setVisible(false);
            MatrixZone2 = null;
            nameMatrixZone2 = null;
            deleteZone2.setEnabled(false);
            resetMatrixZone2();
            initInstructionsZone2();
            resetOperationZone2();
            resetEditionZone2();
        } else {
            for (int i = 0; i < Matrices.size(); i++) {
                if (select.equals(Matrices.get(i))) {
                    MatrixZone2 = (Matrix) Matrices.get(i + 1);
                    nameMatrixZone2 = (String) Matrices.get(i);
                    nColZone2 = MatrixZone2.getNumColumns();
                    nLinesZone2 = MatrixZone2.getNumLines();
                    deleteZone2.setEnabled(true);
                    operationZone2();
                }
            }
        }
    }

    /*
      Once the Matrix has been selected in zone 1, the zone enters operation mode
     */
    private void operationZone1() {
        resetMatrixZone1();
        resetEditionZone1();
        buttonsTabZone1[1].setEnabled(true);
        multLabelZone1.setEnabled(true);
        multScalarZone1.setBackground(Color.yellow);
        multScalarZone1.setEnabled(true);
        buttonsTabZone1[6].setEnabled(true);
        instructionsZone1.setVisible(false);
        initMatrixZone1();
        zone1.add(panelMatrixZone1);
        window.revalidate();
        window.repaint();
        opZone1 = true;
        if (opZone1 && opZone2) {
            addition.setEnabled(true);
            subtraction.setEnabled(true);
            multiplication.setEnabled(true);
        }
    }

    private void resetOperationZone1() {
        buttonsTabZone1[1].setEnabled(false);
        multLabelZone1.setEnabled(false);
        multScalarZone1.setBackground(Color.white);
        multScalarZone1.setEnabled(false);
        buttonsTabZone1[6].setEnabled(false);
        opZone1 = false;
        addition.setEnabled(false);
        subtraction.setEnabled(false);
        multiplication.setEnabled(false);
    }

    /*
    Once the Matrix has been selected in zone 2, the zone enters operation mode
     */
    private void operationZone2() {
        resetMatrixZone2();
        resetEditionZone2();
        buttonsTabZone2[1].setEnabled(true);
        multLabelZone2.setEnabled(true);
        multScalarZone2.setBackground(Color.yellow);
        multScalarZone2.setEnabled(true);
        buttonsTabZone2[6].setEnabled(true);
        instructionsZone2.setVisible(false);
        initMatrixZone2();
        zone2.add(panelMatrixZone2);
        zone2.repaint();
        opZone2 = true;
        if (opZone1 && opZone2) {
            addition.setEnabled(true);
            subtraction.setEnabled(true);
            multiplication.setEnabled(true);
        }
    }

    private void resetOperationZone2() {
        buttonsTabZone2[1].setEnabled(false);
        multLabelZone2.setEnabled(false);
        multScalarZone2.setBackground(Color.white);
        multScalarZone2.setEnabled(false);
        buttonsTabZone2[6].setEnabled(false);
        opZone2 = false;
        addition.setEnabled(false);
        subtraction.setEnabled(false);
        multiplication.setEnabled(false);
    }

    /*
     Edit Mode, allows you to make changes to the Matrix in Zone 1
     */
    private void editionZone1() {
        resetOperationZone1();
        buttonsTabZone1[1].setText("Save");
        for (int i = 1; i < 6; i++) {
            buttonsTabZone1[i].setEnabled(true);
        }
        saveZone1 = true;
        for (int i = 0; i < nLinesZone1; i++) {
            for (int j = 0; j < nColZone1; j++) {
                fieldsMatrixZone1[i][j].setBackground(Color.yellow);
                fieldsMatrixZone1[i][j].setEditable(true);
            }
        }
    }

    private void resetEditionZone1() {
        buttonsTabZone1[1].setText("Edit");
        for (int i = 2; i < 6; i++) {
            buttonsTabZone1[i].setEnabled(false);
        }
        saveZone1 = false;

    }

    /*
     When the save button of zone 1 is pressed, saves the modifications made in the Matrix. The zone returns to operation mode
     */
    private void saveZone1() {
        try {
            String buffer;
            String name;
            double[] elements = new double[nLinesZone1 * nColZone1];
            for (int i = 0; i < nLinesZone1; i++) {
                for (int j = 0; j < nColZone1; j++) {
                    buffer = fieldsMatrixZone1[i][j].getText();
                    elements[i * nColZone1 + j] = Double.parseDouble(buffer);
                }
            }
            Matrix save = new Matrix(nLinesZone1, nColZone1, elements);
            do {
                name = JOptionPane.showInputDialog("Name Matrix :");
                if (name == null) {
                    throw new MatrixException();
                }
                if (Matrices.contains(name)) {
                    JOptionPane.showMessageDialog(null, "Name of existing matrix", "Error", JOptionPane.ERROR_MESSAGE);
                    name = null;
                } else if (name.length() > 5 || name.isEmpty()) {
                    JOptionPane.showMessageDialog(window, "The name of the Matrix must contain between 1 and 5 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                    name = null;
                }
            } while (name == null);

            Matrices.add(name);
            Matrices.add(save);

            refreshSelectorALL();

            if (nameMatrixZone2 != null && opZone2) {
                selectMatrixZone2.setSelectedItem(nameMatrixZone2);
            }
            selectMatrixZone1.setSelectedItem(name);

            MatrixZone1 = save;
            nColZone1 = save.getNumColumns();
            nLinesZone1 = save.getNumLines();

            buttonsTabZone1[1].setText("Edit");
            for (int i = 1; i < 6; i++) {
                buttonsTabZone1[i].setEnabled(false);
            }
            saveZone1 = false;
            operationZone1();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "One or more fields are invalid", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MatrixException e) {

        }
    }

    /*
    Edit Mode, allows you to make changes to the Matrix in Zone 2
     */
    private void editionZone2() {
        resetOperationZone2();
        buttonsTabZone2[1].setText("Save");
        for (int i = 1; i < 6; i++) {
            buttonsTabZone2[i].setEnabled(true);
        }
        saveZone2 = true;
        for (int i = 0; i < nLinesZone2; i++) {
            for (int j = 0; j < nColZone2; j++) {
                fieldsMatrixZone2[i][j].setBackground(Color.yellow);
                fieldsMatrixZone2[i][j].setEditable(true);
            }
        }
    }

    private void resetEditionZone2() {
        buttonsTabZone2[1].setText("Edit");
        for (int i = 2; i < 6; i++) {
            buttonsTabZone2[i].setEnabled(false);
        }
        saveZone2 = false;
    }

    /*
    When the save button of zone 2 is pressed, saves the modifications made in the Matrix. The zone returns to operation mode
     */
    private void saveZone2() {
        try {
            String buffer;
            String name;
            double[] elements = new double[nLinesZone2 * nColZone2];
            for (int i = 0; i < nLinesZone2; i++) {
                for (int j = 0; j < nColZone2; j++) {
                    buffer = fieldsMatrixZone2[i][j].getText();
                    elements[i * nColZone2 + j] = Double.parseDouble(buffer);
                }
            }
            Matrix save = new Matrix(nLinesZone2, nColZone2, elements);
            do {
                name = JOptionPane.showInputDialog("Name Matrix :");
                if (name == null) {
                    throw new MatrixException();
                }
                if (Matrices.contains(name)) {
                    JOptionPane.showMessageDialog(null, "Name of existing matrix", "Error", JOptionPane.ERROR_MESSAGE);
                    name = null;
                } else if (name.length() > 5 || name.isEmpty()) {
                    JOptionPane.showMessageDialog(window, "The name of the Matrix must contain between 1 and 5 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                    name = null;
                }
            } while (name == null);

            Matrices.add(name);
            Matrices.add(save);

            refreshSelectorALL();

            if (nameMatrixZone1 != null && opZone1) {
                selectMatrixZone1.setSelectedItem(nameMatrixZone1);
            }
            selectMatrixZone2.setSelectedItem(name);

            MatrixZone2 = save;
            nColZone2 = save.getNumColumns();
            nLinesZone2 = save.getNumLines();

            buttonsTabZone2[1].setText("Edit");
            for (int i = 1; i < 6; i++) {
                buttonsTabZone2[i].setEnabled(false);
            }
            saveZone2 = false;
            operationZone2();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "One or more fields are invalid", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (MatrixException e) {

        }

    }

    /*
     Empty the Matrix panel of zone 1
     */
    private void resetMatrixZone1() {
        if (panelMatrixZone1 != null) {
            panelMatrixZone1.setVisible(false);
            panelMatrixZone1.revalidate();
            panelMatrixZone1 = null;
            zone1.revalidate();
        }
    }

    private void deleteMatrixZone1() {
        resetMatrixZone1();
        resetEditionZone1();
        resetOperationZone1();
        String name = (String) selectMatrixZone1.getSelectedItem();
        int index = Matrices.indexOf(name);
        MatrixZone1 = null;
        nameMatrixZone1 = null;
        Matrices.remove(index);
        Matrices.remove(index);
        refreshSelectorALL();
        if (nameMatrixZone2 != null) {
            selectMatrixZone2.setSelectedItem(nameMatrixZone2);
        }
        resetMatrixZone1();
        initInstructionsZone1();
        resetOperationZone1();
        resetEditionZone1();
    }

    /*
     Empty the Matrix panel of zone 2
     */
    private void resetMatrixZone2() {
        if (panelMatrixZone2 != null) {
            panelMatrixZone2.setVisible(false);
            panelMatrixZone2.revalidate();
            panelMatrixZone2 = null;
            zone2.revalidate();
        }
    }

    private void deleteMatrixZone2() {
        resetMatrixZone2();
        resetEditionZone2();
        resetOperationZone2();
        String name = (String) selectMatrixZone2.getSelectedItem();
        int index = Matrices.indexOf(name);
        MatrixZone2 = null;
        nameMatrixZone2 = null;
        Matrices.remove(index);
        Matrices.remove(index);
        refreshSelectorALL();
        if (nameMatrixZone1 != null) {
            selectMatrixZone1.setSelectedItem(nameMatrixZone1);
        }
        resetMatrixZone1();
        initInstructionsZone1();
        resetOperationZone1();
        resetEditionZone1();
    }

    private void initNewMatrixZone1() {
        int[] tab = {1, 2, 3, 4, 5, 6, 7, 8};
        instructionsZone1.setVisible(false);
        selectMatrixZone1.setSelectedItem("");
        resetMatrixZone1();

        newMatrixUnderPanelZone1 = new JPanel(null);
        newMatrixUnderPanelZone1.setBounds(105, 125, 250, 95);

        newMatrixPanelZone1 = new JPanel();
        newMatrixPanelZone1.setLayout(new GridLayout(2, 2, 0, 10));
        newMatrixPanelZone1.setBounds(0, 0, 250, 60);

        instructionsZone1.setVisible(false);

        newMatrixLabelLinesZone1 = new JLabel("Number of lines : ");
        newMatrixBoxLinesZone1 = new JComboBox();

        newMatrixLabelColumnsZone1 = new JLabel("Number of columns : ");
        newMatrixBoxColumnsZone1 = new JComboBox();

        for (int i = 0; i < tab.length; i++) {
            newMatrixBoxLinesZone1.addItem(tab[i]);
            newMatrixBoxColumnsZone1.addItem(tab[i]);
        }

        newMatrixButtonZone1 = new JButton("OK");
        newMatrixButtonZone1.setBounds(195, 70, 55, 25);
        newMatrixButtonZone1.addActionListener(this);
        newMatrixUnderPanelZone1.add(newMatrixButtonZone1);

        newMatrixPanelZone1.add(newMatrixLabelLinesZone1);
        newMatrixPanelZone1.add(newMatrixBoxLinesZone1);

        newMatrixPanelZone1.add(newMatrixLabelColumnsZone1);
        newMatrixPanelZone1.add(newMatrixBoxColumnsZone1);

        newMatrixUnderPanelZone1.add(newMatrixPanelZone1);

        zone1.add(newMatrixUnderPanelZone1);
        zone1.revalidate();

    }

    private void initNewMatrixZone2() {
        int[] tab = {1, 2, 3, 4, 5, 6, 7, 8};
        instructionsZone2.setVisible(false);
        selectMatrixZone2.setSelectedItem("");
        resetMatrixZone2();

        newMatrixUnderPanelZone2 = new JPanel(null);
        newMatrixUnderPanelZone2.setBounds(105, 125, 250, 95);

        newMatrixPanelZone2 = new JPanel();
        newMatrixPanelZone2.setLayout(new GridLayout(2, 2, 0, 10));
        newMatrixPanelZone2.setBounds(0, 0, 250, 60);

        instructionsZone2.setVisible(false);

        newMatrixLabelLinesZone2 = new JLabel("Number of lines : ");
        newMatrixBoxLinesZone2 = new JComboBox();

        newMatrixLabelColumnsZone2 = new JLabel("Number of columns : ");
        newMatrixBoxColumnsZone2 = new JComboBox();

        for (int i = 0; i < tab.length; i++) {
            newMatrixBoxLinesZone2.addItem(tab[i]);
            newMatrixBoxColumnsZone2.addItem(tab[i]);
        }

        newMatrixButtonZone2 = new JButton("OK");
        newMatrixButtonZone2.setBounds(195, 70, 55, 25);
        newMatrixButtonZone2.addActionListener(this);
        newMatrixUnderPanelZone2.add(newMatrixButtonZone2);

        newMatrixPanelZone2.add(newMatrixLabelLinesZone2);
        newMatrixPanelZone2.add(newMatrixBoxLinesZone2);

        newMatrixPanelZone2.add(newMatrixLabelColumnsZone2);
        newMatrixPanelZone2.add(newMatrixBoxColumnsZone2);

        newMatrixUnderPanelZone2.add(newMatrixPanelZone2);

        zone2.add(newMatrixUnderPanelZone2);
        zone2.revalidate();

    }

    private void creNewMatrixZone1() {

        nLinesZone1 = (int) newMatrixBoxLinesZone1.getSelectedItem();
        nColZone1 = (int) newMatrixBoxColumnsZone1.getSelectedItem();

        MatrixZone1 = new Matrix(nLinesZone1, nColZone1, 0);

        resetNewMatrixZone1();

        initMatrixZone1();
        zone1.add(panelMatrixZone1);
        zone1.revalidate();

        editionZone1();
        buttonsTabZone1[1].setEnabled(true);
    }

    private void creNewMatrixZone2() {

        nLinesZone2 = (int) newMatrixBoxLinesZone2.getSelectedItem();
        nColZone2 = (int) newMatrixBoxColumnsZone2.getSelectedItem();

        MatrixZone2 = new Matrix(nLinesZone2, nColZone2, 0);

        resetNewMatrixZone2();

        initMatrixZone2();
        zone2.add(panelMatrixZone2);
        zone2.revalidate();

        editionZone2();
        buttonsTabZone2[1].setEnabled(true);
    }

    private void resetNewMatrixZone1() {
        newMatrixUnderPanelZone1.setVisible(false);
        newMatrixUnderPanelZone1.revalidate();
        newMatrixUnderPanelZone1 = null;
        zone1.revalidate();
    }

    private void resetNewMatrixZone2() {
        newMatrixUnderPanelZone2.setVisible(false);
        newMatrixUnderPanelZone2.revalidate();
        newMatrixUnderPanelZone2 = null;
        zone2.revalidate();
    }

    private void addLineZone1() {
        if (nLinesZone1 < 8) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone1; i++) {
                for (int j = 0; j < nColZone1; j++) {
                    buffer = fieldsMatrixZone1[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone1.getElement(i, j);
                    }
                    MatrixZone1.setElement(i, j, value);
                }
            }

            double[] line = new double[nColZone1];
            for (int i = 0; i < line.length; i++) {
                line[i] = 0;
            }
            MatrixZone1.addLine(nLinesZone1, line);
            nLinesZone1++;

            resetMatrixZone1();
            resetOperationZone1();
            resetEditionZone1();
            initMatrixZone1();
            editionZone1();
            zone1.add(panelMatrixZone1);
            zone1.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add more than 8 lines", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLineZone2() {
        if (nLinesZone2 < 8) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone2; i++) {
                for (int j = 0; j < nColZone2; j++) {
                    buffer = fieldsMatrixZone2[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone2.getElement(i, j);
                    }
                    MatrixZone2.setElement(i, j, value);
                }
            }

            double[] line = new double[nColZone2];
            for (int i = 0; i < line.length; i++) {
                line[i] = 0;
            }
            MatrixZone2.addLine(nLinesZone2, line);
            nLinesZone2++;

            resetMatrixZone2();
            resetOperationZone2();
            resetEditionZone2();
            initMatrixZone2();
            editionZone2();
            zone2.add(panelMatrixZone2);
            zone2.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add more than 8 lines", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addColumnZone1() {
        if (nColZone1 < 8) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone1; i++) {
                for (int j = 0; j < nColZone1; j++) {
                    buffer = fieldsMatrixZone1[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone1.getElement(i, j);
                    }
                    MatrixZone1.setElement(i, j, value);
                }
            }

            double[] column = new double[nLinesZone1];
            for (int i = 0; i < column.length; i++) {
                column[i] = 0;
            }
            MatrixZone1.addColumn(nColZone1, column);
            nColZone1++;

            resetMatrixZone1();
            resetOperationZone1();
            resetEditionZone1();
            initMatrixZone1();
            editionZone1();
            zone1.add(panelMatrixZone1);
            zone1.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add more than 8 columns", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addColumnZone2() {
        if (nColZone2 < 8) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone2; i++) {
                for (int j = 0; j < nColZone2; j++) {
                    buffer = fieldsMatrixZone2[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone2.getElement(i, j);
                    }
                    MatrixZone2.setElement(i, j, value);
                }
            }

            double[] column = new double[nLinesZone2];
            for (int i = 0; i < column.length; i++) {
                column[i] = 0;
            }
            MatrixZone2.addColumn(nColZone2, column);
            nColZone2++;

            resetMatrixZone2();
            resetOperationZone2();
            resetEditionZone2();
            initMatrixZone2();
            editionZone2();
            zone2.add(panelMatrixZone2);
            zone2.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add more than 8 columns", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suppColumnZone1() {
        if (nColZone1 != 1) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone1; i++) {
                for (int j = 0; j < nColZone1; j++) {
                    buffer = fieldsMatrixZone1[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone1.getElement(i, j);
                    }
                    MatrixZone1.setElement(i, j, value);
                }
            }

            double[] column = MatrixZone1.deleteColumn(nColZone1 - 1);
            nColZone1--;

            resetMatrixZone1();
            resetOperationZone1();
            resetEditionZone1();
            initMatrixZone1();
            editionZone1();
            zone1.add(panelMatrixZone1);
            zone1.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "A Matrix must contain at least one column", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suppColumnZone2() {
        if (nColZone2 != 1) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone2; i++) {
                for (int j = 0; j < nColZone2; j++) {
                    buffer = fieldsMatrixZone2[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone2.getElement(i, j);
                    }
                    MatrixZone2.setElement(i, j, value);
                }
            }

            double[] column = MatrixZone2.deleteColumn(nColZone2 - 1);
            nColZone2--;

            resetMatrixZone2();
            resetOperationZone2();
            resetEditionZone2();
            initMatrixZone2();
            editionZone2();
            zone2.add(panelMatrixZone2);
            zone2.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "A Matrix must contain at least one column", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMatrix() {
        try {
            nameMatrixZone4 = nameMatrixZone1 + " + " + nameMatrixZone2 + "  =  ";
            MatrixZone4 = (Matrix) MatrixZone1.sum(MatrixZone2);
            resetZone4();
            populateZone4();
        } catch (MatrixException e) {
            JOptionPane.showMessageDialog(null, "Matrices don't have the right format to add up", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void subMatrix() {
        try {
            nameMatrixZone4 = nameMatrixZone1 + " - " + nameMatrixZone2 + "  =  ";
            MatrixZone4 = (Matrix) MatrixZone1.diff(MatrixZone2);
            resetZone4();
            populateZone4();
        } catch (MatrixException e) {
            JOptionPane.showMessageDialog(null, "Matrices don't have the right format to get difference", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void multMatrix() {
        try {
            nameMatrixZone4 = nameMatrixZone1 + " x " + nameMatrixZone2 + " =  ";
            MatrixZone4 = (Matrix) MatrixZone1.product(MatrixZone2);
            resetZone4();
            populateZone4();
        } catch (MatrixException e) {
            JOptionPane.showMessageDialog(null, "Matrices don't have the right format to multiply", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void multValueZone1() {
        String scalarText = multScalarZone1.getText();
        try {
            double scalar = Double.parseDouble(scalarText);
            nameMatrixZone4 = nameMatrixZone1 + " * " + scalarText + " =  ";
            MatrixZone4 = (Matrix) MatrixZone1.product(scalar);
            resetZone4();
            populateZone4();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The entered scalar is not a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void multValueZone2() {
        String scalarText = multScalarZone2.getText();
        try {
            double scalar = Double.parseDouble(scalarText);
            nameMatrixZone4 = nameMatrixZone2 + " * " + scalarText + " =  ";
            MatrixZone4 = (Matrix) MatrixZone2.product(scalar);
            resetZone4();
            populateZone4();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The entered scalar is not a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suppLineZone1() {
        if (nLinesZone1 != 1) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone1; i++) {
                for (int j = 0; j < nColZone1; j++) {
                    buffer = fieldsMatrixZone1[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone1.getElement(i, j);
                    }
                    MatrixZone1.setElement(i, j, value);
                }
            }

            double[] line = MatrixZone1.deleteLine(nLinesZone1 - 1);
            nLinesZone1--;

            resetMatrixZone1();
            resetOperationZone1();
            resetEditionZone1();
            initMatrixZone1();
            editionZone1();
            zone1.add(panelMatrixZone1);
            zone1.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "A Matrix must contain at least one line", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void suppLineZone2() {
        if (nLinesZone2 != 1) {
            double value;
            String buffer;
            for (int i = 0; i < nLinesZone2; i++) {
                for (int j = 0; j < nColZone2; j++) {
                    buffer = fieldsMatrixZone2[i][j].getText();
                    try {
                        value = Double.parseDouble(buffer);
                    } catch (NumberFormatException e) {
                        value = MatrixZone2.getElement(i, j);
                    }
                    MatrixZone2.setElement(i, j, value);
                }
            }

            double[] line = MatrixZone2.deleteLine(nLinesZone2 - 1);
            nLinesZone2--;

            resetMatrixZone2();
            resetOperationZone2();
            resetEditionZone2();
            initMatrixZone2();
            editionZone2();
            zone2.add(panelMatrixZone2);
            zone2.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "A Matrix must contain at least one line", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transposeZone1() {
        nameMatrixZone4 = "T (" + nameMatrixZone1 + ")  =  ";
        MatrixZone4 = (Matrix) MatrixZone1.transpose();
        resetZone4();
        populateZone4();
    }

    private void transposeZone2() {
        nameMatrixZone4 = "T (" + nameMatrixZone2 + ")  =  ";
        MatrixZone4 = (Matrix) MatrixZone2.transpose();
        resetZone4();
        populateZone4();
    }

    private void initZone4() {
        resultZone4 = new JPanel(null);
        resultZone4.setBounds(10, 10, 970, 210);
        resultZone4.setBackground(Color.white);
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        resultZone4.setBorder(border);
        zone4.add(resultZone4);
        window.revalidate();
    }

    private void populateZone4() {
        nameZone4 = new JTextArea(nameMatrixZone4);
        nameZone4.setBounds(80, 20, 60, 25);
        nameZone4.setEditable(false);
        Font font = new Font("Courier New", Font.BOLD, 12);
        nameZone4.setFont(font);
        MatrixAffZone4 = new JTextArea(MatrixZone4.toString());

        MatrixAffZone4.setFont(font);
        MatrixAffZone4.setEditable(false);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(180, 20, 400, 140);
        scrollPane.setViewportView(MatrixAffZone4);

        saveZone4 = new JButton("Save");
        saveZone4.setBounds(600, 20, 120, 25);
        saveZone4.addActionListener(this);
        resultZone4.add(nameZone4);
        resultZone4.add(scrollPane);
        resultZone4.add(saveZone4);
        resultZone4.revalidate();
        zone4.revalidate();
        window.revalidate();
        window.repaint();
    }

    private void resetZone4() {
        if (nameZone4 != null) {
            nameZone4.setVisible(false);
            MatrixAffZone4.setVisible(false);
            scrollPane.setVisible(false);
            saveZone4.setVisible(false);
            window.revalidate();
        }
    }

    private void saveZone4() {
        String buffer;
        String name;
        int nLines = MatrixZone4.getNumLines();
        int nCol = MatrixZone4.getNumColumns();
        double[] elements = new double[nLines * nCol];
        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nCol; j++) {
                elements[i * nCol + j] = MatrixZone4.getElement(i, j);
            }
        }
        Matrix save = new Matrix(nLines, nCol, elements);
        do {
            name = JOptionPane.showInputDialog("Name Matrix :");
            if (name == null) {
                throw new MatrixException();
            }
            if (Matrices.contains(name)) {
                JOptionPane.showMessageDialog(null, "Name of existing matrix", "Error", JOptionPane.ERROR_MESSAGE);
                name = null;
            } else if (name.length() > 5 || name.isEmpty()) {
                JOptionPane.showMessageDialog(window, "The name of the Matrix must contain between 1 and 5 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                name = null;
            }
        } while (name == null);

        Matrices.add(name);
        Matrices.add(save);

        refreshSelectorALL();

        if (nameMatrixZone2 != null) {
            selectMatrixZone2.setSelectedItem(nameMatrixZone2);
        }
        if (nameMatrixZone1 != null) {
            selectMatrixZone1.setSelectedItem(nameMatrixZone1);
        }
        resetZone4();
    }

}
