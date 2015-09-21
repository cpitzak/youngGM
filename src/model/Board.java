package model;

import org.apache.log4j.Logger;

import interfaces.PieceLibrary;

//@formatter:off
/**
 * 0x88 board
 * 
 *  112 113 114 115 116 117 118 119 | 120 121 122 123 124 125 126 127
     96  97  98  99 100 101 102 103 | 104 105 106 107 108 109 110 111
     80  81  82  83  84  85  86  87 |  88  89  90  91  92  93  94  95
     64  65  66  67  68  69  70  71 |  72  73  74  75  76  77  78  79
     48  49  50  51  52  53  54  55 |  56  57  58  59  60  61  62  63
     32  33  34  35  36  37  38  39 |  40  41  42  43  44  45  46  47
     16  17  18  19  20  21  22  23 |  24  25  26  27  28  29  30  31
      0   1   2   3   4   5   6   7 |   8   9  10  11  12  13  14  15
   
   maps a1 to board[0], h1 to board[7], a8 to board[112] and h8 to board[119]
   
   squares on the left is the chess board and squares on the right are illegal locations.
   
   same mapping in base 16 format:
   
   70 71 72 73 74 75 76 77 | 78 79 7a 7b 7c 7d 7e 7f
   60 61 62 63 64 65 66 67 | 68 69 6a 6b 6c 6d 6e 6f
   50 51 52 53 54 55 56 57 | 58 59 5a 5b 5c 5d 5e 5f
   40 41 42 43 44 45 46 47 | 48 49 4a 4b 4c 4d 4e 4f
   30 31 32 33 34 35 36 37 | 38 39 3a 3b 3c 3d 3e 3f
   20 21 22 23 24 25 26 27 | 28 29 2a 2b 2c 2d 2e 2f
   10 11 12 13 14 15 16 17 | 18 19 1a 1b 1c 1d 1e 1f
    0  1  2  3  4  5  6  7 |  8  9  a  b  c  d  e  f
    
    notice 4 bits for the file and 4 bits for the rank. Also, notice that for valid squares the high order bit of the 4 bit value is always 0.
    
    Example of usage (all from our non-base 16 board representation):
    
    getting bishop on f1: board[5]
    get possible moves for bishop on f1:
    board[5 + 17] which is board[22]
    board[22 + 17] which is board[39]
    board[39 + 17] which is board[56]
    and repeat of other diagonals
    
    56 & 0x88 is a non-zero (it's 8) value. When we AND a number from our non-base 16 board with 0x88 and its a non-zero value we know its an invalid square.
 * 
 * @author Clint
 *
 */
//@formatter:on
public class Board {

	final static Logger logger = Logger.getLogger(Board.class);

	private Integer[] board;
	private static final int[] A_FILE = { 112, 96, 80, 64, 48, 32, 16, 0 };
	private static final int[] H_FILE = { 119, 103, 87, 71, 55, 39, 23, 7 };
	private static final int RANK_LENGTH = 8;
	private boolean whiteTurn;
	private boolean whiteCanCastleKingSide;
	private boolean whiteCanCastleQueenSide;
	private boolean blackCanCastleKingSide;
	private boolean blackCanCastleQueenSide;
	private String enPassantTargetSquare;

	public Board() {
		init();
		setupBoard();
	}

	private void init() {
		board = new Integer[128];
		whiteTurn = true;
		whiteCanCastleKingSide = true;
		whiteCanCastleQueenSide = true;
		blackCanCastleKingSide = true;
		blackCanCastleQueenSide = true;
		enPassantTargetSquare = null;
	}

	private void setupBoard() {
		board[0] = PieceLibrary.WHITE_ROOK;
		board[1] = PieceLibrary.WHITE_KNIGHT;
		board[2] = PieceLibrary.WHITE_BISHOP;
		board[3] = PieceLibrary.WHITE_QUEEN;
		board[4] = PieceLibrary.WHITE_KING;
		board[5] = PieceLibrary.WHITE_BISHOP;
		board[6] = PieceLibrary.WHITE_KNIGHT;
		board[7] = PieceLibrary.WHITE_ROOK;

		for (int i = A_FILE[6]; i <= H_FILE[6]; i++) {
			board[i] = PieceLibrary.WHITE_PAWN;
		}

		board[112] = PieceLibrary.BLACK_ROOK;
		board[113] = PieceLibrary.BLACK_KNIGHT;
		board[114] = PieceLibrary.BLACK_BISHOP;
		board[115] = PieceLibrary.BLACK_QUEEN;
		board[116] = PieceLibrary.BLACK_KING;
		board[117] = PieceLibrary.BLACK_BISHOP;
		board[118] = PieceLibrary.BLACK_KNIGHT;
		board[119] = PieceLibrary.BLACK_ROOK;

		for (int i = A_FILE[1]; i <= H_FILE[1]; i++) {
			board[i] = PieceLibrary.BLACK_PAWN;
		}

		System.out.println();
	}

