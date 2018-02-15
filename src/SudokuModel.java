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
	 * @param value, Integer between 1 and 9
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int column) {
		if (value > 0 && value <= 9) {
			matrix[row][column] = value;
		} else {
			throw new IllegalArgumentException("Value needs to be an integer between 1 and 9");
		}
	}

	public int getValue(int row, int column) {
		return matrix[row][column];
	}

	
}
