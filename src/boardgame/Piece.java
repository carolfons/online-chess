package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	//Construtor
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	// n�o possui m�todo set
	
}