	public Integer[] getBoard() {
		return board;
	}
	
	public boolean isWhiteTurn() {
		return whiteTurn;
	}

	public boolean isWhiteCanCastleKingSide() {
		return whiteCanCastleKingSide;
	}

	public boolean isWhiteCanCastleQueenSide() {
		return whiteCanCastleQueenSide;
	}

	public boolean isBlackCanCastleKingSide() {
		return blackCanCastleKingSide;
	}

	public boolean isBlackCanCastleQueenSide() {
		return blackCanCastleQueenSide;
	}

	public void importFEN(String fen) {
		if (fen == null) {
			logger.error("tried to import FEN and provide a null value instead of a FEN string."
					+ " Doing nothing, check FEN and try again.");
			return;
		}
		try {
			final int EXPECTED_FEN_TOKENS_COUNT = 6;
			init();
			String[] fenTokens = fen.split("\\s+");
			if (fenTokens.length != EXPECTED_FEN_TOKENS_COUNT) {
				String message = "FEN doesn't contain proper amount of fields, six fields required";
				logger.error(message);
				throw new IllegalStateException(message);
			}
			String[] rankPieceTokens = fenTokens[0].split("/");
			if (rankPieceTokens.length != RANK_LENGTH) {
				String message = "malformed FEN, perhaps not enough pieces specified in FEN.";
				logger.error(message);
				throw new IllegalStateException(message);
			}
			for (int i = 0; i < rankPieceTokens.length; i++) {
				int boardIndex = A_FILE[i];
				String rankPieces = rankPieceTokens[i].trim();
				for (int j = 0; j < rankPieces.length(); j++) {
					String rankPiece = String.valueOf(rankPieces.charAt(j));
					if (Utilities.isInteger(rankPiece)) {
						boardIndex += Integer.parseInt(rankPiece);
					} else {
						int piece = convertStringPieceToInteger(rankPiece);
						board[boardIndex] = piece;
						boardIndex++;
					}
				}
			}
			if (fenTokens[1].equals("w")) {
				whiteTurn = true;
			} else if (fenTokens[1].equals("b")) {
				whiteTurn = false;
			} else {
				String message = "malformed FEN, perhaps players turn not specified correctly.";
				logger.error(message);
				throw new IllegalStateException(message);
			}

			String castlingRights = fenTokens[2];
			if (!castlingRights.contains("K")) {
				whiteCanCastleKingSide = false;
			}
			if (!castlingRights.contains("Q")) {
				whiteCanCastleQueenSide = false;
			}
			if (!castlingRights.contains("k")) {
				blackCanCastleKingSide = false;
			}
			if (!castlingRights.contains("q")) {
				blackCanCastleQueenSide = false;
			}
			// else if (!castlingRights.matches(".*[KB].*")) {
//				String message = "malformed FEN, perhaps castling rights specified correctly.";
//				logger.error(message);
//				throw new IllegalStateException(message);
//			}

			// TODO: regex to test if it is correct
			enPassantTargetSquare = fenTokens[3];

			if (Utilities.isInteger(fenTokens[4])) {
				int halfMoveClock = Integer.parseInt(fenTokens[4]);
				// TODO: use halfmove clock
			} else {
				String message = "malformed FEN, perhaps halfmove clock not specified correctly.";
				logger.error(message);
				throw new IllegalStateException(message);
			}
			if (Utilities.isInteger(fenTokens[5])) {
				int fullMoveClock = Integer.parseInt(fenTokens[5]);
				// TODO: use fullmove clock
			} else {
				String message = "malformed FEN, perhaps fullmove clock not specified correctly.";
				logger.error(message);
				throw new IllegalStateException(message);
			}
		} catch (IllegalStateException e) {
			init();
			logger.error("Caught exception in importFEN. Cleared board to avoid an illegal state.");
		} catch (IllegalArgumentException e) {
			init();
			logger.error("Caught exception in importFEN. Cleared board to avoid an illegal state.");
		}
	}
	
//	The move format is in long algebraic notation.
//	A nullmove from the Engine to the GUI should be sent as 0000.
//	Examples:  e2e4, e7e5, e1g1 (white short castling), e7e8q (for promotion)
	public void makeMove(String move) {
		final int MIN_MOVE_LENGTH = 4;
		final int MAX_MOVE_LENGTH = 5;
		if (move == null || move.trim().length() < MIN_MOVE_LENGTH || move.trim().length() > MAX_MOVE_LENGTH) {
			logger.error("Invalid move sent to makeMove");
			return;
		}
		String[] tokens = move.trim().split("\\d", 2);
	}

	public void printBoardPieceIndexes() {
		printHelper(false);
	}

	public void print() {
		printHelper(true);
	}

