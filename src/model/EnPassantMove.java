package model;

public class EnPassantMove extends Move {
	
	private int clearSquare;

	public EnPassantMove(int from, int to, int piece, int clearSquare) {
		super(from, to, piece);
		this.clearSquare = clearSquare;
	}
	
	public int getClearSquare() {
		return clearSquare;
	}

}
