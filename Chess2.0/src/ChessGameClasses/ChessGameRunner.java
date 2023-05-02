package ChessGameClasses;

import Movements.*;
import Players.*;
import ChessPieces.*;
import Items.*;
import java.util.ArrayList;

public class ChessGameRunner{
	Player whitePlayer;
	Player blackPlayer;
	
	Board board;
	Boolean gameOver;
	int passiveIncome;

	ArrayList<Item> playerItems = new ArrayList<>();
	
	public ChessGameRunner(int initialMoneyAmount, int initialPassiveIncomeAmount){
		SetUp(initialMoneyAmount, initialPassiveIncomeAmount);
	}
	
	public void SetUp(int initialMoneyAmount, int initialPassiveIncomeAmount){
		this.whitePlayer = new HumanPlayer(createWhiteChessSet(), "White", initialMoneyAmount);
		this.blackPlayer = new HumanPlayer(createBlackChessSet(), "Black", initialMoneyAmount);
		
		this.board = new Board(whitePlayer.getChessPieces(), blackPlayer.getChessPieces());
		
		this.gameOver = false;
		this.passiveIncome = initialPassiveIncomeAmount;
		createItems();
	}
	
	public void runGame(){
		int turn = 1;
		boolean moveMade;
		while (true){
			//Print Turn, whose turn.
			System.out.print("\tTurn " + turn);
			if (turn % 2 == 1) System.out.println("\t(White's Turn)");
			else System.out.println("\t(Black's Turn)");
			PrintBorder();
			
			//Print the display board.
			Board.displayBoard(board.getDisplayBoard());
			System.out.println();
			
			moveMade = false;
			
			//White goes on Odd turn
			if (turn % 2 == 1){
				if (board.isInCheck("White"))
					System.out.println("White is in CHECK");
				moveMade = whitePlayer.run(board, blackPlayer.getChessPieces(), playerItems);
			}
			//Black goes on Even turn
			else{
				if (board.isInCheck("Black"))
					System.out.println("Black is in CHECK");
				moveMade = blackPlayer.run(board, whitePlayer.getChessPieces(), playerItems);
			}
			
			//update the boards
			board.updatePositionBoard();
			board.updateControlBoards();
			
			PrintBorder();
			ClearConsole();
			
			//Increment turn only if player successfully ran his turn.
			if (moveMade) turn++;
		}
	}
	
	public static ArrayList<ChessPiece> createWhiteChessSet(){
		ArrayList<ChessPiece> whiteChessPieces = new ArrayList<ChessPiece>();
		String chessPieceColor = "White";
		
		//First Row
		whiteChessPieces.add(new Pawn(7,0, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,1, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,2, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,3, chessPieceColor, 'P'));
		//whiteChessPieces.add(new Pawn(7,4, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,5, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,6, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,7, chessPieceColor, 'P'));
		whiteChessPieces.add(new Pawn(7,8, chessPieceColor, 'P'));
		
		//Second Row
		whiteChessPieces.add(new Archer(8,0, chessPieceColor, 'A'));
		whiteChessPieces.add(new Cannon(8,1, chessPieceColor, 'C'));
		whiteChessPieces.add(new Archer(8,2, chessPieceColor, 'A'));
		whiteChessPieces.add(new Ninja(8,3, chessPieceColor, 'N'));
		//whiteChessPieces.add(new Archer(8,4, chessPieceColor, 'A'));
		whiteChessPieces.add(new Ninja(8,5, chessPieceColor, 'N'));
		whiteChessPieces.add(new Archer(8,6, chessPieceColor, 'A'));
		whiteChessPieces.add(new Cannon(8,7, chessPieceColor, 'C'));
		whiteChessPieces.add(new Archer(8,8, chessPieceColor, 'A'));
		
		//Third Row
		whiteChessPieces.add(new Rook(9,0, chessPieceColor, 'R'));
		whiteChessPieces.add(new Knight(9,1, chessPieceColor, 'H'));
		whiteChessPieces.add(new Bishop(9,2, chessPieceColor, 'B'));
		whiteChessPieces.add(new Queen(9,3, chessPieceColor, 'Q'));
		whiteChessPieces.add(new King(9,4, chessPieceColor, 'K'));
		whiteChessPieces.add(new Wizard(9,5, chessPieceColor, 'W'));
		whiteChessPieces.add(new Bishop(9,6, chessPieceColor, 'B'));
		whiteChessPieces.add(new Knight(9,7, chessPieceColor, 'H'));
		whiteChessPieces.add(new Rook(9,8, chessPieceColor, 'R'));
		
		return whiteChessPieces;
	}

	public static ArrayList<ChessPiece> createBlackChessSet(){
		ArrayList<ChessPiece> blackChessPieces = new ArrayList<ChessPiece>();
		String chessPieceColor = "Black";
		
		//First Row
		blackChessPieces.add(new Pawn(2,0, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,1, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,2, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,3, chessPieceColor, 'p'));
		//blackChessPieces.add(new Pawn(2,4, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,5, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,6, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,7, chessPieceColor, 'p'));
		blackChessPieces.add(new Pawn(2,8, chessPieceColor, 'p'));
				
		//Second Row
		blackChessPieces.add(new Archer(1,0, chessPieceColor, 'a'));
		blackChessPieces.add(new Cannon(1,1, chessPieceColor, 'c'));
		blackChessPieces.add(new Archer(1,2, chessPieceColor, 'a'));
		//blackChessPieces.add(new Ninja(1,3, chessPieceColor, 'n'));
		//blackChessPieces.add(new Archer(1,4, chessPieceColor, 'a'));
		blackChessPieces.add(new Ninja(1,5, chessPieceColor, 'n'));
		blackChessPieces.add(new Archer(1,6, chessPieceColor, 'a'));
		blackChessPieces.add(new Cannon(1,7, chessPieceColor, 'c'));
		blackChessPieces.add(new Archer(1,8, chessPieceColor, 'a'));
				
		//Third Row
		blackChessPieces.add(new Rook(0,0, chessPieceColor, 'r'));
		blackChessPieces.add(new Knight(0,1, chessPieceColor, 'h'));
		blackChessPieces.add(new Bishop(0,2, chessPieceColor, 'b'));
		blackChessPieces.add(new Queen(0,3, chessPieceColor, 'q'));
		blackChessPieces.add(new King(0,4, chessPieceColor, 'k'));
		blackChessPieces.add(new Wizard(0,5, chessPieceColor, 'w'));
		blackChessPieces.add(new Bishop(0,6, chessPieceColor, 'b'));
		blackChessPieces.add(new Knight(0,7, chessPieceColor, 'h'));
		blackChessPieces.add(new Rook(0,8, chessPieceColor, 'r'));
		
		return blackChessPieces;
	}

	public void createItems(){
		playerItems.add(new QueenGambitOverthrow());
	}
	
	public static void PrintBorder(){
		System.out.println("----------------------------------------------------");
	}
	
	public static void ClearConsole(){
		for (int i = 0; i < 50; i++)
			System.out.println();
	}
}
