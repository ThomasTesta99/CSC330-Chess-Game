package Movements;

import ChessGameClasses.Board;
import java.util.ArrayList;

public class HopCaptureMovement implements Movement{
	//posRow and posCol are the row and column that the chess piece is currently in.

	public static final String movementName = "Hop Capture";
	public static final int movementCost = 800;

	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean moved, Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		
		//Logic
		
		//Hop Up
		hopTowardsDirection(posRow, posCol, color, board, result, -1, 0);
		
		//Hop Down
		hopTowardsDirection(posRow, posCol, color, board, result, 1, 0);
		
		//Hop Left
		hopTowardsDirection(posRow, posCol, color, board, result, 0, -1);
				
		//Hop Right
		hopTowardsDirection(posRow, posCol, color, board, result, 0, 1);
		
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
	}
	
	public void hopTowardsDirection(int posRow, int posCol, String color, Board board, ArrayList<ArrayList<Character>> result, int directionRow, int directionColumn) {
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		int currentRow = posRow + directionRow;
		int currentColumn = posCol + directionColumn;
		
		//If the position is not out of range
		while (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size())
		{
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			
			//The first piece that is found is the piece to jump over.
			if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
					currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol)){
				currentRow += directionRow;
				currentColumn += directionColumn;
				break;
			}
			currentRow += directionRow;
			currentColumn += directionColumn;
		}
		
		//Find possible capture piece
		while (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
				currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size()){
			Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
			
			//The next piece is the piece that can be captured if it is an opponent piece.
			if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
					currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol)){
				//If it is an opponent piece, it can be captured.
				if (currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
					result.get(currentRow).set(currentColumn, moveAndCaptureSymbol);
				
				//If it is an ally piece, it is an invalid move.
				else if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol))
					result.get(currentRow).set(currentColumn, invalidMoveSymbol);
				break;
			}
			currentRow += directionRow;
			currentColumn += directionColumn;
		}
	}

	@Override
	public String getMovementName() {
		return movementName;
	}

	@Override
	public int getMovementCost() {
		return movementCost;
	}
}
