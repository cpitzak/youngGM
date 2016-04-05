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

	private static final String WHITE_STRING = "WHITE";
	private static final String BLACK_STRING = "BLACK";
	private String algebraicMove = "";

	private JButton fromPieceButton;
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
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("Save")); // TODO - add functionality!
		tools.add(new JButton("Restore")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9)) {

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
		Color ochre = new Color(204, 119, 34);
		chessBoard.setBackground(ochre);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.addActionListener(this);
				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				// ImageIcon icon = new ImageIcon(
				// new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(transparentIcon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						// ) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[jj][ii] = b;
			}
		}

		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				switch (jj) {
				case 0:
					chessBoard.add(new JLabel("" + (9 - (ii + 1)), SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
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
			Square square = getButtonSquareInChessBoardSquares(selectedButton);
			// selected a chess board square
			if (square != null) {
				if (fromPieceButton == null) {
					fromPieceButton = selectedButton;
					algebraicMove = algebraicNotation[square.getCol()][square.getRow()];
				} else {
					algebraicMove += algebraicNotation[square.getCol()][square.getRow()];
					System.out.println("move: " + algebraicMove);

					boolean didMove = controller.makeMove(algebraicMove);
					if (didMove) {
						selectedButton.setIcon(fromPieceButton.getIcon());
						fromPieceButton.setIcon(transparentIcon);
					}
					fromPieceButton = null;
					algebraicMove = "";
				}
			}
		}
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
					return new Square(row, col);
				}
			}
		}
		return null;
	}

	private class Square {
		int row;
		int col;

		public Square(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

	}

}
