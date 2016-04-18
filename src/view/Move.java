package view;

import javax.swing.ImageIcon;

public class Move {
	
	private Square from;
	private Square to;
	private ImageIcon pieceCaptured;
	
	public Move(Square from, Square to) {
		if (from.getButton().getIcon() == null || to.getButton().getIcon() == null) {
			throw new IllegalStateException("Move button icons are null");
		}
		this.from = from;
		this.to = to;
		
	}
	public Square getFrom() {
		return from;
	}
	public Square getTo() {
		return to;
	}
	public ImageIcon getPieceCaptured() {
		return pieceCaptured;
	}
	public void setPieceCaptured(ImageIcon pieceCaptured) {
		this.pieceCaptured = pieceCaptured;
	}

}
