package view;

import javax.swing.ImageIcon;

public class PromotionMove extends Move {
	
	private ImageIcon promotionPiece;
	
	public PromotionMove(Square from, Square to, ImageIcon promotionPiece) {
		super(from, to);
		this.promotionPiece = promotionPiece;
	}

	public ImageIcon getPromotionPiece() {
		return promotionPiece;
	}

}
