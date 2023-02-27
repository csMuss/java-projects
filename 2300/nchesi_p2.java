package Assignments.Assignment2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Nick Chesi
 *
 */

public class nchesi_p2 {

	public static void main(String[] args) throws IOException {
		generateInputFiles("nchesi_input_1");
		generateInputFiles("nchesi_input_2");

		File inputFile1 = new File("pa2_input_1.txt");
		File inputFile2 = new File("pa2_input_2.txt");
		File inputFile3 = new File("pa2_input_3.txt");

		File inputFileGenerated1 = new File("nchesi_input_1.txt");
		File inputFileGenerated2 = new File("nchesi_input_2.txt");

		getInput(inputFile1, 1);
		getInput(inputFile2, 2);
		getInput(inputFile3, 3);
		getInput(inputFileGenerated1, 4);
		getInput(inputFileGenerated2, 5);

		// 1,1 5,1
	}

	/**
	 * generateInputFiles
	 * 
	 * generates the two input files
	 * 
	 * @param fileName
	 * @throws IOException
	 */

	public static void generateInputFiles(String fileName) throws IOException {
		Random rand = new Random();
		FileWriter writeToFile = new FileWriter(fileName + ".txt");
		// generates random input files 
		int rowsXColumns = rand.nextInt(10 - 5 + 1) + 5;
		int kNumber = rand.nextInt(4);
		int numberOfTurns = rand.nextInt(35 - 5 + 1) + 5;

		String[] fileOutput = new String[numberOfTurns + 2];

		fileOutput[0] = rowsXColumns + " " + kNumber + "\n";

		for (int i = 1; i < numberOfTurns; i++) {
			int randomX1 = rand.nextInt(rowsXColumns);
			int randomY1 = rand.nextInt(rowsXColumns);
			int randomX2 = rand.nextInt(rowsXColumns);
			int randomY2 = rand.nextInt(rowsXColumns);

			fileOutput[i] = randomX1 + " " + randomY1 + " " + randomX2 + " " + randomY2 + "\n";
		}

		for (int i = 0; i < numberOfTurns; i++) {
			writeToFile.write(fileOutput[i] + "");
		}

		writeToFile.close();

	}

	/**
	 * getNumberOfTurns
	 * 
	 * computes the number of turns that a player has
	 * 
	 * @param inputFile
	 * @return
	 * @throws IOException
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
	 * an input fetcher
	 * 
	 * @param inputFile
	 * @throws IOException
	 */

