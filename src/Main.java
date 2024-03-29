
public class Main {

	// Currently used for manual testing
	public static void main(String[] args) {
		SudokuModel sm = new SudokuModel();
		
		sm.setValue(8, 0, 2);
		sm.setValue(1, 2, 0);
		sm.setValue(2, 2, 2);

		sm.setValue(9, 0, 5);
		sm.setValue(5, 2, 3);
		
		sm.setValue(6, 0, 7);
		sm.setValue(2, 0, 8);

		sm.setValue(5, 1, 8);

		sm.setValue(5, 4, 1);
		sm.setValue(6, 5, 0);

		sm.setValue(2, 3, 3);
		sm.setValue(1, 3, 4);
		
		sm.setValue(9, 3, 7);
		sm.setValue(6, 4, 6);
		sm.setValue(2, 5, 7);
		sm.setValue(8, 5, 8);

		sm.setValue(4, 6, 0);
		sm.setValue(1, 6, 1);
		sm.setValue(8, 7, 0);
		sm.setValue(6, 7, 1);
		
		sm.setValue(6, 6, 3);
		sm.setValue(8, 6, 5);
		sm.setValue(3, 7, 4);
		
		sm.setValue(1, 7, 6);
		sm.setValue(4, 8, 6);




		// testar reglerna
		sm.printMatrix();
		System.out.println("checkMatrix: "+sm.checkMatrix());
		System.out.println("solveSudoku: "+sm.solveSudoku());
		sm.printMatrix();
		
	}
}
