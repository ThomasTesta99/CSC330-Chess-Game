package ChessPieces;

import ChessGameClasses.Board;
import Movements.Movement;
import java.util.ArrayList;

abstract public class ChessPiece{
	private ArrayList<Movement> movements;
	private int posRow;
	private int posCol;
	private String color;
	private boolean isCaptured;
	private boolean hasMoved;
	private char image;

	private ArrayList<Movement> availableUpgrades = new ArrayList<>();
	
	public ChessPiece(int posRow, int posCol, String color, char image){
		this.setPosRow(posRow);
		this.setPosCol(posCol);
		this.setColor(color);
		this.setImage(image);
		
		this.setMovements(new ArrayList<Movement>());
		this.setIsCaptured(false);
		this.setHasMoved(false);
	}
	
	//Copy Constructor
	public ChessPiece(ChessPiece source){
		this.setPosRow(source.posRow);
		this.setPosCol(source.posCol);
		this.setColor(source.color);
		this.setIsCaptured(source.isCaptured);
		this.setHasMoved(source.hasMoved);
		this.setImage(source.image);
		
		this.setMovements(new ArrayList<Movement>());
		
		//Copy the movements by reference since they will not be changed.
		for (Movement movement : source.getMovements())
			getMovements().add(movement);
	}

	public String getColor() { return color; }
	public boolean getHasMoved() { return hasMoved; }
	public char getImage() { return image; }
	public boolean getIsCaptured() { return isCaptured; }
	public ArrayList<Movement> getMovements() { return movements; }
	public int getPosCol() { return posCol; }
	public int getPosRow() { return posRow; }
	
