package model.move;

import java.util.ArrayList;
import java.util.List;

import interfaces.PieceLibrary;
import model.Board;
import model.Validator;

public class MoveGenerator {
	
	public static EnPassantMove getEnPassantMove(int from, int to, int piece, Board board) {
		EnPassantMove move = null;
		if (Validator.canEnPassant(from, to, piece, board)) {
			Integer[] intBoard = board.getBoard();
			if (piece == PieceLibrary.WHITE_PAWN) {
				boolean left = (from + 15) == to;
				boolean right = (from + 17) == to;
				if (left) {
					int targetSquare = from - 1;
					move = new EnPassantMove(from, to, piece, targetSquare, intBoard[targetSquare]);
				} else if (right) {
					int targetSquare = from + 1;
					move = new EnPassantMove(from, to, piece, targetSquare, intBoard[targetSquare]);
				}
			} else if (piece == PieceLibrary.BLACK_PAWN) {
				boolean left = (from - 17) == to;
				boolean right = (from - 15) == to;
				if (left) {
					int targetSquare = from - 1;
					move = new EnPassantMove(from, to, piece, targetSquare, intBoard[targetSquare]);
				} else if (right) {
					int targetSquare = from + 1;
					move = new EnPassantMove(from, to, piece, targetSquare, intBoard[targetSquare]);
				}
			}
		}
		return move;
	}
	
	public static CastleMove getCastleMove(int from, int to, int piece, Board board) {
		CastleMove move = null;
		if (Validator.canCastle(from, to, piece, board)) {
			if (piece == PieceLibrary.WHITE_KING) {
				if (to == Board.G1) { // king side
					Move rookMove = new Move(Board.H1, Board.F1, PieceLibrary.WHITE_ROOK);
					move = new CastleMove(from, to, piece, rookMove);
				} else if (to == Board.C1) { // queen side
					Move rookMove = new Move(Board.A1, Board.D1, PieceLibrary.WHITE_ROOK);
					move = new CastleMove(from, to, piece, rookMove);
				}
			} else if (piece == PieceLibrary.BLACK_KING) {
				if (to == Board.G8) { // king side
					Move rookMove = new Move(Board.H8, Board.F8, PieceLibrary.BLACK_ROOK);
					move = new CastleMove(from, to, piece, rookMove);
				} else if (to == Board.C8) { // queen side
					Move rookMove = new Move(Board.A8, Board.D8, PieceLibrary.BLACK_ROOK);
					move = new CastleMove(from, to, piece, rookMove);
				}
			}
		}
		return move;
	}

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
		if (piece == PieceLibrary.WHITE_KING && from == Board.E1) {
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
		} else if (piece == PieceLibrary.BLACK_KING && from == Board.E8) {
			if (board.isBlackCanCastleKingSide()) {
				Move rookMove = new Move(from+3, from+1, PieceLibrary.BLACK_ROOK);
				CastleMove move = new CastleMove(from, from+2, piece, rookMove);
				moves.add(move);
			}
			if (board.isBlackCanCastleQueenSide()) {
				Move rookMove = new Move(from-4, from-1, PieceLibrary.BLACK_ROOK);
				CastleMove move = new CastleMove(from, from-2, piece, rookMove);
				moves.add(move);
			}
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
		if (isWhite && Board.square0x88ToRank(from) == Board.RANK_5) {
			int leftTo = from + PieceLibrary.PAWN_MOVE_ATTACK_DELTA[0];
			int rightTo = from + PieceLibrary.PAWN_MOVE_ATTACK_DELTA[1];
			if ((leftTo & 0x88) == 0 && intBoard[leftTo] == null && PieceLibrary.isBlack(intBoard[from-1])) {
				moves.add(new EnPassantMove(from, leftTo, piece, leftTo, intBoard[leftTo]));
			}
			if ((rightTo & 0x88) == 0 && intBoard[rightTo] == null && PieceLibrary.isBlack(intBoard[from+1])) {
				moves.add(new EnPassantMove(from, rightTo, piece, rightTo, intBoard[rightTo]));
			}
		} else if (!isWhite && Board.square0x88ToRank(from) == Board.RANK_4){
			int rightTo = from - PieceLibrary.PAWN_MOVE_ATTACK_DELTA[0];
			int leftTo = from - PieceLibrary.PAWN_MOVE_ATTACK_DELTA[1];
			if ((rightTo & 0x88) == 0 && intBoard[rightTo] == null && PieceLibrary.isWhite(intBoard[from+1])) {
				moves.add(new EnPassantMove(from, rightTo, piece, rightTo, intBoard[rightTo]));
			}
			if ((leftTo & 0x88) == 0 && intBoard[leftTo] == null && PieceLibrary.isWhite(intBoard[from-1])) {
				moves.add(new EnPassantMove(from, leftTo, piece, leftTo, intBoard[leftTo]));
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
