package model;

public class Move {

	private int from;
	private int to;
	private int piece;

	public Move(int from, int to, int piece) {
		this.from = from;
		this.to = to;
		this.piece = piece;
	}

	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	public int getPiece() {
		return piece;
	}

}
