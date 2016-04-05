package model;

import java.util.ArrayList;
import java.util.List;

import interfaces.PieceLibrary;

public class MoveGenerator {

	public static List<Move> getPossibleMoves(int piece, int from) {
		List<Move> moves = new ArrayList<Move>();
		if (piece == PieceLibrary.WHITE_BISHOP || piece == PieceLibrary.BLACK_BISHOP) {
			// i = 0 is up left, etc... as mentioned in PieceLibrary.BISHOP_MOVE_DELTA
			for (int i = 0; i < PieceLibrary.BISHOP_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.BISHOP_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
					to += PieceLibrary.BISHOP_MOVE_DELTA[i];
				}
			}
		} else if (piece == PieceLibrary.WHITE_PAWN) {
			for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.PAWN_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else if (piece == PieceLibrary.BLACK_PAWN) {
			for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
				int to = from - PieceLibrary.PAWN_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_KNIGHT || piece == PieceLibrary.BLACK_KNIGHT) {
			for (int i = 0; i < PieceLibrary.KNIGHT_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.KNIGHT_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_KING || piece == PieceLibrary.BLACK_KING) {
			for (int i = 0; i < PieceLibrary.KING_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.KING_MOVE_DELTA[i];
				if ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
				}
			}
		} else if (piece == PieceLibrary.WHITE_ROOK || piece == PieceLibrary.BLACK_ROOK) {
			for (int i = 0; i < PieceLibrary.ROOK_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.ROOK_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
					to += PieceLibrary.ROOK_MOVE_DELTA[i];
				}
			}
		} else if (piece == PieceLibrary.WHITE_QUEEN || piece == PieceLibrary.BLACK_QUEEN) {
			for (int i = 0; i < PieceLibrary.QUEEN_MOVE_DELTA.length; i++) {
				int to = from + PieceLibrary.QUEEN_MOVE_DELTA[i];
				while ((to & 0x88) == 0) {
					moves.add(new Move(from, to, piece));
					to += PieceLibrary.QUEEN_MOVE_DELTA[i];
				}
			}
		} else {
			throw new IllegalArgumentException("ERROR: tried to find possible moves with an undefined piece");
		}
		return moves;
	}

}
