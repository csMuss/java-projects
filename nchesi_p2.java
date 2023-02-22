package Assignments.Assignment2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Nick Chesi
 *
 */

public class nchesi_p2 {

	public static void main(String[] args) throws IOException {
		File inputFile = new File("pa2_input_1.txt");
		//File inputFile = new File("pa2_input_2.txt");
		//File inputFile = new File("pa2_input_3.txt");
		getInput(inputFile);
		// 1,1 5,1
	}

	/**
	 * getNumberOfTurns
	 * 
	 * This is a helper method for getInput to allow the while loop to work with the
	 * arrays
	 */

	public static int getNumberOfTurns(File inputFile) throws IOException {
		Scanner inputParse = new Scanner(inputFile);

		// first int is num of rows vs columns 1 int
		// second int is the k value / the repetition value

		int numberOfTurns = 0;

		while (inputParse.hasNextLine()) {
			numberOfTurns++;
			inputParse.nextLine();
		}

		inputParse.close();

		return (numberOfTurns * 2) - 1; // subtracts the row and k num id
	}

	/**
	 * getInput
	 * 
	 * gets the input from the file and parses it and calls the main method,
	 * organizeInputFile to start the game
	 */

	public static void getInput(File inputFile) throws IOException {
		Scanner inputParse = new Scanner(inputFile);

		int numberOfTurns = getNumberOfTurns(inputFile);

		int[] player1StartX = new int[numberOfTurns];
		int[] player1StartY = new int[numberOfTurns];
		int[] player1EndX = new int[numberOfTurns];
		int[] player1EndY = new int[numberOfTurns];

		int[] player2StartX = new int[numberOfTurns];
		int[] player2StartY = new int[numberOfTurns];
		int[] player2EndX = new int[numberOfTurns];
		int[] player2EndY = new int[numberOfTurns];

		int player1IXCounter = 0;
		int player1IYCounter = 0;
		int player1EXCounter = 0;
		int player1EYCounter = 0;

		int player2IXCounter = 0;
		int player2IYCounter = 0;
		int player2EXCounter = 0;
		int player2EYCounter = 0;

		int turnCounter = 1;

		// parse first line out
		int XY = inputParse.nextInt();
		int kNumber = inputParse.nextInt();

		while (inputParse.hasNextInt()) {

			if (turnCounter == 1 && inputParse.hasNextInt()) {
				player1StartX[player1IXCounter] = inputParse.nextInt();
				player1IXCounter++;
				turnCounter++;
			}

			if (turnCounter == 2 && inputParse.hasNextInt()) {
				player1StartY[player1IYCounter] = inputParse.nextInt();
				player1IYCounter++;
				turnCounter++;
			}

			if (turnCounter == 3 && inputParse.hasNextInt()) {
				player1EndX[player1EXCounter] = inputParse.nextInt();
				player1EXCounter++;
				turnCounter++;
			}

			if (turnCounter == 4 && inputParse.hasNextInt()) {
				player1EndY[player1EYCounter] = inputParse.nextInt();
				player1EYCounter++;
				turnCounter++;
			}

			if (turnCounter == 5 && inputParse.hasNextInt()) {
				player2StartX[player2IXCounter] = inputParse.nextInt();
				player2IXCounter++;
				turnCounter++;
			}

			if (turnCounter == 6 && inputParse.hasNextInt()) {
				player2StartY[player2IYCounter] = inputParse.nextInt();
				player2IYCounter++;
				turnCounter++;
			}

			if (turnCounter == 7 && inputParse.hasNextInt()) {
				player2EndX[player2EXCounter] = inputParse.nextInt();
				player2EXCounter++;
				turnCounter++;
			}

			if (turnCounter == 8 && inputParse.hasNextInt()) {
				player2EndY[player2EYCounter] = inputParse.nextInt();
				player2EYCounter++;
				turnCounter++;
			}

			if (turnCounter == 9) { // resets turn counter once the line is read throught
				turnCounter = 1;
			}
		}

		inputParse.close();

		organizeInputFile(XY, XY, numberOfTurns, kNumber, player1StartX, player1StartY, player1EndX, player1EndY,
				player2StartX, player2StartY, player2EndX, player2EndY, player1IXCounter, player2IXCounter);

	}

