import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;


public class SudokuTestCase {
	SudokuModel sm;

	@Before
	public void setUp() throws Exception {
		sm = new SudokuModel();
	}

	@After
	public void tearDown() throws Exception {
		sm = null;
	}

	/**
	 * Test solve an empty sudoku
	 */
	@Test
	public final void testSolveEmptySudoku() {
		assertTrue("After: Invalid solution", sm.solveSudoku());
	}

	/**
	 * Test if solution is correct
	 */
	@Test
	public void testUnsovableSudoku() {
		sm.setValue(5, 0, 0);
		sm.setValue(5, 0, 1);
		assertTrue("After: ", sm.solveSudoku());
		
		sm.setValue(5, 0, 0);
		sm.setValue(5, 1, 0);
		assertTrue("After: ", sm.solveSudoku());
		
		sm.setValue(5, 0, 0);
		sm.setValue(5, 1, 1);
		assertTrue("After: ", sm.solveSudoku());
		
	}
	
	@Test
	public void testUnsovableSudokuAndRemove() {
		sm.setValue(1, 0, 0);
		sm.setValue(1, 0, 0);
		sm.setValue(1, 0, 0);
		sm.setValue(1, 0, 0);
		sm.setValue(1, 0, 0);
		sm.setValue(1, 0, 0);
	}

}
