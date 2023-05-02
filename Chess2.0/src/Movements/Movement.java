package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public interface Movement{
	//Movement Type Symbols.
	public static final Character moveAndCaptureSymbol = 'X';
	public static final Character captureSymbol = 'x';
	public static final Character moveSymbol = 'O';
	public static final Character invalidMoveSymbol = ' ';
	
	//Symbol to mark the current piece's position.
	public static final Character currentPositionSymbol = '@';
	
	//posRow and posCol are the row and column that the chess piece is currently in.
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board);
	public String getMovementName();
	public int getMovementCost();
	//Create empty array.
	public default ArrayList<ArrayList<Character>> createAnEmptyArrayList(Board board){
		ArrayList<ArrayList<Character>> result = new ArrayList<ArrayList<Character>>();
		for (int row = 0; row < board.getPositionBoard().size(); row++){
			ArrayList<Character> currentRow = new ArrayList<Character>();
			for (int column = 0; column < board.getPositionBoard().get(row).size(); column++)
				currentRow.add(invalidMoveSymbol);
				
			result.add(currentRow);
		}
		return result;
	}
	
	//Chess Piece symbols for the position board will be stored in an ArrayList of four elements. 
	//Index 0 holds the ally king symbol
	//Index 1 holds the ally piece symbol
	//Index 2 holds the enemy king symbol
	//Index 0 holds the enemy piece symbol
	public default ArrayList<Character> getSymbols(String color){
		ArrayList<Character> chessPieceSymbols = new ArrayList<Character>();
		//If color is white, white pieces are allies and black pieces are enemies.
		if (color.equals("White")){
			chessPieceSymbols.add(Board.whiteKingPieceSymbol);
			chessPieceSymbols.add(Board.whitePieceSymbol);
			chessPieceSymbols.add(Board.blackKingPieceSymbol);
			chessPieceSymbols.add(Board.blackPieceSymbol);
		}
		//Else color is black, black pieces are allies and white pieces are enemies.
		else{
			chessPieceSymbols.add(Board.blackKingPieceSymbol);
			chessPieceSymbols.add(Board.blackPieceSymbol);
			chessPieceSymbols.add(Board.whiteKingPieceSymbol);
			chessPieceSymbols.add(Board.whitePieceSymbol);
		}
		return chessPieceSymbols;
	}
}
