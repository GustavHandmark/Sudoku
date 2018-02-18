import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private int[][] matrix;

	public SudokuModel() {
		matrix = new int[9][9];

	}

	/**
	 * Sets the given value at the given position in the matrix
	 * 
	 * @param value
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int column) {
		if (value > 0 && value < 10) {
			checkRules(value, row, column);
			matrix[row][column] = value;
		} else
			throw new IllegalArgumentException("Value needs to be an integer between 1 and 9");

	}

	/**
	 * Return true if the placement of a value is acceptable, else false. RULES 1.
	 * Only one occurance of a number per line 2. Only one occurance of a number
	 * within a region
	 * 
	 * @param value
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean checkRules(int value, int row, int column) {
		for (int i = 0; i < 9; i++) {
			if (i != column && matrix[row][i] == value) { // check row for duplicate
				System.out.println("Detta värde finns redan på denna rad");
				return false;
			} else if (i != row && matrix[i][column] == value) {// check column for duplicate
				System.out.println("Detta värde finns redan i denna kolumn");
				return false;
			}
		}
		// Check region
		
		
		
		return true;
	}

	/**
	 * Return the value at the destined position in the matrix, or 0 if a value has
	 * yet to be assigned.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public int getValue(int row, int column) {
		return matrix[row][column];
	}

	/**
	 * TEST METHOD Prints the matrix in console with VERY NICE layout and design
	 * 
	 */
	public void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			if (i != 0 && (i % 3) == 0) {
				System.out.print("- - - + - - - + - - -\n");
			}
			for (int j = 0; j < matrix[i].length; j++) {
				if (j != 0 && (j % 3) == 0)
					System.out.print("| ");
				if (matrix[i][j] == 0) {
					System.out.print("□ ");
				} else {
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}

	}
}
