package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("ERROR! At least 1 row and 1 column!");
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}


	public int getColumns() {
		return columns;
	}
	
	//m�todo para retornar a pe�a dada uma coluna
	public Piece piece(int row, int column) {
		if(!positionExists(row,column)) {
			throw new BoardException("ERROR! Position not on the board!");
		}
		return pieces[row][column];
	}
	
	//m�todo que retorna a matriz pieces na linha row  e coluna column
	public Piece piece (Position pos) {
		if(!positionExists(pos)) {
			throw new BoardException("ERROR! Position not on the board!");
		}
		return pieces[pos.getRow()][pos.getColumn()];
	}
	
	//m�todo para colocar a pe�a na posi��o do tabuleiro
	public void placePiece(Piece piece, Position pos) {
		//exception
		if(thereIsAPiece(pos)) {
			throw new BoardException("ERROR! There's already a piece at position "+pos);
		}
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	
	private boolean positionExists(int row, int column) {
		return row>=0 && row<rows && column>= 0 && column<columns;
	}
	
	public boolean positionExists(Position pos) {
		return positionExists(pos.getRow(), pos.getColumn());
	}
	
	// m�todo para testar se tem alguma pe�a nessa posi��o
	
	public boolean thereIsAPiece(Position pos) {
		//exce��o para checar se existe a posi��o no tabuleiro
		if(!positionExists(pos)) {
			throw new BoardException("ERROR! Position not on the board!");
		}
		return piece(pos)!=null;
	}
	
}
