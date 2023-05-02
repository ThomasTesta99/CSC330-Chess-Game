package Players;

import ChessGameClasses.Board;
import Items.*;
import ChessPieces.ChessPiece;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player{
	public HumanPlayer(ArrayList<ChessPiece> chessPieces, String color, int money)
	{
		super(chessPieces, color, money);
	}
	
	//Make a decision. Buy items or move a chess piece
	public String makeDecision(){
		System.out.println("Make A Decision (0 - Buy Item) (1 - Move Chess Piece) (2 - Upgrade Chess Piece)");
		int decision = takeIntegerInput("Input: ", 0, 3);
		System.out.println();
		
		if (decision == 0)
			return Player.BuyItem;
		else if(decision == 1)
			return Player.MoveChessPiece;
		else{
			return Player.UpgradeChessPiece;
		}
	}
	
	// Get and make a decision between three options of price to pay for different chances of items from different tiers. 
	//Check if player has enough money, if player has enough money, use the specified chances to give the player an item randomly. 
	//Then use the item by passing in the board and the player’s color.
	public void buyItem(ArrayList<ChessPiece> opponentChessPieces, ArrayList<Item> items){
		int choice = -1;
		System.out.println("Player Items: ");
		for(int i = 0; i < items.size(); i++){
			System.out.println(i + ") " + items.get(i).getItemName() + " - $" + items.get(i).getItemCost());
			choice = takeIntegerInput("Choose an item: ", 0, items.size());
		}
		items.get(choice).use(opponentChessPieces);
	}
	
	//Take user input until a piece that the player owns is selected.  
	public int selectPiece(Board board){
		ArrayList<Integer> piecePosition;
		int row;
		int col;
		int selectedPieceIndex = -1;
		
		//Prompt user to select a chess piece.
		System.out.println("Select A Piece That You Own");
		
		//Take user input until a piece that the player owns is selected.
		do {
			piecePosition = takeUserInputForBoardPosition(board);
			row = piecePosition.get(0);
			col = piecePosition.get(1);
			
			for (int i = 0; i < getChessPieces().size(); i++){
				//Piece is found.
				if (getChessPieces().get(i).getPosRow() == row && getChessPieces().get(i).getPosCol() == col){
					selectedPieceIndex = i;
					break;
				}
			}
		}while (selectedPieceIndex == -1);
		System.out.println();
		
		return selectedPieceIndex;
	}
		
	//Calculate all of the possible moves the piece can make, then get or generative decision to make it. Ignore inputs that are invalid. 
	//There should be a reselect mechanism. 
	public ArrayList<Integer> selectMovement(int selectedPieceIndex, Board board){
		ArrayList<Integer> movementPosition = new ArrayList<Integer>();
		
		//Display the movement board of the piece.

		System.out.println("Possible Movements This Piece Can Make");
		ArrayList<ArrayList<Character>> potentialMovements = getChessPieces().get(selectedPieceIndex).calculatePotentialMovements(board);
		ArrayList<ArrayList<Character>> possibleMovements = getChessPieces().get(selectedPieceIndex).calculatePossibleMovements(potentialMovements, board);
		Board.displayBoard(possibleMovements);
		System.out.println();
		
		//Prompt user to select a move.
		System.out.println("Select A Move");
		
		//Get user input
		ArrayList<Integer> userInput = takeUserInputForBoardPosition(board);
		movementPosition.add(userInput.get(0));
		movementPosition.add(userInput.get(1));
		
		System.out.println();
		
		return movementPosition;
	}
	
	//Lower bound inclusive, upperbound exclusive
	public Integer takeIntegerInput(String prompt, int lowerbound, int upperbound){
		Scanner scanner = new Scanner(System.in);
		
		//Take an input. Ignore out of bound and non integer inputs.
		int input;
		do{
			System.out.print(prompt);
			try{
				input = scanner.nextInt();	
				scanner.nextLine(); //Remove the "\n".
			}catch(InputMismatchException e){//Catch exception for non integer inputs.
				input = lowerbound - 1; //Out of bound to restart loop. 
				scanner.nextLine(); //Remove non integer inputs.
			}
		} while (input < lowerbound || input >= upperbound); //Try again if input was out of bound.
		return input;
	}
	
	//Get user input for a valid board position. 
	public ArrayList<Integer> takeUserInputForBoardPosition(Board board){
		ArrayList<Integer> boardPositionInput = new ArrayList<Integer>();
		
		//Get valid row.
		int inputRow = takeIntegerInput("Input The Row: ", 0, Board.rowNum);
		
		//Get valid column.
		int inputColumn = takeIntegerInput("Input The Column: ", 0, Board.colNum);
		
		//Prepare userInput to be returned.
		boardPositionInput.add(inputRow);
		boardPositionInput.add(inputColumn);
		
		return boardPositionInput;
	}

	public void upgrade(Board board){
		int pieceIndex = selectPiece(board);
		ChessPiece currentPiece = getChessPieces().get(pieceIndex);

		currentPiece.displayUpgrades();
		int upgradeChoice = takeIntegerInput("Select the upgrade: ", 0, currentPiece.getAvailableUpgrades().size());

		if(getMoney() >= currentPiece.getAvailableUpgrades().get(upgradeChoice).getMovementCost() * currentPiece.getMaterialWorth()){
			currentPiece.upgrade(upgradeChoice);
		}else{
			System.out.println("Not enough money");
		}
	}
}
