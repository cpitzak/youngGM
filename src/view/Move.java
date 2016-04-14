package view;

public class Move {
	
	private Square from;
	private Square to;
	private Square secondFrom;
	private Square secondTo;
	
	public Move(Square from, Square to, Square secondFrom, Square secondTo) {
		super();
		this.from = from;
		this.to = to;
		this.secondFrom = secondFrom;
		this.secondTo = secondTo;
	}
	public Square getFrom() {
		return from;
	}
	public Square getTo() {
		return to;
	}
	public Square getSecondFrom() {
		return secondFrom;
	}
	public Square getSecondTo() {
		return secondTo;
	}

}
