import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private int [][] nbrsMatrix;

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

	/**
	 * INTE KLAR - TODO: Kolla region
	 * 
	 * Return true if the placement of a value is acceptable, else false. RULES
	 * 1. talet måste vara mellan 1 och 9. 2. Only one occurance of a number per
	 * row || column 3. Only one occurance of a number within a region
	 * 
	 * @param value
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean checkRules(int value, int row, int column) {
		if (value > 0 && value < 10) {
			for (int i = 0; i < 9; i++) {
				if (matrix[row][i] != null || matrix[i][column] != null) {
					if (i != column && matrix[row][i].value == value) { // check
																		// row
																		// for
																		// duplicate
						System.out.println("Detta värde finns redan på denna rad på plats: " + matrix[row][i].value
								+ "[" + row + "," + i + "]");
						return false;
					} else if (i != row && matrix[i][column].value == value) {// check
																				// column
																				// for
																				// duplicate
						System.out.println("Detta värde finns redan i denna kolumn på plats: " + matrix[i][column].value
								+ "[" + i + "," + column + "]");
						return false;
					} else if (regionList[getRegion(row, column)][i].value == value) {// INTE
																						// KLAR,
																						// inget
																						// undantag
																						// för
																						// sig
																						// själv
						System.out.println("Detta värde finns redan i denna region på plats: "
								+ matrix[getRegion(row, column)][i].value + "[" + getRegion(row, column) + "," + i
								+ "]");
						return false;
					}

				}
			}

		} else {
			System.out.print("Value needs to be an integer between 1 and 9");
			return false;
		}

		return true;
	}

	/**
	 * returns true if the given value is already found within the region
	 * corresponding to the given row and column.
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	private boolean checkRegion(int row, int col, int value) {
		int regionRow = (row / 3) * 3;
		int regionCol = (col / 3) * 3;

		for (int i = regionRow; i <= regionRow + 2; i++) {
			for (int j = regionCol; j <= regionCol + 2; i++) {
				if (nbrsMatrix[i][j] == value) {
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
