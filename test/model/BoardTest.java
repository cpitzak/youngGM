package model;

import interfaces.PieceLibrary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class BoardTest {
	
	@Test
	public void print() {
		Board board = new Board();
		board.print();
		System.out.println();
		board.printBoardPieceIndexes();
	}

	@Test
	public void setup() {
		Board board = new Board();
		Integer[] intBoard = board.getBoard();
		
		assertThat(intBoard[0], is(PieceLibrary.WHITE_ROOK));
		assertThat(intBoard[1], is(PieceLibrary.WHITE_KNIGHT));
		assertThat(intBoard[2], is(PieceLibrary.WHITE_BISHOP));
		assertThat(intBoard[3], is(PieceLibrary.WHITE_QUEEN));
		assertThat(intBoard[4], is(PieceLibrary.WHITE_KING));
		assertThat(intBoard[5], is(PieceLibrary.WHITE_BISHOP));
		assertThat(intBoard[6], is(PieceLibrary.WHITE_KNIGHT));
		assertThat(intBoard[7], is(PieceLibrary.WHITE_ROOK));

		assertThat(intBoard[16], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[17], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[18], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[19], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[20], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[21], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[22], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[23], is(PieceLibrary.WHITE_PAWN));

		for (int i = 24; i < 80; i++) {
			assertThat(intBoard[i], nullValue());
		}

		assertThat(intBoard[112], is(PieceLibrary.BLACK_ROOK));
		assertThat(intBoard[113], is(PieceLibrary.BLACK_KNIGHT));
		assertThat(intBoard[114], is(PieceLibrary.BLACK_BISHOP));
		assertThat(intBoard[115], is(PieceLibrary.BLACK_QUEEN));
		assertThat(intBoard[116], is(PieceLibrary.BLACK_KING));
		assertThat(intBoard[117], is(PieceLibrary.BLACK_BISHOP));
		assertThat(intBoard[118], is(PieceLibrary.BLACK_KNIGHT));
		assertThat(intBoard[119], is(PieceLibrary.BLACK_ROOK));

		assertThat(intBoard[96], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[97], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[98], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[99], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[100], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[101], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[102], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[103], is(PieceLibrary.BLACK_PAWN));

	}

}
