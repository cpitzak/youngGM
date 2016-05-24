package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

public class MoveTest {
	
	@Before
	public void setup() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void correctTurnWhiteTest() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		assertNull(moveResult);
		moveResult = board.makeMove("d2d3");
		assertNotNull(moveResult);
	}
	
	@Test
	public void correctTurnBlackTest() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		assertNull(moveResult);
		moveResult = board.makeMove("e7e5");
		assertNull(moveResult);
		moveResult = board.makeMove("d7d6");
		assertNotNull(moveResult);
	}
	
	@Test
	public void tryToMoveToOccupiedSquareOfSameColor() {
		Board board = new Board();
		String moveResult = board.makeMove("f1f2");
		assertNotNull(moveResult);
		moveResult = board.makeMove("g1e2");
		assertNotNull(moveResult);
		
		moveResult = board.makeMove("e2e4");
		moveResult = board.makeMove("f8e7");
		assertNotNull(moveResult);
		moveResult = board.makeMove("g8e7");
		assertNotNull(moveResult);
	}
	
	@Test
	public void testCastleWhiteKingSide() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		moveResult = board.makeMove("e7e5");
		moveResult = board.makeMove("g1f3");
		moveResult = board.makeMove("g8f6");
		moveResult = board.makeMove("f1e2");
		moveResult = board.makeMove("f8e7");
		// castle move
		moveResult = board.makeMove("e1g1");
		assertNull(moveResult);
	}
	
	@Test
	public void testCastleWhiteQueenSide() {
		Board board = new Board();
		String moveResult = board.makeMove("d2d4");
		moveResult = board.makeMove("d7d5");
		moveResult = board.makeMove("b1c3");
		moveResult = board.makeMove("b8c6");
		moveResult = board.makeMove("d1d3");
		moveResult = board.makeMove("d8d6");
		moveResult = board.makeMove("c1d2");
		moveResult = board.makeMove("c8d7");
		// castle move
		moveResult = board.makeMove("e1c1");
		assertNull(moveResult);
	}
	
	@Test
	public void testCastleBlackKingSide() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		moveResult = board.makeMove("e7e5");
		moveResult = board.makeMove("g1f3");
		moveResult = board.makeMove("g8f6");
		moveResult = board.makeMove("f1e2");
		moveResult = board.makeMove("f8e7");
		moveResult = board.makeMove("b1c3");
		// castle move
		moveResult = board.makeMove("e8g8");
		assertNull(moveResult);
	}
	
	@Test
	public void testCastleBlackQueenSide() {
		Board board = new Board();
		String moveResult = board.makeMove("d2d4");
		moveResult = board.makeMove("d7d5");
		moveResult = board.makeMove("b1c3");
		moveResult = board.makeMove("b8c6");
		moveResult = board.makeMove("d1d3");
		moveResult = board.makeMove("d8d6");
		moveResult = board.makeMove("c1d2");
		moveResult = board.makeMove("c8d7");
		moveResult = board.makeMove("a2a3");
		moveResult = board.makeMove("e8c8");
		// castle move
		assertNull(moveResult);
	}
	
	@Test
	public void testPawnWhiteCapture() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		moveResult = board.makeMove("d7d5");
		// capture
		moveResult = board.makeMove("e4d5");
		assertNull(moveResult);
	}
	
	@Test
	public void testPawnBlackCapture() {
		Board board = new Board();
		String moveResult = board.makeMove("e2e4");
		moveResult = board.makeMove("d7d5");
		moveResult = board.makeMove("d2d3");
		moveResult = board.makeMove("d5e4");
		// capture
		assertNull(moveResult);
	}
	
	@Test
	public void testKnightWhiteCapture() {
		Board board = new Board();
		String moveResult = board.makeMove("g1f3");
		moveResult = board.makeMove("g8f6");
		moveResult = board.makeMove("f3e5");
		moveResult = board.makeMove("f6e4");
		// capture
		moveResult = board.makeMove("e5f7");
		assertNull(moveResult);
	}
	
	@Test
	public void testKnightBlackCapture() {
		Board board = new Board();
		String moveResult = board.makeMove("g1f3");
		moveResult = board.makeMove("g8f6");
		moveResult = board.makeMove("f3e5");
		moveResult = board.makeMove("f6e4");
		moveResult = board.makeMove("h2h3");
		// capture
		moveResult = board.makeMove("e4f2");
		assertNull(moveResult);
	}
	
	@Test
	public void moveTest() {
		Board board = new Board();
		board.makeMove("e2e4");
		board.print();
	}
	
	@Test
	public void pawnPromotionTest() {
		Board board = new Board();
		board.importFEN("8/4P3/8/8/8/8/K7/7k w ---- - 0 1");
		
		board.makeMove("e7e8Q");
		
		board.print();
	}
	
}
