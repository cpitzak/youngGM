package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Board;
import view.ChessGUI;

public class YoungGM {
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				Board model = new Board();
				ChessGUI view = new ChessGUI();
				model.addObserver(view);
				Controller controller = new Controller();
				controller.addModel(model);
				controller.addView(view);
				view.addController(controller);

				JFrame f = new JFrame("ChessChamp");
				f.add(view.getGui());
				// Ensures JVM closes after frame(s) closed and
				// all non-daemon threads are finished
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// See http://stackoverflow.com/a/7143398/418556 for demo.
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		// Swing GUIs should be created and updated on the EDT
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
	}

}
