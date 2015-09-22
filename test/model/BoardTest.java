package model;

import interfaces.PieceLibrary;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class BoardTest {
	
	@Before
	public void setup() {
		BasicConfigurator.configure();
	}

	@Test
	public void setupBoardTest() {
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

	@Test
	public void importFENStandarSetupTest() {
		Board board = new Board();
		board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

		assertThat(board.isWhiteTurn(), is(true));
		assertThat(board.isWhiteCanCastleKingSide(), is(true));
		assertThat(board.isWhiteCanCastleQueenSide(), is(true));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));

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

	@Test
	public void importFENCastlingRightsTest() {
		boolean result = false;
		Board board = new Board();

		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		assertThat(board.isWhiteCanCastleKingSide(), is(true));
		assertThat(board.isWhiteCanCastleQueenSide(), is(true));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));
		assertThat(result, is(true));

		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w Qkq - 0 1");
		assertThat(board.isWhiteCanCastleKingSide(), is(false));
		assertThat(board.isWhiteCanCastleQueenSide(), is(true));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));
		assertThat(result, is(true));

		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w kq - 0 1");
		assertThat(board.isWhiteCanCastleKingSide(), is(false));
		assertThat(board.isWhiteCanCastleQueenSide(), is(false));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));
		assertThat(result, is(true));

		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w q - 0 1");
		assertThat(board.isWhiteCanCastleKingSide(), is(false));
		assertThat(board.isWhiteCanCastleQueenSide(), is(false));
		assertThat(board.isBlackCanCastleKingSide(), is(false));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));
		assertThat(result, is(true));

		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1");
		assertThat(board.isWhiteCanCastleKingSide(), is(false));
		assertThat(board.isWhiteCanCastleQueenSide(), is(false));
		assertThat(board.isBlackCanCastleKingSide(), is(false));
		assertThat(board.isBlackCanCastleQueenSide(), is(false));
		assertThat(result, is(true));
		
		result = board.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w a - 0 1");
		assertThat(result, is(false));
	}

	@Test
	public void importFENMoveTest1() {
		// 1. e4
		final int E4 = 52;
		Board board = new Board();
		board.importFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");

		assertThat(board.isWhiteTurn(), is(false));
		assertThat(board.isWhiteCanCastleKingSide(), is(true));
		assertThat(board.isWhiteCanCastleQueenSide(), is(true));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));

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
		assertThat(intBoard[20], is(nullValue()));
		assertThat(intBoard[21], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[22], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[23], is(PieceLibrary.WHITE_PAWN));

		for (int i = 24; i < 80; i++) {
			if (i == E4) {
				assertThat(intBoard[E4], is(PieceLibrary.WHITE_PAWN));
			} else {
				assertThat(intBoard[i], nullValue());
			}
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

	@Test
	public void importFENMoveTest2() {
		// 1. e4
		final int E4 = 52;
		final int C5 = 66;
		Board board = new Board();
		board.importFEN("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");

		assertThat(board.isWhiteTurn(), is(true));
		assertThat(board.isWhiteCanCastleKingSide(), is(true));
		assertThat(board.isWhiteCanCastleQueenSide(), is(true));
		assertThat(board.isBlackCanCastleKingSide(), is(true));
		assertThat(board.isBlackCanCastleQueenSide(), is(true));

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
		assertThat(intBoard[20], is(nullValue()));
		assertThat(intBoard[21], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[22], is(PieceLibrary.WHITE_PAWN));
		assertThat(intBoard[23], is(PieceLibrary.WHITE_PAWN));

		for (int i = 24; i < 80; i++) {
			if (i == E4) {
				assertThat(intBoard[E4], is(PieceLibrary.WHITE_PAWN));
			} else if (i == C5) {
				assertThat(intBoard[C5], is(PieceLibrary.BLACK_PAWN));
			} else {
				assertThat(intBoard[i], nullValue());
			}
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
		assertThat(intBoard[98], is(nullValue()));
		assertThat(intBoard[99], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[100], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[101], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[102], is(PieceLibrary.BLACK_PAWN));
		assertThat(intBoard[103], is(PieceLibrary.BLACK_PAWN));
	}

	@Test
	public void importFENBlankBoardTest() {
		Board board = new Board();
		board.importFEN("8/8/8/8/8/8/8/8 b KQkq e3 0 1");
		Integer[] intBoard = board.getBoard();
		for (int i = 0; i < intBoard.length; i++) {
			assertThat(intBoard[i], nullValue());
		}
	}
	
	@Test
	public void importFENEnPassantTargetSquareTest() {
		boolean result = false;
		Board board = new Board();
		result = board.importFEN("8/8/8/8/8/8/8/8 b KQkq e3 0 1");
		assertThat(board.getEnPassantTargetSquare(), is("e3"));
		assertThat(result, is(true));
		
		result = board.importFEN("8/8/8/8/8/8/8/8 b KQkq - 0 1");
		assertThat(board.getEnPassantTargetSquare(), is(nullValue()));
		assertThat(result, is(true));
		
		result = board.importFEN("8/8/8/8/8/8/8/8 b KQkq a 0 1");
		assertThat(board.getEnPassantTargetSquare(), is(nullValue()));
		assertThat(result, is(false));
	}
	
}
