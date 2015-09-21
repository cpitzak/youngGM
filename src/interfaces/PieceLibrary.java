package interfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

	public static final String WHITE_PAWN_STRING = "P";
	public static final String WHITE_KNIGHT_STRING = "N";
	public static final String WHITE_KING_STRING = "K";
	public static final String WHITE_BISHOP_STRING = "B";
	public static final String WHITE_ROOK_STRING = "R";
	public static final String WHITE_QUEEN_STRING = "Q";

	public static final String BLACK_PAWN_STRING = "p";
	public static final String BLACK_KNIGHT_STRING = "n";
	public static final String BLACK_KING_STRING = "k";
	public static final String BLACK_BISHOP_STRING = "b";
	public static final String BLACK_ROOK_STRING = "r";
	public static final String BLACK_QUEEN_STRING = "q";

	// up left, up right, down left, down right
	public static final int[] BISHOP_MOVE_DELTA = { 15, 17, -15, -17 };
	public static final int[] PAWN_MOVE_DELTA = { 16, 32 };
	// 0: N NW, 1: N NE, 2: E NE, 3: E SE, 4: S SE, 5: S SW, 6: W SW, 7: W NW
	public static final int[] KNIGHT_MOVE_DELTA = { 31, 33, 18, -14, -31, -33, -18, 14 };
	// starts with left and goes counter clockwise
	public static final int[] KING_MOVE_DELTA = { -1, 15, 16, 17, 1, -15, -16, -17 };
	public static final int[] ROOK_MOVE_DELTA = { -1, 16, 1, -16 };
	public static final int[] QUEEN_MOVE_DELTA = { -1, 15, 16, 17, 1, -15, -16, -17 };

	public static final Map<String, Integer> stringToIntMap = Collections.unmodifiableMap(new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(WHITE_PAWN_STRING, WHITE_PAWN);
			put(WHITE_KNIGHT_STRING, WHITE_KNIGHT);
			put(WHITE_KING_STRING, WHITE_KING);
			put(WHITE_BISHOP_STRING, WHITE_BISHOP);
			put(WHITE_ROOK_STRING, WHITE_ROOK);
			put(WHITE_QUEEN_STRING, WHITE_QUEEN);

			put(BLACK_PAWN_STRING, BLACK_PAWN);
			put(BLACK_KNIGHT_STRING, BLACK_KNIGHT);
			put(BLACK_KING_STRING, BLACK_KING);
			put(BLACK_BISHOP_STRING, BLACK_BISHOP);
			put(BLACK_ROOK_STRING, BLACK_ROOK);
			put(BLACK_QUEEN_STRING, BLACK_QUEEN);
		}
	});
	
	public static final Map<Integer, String> intToStringMap = Collections.unmodifiableMap(new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(WHITE_PAWN, WHITE_PAWN_STRING);
			put(WHITE_KNIGHT, WHITE_KNIGHT_STRING);
			put(WHITE_KING, WHITE_KING_STRING);
			put(WHITE_BISHOP, WHITE_BISHOP_STRING);
			put(WHITE_ROOK, WHITE_ROOK_STRING);
			put(WHITE_QUEEN, WHITE_QUEEN_STRING);

			put(BLACK_PAWN, BLACK_PAWN_STRING);
			put(BLACK_KNIGHT, BLACK_KNIGHT_STRING);
			put(BLACK_KING, BLACK_KING_STRING);
			put(BLACK_BISHOP, BLACK_BISHOP_STRING);
			put(BLACK_ROOK, BLACK_ROOK_STRING);
			put(BLACK_QUEEN, BLACK_QUEEN_STRING);
		}
	});

}