	/**
	 * organizeInputFile
	 * 
	 * organizes and takes the actions of the fileinput and then calls other methods
	 * to actually play the game
	 */

	public static void organizeInputFile(int rows, int columns, int numberOfTurns, int kNumber, int[] player1StartX,
			int[] player1StartY, int[] player1EndX, int[] player1EndY, int[] player2StartX, int[] player2StartY,
			int[] player2EndX, int[] player2EndY, int player1Turns, int player2Turns) {

		int player1Count = (player1EndY.length);
		int player2Count = (player2EndY.length);

		int[][] currentIntBoard = new int[player1Count][player1Count];
		char[][] currentCharBoard = new char[player1Count][player1Count];

		Player[] player1 = new Player[player1Count];
		Player[] player2 = new Player[player2Count];

		GameBoard board = new GameBoard(rows);

		board.setBoardSize(rows);

		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				currentIntBoard[i][j] = 1;
			}
		}
		
		// Creates all player turns for player 1 (X,Y) pos
		System.out.println("Player 1 Turns: ");

		for (int i = 0; i < player1Count; i++) {
			player1[i] = new Player(player1StartX[i], player1StartY[i], player1EndX[i], player1EndY[i]);
			if (player1[i].getInitalxPos() != 0 && player1[i].getInitalyPos() != 0 && player1[i].getEndxPos() != 0
					&& player1[i].getEndyPos() != 0) {
				player1[i].printString();
			}
		}

		// Creates all player turns for player 2 (X,Y) pos
		System.out.println("Player 2 Turns: ");

		for (int i = 0; i < player2Count; i++) {
			player2[i] = new Player(player2StartX[i], player2StartY[i], player2EndX[i], player2EndY[i]);
			if (player2[i].getInitalxPos() != 0 && player2[i].getInitalyPos() != 0 && player2[i].getEndxPos() != 0
					&& player2[i].getEndyPos() != 0) {
				player2[i].printString();
			}
		}

		for (int i = 0; i < player1Count; i++) {
			if (player2[i].getInitalxPos() != 0 && player2[i].getInitalyPos() != 0 && player2[i].getEndxPos() != 0
					&& player2[i].getEndyPos() != 0 && player1[i].getInitalxPos() != 0
					&& player1[i].getInitalyPos() != 0 && player1[i].getEndxPos() != 0
					&& player1[i].getEndyPos() != 0) {
				drawLineSquares(currentIntBoard, player1, player2, i);
			}
		}

		// sets board chars, change this when there is an update
		// player char finder needs to be in a 2D array and get all the info passed to
		// it and then amke a discision
		for (int i = 0; i < player1Count; i++) {
			for (int j = 0; j < player1Count; j++) {
				currentCharBoard[i][j] = playerCharFinder(currentIntBoard[i][j]);
			}
		}

		board.setPlayerChar(rows, currentCharBoard);
		board.drawBoard(rows, columns); // draws the board, call this when there is an update

		// needs to be made
		if (isTurnValid(kNumber)) {

		}
	}

	/**
	 * playerCharFinder
	 * 
	 * @return a char based on who owns the square
	 */

	public static char playerCharFinder(int board) {
		char charDecider;

		switch (board) {
		case -1: // player 1
			charDecider = 'X';
			break;
		case -2: // player 2
			charDecider = 'O';
			break;
		case 1: // board peice
			charDecider = '-';
			break;
		default:
			charDecider = 'P';
			break;
		}

		return charDecider;
	}

	/**
	 * isTurnValid
	 * 
	 * @return returns true or false based on the kNumber turn checker
	 */

	public static boolean isTurnValid(int kNumber) {
		// needs to be made
		return false;
	}

	public static void drawLineSquares(int[][] coordinates, Player[] player1, Player[] player2, int iteration) {

		// needs to take the coordinates array by referecence then fillin the lines
		// between the two points
		// 1,5 to 5,5
		// y = mx + b
		// endY = m(endX) + b
		
		int dy = player1[iteration].getEndyPos() - player1[iteration].getInitalyPos();
		int dx = player1[iteration].getEndxPos() - player1[iteration].getInitalxPos();
		// if dx || dy == 0 then end point is the same as inital point 
		int m = dy / dx;
		int b = player1[iteration].getInitalyPos() - (m * player1[iteration].getInitalxPos());
		// y - y1 = m(x - x1)

		for (int i = 0; i < player1.length; i++) {
			if (player1[i].getInitalxPos() != 0 && player1[i].getInitalyPos() != 0 && player1[i].getEndxPos() != 0
					&& player1[i].getEndyPos() != 0) {

				System.out.println("Player1: B: " + b + " M: " + m + " X: " + player1[i].getInitalxPos() + " Y: "
						+ player1[i].getInitalyPos());

				for (int j = 0; j < player1.length; j++) {
					if (coordinateIsOnLine(m, b, i, j)) {
						coordinates[i][j] = -1;
					}
				}
			}
		}

		dy = player2[iteration].getEndyPos() - player2[iteration].getInitalyPos();
		dx = player2[iteration].getEndxPos() - player2[iteration].getInitalxPos();
		// if dx || dy == 0 then end point is the same as inital point 
		m = dy / dx;
		b = player2[iteration].getInitalyPos() - (m * player2[iteration].getInitalxPos()) ;

		for (int i = 0; i < player1.length; i++) {
			if (player2[i].getInitalxPos() != 0 && player2[i].getInitalyPos() != 0 && player2[i].getEndxPos() != 0
					&& player2[i].getEndyPos() != 0) {
				// y - y1 = m(x - x1)
				
				System.out.println("Player2: B: " + b + " M: " + m + " X: " + player2[i].getInitalxPos() + " Y: "
						+ player2[i].getInitalyPos());
				
				for (int j = 0; j < player1.length; j++) {
					if (coordinateIsOnLine(m, b, i, j)) {
						coordinates[i][j] = -2;
					}
				}
			}
		}
	}

	public static boolean coordinateIsOnLine(int m, int b, int x, int y) {
		boolean isTrue = false;

		if (y == ((m * x) + b)) {
			isTrue = true;
		} else {
			isTrue = false;
		}

		return isTrue;
	}
	
	public void outputToFile() throws IOException {
		// takes all games, displays the counter of 
	}

}

