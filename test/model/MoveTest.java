package model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import interfaces.PieceLibrary;

public class MoveTest {
	
private final static Logger logger = Logger.getLogger(BoardTest.class);
	
	@Before
	public void setup() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void moveTest() {
		Board board = new Board();
		board.makeMove("e2e4");
		board.print();
		System.out.println("-----");
		board.makeMove("a7a8q");
		board.print();
	}
	
}
