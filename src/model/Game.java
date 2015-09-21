package model;

import interfaces.UCICommands;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Game {
	
	final static Logger logger = Logger.getLogger(Game.class);
	
	private Board board;

	public Game() {
		board = new Board();
	}

	public void start() {
		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			String[] tokens = line.split("\\s", 2);
			if (tokens[0].equalsIgnoreCase("debug")) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.IS_READY)) {
				logger.warn(UCICommands.IS_READY + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.UCI_NEW_GAME)) {
				logger.warn(UCICommands.UCI_NEW_GAME + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.SET_OPTION)) {
				logger.warn(UCICommands.SET_OPTION + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.REGISTER)) {
				logger.warn(UCICommands.REGISTER + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.POSITION)) {
				UCICommandRunner.runPosition(board, tokens[1]);
			} else if (tokens[0].equalsIgnoreCase(UCICommands.GO)) {
				logger.warn(UCICommands.GO + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.STOP)) {
				logger.warn(UCICommands.STOP + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.PONDER_HIT)) {
				logger.warn(UCICommands.PONDER_HIT + " is not yet implemented");
			} else if (tokens[0].equalsIgnoreCase(UCICommands.QUIT)) {
				logger.warn(UCICommands.QUIT + " is not yet implemented");
			}
		}
	}

	public static void main(String args[]) {
		BasicConfigurator.configure();
		Game game = new Game();
		game.start();
	}

}
