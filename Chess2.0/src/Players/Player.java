package Players;
import java.util.ArrayList;

import ChessPieces.ChessPiece;
import ChessGameClasses.Board;
import Items.*;

abstract public class Player{
	public static final String BuyItem = "Buy Item";
	public static final String MoveChessPiece = "Move Chess Piece";
	public static final String UpgradeChessPiece = "Upgrade Chess Piece";
	
	private ArrayList<ChessPiece> chessPieces;
	private String color;
	private int money;
	private ArrayList<Item> items;
	
	public Player(ArrayList<ChessPiece> chessPieces, String color, int money){
		this.setChessPieces(chessPieces);
		this.setColor(color);
		this.setMoney(money);
	}
	
	public ArrayList<ChessPiece> getChessPieces() { return chessPieces; }
	public String getColor() { return color; }
	public int getMoney() { return money; }
	
	public void setChessPieces(ArrayList<ChessPiece> chessPieces) { this.chessPieces = chessPieces; }
	public void setColor(String color) { this.color = color; }
	public void setMoney(int money) { this.money = money; }

	//Make a decision. Buy items or move a chess piece
	abstract public String makeDecision();
	
	// Get and make a decision between three options of price to pay for different chances of items from different tiers. 
	//Check if player has enough money, if player has enough money, use the specified chances to give the player an item randomly. 
	//Then use the item by passing in the board and the player’s color.
	abstract public void buyItem(ArrayList<ChessPiece> opponentChessPieces, ArrayList<Item> items); 

	//Select a piece that the player owns
	abstract public int selectPiece(Board board);
	
	//Calculate all of the possible moves the piece can make, then get or generate a decision to make it. Ignore inputs that are invalid. 
	abstract public ArrayList<Integer> selectMovement(int selectedPieceIndex, Board board);
	
	//Make decision, then buy item or move a chess piece. Return true if it successfully bought an item or moved a chess piece.
	public boolean run(Board board, ArrayList<ChessPiece> opponentChessPieces, ArrayList<Item> items){
		String decision = makeDecision();
		if (decision.equals(Player.BuyItem)){
			buyItem(opponentChessPieces, items);
			return true;
		}else if(decision.equals(Player.UpgradeChessPiece)){
			if(this instanceof HumanPlayer){
				((HumanPlayer) this).upgrade(board);
			}
			return true;
		}else{ //decision.equals(Player.MoveChessPiece)

			//Get input to select a valid piece.
			int selectedPieceIndex = selectPiece(board);

			//Get input to make a movement.
			ArrayList<Integer> movementPosition = selectMovement(selectedPieceIndex, board);

			//Run the movement and return whether a movement was successfully made.
			return getChessPieces().get(selectedPieceIndex).makeMovement(board, movementPosition);
		}
	}
}
