package com.example.sudokuandroid;

public class SudokuModel {
	private int[][] nbrsMatrix;
    // matrix representing the sudoku grid.
    private int[][] nbrsMatrix;

    /**
     * Creates an empty Sudoku
     */
    public SudokuModel() {

        nbrsMatrix = new int[9][9];
    }

    /**
	* Checks if all values on the board fulfill the rules enforced in checkRules()
	* Returns false if a rule is broken, else true
	*
	* @return 
	*/
    public boolean checkMatrix() {
        for (int i = 0; i < nbrsMatrix.length; i++) {
            for (int j = 0; j < nbrsMatrix[i].length; j++) {
                if (nbrsMatrix[i][j] != 0 && checkRules(i, j, nbrsMatrix[i][j]) == false) {
                    return false;
                }
            }
        }
        return true;
    }
   
    /**
     * Sets the given value at the given position in the matrix
     *
     * @param value
     * @param row
     * @param column
     */
    public void setValue(int value, int row, int column) {
        if (value > 0 && value <= 9) {
            nbrsMatrix[row][column] = value;
        } else {
            throw new IllegalArgumentException("Value needs to be an integer between (and including) 1 and 9");
        }
    }
	
	/**
	 * Returns the value at position (row, col)
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
    public int getValue(int row, int col) {
        return nbrsMatrix[row][col];
    }
	
	/**
	 * Clears the value at position (row, col)
	 *
	 *@param row
	 *@param col
	 */
    public void clearValue(int row, int col) {
        nbrsMatrix[row][col] = 0;
    }

	/**
	 *  Clears entire matrix
	 * 
	 */
    public void clearMatrix() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++)
                nbrsMatrix[r][c] = 0;
        }
    }


    /**
     * Solves the sudoku using recursive backtracking through a private method.
	 *
	 * Base case: if we are at the last row col and the inserted value is cleared by checkrules -> return true (sudoku solved)
     *
	 * else -> if there is not already an assigned value -> try inserting value 1 and clear by checkrules, if checkrules returns false -> increment value by one and try again until
     * a possible candidate is found, Go to the next cell and repeat.
     *
     * if no value (1-9) is a possible candidate, go back to the previous cell and try another possible candidate then repeat.
     *
     * @return false if sudoku isn't solveable
     */
    public boolean solveSudoku() {
        if (checkMatrix())
            return solveSudoku(0, 0);
        return false;
    }

    private boolean solveSudoku(int row, int col) {
        // Non-empty cell -> proceed to the next row-col.
        if (row == 9) {
            return true;
        }
        if (nbrsMatrix[row][col] > 0) {
            if (col == 8) {
                return solveSudoku(row + 1, 0);
            } else {
                return solveSudoku(row, col + 1);
            }
        }
        // Empty cells, Recursively attemps to solve the sudoku.
        for (int value = 1; value <= 9; value++) {

            if (row == 8 && col == 8 && checkRules(row, col, value)) {
                nbrsMatrix[row][col] = value;
                return true;
            }
            if (checkRules(row, col, value)) {
                nbrsMatrix[row][col] = value;
                if (col == 8) {
                    if (solveSudoku(row + 1, 0)) {
                        return true;
                    }

                } else {
                    if (solveSudoku(row, col + 1)) {
                        return true;
                    }
                }
            }
        }
        nbrsMatrix[row][col] = 0; // No candidate is possible, set to 0, return
        // false and go back.
        return false;
    }

    /**
     * Returns false if the given value at position (row,col) interferes with the ruleset. returns true if the given value is a possible candidate.
     *
     * @param row
     * @param col
     * @param value
     * @return
     */
    public boolean checkRules(int row, int col, int value) {
        
		// Checks if the parameter value is permitted depending on the numbers in the given row. Should not return false when the parameter value is checked against itself
		
        for (int i = 0; i < 9; i++) {
            if (nbrsMatrix[row][i] == value && i != col) {
                return false;
            }
        }

		
		// Checks if the value is permitted depending on the numbers in the given column, the parameter value should not return false.
		 
        for (int i = 0; i < 9; i++) {
            if (nbrsMatrix[i][col] == value && i != row) {
                return false;
            }
        }

		// Checks if the value is permitted depending on the numbers in the matrix region.
		
        int regionRow = (row / 3) * 3;
        int regionCol = (col / 3) * 3;
        for (int i = regionRow; i <= regionRow + 2; i++) {
            for (int j = regionCol; j <= regionCol + 2; j++) {
                if (i != row && j != col && nbrsMatrix[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * TEST METHOD Prints the matrix in console with VERY NICE layout and design
	 */
    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < nbrsMatrix.length; i++) {
            if (i != 0 && (i % 3) == 0) {
                System.out.print("----- + ----- + -----\n");
            }
            for (int j = 0; j < nbrsMatrix[i].length; j++) {
                if (j != 0 && (j % 3) == 0)
                    System.out.print("| ");
                if (nbrsMatrix[i][j] == 0) {
                    System.out.print("□ ");
                } else {
                    System.out.print(nbrsMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
