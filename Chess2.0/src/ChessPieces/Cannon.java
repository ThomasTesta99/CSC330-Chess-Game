package ChessPieces;

import Movements.*;

import java.util.ArrayList;

public class Cannon extends ChessPiece{
	public static String name = "Cannon";
	public static int materialWorth = 5;

	public Cannon(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new HopCaptureMovement());
		getMovements().add(new OrthogonalNonCaptureMovement());

		setUpgrades();
	}
	
	public Cannon(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() {
		return materialWorth;
	}
	public String getName() {
		return name;
	}

	private void setUpgrades(){
		ArrayList<Movement> cannonUpgrades = getAvailableUpgrades();

		cannonUpgrades.add(new SquareMovement());
		cannonUpgrades.add(new DiagonalMovement());
		cannonUpgrades.add(new OrthogonalMovement());
		cannonUpgrades.add(new LMovement());
		cannonUpgrades.add(new RangeCaptureMovement());
		cannonUpgrades.add(new TeleportationMovement());
		cannonUpgrades.add(new RestrictedTeleportationMovement());
		cannonUpgrades.add(new LeftRightCaptureMovement());
	}
}
