package model;

import java.util.ArrayList;
import java.util.List;

import interfaces.PieceLibrary;

public class MoveGenerator {

	public static List<Move> getPossibleMoves(Integer piece, int from, Board board) {
		List<Move> moves = new ArrayList<Move>();
		if (piece == PieceLibrary.WHITE_BISHOP) {
			moves = getSliderMoves(piece, from, board.getBoard(), true, PieceLibrary.BISHOP_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_BISHOP) {
			moves = getSliderMoves(piece, from, board.getBoard(), false, PieceLibrary.BISHOP_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_ROOK) {
			moves = getSliderMoves(piece, from, board.getBoard(), true, PieceLibrary.ROOK_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_ROOK) {
			moves = getSliderMoves(piece, from, board.getBoard(), false, PieceLibrary.ROOK_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_QUEEN) {
			moves = getSliderMoves(piece, from, board.getBoard(), true, PieceLibrary.QUEEN_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_QUEEN) {
			moves = getSliderMoves(piece, from, board.getBoard(), false, PieceLibrary.QUEEN_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_KNIGHT) {
			moves = getSingleMoves(piece, from, board.getBoard(), true, PieceLibrary.KNIGHT_MOVE_DELTA);
		} else if (piece == PieceLibrary.BLACK_KNIGHT) {
			moves = getSingleMoves(piece, from, board.getBoard(), false, PieceLibrary.KNIGHT_MOVE_DELTA);
		} else if (piece == PieceLibrary.WHITE_KING) {
			moves = getSingleMoves(piece, from, board.getBoard(), true, PieceLibrary.KING_MOVE_DELTA);
			addKingCastle(piece, from, board, moves);
		} else if (piece == PieceLibrary.BLACK_KING) {
			moves = getSingleMoves(piece, from, board.getBoard(), false, PieceLibrary.KING_MOVE_DELTA);
			addKingCastle(piece, from, board, moves);
		} else if (piece == PieceLibrary.WHITE_PAWN) {
			moves = getPawnMoves(piece, from, board, true, PieceLibrary.PAWN_MOVE_DELTA,
					PieceLibrary.PAWN_MOVE_ATTACK_DELTA);
		} else if (piece == PieceLibrary.BLACK_PAWN) {
			moves = getPawnMoves(piece, from, board, false, PieceLibrary.PAWN_MOVE_DELTA,
					PieceLibrary.PAWN_MOVE_ATTACK_DELTA);
		} else {
			throw new IllegalArgumentException("ERROR: tried to find possible moves with an undefined piece");
		}
		return moves;
	}
	
	private static void addKingCastle(Integer piece, int from, Board board, List<Move> moves) {
		if (board.isWhiteCanCastleKingSide()) {
			Move rookMove = new Move(from+3, from+1, PieceLibrary.WHITE_ROOK);
			CastleMove move = new CastleMove(from, from+2, piece, rookMove);
			moves.add(move);
		}
		if (board.isWhiteCanCastleQueenSide()) {
			Move rookMove = new Move(from-4, from-1, PieceLibrary.WHITE_ROOK);
			CastleMove move = new CastleMove(from, from-2, piece, rookMove);
			moves.add(move);
		}
		if (board.isBlackCanCastleKingSide()) {
			Move rookMove = new Move(from-3, from-1, PieceLibrary.BLACK_ROOK);
			CastleMove move = new CastleMove(from, from-2, piece, rookMove);
			moves.add(move);
		}
		if (board.isBlackCanCastleQueenSide()) {
			Move rookMove = new Move(from+4, from+1, PieceLibrary.BLACK_ROOK);
			CastleMove move = new CastleMove(from, from+2, piece, rookMove);
			moves.add(move);
		}
	}

	private static List<Move> getPawnMoves(Integer piece, int from, Board board, boolean isWhite, int[] delta,
			int[] attackDelta) {
		Integer[] intBoard = board.getBoard();
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < PieceLibrary.PAWN_MOVE_ATTACK_DELTA.length; i++) {
			int to = isWhite ? (from + PieceLibrary.PAWN_MOVE_ATTACK_DELTA[i])
					: (from - PieceLibrary.PAWN_MOVE_ATTACK_DELTA[i]);
			if ((to & 0x88) == 0 && isAttack(piece, to, intBoard)) {
				moves.add(new Move(from, to, piece));
			}
		}
		for (int i = 0; i < PieceLibrary.PAWN_MOVE_DELTA.length; i++) {
			int to = isWhite ? (from + PieceLibrary.PAWN_MOVE_DELTA[i]) : (from - PieceLibrary.PAWN_MOVE_DELTA[i]);
			if ((to & 0x88) == 0 && !isAttack(piece, to, intBoard)) {
				moves.add(new Move(from, to, piece));
			}
		}
		// en passant
		if (isWhite && board.square0x88ToRank(from) == Board.RANK_5) {
			int leftTo = from + PieceLibrary.PAWN_MOVE_ATTACK_DELTA[0];
			int rightTo = from + PieceLibrary.PAWN_MOVE_ATTACK_DELTA[1];
			if ((leftTo & 0x88) == 0 && intBoard[leftTo] == null && PieceLibrary.isBlack(intBoard[from-1])) {
				moves.add(new EnPassantMove(from, leftTo, piece, leftTo));
			}
			if ((rightTo & 0x88) == 0 && intBoard[rightTo] == null && PieceLibrary.isBlack(intBoard[from+1])) {
				moves.add(new EnPassantMove(from, rightTo, piece, rightTo));
			}
		} else if (!isWhite && board.square0x88ToRank(from) == Board.RANK_4){
			int rightTo = from - PieceLibrary.PAWN_MOVE_ATTACK_DELTA[0];
			int leftTo = from - PieceLibrary.PAWN_MOVE_ATTACK_DELTA[1];
			if ((rightTo & 0x88) == 0 && intBoard[rightTo] == null && PieceLibrary.isWhite(intBoard[from+1])) {
				moves.add(new EnPassantMove(from, rightTo, piece, rightTo));
			}
			if ((leftTo & 0x88) == 0 && intBoard[leftTo] == null && PieceLibrary.isWhite(intBoard[from-1])) {
				moves.add(new EnPassantMove(from, leftTo, piece, leftTo));
			}
		}
		return moves;
	}

	private static List<Move> getSliderMoves(Integer piece, int from, Integer[] board, boolean isWhite, int[] delta) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < delta.length; i++) {
			int to = from + delta[i];
			while ((to & 0x88) == 0
					&& (!isWhite && !PieceLibrary.isBlack(board[to]) || isWhite && !PieceLibrary.isWhite(board[to]))) {
				moves.add(new Move(from, to, piece));
				if (isAttack(piece, to, board)) {
					break;
				}
				to += delta[i];
			}
		}
		return moves;
	}

	private static List<Move> getSingleMoves(Integer piece, int from, Integer[] board, boolean isWhite, int[] delta) {
		List<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < delta.length; i++) {
			int to = from + delta[i];
			if ((to & 0x88) == 0
					&& (!isWhite && !PieceLibrary.isBlack(board[to]) || isWhite && !PieceLibrary.isWhite(board[to]))) {
				moves.add(new Move(from, to, piece));
			}
		}
		return moves;
	}

	private static boolean isAttack(Integer piece, Integer to, Integer[] board) {
		return PieceLibrary.isWhite(piece) && PieceLibrary.isBlack(board[to])
				|| PieceLibrary.isBlack(piece) && PieceLibrary.isWhite(board[to]);
	}

}
