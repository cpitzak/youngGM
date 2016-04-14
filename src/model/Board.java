package model;

import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import interfaces.GeneralCommands;
import interfaces.PieceLibrary;
import interfaces.SquareLibrary;

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
public class Board extends Observable {

	private final static Logger logger = Logger.getLogger(Board.class);

	public static final int RANK_1 = 0;
	public static final int RANK_2 = 1;
	public static final int RANK_3 = 2;
	public static final int RANK_4 = 3;
	public static final int RANK_5 = 4;
	public static final int RANK_6 = 5;
	public static final int RANK_7 = 6;
	public static final int RANK_8 = 7;

	public static final int FILE_1 = 0;
	public static final int FILE_2 = 1;
	public static final int FILE_3 = 2;
	public static final int FILE_4 = 3;
	public static final int FILE_5 = 4;
	public static final int FILE_6 = 5;
	public static final int FILE_7 = 6;
	public static final int FILE_8 = 7;

	public static final int A1 = 0, B1 = 1, C1 = 2, D1 = 3, E1 = 4, F1 = 5, G1 = 6, H1 = 7, A2 = 16, B2 = 17, C2 = 18,
			D2 = 19, E2 = 20, F2 = 21, G2 = 22, H2 = 23, A3 = 32, B3 = 33, C3 = 34, D3 = 35, E3 = 36, F3 = 37, G3 = 38,
			H3 = 39, A4 = 48, B4 = 49, C4 = 50, D4 = 51, E4 = 52, F4 = 53, G4 = 54, H4 = 55, A5 = 64, B5 = 65, C5 = 66,
			D5 = 67, E5 = 68, F5 = 69, G5 = 70, H5 = 71, A6 = 80, B6 = 81, C6 = 82, D6 = 83, E6 = 84, F6 = 85, G6 = 86,
			H6 = 87, A7 = 96, B7 = 97, C7 = 98, D7 = 99, E7 = 100, F7 = 101, G7 = 102, H7 = 103, A8 = 112, B8 = 113,
			C8 = 114, D8 = 115, E8 = 116, F8 = 117, G8 = 118, H8 = 119;

	private static final int[] A_FILE = { A8, A7, A6, A5, A4, A3, A2, A1 };

	private Integer[] board;
	private static final int RANK_LENGTH = 8;
	private boolean isWhitesTurn;
	private boolean whiteCanCastleKingSide;
	private boolean whiteCanCastleQueenSide;
	private boolean blackCanCastleKingSide;
	private boolean blackCanCastleQueenSide;
	private String enPassantTargetSquare;
	private int halfMoveClock;
	private int fullMoveClock;

	private History history = new History();

	public Board() {
		initBoard();
	}

	public void initBoard() {
		init();
		setupBoard();
		setChanged();
		notifyObservers(GeneralCommands.RESET_BOARD);
	}

	private void init() {
		board = new Integer[128];
		isWhitesTurn = true;
		whiteCanCastleKingSide = true;
		whiteCanCastleQueenSide = true;
		blackCanCastleKingSide = true;
		blackCanCastleQueenSide = true;
		enPassantTargetSquare = null;
		history.record(null, this);
	}

	private void setupBoard() {
		board[A1] = PieceLibrary.WHITE_ROOK;
		board[B1] = PieceLibrary.WHITE_KNIGHT;
		board[C1] = PieceLibrary.WHITE_BISHOP;
		board[D1] = PieceLibrary.WHITE_QUEEN;
		board[E1] = PieceLibrary.WHITE_KING;
		board[F1] = PieceLibrary.WHITE_BISHOP;
		board[G1] = PieceLibrary.WHITE_KNIGHT;
		board[H1] = PieceLibrary.WHITE_ROOK;

		for (int i = A2; i <= H2; i++) {
			board[i] = PieceLibrary.WHITE_PAWN;
		}

		board[A8] = PieceLibrary.BLACK_ROOK;
		board[B8] = PieceLibrary.BLACK_KNIGHT;
		board[C8] = PieceLibrary.BLACK_BISHOP;
		board[D8] = PieceLibrary.BLACK_QUEEN;
		board[E8] = PieceLibrary.BLACK_KING;
		board[F8] = PieceLibrary.BLACK_BISHOP;
		board[G8] = PieceLibrary.BLACK_KNIGHT;
		board[H8] = PieceLibrary.BLACK_ROOK;

		for (int i = A7; i <= H7; i++) {
			board[i] = PieceLibrary.BLACK_PAWN;
		}
	}

