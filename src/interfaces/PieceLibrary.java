package interfaces;

public interface PieceLibrary {

	public static final int WHITE_PAWN = 1;
	public static final int WHITE_KNIGHT = 2;
	public static final int WHITE_KING = 3;
	public static final int WHITE_BISHOP = 4;
	public static final int WHITE_ROOK = 5;
	public static final int WHITE_QUEEN = 6;

	public static final int BLACK_PAWN = 7;
	public static final int BLACK_KNIGHT = 8;
	public static final int BLACK_KING = 9;
	public static final int BLACK_BISHOP = 10;
	public static final int BLACK_ROOK = 11;
	public static final int BLACK_QUEEN = 12;

	// up left, up right, down left, down right
	public static final int[] BISHOP_MOVE_DELTA = {15, 17, -15, -17};
	public static final int[] PAWN_MOVE_DELTA = {16, 32};
	// 0: N NW, 1: N NE, 2: E NE, 3: E SE, 4: S SE, 5: S SW, 6: W SW, 7: W NW
	public static final int[] KNIGHT_MOVE_DELTA = {31, 33, 18, -14, -31, -33, -18, 14};
	// starts with left and goes counter clockwise
	public static final int[] KING_MOVE_DELTA = {-1, 15, 16, 17, 1, -15, -16, -17};
	public static final int[] ROOK_MOVE_DELTA = {-1, 16, 1, -16};
	public static final int[] QUEEN_MOVE_DELTA = {-1, 15, 16, 17, 1, -15, -16, -17};



}