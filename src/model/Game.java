package model;

import interfaces.UCICommands;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

public class Game {
	
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
			} else if (tokens[0].equalsIgnoreCase(UCICommands.UCI_NEW_GAME)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.SET_OPTION)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.REGISTER)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.POSITION)) {
				UCICommandRunner.runPosition(board, tokens[1]);
			} else if (tokens[0].equalsIgnoreCase(UCICommands.GO)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.STOP)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.PONDER_HIT)) {
			} else if (tokens[0].equalsIgnoreCase(UCICommands.QUIT)) {
			}
		}
	}

	public static void main(String args[]) {
		BasicConfigurator.configure();
		Game game = new Game();
		game.start();
	}

}
