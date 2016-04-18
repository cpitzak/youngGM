package model.move;

public class Move {

	protected int from;
	protected int to;
	protected int piece;

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
