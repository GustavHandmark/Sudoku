
public class Main {

	// Currently used for manual testing
	public static void main(String[] args) {
		SudokuModel sm = new SudokuModel();
		sm.setValue(3, 1, 1);
		sm.setValue(5, 8, 8);
		sm.setValue(9, 7, 8);
		sm.setValue(4, 3, 5);

		// testar reglerna
		sm.setValue(4, 3, 2);
		sm.setValue(4, 8, 5);
		sm.setValue(4, 4, 4);
		sm.setValue(19, 7, 8);

		sm.printMatrix();

	}
}
