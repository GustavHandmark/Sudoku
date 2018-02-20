import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private int[][] nbrsMatrix;

	/**
	 * Creates an empty Sudoku
	 */
	public SudokuModel() {
		nbrsMatrix = new int[9][9];
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

	public int getValue(int row, int col) {
		return nbrsMatrix[row][col];
	}

	public int[][] getMatrix() {
		return nbrsMatrix;
	}

	/**
	 * Return true if the placement of a value is acceptable, else false. RULES
	 * 1. talet måste vara mellan 1 och 9. 2. Only one occurance of a number per
	 * row || column 3. Only one occurance of a number within a region Return
	 * true if the placement of a value is acceptable, else false. RULES 1.
	 * talet måste vara mellan 1 och 9. 2. Only one occurance of a number per
	 * row || column 3. Only one occurance of a number within a region
	 * 
	 * @param value
	 * @param row
	 * @param column
	 * @return
	 */
	// public boolean checkRules(int value, int row, int column) {
	// if (value > 0 && value < 10) {
	// for (int i = 0; i < 9; i++) {
	// if (matrix[row][i] != null || matrix[i][column] != null) {
	// if (i != column && matrix[row][i].value == value) { // check row
	// System.out.println("Detta värde finns redan på denna rad på plats: " +
	// matrix[row][i].value + "[" + row + "," + i + "]");
	// return false;
	// } else if (i != row && matrix[i][column].value == value) {// check column
	// System.out.println("Detta värde finns redan i denna kolumn på plats: " +
	// matrix[i][column].value + "[" + i + "," + column + "]");
	// return false;
	// } else if (value != 0 && regionList[getRegion(row, column)][i].value ==
	// value) {// INTE KLAR
	// System.out.println(
	// "Detta värde finns redan i denna region på plats: " +
	// regionList[getRegion(row, column)][i].value + "[" + getRegion(row,
	// column) + "," + column + "]");
	// return false;
	// }
	// }
	// }
	//
	// } else {
	// System.out.print("Value needs to be an integer between 1 and 9");
	// return false;
	// }
	//
	// return true;
	// }
	public boolean solveSudoku() {
		return solveSudoku(0, 0);
	}

	private boolean solveSudoku(int row, int col) {
		/**
		 * Base case: if we are at the last row col and the inserted value is
		 * cleared by checkrules -> return true (sudoku solved)
		 * 
		 * else -> if there is not already an assigned value -> try inserting
		 * value 1 and clear by checkrules, if checkrules returns false ->
		 * increment value by one and try again until a possible candidate is
		 * found, Go to the next cell and repeat.
		 *
		 * if no value (1-9) is a possible candidate, go back to the previous
		 * cell and try another possible candidate then repeat.
		 * 
		 * If the sudoku has no solution, i.e.the last cannot fulfill the
		 * condition so as that it is cleared -> return false
		 */
		if (row == 8 && col == 8 && checkRules(8, 9, nbrsMatrix[9][9]) == true) {
			return true;
		}

		// implement recursive solver for the sudoku.
		return false;
	}

	/**
	 * returns false if the given value is already found within the sudoku.
	 * returns true if the given value is a possible candidate.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public boolean checkRules(int row, int col, int value) {
		// Checks if the parameter value is permitted depending on the numbers
		// in the given row
		// the parameter value should not return false.
		for (int i = 0; i < 9; i++) {
			if (nbrsMatrix[row][i] == value && i != col) {
				return false;
			}
		}

		// Checks if the value is permitted depending on the numbers in the
		// given column,
		// the parameter value should not return false.
		for (int i = 0; i < 9; i++) {
			if (nbrsMatrix[i][col] == value && i != row) {
				return false;
			}
		}

		// Checks if the value is permitted depending on the numbers in the
		// matrix region.
		int regionRow = (row / 3) * 3;
		int regionCol = (col / 3) * 3;

		for (int i = regionRow; i <= regionRow + 2; i++) {
			for (int j = regionCol; j <= regionCol + 2; i++) {
				// The number which we're checking should not throw a false,
				if (nbrsMatrix[i][j] == value && (regionRow + i) != row && (regionCol + j) != col) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * TEST METHOD Prints the matrix in console with VERY NICE layout and design
	 * 
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
				if (nbrsMatrix[i][j] == 0) { // Ta
												// bort
												// en av
												// dessa
												// senare
					System.out.print("□ ");
				} else {
					System.out.print(nbrsMatrix[i][j] + " ");
					// System.out.print(matrix[i][j].region + " "); // TEST

				}
			}
			System.out.println();
		}
	}
}
