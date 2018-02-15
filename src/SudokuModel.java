import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private int[][] nbrsMatrix;

	public SudokuModel() {
		nbrsMatrix = new int[9][9];
	}

	/**
	 * Sets the given value at the destined position in the matrix
	 * 
	 * @param value
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int column) {
		if (value > 0 && value <= 9) {
			nbrsMatrix[row][column] = value;
		} else {
			throw new IllegalArgumentException("Value needs to be an integer between 1 and 9");
		}
	}

	/**
	 * Return the value at the destined position in the matrix, or 0 if a 
	 * value has yet to be assigned.
	 * @param row
	 * @param column
	 * @return
	 */
	public int getValue(int row, int column) {
		return nbrsMatrix[row][column];
	}

	
}
