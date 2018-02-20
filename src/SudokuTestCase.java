import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test solve an empty sudoku
	 */
	@Test
	public final void testNewFifoQueue() {
		assertTrue(myIntQueue.isEmpty());
		assertTrue(myIntQueue.size() == 0);
	}


}
