package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
//	construtor
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		initialSetup();
	}
	
// 	getters 
	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
	}
// - - - - - - - end - - - - - - - - -	
	
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
				
			}		
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source,target);
		
//		testando se o jogador se colocou em xeque
		if(testCheck(currentPlayer)) {
			undoMove(source,target,capturedPiece);
			throw new ChessException("Pay Attention! You can't put yourself in check");
		}
		
//		testando se o jogador fez o movimento de xeque		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
//		testando se o jogador fez o movimento de xequemate	
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		return (ChessPiece)capturedPiece;
			
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
//		contando os movimentos das peças
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		/*
		 * caso capture uma peça, essa peça é removida do 
		 * tabuleiro e adicionado na lista de peças capturadas
		 * */
	 		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;		
	}
//	método para desfazer o movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position" );
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours!");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no moves for the chosen piece");
		}
		
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The Chosen Piece can't move to target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK: Color.WHITE;
	}
	
//	Método para inverter as cores, caso seja branca->preta, caso seja preta->branca
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
//	Método para localizar o rei da cor indicada
	private  ChessPiece king(Color color) {
//		expressão lambda para filtrar uma lista
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
//		percorrendo a lista e retorna caso a peça seja o rei
		for (Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}	
		throw new IllegalStateException("There is no "+color+" King on the board!");		
	}
	
//	Método para testar se o rei de uma determinada cor está em xeque
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
//		pegando a lista de peças do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true; // rei em xeque
			}
		}
		return false;
	}
	
//	Método para testar se o rei de uma determinada cor está em xeque mate
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
	//	pegando todas as peças da cor passada na função
		List<Piece> list = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
	//	Se existir nessa lista um movimento que tira do xeque, retorna, caso não tenha
	//	movimentos então -> xequemate
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
		//	percorrendo as linhas da matriz
			for (int i = 0; i < board.getRows(); i++) {
			//	percorrendo as colunas da matriz
				for (int j = 0; j <board.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source,target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
//		coloca a peça no tabuleiro
		board.placePiece(piece, new ChessPosition(column,row).toPosition());
//		coloca a peça na lista do tabuleiro
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		
		placeNewPiece('h',7,new Rook(board,Color.WHITE));
		placeNewPiece('d',1,new Rook(board,Color.WHITE));
		placeNewPiece('e',1,new King(board,Color.WHITE));
		
		placeNewPiece('b',8,new Rook(board,Color.BLACK));
		placeNewPiece('a',8,new King(board,Color.BLACK));
	}
	
}
