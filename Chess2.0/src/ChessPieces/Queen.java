package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Queen extends ChessPiece{
	public static final String name = "Queen";
	public static final int materialWorth = 9;
	
	public Queen(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new DiagonalMovement());
		getMovements().add(new OrthogonalMovement());

		setUpgrades();
	}
	
	public Queen(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> queenUpgrade = getAvailableUpgrades();

		queenUpgrade.add(new HopCaptureMovement());
		queenUpgrade.add(new LMovement());
		queenUpgrade.add(new RangeCaptureMovement());
		queenUpgrade.add(new RestrictedTeleportationMovement());
		queenUpgrade.add(new TeleportationMovement());
	}
}
