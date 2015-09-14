package model;

import interfaces.PieceLibrary;
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
public class Board {

	private Integer[] board = new Integer[128];
	private Piece[] allPieces = new Piece[33];

	public Board() {
		setupBoard();
	}

	private void setupBoard() {
		allPieces[0] = new Piece(PieceLibrary.WHITE_ROOK, 0);
		allPieces[1] = new Piece(PieceLibrary.WHITE_KNIGHT, 1);
		allPieces[2] = new Piece(PieceLibrary.WHITE_BISHOP, 2);
		allPieces[3] = new Piece(PieceLibrary.WHITE_QUEEN, 3);
		allPieces[4] = new Piece(PieceLibrary.WHITE_KING, 4);
		allPieces[5] = new Piece(PieceLibrary.WHITE_BISHOP, 5);
		allPieces[6] = new Piece(PieceLibrary.WHITE_KNIGHT, 6);
		allPieces[7] = new Piece(PieceLibrary.WHITE_ROOK, 7);
		for (int i = 8, startPosition = 16; i < 16; i++, startPosition++) {
			allPieces[i] = new Piece(PieceLibrary.WHITE_PAWN, startPosition);
		}

		allPieces[17] = new Piece(PieceLibrary.BLACK_ROOK, 112);
		allPieces[18] = new Piece(PieceLibrary.BLACK_KNIGHT, 113);
		allPieces[19] = new Piece(PieceLibrary.BLACK_BISHOP, 114);
		allPieces[20] = new Piece(PieceLibrary.BLACK_QUEEN, 115);
		allPieces[21] = new Piece(PieceLibrary.BLACK_KING, 116);
		allPieces[22] = new Piece(PieceLibrary.BLACK_BISHOP, 117);
		allPieces[23] = new Piece(PieceLibrary.BLACK_KNIGHT, 118);
		allPieces[24] = new Piece(PieceLibrary.BLACK_ROOK, 119);
		for (int i = 25, startPosition = 96; i < 33; i++, startPosition++) {
			allPieces[i] = new Piece(PieceLibrary.BLACK_PAWN, startPosition);
		}

		/* setup board with index into piece arrays for speed optimizations */

		// white back row pieces
		for (int i = 0; i < 8; i++) {
			board[i] = i;
		}
		// white pawns
		for (int pieceIndex = 8, boardPosition = 16; pieceIndex < 16; pieceIndex++, boardPosition++) {
			board[boardPosition] = pieceIndex;
		}
		// black back row pieces
		for (int pieceIndex = 17, boardPosition = 112; pieceIndex < 25; pieceIndex++, boardPosition++) {
			board[boardPosition] = pieceIndex;
		}
		// black pawns
		for (int pieceIndex = 25, boardPosition = 96; pieceIndex < 33; pieceIndex++, boardPosition++) {
			board[boardPosition] = pieceIndex;
		}
		System.out.println();
	}

	public Integer[] getBoard() {
		return board;
	}

	public Piece[] getAllPieces() {
		return allPieces;
	}
	
	public void printBoardPieceIndexes() {
		printHelper(false);
	}
	
	public void print() {
		printHelper(true);
	}
	
	private void printHelper(boolean pretty) {
		printRow(112, pretty);
		printRow(96, pretty);
		printRow(80, pretty);
		printRow(64, pretty);
		printRow(48, pretty);
		printRow(32, pretty);
		printRow(16, pretty);
		printRow(0, pretty);
	}

	/**
	 * Print one row of the 0x88 chess board. Use the first index for the
	 * desired row to print.
	 *
	 * @param startIndex
	 *            the index of the 0x88 board
	 */
	private void printRow(int startIndex, boolean pretty) {
		Integer pieceIndex = board[startIndex++];
		String s = null;
		if (pieceIndex == null) {
			s = ". ";
		} else if (pretty) {
			s = convertPieceToString(allPieces[pieceIndex].getPiece());
		} else {
			s = pieceIndex.toString();
		}
		System.out.print(s);
		
		for(int i = 1; i < 7; i++, startIndex++) {
			pieceIndex = board[startIndex];
			if (pieceIndex == null) {
				s = ". ";
			} else if (pretty) {
				s = " " + convertPieceToString(allPieces[pieceIndex].getPiece());
			} else {
				s = " " + pieceIndex;
			}
			System.out.print(s);
		}
		
		pieceIndex = board[startIndex++];
		if (pieceIndex == null) {
			s = ".";
		} else if (pretty) {
			s = " " + convertPieceToString(allPieces[pieceIndex].getPiece());
		} else {
			s = " " + pieceIndex;
		}
		System.out.print(s);
		
		// fake board side
		System.out.print(" |");
		for (int i = 0; i < 8; i++, startIndex++) {
			if (pretty) {
				System.out.print(" " + board[startIndex]);
			} else {
				System.out.print(" " + startIndex);
			}
		}
		System.out.println();
	}

	private String convertPieceToString(int piece) {
		String result = null;
		if (piece == PieceLibrary.WHITE_PAWN) {
			result = "P";
		} else if (piece == PieceLibrary.WHITE_BISHOP) {
			result = "B";
		} else if (piece == PieceLibrary.WHITE_KING) {
			result = "K";
		} else if (piece == PieceLibrary.WHITE_KNIGHT) {
			result = "N";
		} else if (piece == PieceLibrary.WHITE_QUEEN) {
			result = "Q";
		} else if (piece == PieceLibrary.WHITE_ROOK) {
			result = "R";
		} else if (piece == PieceLibrary.BLACK_PAWN) {
			result = "p";
		} else if (piece == PieceLibrary.BLACK_BISHOP) {
			result = "b";
		} else if (piece == PieceLibrary.BLACK_KING) {
			result = "k";
		} else if (piece == PieceLibrary.BLACK_KNIGHT) {
			result = "n";
		} else if (piece == PieceLibrary.BLACK_QUEEN) {
			result = "q";
		} else if (piece == PieceLibrary.BLACK_ROOK) {
			result = "r";
		} else {
			throw new IllegalArgumentException("Tried to convert an undefined chess piece to a string.");
		}
		return result;
	}

}
