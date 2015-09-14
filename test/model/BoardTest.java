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
		Piece[] allPieces = board.getAllPieces();
		
		assertThat(allPieces[intBoard[0]].getPiece(), is(PieceLibrary.WHITE_ROOK));
		assertThat(allPieces[intBoard[1]].getPiece(), is(PieceLibrary.WHITE_KNIGHT));
		assertThat(allPieces[intBoard[2]].getPiece(), is(PieceLibrary.WHITE_BISHOP));
		assertThat(allPieces[intBoard[3]].getPiece(), is(PieceLibrary.WHITE_QUEEN));
		assertThat(allPieces[intBoard[4]].getPiece(), is(PieceLibrary.WHITE_KING));
		assertThat(allPieces[intBoard[5]].getPiece(), is(PieceLibrary.WHITE_BISHOP));
		assertThat(allPieces[intBoard[6]].getPiece(), is(PieceLibrary.WHITE_KNIGHT));
		assertThat(allPieces[intBoard[7]].getPiece(), is(PieceLibrary.WHITE_ROOK));

		assertThat(allPieces[intBoard[16]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[17]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[18]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[19]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[20]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[21]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[22]].getPiece(), is(PieceLibrary.WHITE_PAWN));
		assertThat(allPieces[intBoard[23]].getPiece(), is(PieceLibrary.WHITE_PAWN));

		for (int i = 24; i < 80; i++) {
			assertThat(intBoard[i], nullValue());
		}

		assertThat(allPieces[intBoard[112]].getPiece(), is(PieceLibrary.BLACK_ROOK));
		assertThat(allPieces[intBoard[113]].getPiece(), is(PieceLibrary.BLACK_KNIGHT));
		assertThat(allPieces[intBoard[114]].getPiece(), is(PieceLibrary.BLACK_BISHOP));
		assertThat(allPieces[intBoard[115]].getPiece(), is(PieceLibrary.BLACK_QUEEN));
		assertThat(allPieces[intBoard[116]].getPiece(), is(PieceLibrary.BLACK_KING));
		assertThat(allPieces[intBoard[117]].getPiece(), is(PieceLibrary.BLACK_BISHOP));
		assertThat(allPieces[intBoard[118]].getPiece(), is(PieceLibrary.BLACK_KNIGHT));
		assertThat(allPieces[intBoard[119]].getPiece(), is(PieceLibrary.BLACK_ROOK));

		assertThat(allPieces[intBoard[96]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[97]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[98]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[99]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[100]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[101]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[102]].getPiece(), is(PieceLibrary.BLACK_PAWN));
		assertThat(allPieces[intBoard[103]].getPiece(), is(PieceLibrary.BLACK_PAWN));


		//test location
		assertThat(allPieces[intBoard[0]].getLocation(), is(0));
		assertThat(allPieces[intBoard[1]].getLocation(), is(1));
		assertThat(allPieces[intBoard[2]].getLocation(), is(2));
		assertThat(allPieces[intBoard[3]].getLocation(), is(3));
		assertThat(allPieces[intBoard[4]].getLocation(), is(4));
		assertThat(allPieces[intBoard[5]].getLocation(), is(5));
		assertThat(allPieces[intBoard[6]].getLocation(), is(6));
		assertThat(allPieces[intBoard[7]].getLocation(), is(7));

		assertThat(allPieces[intBoard[16]].getLocation(), is(16));
		assertThat(allPieces[intBoard[17]].getLocation(), is(17));
		assertThat(allPieces[intBoard[18]].getLocation(), is(18));
		assertThat(allPieces[intBoard[19]].getLocation(), is(19));
		assertThat(allPieces[intBoard[20]].getLocation(), is(20));
		assertThat(allPieces[intBoard[21]].getLocation(), is(21));
		assertThat(allPieces[intBoard[22]].getLocation(), is(22));
		assertThat(allPieces[intBoard[23]].getLocation(), is(23));

		assertThat(allPieces[intBoard[112]].getLocation(), is(112));
		assertThat(allPieces[intBoard[113]].getLocation(), is(113));
		assertThat(allPieces[intBoard[114]].getLocation(), is(114));
		assertThat(allPieces[intBoard[115]].getLocation(), is(115));
		assertThat(allPieces[intBoard[116]].getLocation(), is(116));
		assertThat(allPieces[intBoard[117]].getLocation(), is(117));
		assertThat(allPieces[intBoard[118]].getLocation(), is(118));
		assertThat(allPieces[intBoard[119]].getLocation(), is(119));

		assertThat(allPieces[intBoard[96]].getLocation(), is(96));
		assertThat(allPieces[intBoard[97]].getLocation(), is(97));
		assertThat(allPieces[intBoard[98]].getLocation(), is(98));
		assertThat(allPieces[intBoard[99]].getLocation(), is(99));
		assertThat(allPieces[intBoard[100]].getLocation(), is(100));
		assertThat(allPieces[intBoard[101]].getLocation(), is(101));
		assertThat(allPieces[intBoard[102]].getLocation(), is(102));
		assertThat(allPieces[intBoard[103]].getLocation(), is(103));

	}

}
