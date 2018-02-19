import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private Cell[][] matrix;

	public SudokuModel() {
		matrix = new Cell[9][9];
	}

	/**
	 * Sets the given value at the given position in the matrix
	 * 
	 * @param value
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int column) {
		checkRules(value, row, column); // hur hanterar vi ifall denna returnar false?
		matrix[row][column] = new Cell(value, setRegion(row, column));

	}

	private int setRegion(int row, int column) {
		int regionRow = (row) / 3; // zero based majorRow
		int regionCol = (column) / 3; // zero based majorCol
		return regionCol + regionRow * 3 + 1;
	}

	/**
	 * INTE KLAR - TODO: Kolla region
	 * 
	 * Return true if the placement of a value is acceptable, else false. RULES 1.
	 * talet måste vara mellan 1 och 9. 2. Only one occurance of a number per row ||
	 * column 3. Only one occurance of a number within a region
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
					if (i != column && matrix[row][i].value == value) { // check row for duplicate
						System.out.println("Detta värde finns redan på denna rad ");
						return false;
					} 
					else if (i != row && matrix[i][column].value == value) {// check column for duplicate
						System.out.println("Detta värde finns redan i denna kolumn ");
						return false;
					}
					else if () {
						int r = matrix[row][column].region;
						
					}
				}
			}
			
			
			
		} else
			System.out.print("Value needs to be an integer between 1 and 9");

		return true;
	}

	// Här försöker jag definiera en "Cell", en ny klass vi gör matrisen av så att
	// den kan ha både värde och region
	static class Cell {
		int value = 0;
		int region;

		private Cell(int v, int r) {
			this.value = v;
			this.setRegion(r);
		}

		private void setRegion(int r) {
			this.region = r;
		}
	}

	// WIP
	private Cell[] getRegionList(int r) {
		Cell[] list = new Cell[9];
		
		
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
				if (matrix[i][j] == null) {
					System.out.print("□ ");
				} else {
					System.out.print(matrix[i][j].value + " ");
				}
			}
			System.out.println();
		}

	}
}
