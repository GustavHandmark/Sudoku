
public class Main {

	// Currently used for manual testing
	public static void main(String[] args) {
		SudokuModel sm = new SudokuModel();
		sm.setValue(3, 1, 1);
		sm.setValue(5, 8, 8);

		sm.printMatrix();
		
	}
}