	private void printHelper(boolean pretty) {
		for (int i = 0; i < A_FILE.length; i++) {
			printRow(A_FILE[i], pretty);
		}
	}

	/**
	 * Print one row of the 0x88 chess board. Use the first index for the
	 * desired row to print.
	 *
	 * @param startIndex
	 *            the index of the 0x88 board
	 */
	private void printRow(int startIndex, boolean pretty) {
		Integer piece = null;
		String s = null;
		int totalRowLength = RANK_LENGTH * 2;
		for (int i = 0; i < totalRowLength; i++, startIndex++) {
			if (i == RANK_LENGTH) {
				// fake board start
				System.out.print("| ");
			}
			piece = board[startIndex];
			if (pretty) {
				if (piece == null) {
					s = ". ";
				} else {
					s = convertPieceToString(piece) + " ";
				}
			} else {
				if (piece == null) {
					s = " . ";
				} else {
					if (piece < 10) {
						s = " " + piece + " ";
					} else {
						s = piece + " ";
					}
				}
			}
			System.out.print(s);
		}
		System.out.println();
	}

	private int convertStringPieceToInteger(String piece) {
		int result = 0;
		if (piece.equals(PieceLibrary.WHITE_PAWN_STRING)) {
			result = PieceLibrary.WHITE_PAWN;
		} else if (piece.equals(PieceLibrary.WHITE_BISHOP_STRING)) {
			result = PieceLibrary.WHITE_BISHOP;
		} else if (piece.equals(PieceLibrary.WHITE_KING_STRING)) {
			result = PieceLibrary.WHITE_KING;
		} else if (piece.equals(PieceLibrary.WHITE_KNIGHT_STRING)) {
			result = PieceLibrary.WHITE_KNIGHT;
		} else if (piece.equals(PieceLibrary.WHITE_QUEEN_STRING)) {
			result = PieceLibrary.WHITE_QUEEN;
		} else if (piece.equals(PieceLibrary.WHITE_ROOK_STRING)) {
			result = PieceLibrary.WHITE_ROOK;
		} else if (piece.equals(PieceLibrary.BLACK_PAWN_STRING)) {
			result = PieceLibrary.BLACK_PAWN;
		} else if (piece.equals(PieceLibrary.BLACK_BISHOP_STRING)) {
			result = PieceLibrary.BLACK_BISHOP;
		} else if (piece.equals(PieceLibrary.BLACK_KING_STRING)) {
			result = PieceLibrary.BLACK_KING;
		} else if (piece.equals(PieceLibrary.BLACK_KNIGHT_STRING)) {
			result = PieceLibrary.BLACK_KNIGHT;
		} else if (piece.equals(PieceLibrary.BLACK_QUEEN_STRING)) {
			result = PieceLibrary.BLACK_QUEEN;
		} else if (piece.equals(PieceLibrary.BLACK_ROOK_STRING)) {
			result = PieceLibrary.BLACK_ROOK;
		} else {
			throw new IllegalArgumentException("Tried to convert an undefined chess piece to a string.");
		}
		return result;
	}

	private String convertPieceToString(int piece) {
		String result = null;
		if (piece == PieceLibrary.WHITE_PAWN) {
			result = PieceLibrary.WHITE_PAWN_STRING;
		} else if (piece == PieceLibrary.WHITE_BISHOP) {
			result = PieceLibrary.WHITE_BISHOP_STRING;
		} else if (piece == PieceLibrary.WHITE_KING) {
			result = PieceLibrary.WHITE_KING_STRING;
		} else if (piece == PieceLibrary.WHITE_KNIGHT) {
			result = PieceLibrary.WHITE_KNIGHT_STRING;
		} else if (piece == PieceLibrary.WHITE_QUEEN) {
			result = PieceLibrary.WHITE_QUEEN_STRING;
		} else if (piece == PieceLibrary.WHITE_ROOK) {
			result = PieceLibrary.WHITE_ROOK_STRING;
		} else if (piece == PieceLibrary.BLACK_PAWN) {
			result = PieceLibrary.BLACK_PAWN_STRING;
		} else if (piece == PieceLibrary.BLACK_BISHOP) {
			result = PieceLibrary.BLACK_BISHOP_STRING;
		} else if (piece == PieceLibrary.BLACK_KING) {
			result = PieceLibrary.BLACK_KING_STRING;
		} else if (piece == PieceLibrary.BLACK_KNIGHT) {
			result = PieceLibrary.BLACK_KNIGHT_STRING;
		} else if (piece == PieceLibrary.BLACK_QUEEN) {
			result = PieceLibrary.BLACK_QUEEN_STRING;
		} else if (piece == PieceLibrary.BLACK_ROOK) {
			result = PieceLibrary.BLACK_ROOK_STRING;
		} else {
			throw new IllegalArgumentException("Tried to convert an undefined chess piece to a string.");
		}
		return result;
	}
}
