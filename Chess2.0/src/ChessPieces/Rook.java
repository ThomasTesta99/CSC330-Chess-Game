package ChessPieces;

import Movements.*;
import java.util.ArrayList;

public class Rook extends ChessPiece{
	public static String name = "Rook";
	public static int materialWorth = 5;
	
	public Rook(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new OrthogonalMovement());

		setUpgrades();
	}
	
	public Rook(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> rookUpgrade = getAvailableUpgrades();

		rookUpgrade.add(new SquareMovement());
		rookUpgrade.add(new DiagonalMovement());
		rookUpgrade.add(new LMovement());
		rookUpgrade.add(new HopCaptureMovement());
		rookUpgrade.add(new RangeCaptureMovement());
		rookUpgrade.add(new TeleportationMovement());
		rookUpgrade.add(new RestrictedTeleportationMovement());
		rookUpgrade.add(new LeftRightCaptureMovement());
	}
}
