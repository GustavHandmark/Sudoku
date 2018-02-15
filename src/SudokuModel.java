import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private int[][] nbrsMatrix;

	public SudokuModel() {
		nbrsMatrix = new int[9][9];
	}

	/**
	 * Sets
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

	public int getValue(int row, int column) {
		return nbrsMatrix[row][column];
	}

}