	public static void getInput(File inputFile, int iteration) throws IOException {
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
			// iterates over turns
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
				player2StartX, player2StartY, player2EndX, player2EndY, player1IXCounter, player2IXCounter, iteration);

	}

	/**
	 * organizeInputFile
	 * 
	 * organizes the fileinput and starts calculations
	 * 
	 * @param rows
	 * @param columns
	 * @param numberOfTurns
	 * @param kNumber
	 * @param player1StartX
	 * @param player1StartY
	 * @param player1EndX
	 * @param player1EndY
	 * @param player2StartX
	 * @param player2StartY
	 * @param player2EndX
	 * @param player2EndY
	 * @param player1Turns
	 * @param player2Turns
	 * @throws IOException
	 */

	public static void organizeInputFile(int rows, int columns, int numberOfTurns, int kNumber, int[] player1StartX,
			int[] player1StartY, int[] player1EndX, int[] player1EndY, int[] player2StartX, int[] player2StartY,
			int[] player2EndX, int[] player2EndY, int player1Turns, int player2Turns, int iteration)
			throws IOException {

		int player1Count = (player1EndY.length);
		int player2Count = (player2EndY.length);

		int[][] currentIntBoard = new int[player1Count][player1Count];
		char[][] currentCharBoard = new char[player1Count][player1Count];

		Player[] player1 = new Player[player1Count];
		Player[] player2 = new Player[player2Count];

		GameBoard board = new GameBoard(rows);

		board.setBoardSize(rows);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				currentIntBoard[i][j] = 1;
			}
		}
		// Creates all player turns for player 1 (X,Y) pos

		for (int i = 0; i < player1Count; i++) {
			player1[i] = new Player(player1StartX[i], player1StartY[i], player1EndX[i], player1EndY[i]);
		}

		// Creates all player turns for player 2 (X,Y) pos

		for (int i = 0; i < player2Count; i++) {
			player2[i] = new Player(player2StartX[i], player2StartY[i], player2EndX[i], player2EndY[i]);
		}
		// creates the lines for the board
		for (int i = 0; i < player1Count; i++) {
			if (player2[i].getInitalxPos() != 0 && player2[i].getInitalyPos() != 0 && player2[i].getEndxPos() != 0
					&& player2[i].getEndyPos() != 0 && player1[i].getInitalxPos() != 0
					&& player1[i].getInitalyPos() != 0 && player1[i].getEndxPos() != 0
					&& player1[i].getEndyPos() != 0) {

				if (isTurnValid(kNumber, player1, player2) == true) {
					drawLineSquares(currentIntBoard, player1, player2, i);
				}
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

		countPlayerScore(currentIntBoard, rows, columns, player1[0], false);
		countPlayerScore(currentIntBoard, rows, columns, player2[0], true);

		board.setPlayerChar(rows, currentCharBoard);
		board.drawBoard(rows, columns); // draws the board, call this when there is an update

		System.out.println("Player 1 Score: " + player1[0].getPlayerScore());
		System.out.println("Player 2 Score: " + player2[0].getPlayerScore());

		// needs to be made

		outputToFile(board.getGameBoard(), currentIntBoard, rows, columns, player1, player2, iteration);

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
	 * drawLineSquares
	 * 
	 * assigns a -1 or -2 value to the array of coordinates based on if there is a
	 * line there or not
	 * 
	 * @param coordinates passes the coordinates by reference
	 * @param player1     takes all the player1 coordinates
	 * @param player2     takes all the player2 coordinates
	 * @param iteration   takes the iteration of the for loop to iterate the check
	 *                    string function
	 */

	public static void drawLineSquares(int[][] coordinates, Player[] player1, Player[] player2, int iteration) {

		// needs to take the coordinates array by referecence then fillin the lines
		// between the two points
		// y = mx + b
		// endY = m(endX) + b is what we compare to

		int dy = player1[iteration].getEndyPos() - player1[iteration].getInitalyPos();
		int dx = player1[iteration].getEndxPos() - player1[iteration].getInitalxPos();
		// if dx || dy == 0 then end point is the same as inital point
		int m = 0;
		// cannot divide by zero
		if (dy != 0 && dx != 0) {
			m = dy / dx;
		}

		int b = player1[iteration].getInitalyPos() - (m * player1[iteration].getInitalxPos());
		// y - y1 = m(x - x1)

		for (int i = 0; i < player1.length; i++) {
			if (player1[i].getInitalxPos() != 0 && player1[i].getInitalyPos() != 0 && player1[i].getEndxPos() != 0
					&& player1[i].getEndyPos() != 0) {

				for (int j = 0; j < player1.length; j++) {
					if (coordinateIsOnLine(m, b, i, j)) {// compares
						// if is on the line, then -1 for player 1
						coordinates[i][j] = -1;
					}
				}
			}
		}

		dy = player2[iteration].getEndyPos() - player2[iteration].getInitalyPos();
		dx = player2[iteration].getEndxPos() - player2[iteration].getInitalxPos();
		// if dx || dy == 0 then end point is the same as inital point
		m = 0;
		// cannot divide by zero
		if (dy != 0 && dx != 0) {
			m = dy / dx;
		}

		b = player2[iteration].getInitalyPos() - (m * player2[iteration].getInitalxPos());

		for (int i = 0; i < player1.length; i++) {
			if (player2[i].getInitalxPos() != 0 && player2[i].getInitalyPos() != 0 && player2[i].getEndxPos() != 0
					&& player2[i].getEndyPos() != 0) {
				// y - y1 = m(x - x1)

				for (int j = 0; j < player1.length; j++) {
					if (coordinateIsOnLine(m, b, i, j)) { // compares
						// if is on the line, then -2 for player 2
						coordinates[i][j] = -2;
					}
				}
			}
		}
	}

	/**
	 * coordinateIsOnLine
	 * 
	 * @param m
	 * @param b
	 * @param x
	 * @param y
	 * @return returns true or false based on if y = mx+b
	 */

	public static boolean coordinateIsOnLine(int m, int b, int x, int y) {
		boolean isTrue = false;
		// simple line compaire
		if (y == ((m * x) + b)) {
			isTrue = true;
		} else {
			isTrue = false;
		}

		return isTrue;
	}

	/**
	 * isTurnValid
	 * 
	 * @return returns true or false based on the kNumber turn checker
	 */

	public static boolean isTurnValid(int kNumber, Player[] player1, Player[] player2) {
		boolean isValid = true;

		int kNumCounter = 0;

		if (kNumber > 0) {
			for (int i = 0; i < player1.length; i++) {
				for (int x = 0; x < player1.length; x++) {
					if (kNumber < kNumCounter) {
						
						kNumCounter++;
						// checks if the turn is valid comaired to the two inital coords and loops throught 
						// player 1 is iterated through I and player2 is iterated through X
						if (player1[i].getInitalxPos() == player2[x].getInitalxPos()
								&& player1[i].getInitalyPos() == player2[x].getInitalyPos()) {
							isValid = false;
						}
					}
				}
			}
		} else {
			isValid = true;
		}

		return isValid;
	}

	/**
	 * outputToFile
	 * 
	 * takes a all the parameters and outputs them to a file after all calculations
	 * are done.
	 * 
	 * @param outputGrid
	 * @param rows
	 * @param columns
	 * @param player
	 * @throws IOException
	 */

	public static void outputToFile(char[][] outputGrid, int[][] outputGridInt, int rows, int columns,
			Player[] player1Score, Player[] player2Score, int iteration) throws IOException {
		// takes all games, displays the counter of

		FileWriter fileOutput = new FileWriter("nchesi_output_" + iteration + ".txt");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (outputGrid[i][j] != 'P' && outputGrid[i][j] != '\t') {
					fileOutput.write(outputGrid[i][j] + "\t"); // game board
				}
			}

			fileOutput.write("\n");
		}
		// outputs score to file
		fileOutput.write("\nPlayer1 Score: " + player1Score[0].getPlayerScore() + "\n");
		fileOutput.write("Player2 Score: " + player2Score[0].getPlayerScore());
		fileOutput.close();
	}

	/**
	 * countPlayerScore
	 * 
	 * 
	 * 
	 * @param coordinates
	 * @param rows
	 * @param columns
	 * @param player
	 * @param checkingForPlayer2
	 */

	public static void countPlayerScore(int[][] coordinates, int rows, int columns, Player player,
			boolean checkingForPlayer2) {

		int counter = 0;
		// checks if the coordinates are -2 or -1 and adds to the cooresponding players
		// score, -1 = player 1, -2 = player 2
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (coordinates[i][j] == -1 && checkingForPlayer2 == false) {
					counter++;
					player.setPlayerScore(counter);

				} else if (coordinates[i][j] == -2 && checkingForPlayer2 == true) {
					counter++;
					player.setPlayerScore(counter);
				}
			}
		}
	}

}

// holds player position information, inital postion and the 
class Player {

	private int initalxPos;
	private int initalyPos;
	private int endxPos;
	private int endyPos;

	private int playerScore;

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

	public void setPlayerScore(int score) {
		this.playerScore = score;
	}

	public int getPlayerScore() {
		return playerScore;
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
		System.out.println("===========================================");

		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {

				System.out.print(getGameBoard()[x][y] + "\t");
			}
			System.out.println();
		}

		System.out.println("===========================================");
	}

}
