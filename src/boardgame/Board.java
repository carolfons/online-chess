package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//m�todo para retornar a pe�a dada uma coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//m�todo que retorna a matriz pieces na linha row  e coluna column
	public Piece piece (Position pos) {
		return pieces[pos.getRow()][pos.getColumn()];
	}
	
	//m�todo para colocar a pe�a na posi��o do tabuleiro
	public void placePiece(Piece piece, Position pos) {
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	
}
