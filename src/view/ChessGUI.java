package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;

/**
 * Code from:
 * http://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-
 * chess-gui
 * 
 * Modified by Clint
 */
public class ChessGUI implements Observer, ActionListener {

	private Controller controller;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private JPanel chessBoard;
	private final JLabel message = new JLabel("YoungGM is ready to play!");
	private static final String COLS = "ABCDEFGH";
	public static final int KING = 0, QUEEN = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK };
	public static final int BLACK = 0, WHITE = 1;
	private static final String PAWN_BLACK = "PAWN_BLACK";
	private static final String QUEEN_BLACK = "QUEEN_BLACK";
	private static final String KING_BLACK = "KING_BLACK";
	private static final String ROOK_BLACK = "ROOK_BLACK";
	private static final String KNIGHT_BLACK = "KNIGHT_BLACK";
	private static final String BISHOP_BLACK = "BISHOP_BLACK";

	private static final String PAWN_WHITE = "PAWN_WHITE";
	private static final String QUEEN_WHITE = "QUEEN_WHITE";
	private static final String KING_WHITE = "KING_WHITE";
	private static final String ROOK_WHITE = "ROOK_WHITE";
	private static final String KNIGHT_WHITE = "KNIGHT_WHITE";
	private static final String BISHOP_WHITE = "BISHOP_WHITE";

	private Stack<Move> moveHistory;

	private String algebraicMove = "";

	private Square fromPieceSquare;
	private ImageIcon transparentIcon;

	private String[][] algebraicNotation = new String[][] { { "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8" },
			{ "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7" }, { "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6" },
			{ "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5" }, { "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4" },
			{ "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3" }, { "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2" },
			{ "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1" } };

	public ChessGUI() {
		initializeGui();
	}

	public final void initializeGui() {
		transparentIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		transparentIcon.setDescription("");
		// create the images for the chess pieces
		createImages();

		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New") {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resetBoard();
				setupNewGame();
				fromPieceSquare = null;
				moveHistory = new Stack<Move>();
			}
		};
		tools.add(newGameAction);

		Action undoAction = new AbstractAction("Undo") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: if undo move successful update GUI
				if (!moveHistory.isEmpty()) {
					controller.undoMove();
					Move move = moveHistory.pop();
					move.getFrom().getButton().setIcon(move.getTo().getButton().getIcon());
					move.getTo().getButton().setIcon(transparentIcon);
					if (move.getSecondFrom() != null && move.getSecondTo() != null) {
						move.getSecondFrom().getButton().setIcon(move.getSecondTo().getButton().getIcon());
						move.getSecondTo().getButton().setIcon(transparentIcon);
					}
					if (controller.isWhiteTurn()) {
						message.setText("White's Turn");
					} else {
						message.setText("Black's Turn");
					}
				}
			}
		};
		tools.add(undoAction);
		// tools.add(new JButton("Restore")); // TODO - add functionality!
		// tools.addSeparator();
		// tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 10)) {

			/**
			 * Override the preferred size to return the largest it can, in a
			 * square shape. Must (must, must) be added to a GridBagLayout as
			 * the only component (it uses the parent as a guide to size) with
			 * no GridBagConstaint (so it is centered).
			 */
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null) {
					prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
				} else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
					prefSize = c.getSize();
				} else {
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w > h ? h : w);
				return new Dimension(s, s);
			}
		};
		chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
		// Set the BG to be ochre
		Color beigeSquare = new Color(238, 238, 210);
		chessBoard.setBackground(Color.LIGHT_GRAY);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(Color.WHITE);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		Color greenSquare = new Color(118, 150, 86);
		Border squareBorder = new LineBorder(Color.BLACK, 1);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares[i].length; j++) {
				JButton b = new JButton();
				b.addActionListener(this);
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				// ImageIcon icon = new ImageIcon(
				// new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(transparentIcon);
				b.setBorder(squareBorder);
				if ((j % 2 == 1 && i % 2 == 1)
						// ) {
						|| (j % 2 == 0 && i % 2 == 0)) {
					b.setBackground(beigeSquare);
				} else {
					b.setBackground(greenSquare);
				}
				chessBoardSquares[j][i] = b;
			}
		}

		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		for (int i = 0; i < 8; i++) {
			chessBoard.add(new JLabel("", SwingConstants.CENTER));
		}
		chessBoard.add(new JLabel(""));
		// fill the black non-pawn piece row
		for (int row = 0; row < 8; row++) {
			chessBoard.add(new JLabel("" + (8 - row), SwingConstants.CENTER));
			for (int col = 0; col < 8; col++) {
				chessBoard.add(chessBoardSquares[col][row]);
			}
			chessBoard.add(new JLabel(""));
		}
		chessBoard.add(new JLabel(""));
		for (int i = 0; i < 8; i++) {
			chessBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}
	}

	public final JComponent getGui() {
		return gui;
	}

	private final void createImages() {
		try {
			// URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(new File("memI0.png"));
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	private final void setupNewGame() {
		message.setText("Make your move!");
		for (int row = 0; row < chessBoardSquares.length; row++) {
			for (int col = 0; col < chessBoardSquares[0].length; col++) {
				chessBoardSquares[row][col].setIcon(transparentIcon);
			}
		}
		// set up the black pieces
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon blackStartingRow = new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[i]]);
			blackStartingRow.setDescription(pieceNumToName(BLACK, STARTING_ROW[i]));
			chessBoardSquares[i][0].setIcon(blackStartingRow);
		}
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon blackPawnIcon = new ImageIcon(chessPieceImages[BLACK][PAWN]);
			blackPawnIcon.setDescription(PAWN_BLACK);
			chessBoardSquares[i][1].setIcon(blackPawnIcon);
		}
		// set up the white pieces
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon whitePawn = new ImageIcon(chessPieceImages[WHITE][PAWN]);
			whitePawn.setDescription(PAWN_WHITE);
			chessBoardSquares[i][6].setIcon(whitePawn);
		}
		for (int i = 0; i < STARTING_ROW.length; i++) {
			ImageIcon whiteStartingRow = new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[i]]);
			whiteStartingRow.setDescription(pieceNumToName(WHITE, STARTING_ROW[i]));
			chessBoardSquares[i][7].setIcon(whiteStartingRow);
		}
	}

	private String pieceNumToName(int color, int piece) {
		if (piece == QUEEN) {
			return color == 1 ? QUEEN_WHITE : QUEEN_BLACK;
		} else if (piece == KING) {
			return color == 1 ? KING_WHITE : KING_BLACK;
		} else if (piece == ROOK) {
			return color == 1 ? ROOK_WHITE : ROOK_BLACK;
		} else if (piece == KNIGHT) {
			return color == 1 ? KNIGHT_WHITE : KNIGHT_BLACK;
		} else if (piece == BISHOP) {
			return color == 1 ? BISHOP_WHITE : BISHOP_BLACK;
		}
		throw new IllegalStateException("error in pieceNumToName in GUI");
	}

	public void addController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("Receieved command: " + arg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button pressed");
		if (e.getSource() instanceof JButton) {
			JButton selectedButton = (JButton) e.getSource();
			Square selectedSquare = getButtonSquareInChessBoardSquares(selectedButton);
			// selected a chess board square
			if (selectedSquare != null) {
				String algebraicSquare = algebraicNotation[selectedSquare.getCol()][selectedSquare.getRow()];
				if (fromPieceSquare == null) {
					if (!controller.isSquareEmpty(algebraicSquare)) {
						fromPieceSquare = selectedSquare;
						algebraicMove = algebraicSquare;
					}
				} else {
					algebraicMove += algebraicSquare;
					Square fromSquare = getButtonSquareInChessBoardSquares(fromPieceSquare.getButton());
					ImageIcon fromIcon = (ImageIcon) fromPieceSquare.getButton().getIcon();
					ImageIcon toIcon = (ImageIcon) selectedButton.getIcon();
					JButton enPassantTargetButton = getEnPassantTargetButton(selectedSquare, fromSquare, fromIcon,
							toIcon);
					String promotionPiece = getPromotionPiece(selectedSquare, fromIcon);
					System.out.println("move: " + algebraicMove);
					boolean didMove = controller.makeMove(algebraicMove);
					if (didMove) {
						boolean guiDidMove = false;
						guiDidMove = castleMove(selectedSquare);
						if (!guiDidMove) {
							guiDidMove = pawnMove(selectedButton, promotionPiece, enPassantTargetButton);
						}
						if (!guiDidMove) {
							selectedButton.setIcon(fromPieceSquare.getButton().getIcon());
							fromPieceSquare.getButton().setIcon(transparentIcon);
						}
						if (controller.isWhiteTurn()) {
							message.setText("White's Turn");
						} else {
							message.setText("Black's Turn");
						}
					}
					fromPieceSquare = null;
					algebraicMove = "";
				}
			}
		}
	}

	private String getPromotionPiece(Square selectedSquare, ImageIcon fromIcon) {
		String promotionPiece = null;
		if (selectedSquare.getCol() == 0 && fromIcon.getDescription().equals(PAWN_WHITE)) {
			promotionPiece = "Q";
			algebraicMove += promotionPiece;
		} else if (selectedSquare.getCol() == 7 && fromIcon.getDescription().equals(PAWN_BLACK)) {
			promotionPiece = "q";
			algebraicMove += promotionPiece;
		}
		return promotionPiece;
	}

	private JButton getEnPassantTargetButton(Square selectedSquare, Square fromSquare, ImageIcon fromIcon,
			ImageIcon toIcon) {
		JButton enPassantTargetButton = null;
		if (fromSquare.getCol() == 3 && fromIcon.getDescription().equals(PAWN_WHITE)
				&& toIcon.getDescription().isEmpty()) {
			// going left
			if (fromSquare.getRow() - 1 == selectedSquare.getRow()) {
				JButton temp = chessBoardSquares[fromSquare.getRow() - 1][fromSquare.getCol()];
				ImageIcon targetIcon = (ImageIcon) temp.getIcon();
				if (targetIcon.getDescription().equals(PAWN_BLACK)) {
					enPassantTargetButton = temp;
				}
			} else if (fromSquare.getRow() + 1 == selectedSquare.getRow()) {
				// going right
				JButton temp = chessBoardSquares[fromSquare.getRow() + 1][fromSquare.getCol()];
				ImageIcon targetIcon = (ImageIcon) temp.getIcon();
				if (targetIcon.getDescription().equals(PAWN_BLACK)) {
					enPassantTargetButton = temp;
				}
			}
		} else if (fromSquare.getCol() == 4 && fromIcon.getDescription().equals(PAWN_BLACK)
				&& toIcon.getDescription().isEmpty()) {
			// going left
			if (fromSquare.getRow() - 1 == selectedSquare.getRow()) {
				JButton temp = chessBoardSquares[fromSquare.getRow() - 1][fromSquare.getCol()];
				ImageIcon targetIcon = (ImageIcon) temp.getIcon();
				if (targetIcon.getDescription().equals(PAWN_WHITE)) {
					enPassantTargetButton = temp;
				}
			} else if (fromSquare.getRow() + 1 == selectedSquare.getRow()) {
				// going right
				JButton temp = chessBoardSquares[fromSquare.getRow() + 1][fromSquare.getCol()];
				ImageIcon targetIcon = (ImageIcon) temp.getIcon();
				if (targetIcon.getDescription().equals(PAWN_WHITE)) {
					enPassantTargetButton = temp;
				}
			}
		}
		return enPassantTargetButton;
	}

	private boolean pawnMove(JButton selectedButton, String promotionPiece, JButton enPassantTargetButton) {
		boolean didMove = false;
		if (promotionPiece != null) {
			if (promotionPiece.equals("Q")) {
				ImageIcon queenIcon = new ImageIcon(chessPieceImages[WHITE][QUEEN]);
				queenIcon.setDescription(QUEEN_WHITE);
				selectedButton.setIcon(queenIcon);
				didMove = true;
			} else if (promotionPiece.equals("q")) {
				ImageIcon queenIcon = new ImageIcon(chessPieceImages[BLACK][QUEEN]);
				queenIcon.setDescription(QUEEN_BLACK);
				selectedButton.setIcon(queenIcon);
				didMove = true;
			} else {
				throw new IllegalStateException("invalid promotion state");
			}
		} else if (enPassantTargetButton != null) {
			selectedButton.setIcon(fromPieceSquare.getButton().getIcon());
			enPassantTargetButton.setIcon(transparentIcon);
			((ImageIcon) enPassantTargetButton.getIcon()).setDescription("");
			didMove = true;
		}
		if (didMove) {
			selectedButton.setIcon(fromPieceSquare.getButton().getIcon());
			fromPieceSquare.getButton().setIcon(transparentIcon);
		}
		return didMove;
	}

	private boolean castleMove(Square selectedSquare) {
		boolean didMove = false;
		Square secondFromSquare = null;
		Square secondToSquare = null;
		if (isCastleAttemptWhiteKingSide()) {
			chessBoardSquares[5][7].setIcon(chessBoardSquares[7][7].getIcon());
			secondFromSquare = new Square(7, 7, chessBoardSquares[7][7]);
			secondToSquare = new Square(5, 7, chessBoardSquares[5][7]);
			chessBoardSquares[7][7].setIcon(transparentIcon);
			didMove = true;
		} else if (isCastleAttemptBlackKingSide()) {
			chessBoardSquares[5][0].setIcon(chessBoardSquares[7][0].getIcon());
			secondFromSquare = new Square(7, 0, chessBoardSquares[7][0]);
			secondToSquare = new Square(5, 0, chessBoardSquares[5][0]);
			chessBoardSquares[7][0].setIcon(transparentIcon);
			didMove = true;
		} else if (isCastleAttemptWhiteQueenSide()) {
			chessBoardSquares[3][7].setIcon(chessBoardSquares[0][7].getIcon());
			secondFromSquare = new Square(0, 7, chessBoardSquares[0][7]);
			secondToSquare = new Square(3, 7, chessBoardSquares[3][7]);
			chessBoardSquares[0][7].setIcon(transparentIcon);
			didMove = true;
		} else if (isCastleAttemptBlackQueenSide()) {
			chessBoardSquares[3][0].setIcon(chessBoardSquares[0][0].getIcon());
			secondFromSquare = new Square(0, 0, chessBoardSquares[0][0]);
			secondToSquare = new Square(3, 0, chessBoardSquares[3][0]);
			chessBoardSquares[0][0].setIcon(transparentIcon);
			didMove = true;
		}
		if (didMove) {
			selectedSquare.getButton().setIcon(fromPieceSquare.getButton().getIcon());
			fromPieceSquare.getButton().setIcon(transparentIcon);
			Move move = new Move(fromPieceSquare, selectedSquare, secondFromSquare, secondToSquare);
			moveHistory.push(move);
		}
		return didMove;
	}

	private boolean isCastleAttemptWhiteKingSide() {
		if (algebraicMove.length() == 4) {
			String from = algebraicMove.substring(0, 2);
			String to = algebraicMove.substring(2, 4);
			if (from.equals("e1") && to.equals("g1")) {
				return true;
			}
		}
		return false;
	}

	private boolean isCastleAttemptWhiteQueenSide() {
		if (algebraicMove.length() == 4) {
			String from = algebraicMove.substring(0, 2);
			String to = algebraicMove.substring(2, 4);
			if (from.equals("e1") && to.equals("c1")) {
				return true;
			}
		}
		return false;
	}

	private boolean isCastleAttemptBlackKingSide() {
		if (algebraicMove.length() == 4) {
			String from = algebraicMove.substring(0, 2);
			String to = algebraicMove.substring(2, 4);
			if (from.equals("e8") && to.equals("g8")) {
				return true;
			}
		}
		return false;
	}

	private boolean isCastleAttemptBlackQueenSide() {
		if (algebraicMove.length() == 4) {
			String from = algebraicMove.substring(0, 2);
			String to = algebraicMove.substring(2, 4);
			if (from.equals("e8") && to.equals("c8")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param button
	 *            the button to find
	 * @return the square if in chessBoardSquares, otherwise false
	 */
	private Square getButtonSquareInChessBoardSquares(JButton button) {
		for (int row = 0; row < chessBoardSquares.length; row++) {
			for (int col = 0; col < chessBoardSquares[0].length; col++) {
				if (button == chessBoardSquares[row][col]) {
					System.out.println("row: " + row + ", col: " + col);
					return new Square(row, col, button);
				}
			}
		}
		return null;
	}

}