// holds player position information, inital postion and the 

class Player {

	private int initalxPos;
	private int initalyPos;
	private int endxPos;
	private int endyPos;

	public Player(int startX, int startY, int endX, int endY) {
		this.initalxPos = startX;
		this.initalyPos = startY;
		this.endxPos = endX;
		this.endyPos = endY;
	}

	public int getInitalxPos() {
		return initalxPos;
	}

	public void setInitalxPos(int initalxPos) {
		this.initalxPos = initalxPos;
	}

	public int getInitalyPos() {
		return initalyPos;
	}

	public void setInitalyPos(int initalyPos) {
		this.initalyPos = initalyPos;
	}

	public int getEndxPos() {
		return endxPos;
	}

	public void setEndxPos(int endxPos) {
		this.endxPos = endxPos;
	}

	public int getEndyPos() {
		return endyPos;
	}

	public void setEndyPos(int endyPos) {
		this.endyPos = endyPos;
	}

	public void printString() {
		System.out.println("Start X: " + getInitalxPos() + " Start Y: " + getInitalyPos() + " End X: " + getEndxPos()
				+ " End Y: " + getEndyPos());
	}
}

// deals with updating creating, and displaying the game board

class GameBoard {

	private char[][] gameBoard;
	private int boardSize;

	public GameBoard(int boardSize) {
		gameBoard = new char[boardSize][boardSize];
	}

	public char[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(char[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public void setPlayerChar(int rows, char[][] playerChars) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < rows; y++) {
				setGameBoard(playerChars);
			}
		}
	}

	/**
	 * drawBoard Draws a gameboard, does not create it, for the rows and columns, to
	 * display it to the player
	 */

	public void drawBoard(int rows, int columns) {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {

				System.out.print(getGameBoard()[x][y] + "\t");
			}
			System.out.println();
		}
	}

}
