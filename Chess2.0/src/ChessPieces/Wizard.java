package ChessPieces;

import Movements.*;

import java.util.ArrayList;

public class Wizard extends ChessPiece{
	public static String name = "Wizard";
	public static int materialWorth = 9;
	
	public Wizard(int posRow, int posCol, String color, char image){
		super(posRow, posCol, color, image);
		getMovements().add(new SquareMovement());
		getMovements().add(new TeleportationMovement());

		setUpgrades();
	}
	
	public Wizard(ChessPiece source){
		super(source);
	}
	
	public int getMaterialWorth() { return materialWorth; }
	public String getName() { return name; }

	private void setUpgrades(){
		ArrayList<Movement> wizardUpgrades = getAvailableUpgrades();

		wizardUpgrades.add(new HopCaptureMovement());
		wizardUpgrades.add(new RangeCaptureMovement());
	}

}
