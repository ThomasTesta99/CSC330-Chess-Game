package Movements;

import ChessGameClasses.Board;
import ChessPieces.ChessPiece;
import java.util.ArrayList;

public class CastleMovement implements Movement
{

	public static final String movementName = "Castle Movement";
	public static final int movementCost = 0;
	//posRow and posCol are the row and column that the chess piece is currently in.
	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean hasMoved, Board board)
	{
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		int kingColumn = 4;
		
		//Can only castle if King is not in check
		if (!board.isInCheck(color))
		{
			//Castle to the left
			//Left castle path is from column 1 to column 3.
			//Left Rook is at column 0
			castleInDirection(posRow, posCol, color, hasMoved, board, result, 1, kingColumn - 1, 0);
			
			//Castle to the right
			//Right castle path is from column 5 to column 7.
			//Right Rook is at column 8
			castleInDirection(posRow, posCol, color, hasMoved, board, result, kingColumn + 1, Board.colNum - 2, Board.colNum - 1);
		}
		//To mark itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		return result;
	}

	@Override
	public String getMovementName() {
		return movementName;
	}

	@Override
	public int getMovementCost() {
		return movementCost;
	}

	public void castleInDirection(int posRow, int posCol, String color, boolean hasMoved, Board board, 
			ArrayList<ArrayList<Character>> result, int fromCol, int toCol, int rookCol)
	{
		//Determine the ally and enemy symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character allyKingSymbol = pieceSymbols.get(0);
		Character allyPieceSymbol = pieceSymbols.get(1);
		Character enemyKingSymbol = pieceSymbols.get(2);
		Character enemyPieceSymbol = pieceSymbols.get(3);
		
		//Determine row
		int currentRow;
		if (color.equals("White")) 
			currentRow = Board.rowNum - 1; //White always starts from the bottom
		else //color.equals("Black")
			currentRow = 0; //Black always starts from the top
		
		int kingColumn = 4;
		
		//By the end of test cases, if canCastle remains true, then the King can castle.
		boolean canCastle = true;
		
		//Checks if the castle path is open. 
		for (int currentColumn = fromCol; currentColumn <= toCol; currentColumn++)
		{
			//If the position is not out of range
			if (currentRow >= 0 && currentRow < board.getPositionBoard().size() &&
					currentColumn >= 0 && currentColumn < board.getPositionBoard().get(currentRow).size())
			{
				Character currentCharacter = board.getPositionBoard().get(currentRow).get(currentColumn);
				
				//If position is occupied then king can't castle
				if (currentCharacter.equals(allyKingSymbol) || currentCharacter.equals(allyPieceSymbol) ||
						currentCharacter.equals(enemyKingSymbol) || currentCharacter.equals(enemyPieceSymbol))
					
				{
					canCastle = false;
					break;
				}
			}
		}
		
		//Check if rook and King are in position and haven't moved yet.
		if (canCastle)
		{
			//King can't castle if king is out of position or has moved
			if (posRow != currentRow || posCol != kingColumn || hasMoved)
				canCastle = false;
			
			//Find the rook
			ArrayList<ChessPiece> chessPieces;
			if (color.equals("White")) chessPieces = board.getWhiteChessPieces();
			else chessPieces = board.getBlackChessPieces();
			
			//If a chess piece of the corresponding color was found at the bottom left corner,
			//and it is a rook and has not moved, then the rook was found. 
			boolean found = false;
			for (ChessPiece chessPiece : chessPieces)
				if (chessPiece.getPosRow() == currentRow && chessPiece.getPosCol() == rookCol)
					if (chessPiece.getName().equals("Rook"))
						if (!chessPiece.getHasMoved())
							found = true;
			
			//If the a valid rook for left castle was found, left castle is valid. 
			if (found) canCastle = true;
		}
		
		//Mark the Rook position as the castle movement
		if (canCastle)
		{
			result.get(currentRow).set(rookCol, Movement.castleSymbol);
		}
	}
}