	public Integer[] getBoard() {
		return board;
	}

	public void clear() {
		board = new Integer[128];
	}

	public int getHalfMoveClock() {
		return halfMoveClock;
	}

	public int getFullMoveClock() {
		return fullMoveClock;
	}

	public String getEnPassantTargetSquare() {
		return enPassantTargetSquare;
	}

	public boolean isWhitesTurn() {
		return isWhitesTurn;
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

	public boolean importFEN(String fen) {
		if (fen == null) {
			logger.error("tried to import FEN and provide a null value instead of a FEN string."
					+ " Doing nothing, check FEN and try again.");
			return false;
		}
		final int EXPECTED_FEN_TOKENS_COUNT = 6;
		init();
		String[] fenTokens = fen.split("\\s+");
		if (fenTokens.length != EXPECTED_FEN_TOKENS_COUNT) {
			logger.error("FEN doesn't contain proper amount of fields, six fields required");
			return false;
		}
		String[] rankPieceTokens = fenTokens[0].split("/");
		if (rankPieceTokens.length != RANK_LENGTH) {
			logger.error("malformed FEN, perhaps not enough pieces specified in FEN.");
			return false;
		}
		for (int i = 0; i < rankPieceTokens.length; i++) {
			int boardIndex = A_FILE[i];
			String rankPieces = rankPieceTokens[i].trim();
			for (int j = 0; j < rankPieces.length(); j++) {
				String rankPiece = String.valueOf(rankPieces.charAt(j));
				if (Utilities.isInteger(rankPiece)) {
					boardIndex += Integer.parseInt(rankPiece);
				} else {
					Integer piece = PieceLibrary.stringToIntMap.get(rankPiece);
					if (piece == null) {
						logger.error("malformed FEN, piece not specified correctly.");
						return false;
					}
					board[boardIndex] = piece;
					boardIndex++;
				}
			}
		}
		if (fenTokens[1].equals("w")) {
			isWhitesTurn = true;
		} else if (fenTokens[1].equals("b")) {
			isWhitesTurn = false;
		} else {
			logger.error("malformed FEN, perhaps players turn not specified correctly.");
			return false;
		}

		final int MAX_CASTLING_RIGHTS_LENGTH = 4;
		String castlingRights = fenTokens[2];
		Pattern p = Pattern.compile("[QKqk-]");
		Matcher m = p.matcher(castlingRights);
		if (castlingRights.length() > MAX_CASTLING_RIGHTS_LENGTH || !m.find()) {
			logger.error("malformed FEN, perhaps castling rights not specified correctly.");
			return false;
		}
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

		if (!fenTokens[3].equals("-") && SquareLibrary.stringToIntMap.get(fenTokens[3]) == null) {
			logger.error("malformed FEN, perhaps enpassant target square not specified correctly.");
			return false;
		}

		if (!fenTokens[3].equals("-")) {
			enPassantTargetSquare = fenTokens[3];
		}

		if (Utilities.isInteger(fenTokens[4])) {
			halfMoveClock = Integer.parseInt(fenTokens[4]);
		} else {
			logger.error("malformed FEN, perhaps halfmove clock not specified correctly.");
			return false;
		}
		if (Utilities.isInteger(fenTokens[5])) {
			fullMoveClock = Integer.parseInt(fenTokens[5]);
		} else {
			logger.error("malformed FEN, perhaps fullmove clock not specified correctly.");
			return false;
		}
		return true;
	}

	public boolean isSquareEmpty(String algebraicSquare) {
		Integer squareInt = SquareLibrary.stringToIntMap.get(algebraicSquare);
		if (squareInt == null) {
			throw new IllegalArgumentException("invalid square");
		}
		return board[squareInt] == null;
	}

	public boolean undoMove() {
		if (history.size() < 2) {
			return false;
		}
		State state = history.pop();
		Move move = state.getMove();

		if (move instanceof CastleMove) {
			CastleMove castleMove = (CastleMove) move;
			board[castleMove.getFrom()] = board[castleMove.getTo()];
			board[castleMove.getTo()] = null;
			board[castleMove.getRookMove().getFrom()] = board[castleMove.getRookMove().getTo()];
			board[castleMove.getRookMove().getTo()] = null;
		} else if (move instanceof EnPassantMove) {

		} else if (move instanceof PromotionMove) {

		} else {

		}

		State stateBeforeMove = history.peek();
		updateState(stateBeforeMove);
		return true;
	}

	private void updateState(State state) {
		this.isWhitesTurn = state.isWhitesTurn();
		this.whiteCanCastleKingSide = state.isWhiteCanCastleKingSide();
		this.whiteCanCastleQueenSide = state.isWhiteCanCastleQueenSide();
		this.blackCanCastleKingSide = state.isBlackCanCastleKingSide();
		this.blackCanCastleQueenSide = state.isBlackCanCastleQueenSide();
		this.enPassantTargetSquare = state.getEnPassantTargetSquare();
		this.halfMoveClock = state.getHalfMoveClock();
		this.fullMoveClock = state.getFullMoveClock();
	}

	// The move format is in long algebraic notation.
	// A nullmove from the Engine to the GUI should be sent as 0000.
	// Examples: e2e4, e7e5, e1g1 (white short castling), e7e8q (for promotion)
	// https://chessprogramming.wikispaces.com/Algebraic+Chess+Notation#Long
	// Algebraic Notation (LAN)
	public boolean makeMove(String moveStr) {
		final int MIN_MOVE_LENGTH = 4;
		final int MAX_MOVE_LENGTH = 5;
		if (moveStr == null || moveStr.trim().length() < MIN_MOVE_LENGTH || moveStr.trim().length() > MAX_MOVE_LENGTH) {
			logger.error("Invalid move sent to makeMove");
			return false;
		}
		moveStr = moveStr.trim();
		if (moveStr.length() != 4 && moveStr.length() != 5) {
			logger.error(
					"malformed move tried to be made, move format is in long algebraic notation. see uci interface");
			return false;
		}
		String fromSquare = moveStr.substring(0, 2).toLowerCase();
		String toSquare = moveStr.substring(2, 4).trim().toLowerCase();

		Integer from = SquareLibrary.stringToIntMap.get(fromSquare);
		Integer to = SquareLibrary.stringToIntMap.get(toSquare);

		if (from == null || to == null) {
			logger.error("malformed move");
			return false;
		}

		if (PieceLibrary.isWhite(board[from]) && !isWhitesTurn) {
			logger.error("it's black's turn");
			return false;
		} else if (PieceLibrary.isBlack(board[from]) && isWhitesTurn) {
			logger.error("it's white's turn");
			return false;
		}

		String promotion = null;
		if (moveStr.length() == 5) {
			promotion = moveStr.substring(4);
		}

		int piece = board[from];

		// king trying to castle
		if ((from == E1 && to == G1) || (from == E1 && to == C1) || (from == E8 && to == G8)
				|| (from == E8 && to == C8)) {
			Move castleAttemptMove = MoveGenerator.getCastleMove(from, to, piece, this);
			// invalid castle attempt
			if (castleAttemptMove == null) {
				logger.error("invalid castle move");
				return false;
			} else {
				CastleMove castleMove = (CastleMove) castleAttemptMove;
				board[castleMove.getTo()] = board[castleMove.getFrom()];
				board[castleMove.getFrom()] = null;
				board[castleMove.getRookMove().getTo()] = board[castleMove.getRookMove().getFrom()];
				board[castleMove.getRookMove().getFrom()] = null;
				if (piece == PieceLibrary.WHITE_KING) {
					this.whiteCanCastleKingSide = false;
					this.whiteCanCastleQueenSide = false;
				} else if (piece == PieceLibrary.BLACK_KING) {
					this.blackCanCastleKingSide = false;
					this.blackCanCastleQueenSide = false;
				} else {
					throw new IllegalStateException("castle rights are in an invalid state");
				}
				// update whos turn it is
				isWhitesTurn = !isWhitesTurn;
				history.record(castleMove, this);
				return true;
			}
		} else if ((piece == PieceLibrary.WHITE_PAWN && Board.square0x88ToRank(from) == Board.RANK_5)
				|| (piece == PieceLibrary.BLACK_PAWN && Board.square0x88ToRank(from) == Board.RANK_4)) {
			Move enPassantAttemptMove = MoveGenerator.getEnPassantMove(from, to, piece, this);
			// invalid enpassant attempt
			if (enPassantAttemptMove != null) {
				EnPassantMove enpassantMove = (EnPassantMove) enPassantAttemptMove;
				board[enpassantMove.getTo()] = board[enpassantMove.getFrom()];
				board[enpassantMove.getFrom()] = null;
				board[enpassantMove.getTargetSquare()] = null;
				// update whos turn it is
				isWhitesTurn = !isWhitesTurn;
				history.record(enpassantMove, this);
				return true;
			}
		}

		if (promotion != null) {
			Integer promotionPiece = PieceLibrary.stringToIntMap.get(promotion);
			if (promotionPiece == null) {
				logger.error("malformed move");
				return false;
			}
			PromotionMove promotionMove = new PromotionMove(from, to, piece, promotionPiece);
			if (!Validator.isValidMove(promotionMove, this)) {
				logger.error("invalid promotion move");
				return false;
			}
			board[to] = promotionPiece;
			board[from] = null;
			// update whos turn it is
			isWhitesTurn = !isWhitesTurn;
			history.record(promotionMove, this);
			return true;
		} else if (toSquare.length() == 2) {
			Move generalMove = new Move(from, to, piece);
			if (!Validator.isValidMove(generalMove, this)) {
				logger.error("invalid move");
				return false;
			}
			board[to] = board[from];
			board[from] = null;
			if (piece == PieceLibrary.WHITE_KING) {
				this.whiteCanCastleKingSide = false;
				this.whiteCanCastleQueenSide = false;
			} else if (piece == PieceLibrary.BLACK_KING) {
				this.blackCanCastleKingSide = false;
				this.blackCanCastleQueenSide = false;
			} else if (piece == PieceLibrary.WHITE_ROOK && from == H1) {
				this.whiteCanCastleKingSide = false;
			} else if (piece == PieceLibrary.WHITE_ROOK && from == A1) {
				this.whiteCanCastleQueenSide = false;
			} else if (piece == PieceLibrary.BLACK_ROOK && from == H8) {
				this.blackCanCastleKingSide = false;
			} else if (piece == PieceLibrary.BLACK_ROOK && from == A8) {
				this.blackCanCastleQueenSide = false;
			}
			// update whos turn it is
			isWhitesTurn = !isWhitesTurn;
			history.record(generalMove, this);
			return true;
		}
		logger.error("malformed move tried to be made, move format is in long algebraic notation. see uci interface");
		return false;
	}

	public static boolean isOnBoard(int square0x88) {
		return (square0x88 & 0x88) == 0;
	}

	public static int rankFileToSquare0x88(int rank, int file) {
		return 16 * rank + file;
	}

	public static int square0x88ToFile(int square0x88) {
		return square0x88 & 7;
	}

	public static int square0x88ToRank(int square0x88) {
		return square0x88 >> 4;
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
					s = PieceLibrary.intToStringMap.get(piece) + " ";
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

	public void print088Definition() {
		String board088 = "112 113 114 115 116 117 118 119 | 120 121 122 123 124 125 126 127\n"
				+ "96  97  98  99 100 101 102 103 | 104 105 106 107 108 109 110 111\n"
				+ "80  81  82  83  84  85  86  87 |  88  89  90  91  92  93  94  95\n"
				+ "64  65  66  67  68  69  70  71 |  72  73  74  75  76  77  78  79\n"
				+ "48  49  50  51  52  53  54  55 |  56  57  58  59  60  61  62  63\n"
				+ "32  33  34  35  36  37  38  39 |  40  41  42  43  44  45  46  47\n"
				+ "16  17  18  19  20  21  22  23 |  24  25  26  27  28  29  30  31\n"
				+ "0   1   2   3   4   5   6   7 |   8   9  10  11  12  13  14  15";
		System.out.println(board088);
	}

}