	public void setColor(String color) { this.color = color; }
	public void setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }
	public void setImage(char image) { this.image = image; }
	public void setIsCaptured(boolean isCaptured) { this.isCaptured = isCaptured; }
	public void setMovements(ArrayList<Movement> movements) { this.movements = movements; }
	public void setPosCol(int posCol) { this.posCol = posCol; }
	public void setPosRow(int posRow) { this.posRow = posRow; }
	
	abstract public int getMaterialWorth();
	abstract public String getName();

	//Get the combinational movement board of all the movements. 
	//These are the potential moves that the chess piece can make.
	public ArrayList<ArrayList<Character>> calculatePotentialMovements(Board board){
		//Create an empty ArrayList<ArrayList<Character>> to store the potential movement.
		ArrayList<ArrayList<Character>> allPotentialMovements = new ArrayList<ArrayList<Character>>();
		for (int row = 0; row < board.getPositionBoard().size(); row++){
			ArrayList<Character> currentRow = new ArrayList<Character>();
			for (int column = 0; column < board.getPositionBoard().get(row).size(); column++)
				currentRow.add(Movement.invalidMoveSymbol);
				
			allPotentialMovements.add(currentRow);
		}

		
		//Loop through all of the movements and add them to the potential movement.
		for (Movement movement : getMovements()){
			ArrayList<ArrayList<Character>> currentPotentialMovements = movement.calculateMovement(getPosRow(), getPosCol(), getColor(), board, getHasMoved());
			for (int row = 0; row < currentPotentialMovements.size(); row++){
				for (int column = 0; column < currentPotentialMovements.get(row).size(); column++){
					Character charInAll = allPotentialMovements.get(row).get(column);
					Character charInCurrent = currentPotentialMovements.get(row).get(column);
					
					//Precedence Order from greatest to lowest
					//(capture&move, capture) to move to invalid move.
					if (charInAll.equals(Movement.moveAndCaptureSymbol) || charInCurrent.equals(Movement.moveAndCaptureSymbol))
						allPotentialMovements.get(row).set(column, Movement.moveAndCaptureSymbol); 
					else if (charInAll.equals(Movement.captureSymbol) || charInCurrent.equals(Movement.captureSymbol))
						allPotentialMovements.get(row).set(column, Movement.captureSymbol); 
					else if (charInAll.equals(Movement.moveSymbol) || charInCurrent.equals(Movement.moveSymbol))
						allPotentialMovements.get(row).set(column, Movement.moveSymbol); 
					else
						allPotentialMovements.get(row).set(column, Movement.invalidMoveSymbol); 
				}
			}
		}
		//The piece can't move onto itself
		allPotentialMovements.get(getPosRow()).set(getPosCol(), Movement.currentPositionSymbol); 
		return allPotentialMovements;
	}
	
	//Update the position of the Chess Piece
	public void updateMove(int newRow, int newCol){
		this.posRow = newRow;
		this.posCol = newCol;
		hasMoved = true;
	}
	
	//Simulate a move and check whether or not it will result in a check. If it does, mark it as an invalid move. 
	public boolean thisMoveResultsInCheck(int moveRow, int moveCol, Character currentMovementType, Board board){
		//Create a copy of the current board and store the inputs into an ArrayList
		Board copyBoard = new Board(board);
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(moveRow);
		input.add(moveCol);
		
		ArrayList<ChessPiece> opponentChessPieces;
		ArrayList<ChessPiece> allyChessPieces;
		if (color == "White"){
			opponentChessPieces = copyBoard.getBlackChessPieces();
			allyChessPieces = copyBoard.getWhiteChessPieces();
		}//color == "Black"
		else{
			opponentChessPieces = copyBoard.getWhiteChessPieces();
			allyChessPieces = copyBoard.getBlackChessPieces();
		}
		
		//Find the current ChessPiece in the copyBoard
		int currentChessPieceIndex = -1;
		for (int i = 0; i < allyChessPieces.size(); i++)
			if (allyChessPieces.get(i).getPosRow() == posRow && allyChessPieces.get(i).getPosCol() == posCol)
				currentChessPieceIndex = i;
		
		//Record the type of movement this is. 
		ArrayList<ArrayList<Character>> potentialMovements = allyChessPieces.get(currentChessPieceIndex).calculatePotentialMovements(board);
		Character movementType = potentialMovements.get(moveRow).get(moveCol);
		
		//Move the ChessPiece if it was a Move or Move&Capture movement type.
		if (movementType.equals(Movement.moveSymbol) || movementType.equals(Movement.moveAndCaptureSymbol))
			allyChessPieces.get(currentChessPieceIndex).updateMove(moveRow, moveCol);
		
		//Capture Black pieces
		if (movementType.equals(Movement.captureSymbol) || movementType.equals(Movement.moveAndCaptureSymbol))
			for (int i = 0; i < opponentChessPieces.size(); i++)
				if (opponentChessPieces.get(i).getPosRow() == moveRow && opponentChessPieces.get(i).getPosCol() == moveCol)
					opponentChessPieces.get(i).chessPieceGetsCaptured();
		
		copyBoard.updatePositionBoard();
		copyBoard.updateControlBoards();
		
		return copyBoard.isInCheck(color);
	}
	
	//Loop through all of the potential moves and eliminate moves that if made, 
	//result in the Chess Piece's King being checked. Edits out the current position marker as well.
	//(This accounts for pinned pieces and King being checked)
	public ArrayList<ArrayList<Character>> calculatePossibleMovements(ArrayList<ArrayList<Character>> potentialMovements, Board board) {
		for (int row = 0; row < potentialMovements.size(); row++){
			for (int col = 0; col < potentialMovements.get(row).size(); col++){
				//If the current potential move results in a check, it is an invalid move.
				Character movementType = potentialMovements.get(row).get(col);
				if (movementType.equals(Movement.moveSymbol) || 
						movementType.equals(Movement.captureSymbol) ||
							movementType.equals(Movement.moveAndCaptureSymbol))
				{
					if (thisMoveResultsInCheck(row, col, movementType, board))
						potentialMovements.get(row).set(col, Movement.invalidMoveSymbol);
				}
			}
		}
		return potentialMovements; //Return edited potentialMovements.
	}

	//1) Call calculatePotentialMovements,
	//2) Account for invalid moves which are moves that if made, result in check, 
	//3) Prepare user input. 
	//4) Run the move
	public boolean makeMovement(Board board, ArrayList<Integer> userInput){
		//1) Get all of the potential moves that can be made.
		ArrayList<ArrayList<Character>> potentialMovements = calculatePotentialMovements(board);
		
		//2) Determine which of the potential moves are actually legal moves.
		ArrayList<ArrayList<Character>> possibleMovements = calculatePossibleMovements(potentialMovements, board);
					
		//3) Format userInput - (Index 1: row) (Index 2: column).
		int inputRow = userInput.get(0);
		int inputColumn = userInput.get(1);
		
		//4) Run the move
		Character movementType = possibleMovements.get(inputRow).get(inputColumn);
		
		//4a) Move the ChessPiece if it was a Move or Move&Capture movement type.
		if (movementType.equals(Movement.moveSymbol) || movementType.equals(Movement.moveAndCaptureSymbol))
			updateMove(inputRow, inputColumn);
		
		//4b) Capture
		//Determine opponentChessPieces
		ArrayList<ChessPiece> opponentChessPieces;
		if (color == "White") opponentChessPieces = board.getBlackChessPieces();
		else opponentChessPieces = board.getWhiteChessPieces();
		
		//Capture enemy piece if it was a Capture or Move&Capture.
		if (movementType.equals(Movement.captureSymbol) || movementType.equals(Movement.moveAndCaptureSymbol))
			for (int i = 0; i < opponentChessPieces.size(); i++)
				if (opponentChessPieces.get(i).getPosRow() == inputRow && opponentChessPieces.get(i).getPosCol() == inputColumn)
					opponentChessPieces.get(i).chessPieceGetsCaptured();
		
		//The move was made if it was a valid movement type.
		return (movementType.equals(Movement.moveSymbol) ||
				movementType.equals(Movement.captureSymbol) ||
				movementType.equals(Movement.moveAndCaptureSymbol));
	}
	
	public void chessPieceGetsCaptured(){
		this.posRow = -1;
		this.posCol = -1;
		this.isCaptured = true;
	}

	public ArrayList<Movement> getAvailableUpgrades() {
		return availableUpgrades;
	}

	public void displayUpgrades(){
		int upgradeSize = availableUpgrades.size();
		if(upgradeSize == 0){
			System.out.println("You can not upgrade this piece");
		}else{
			System.out.println(getName() + " upgrade list: ");
			for(int i = 0; i < upgradeSize; i++){
				System.out.print(i + ") ");
				System.out.println(availableUpgrades.get(i).getMovementName() + " - $" + availableUpgrades.get(i).getMovementCost() * getMaterialWorth());
			}
		}
	}

	//call display upgrade, take in user input and upgrade the piece
	public void upgrade(int choice){
		getMovements().add(availableUpgrades.get(choice));
		availableUpgrades.remove(choice);
	}
}
