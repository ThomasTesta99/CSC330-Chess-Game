package Movements;

import ChessGameClasses.Board;
import ChessPieces.ChessPiece;
import ChessPieces.Pawn;
import java.util.ArrayList;

public class EnPassantMovement implements Movement
{

	public static final String movementName = "EnPassant";
	public static final int movementCost = 0;

	//posRow and posCol are the row and column that the chess piece is currently in.
	@Override
	public ArrayList<ArrayList<Character>> calculateMovement(int posRow, int posCol, String color, boolean hasMoved, Board board)
	{
		//Create an empty ArrayList<ArrayList<Character>> to store the possible moves
		ArrayList<ArrayList<Character>> result = createAnEmptyArrayList(board);
		
		//Check left side
		checkPosition(posRow, posCol, color, board, result, posRow, posCol - 1);
		
		//Check right side
		checkPosition(posRow, posCol, color, board, result, posRow, posCol + 1);
		
		//Mark the piece itself
		result.get(posRow).set(posCol, currentPositionSymbol); 
		
		return result;
	}
	
	public void checkPosition(int posRow, int posCol, String color, Board board, ArrayList<ArrayList<Character>> result, int rowToCheck, int colToCheck)
	{
		//Determine the enemy piece symbols depending on the color
		ArrayList<Character> pieceSymbols = getSymbols(color);
		Character enemyPieceSymbol = pieceSymbols.get(3);
				
		//Determine the opponent chess piece set and direction
		ArrayList<ChessPiece> opponentChessPieces;
		int direction;
		if (color.equals("White")) 
		{
			opponentChessPieces = board.getBlackChessPieces();
			direction = -1;
		}
		else 
		{
			opponentChessPieces = board.getWhiteChessPieces();
			direction = 1;
		}
			
		//Found a valid pawn to perform En Passant Capture on
		boolean found = false;
				
		//If row and column is within bound
		if (board.rowColWithinBound(rowToCheck, colToCheck))
			//If the position is occupied by an enemyPiece
			if (board.getPositionBoard().get(rowToCheck).get(colToCheck).equals(enemyPieceSymbol))
				//Find the chessPiece at that position
				for (ChessPiece chessPiece : opponentChessPieces)
					//En Passant is valid if it is a pawn and moved two units up.
					if (chessPiece.getName().equals("Pawn"))
					{
						Pawn pawn = (Pawn) chessPiece;
						if (pawn.getMovedTwoUnitsUp())
							found = true;
					}
				
		//If a valid pawn was found, mark the position behind that Pawn as en passant symbol.
		if (found) result.get(rowToCheck + direction).set(colToCheck, Movement.enPassantSymbol);
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
