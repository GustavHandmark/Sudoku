import java.util.*;

public class SudokuModel {
	// matrix representing the sudoku grid.
	private Cell[][] matrix;
	private Cell[][] regionList;

	public SudokuModel() {
		matrix = new Cell[9][9];
		regionList = new Cell[9][9];
		populateCells();
	}

	/**
	 * Creates value=0, region=getRegion(i,j) Cells for all [9][9] of matrix[][] and
	 * then links every cell to vectors in regionList[][]
	 * 
	 */
	private void populateCells() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix[i][j] = new Cell(0, getRegion(i, j));
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				regionList[i][j] = matrix[((i / 3) * 3 + j / 3)][((i % 3) * 3 + j % 3)]; // FUNKAR, men måste vara i en
																							// egen nested for-loop

				// System.out.println("regionList[" + i + "," + j + "] = matrix[" + ((i / 3) * 3
				// + j / 3) + "," // TEST
				// + ((i % 3) * 3 + j % 3) + "] region:" + getRegion(i, j));
			}
		}

	}

	// (i/3)*3+j/3, (i%3)*3+j%3

	/**
	 * Sets the given value at the given position in the matrix
	 * 
	 * @param value
	 * @param row
	 * @param column
	 */
	public void setValue(int value, int row, int column) {
		if (checkRules(value, row, column))
			matrix[row][column].value = value;
	}

	private int getRegion(int row, int column) {
		int regionRow = (row) / 3;
		int regionCol = (column) / 3;
		return regionCol + regionRow * 3;
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
						System.out.println("Detta värde finns redan på denna rad på plats: " + matrix[row][i].value
								+ "[" + row + "," + i + "]");
						return false;
					} else if (i != row && matrix[i][column].value == value) {// check column for duplicate
						System.out.println("Detta värde finns redan i denna kolumn på plats: " + matrix[i][column].value
								+ "[" + i + "," + column + "]");
						return false;
					} else if (regionList[getRegion(row, column)][i].value == value) {// INTE KLAR, inget undantag för
																						// sig själv
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

	/**
	 * TEST METHOD Prints the matrix in console with VERY NICE layout and design
	 * 
	 */
	public void printMatrix() {
		System.out.println();
		for (int i = 0; i < matrix.length; i++) {
			if (i != 0 && (i % 3) == 0) {
				System.out.print("----- + ----- + -----\n");
			}
			for (int j = 0; j < matrix[i].length; j++) {
				if (j != 0 && (j % 3) == 0)
					System.out.print("| ");
				if (matrix[i][j] == null || matrix[i][j].value == 0) { // Ta bort en av dessa senare
					System.out.print("□ ");
				} else {
					System.out.print(matrix[i][j].value + " ");
					// System.out.print(matrix[i][j].region + " "); // TEST
				}
			}
			System.out.println();
		}
	}
}
