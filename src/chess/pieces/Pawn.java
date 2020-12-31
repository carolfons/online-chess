package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0,0);
		
//		MOVIMENTOS PARA A PEÇA BRANCA		
		if(getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
//			se a linha acima do peão branco existir e tiver vazia, então ele faz o movimento
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			/*
			 * se a linha acima do peão branco existir, tiver vazia,
			 *  não tiver peça na posição p2 e  a 
			 * contagem de movimentos do peão for zero (primeiro movimento da peça)
			 *  então ele faz o movimento
			 * */
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) 
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
					getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
//			lógica de ataque do peão (diagonal)		
			p.setValues(position.getRow() - 1, position.getColumn()-1);
			
			/*
			 * se a linha na diagonal acima do peão branco existir
			 * e tiver um oponente, então ele faz o movimento
			 * */			 
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
//			lógica de ataque do peão (na outra diagonal)		
			p.setValues(position.getRow() - 1, position.getColumn()+1);
			
			/*se a linha na diagonal acima do peão branco existir
			 * e tiver um oponente, então ele faz o movimento
			 * */			 
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
			
//			MOVIMENTOS PARA A PEÇA PRETA
			
			else {
					p.setValues(position.getRow() + 1, position.getColumn());
//					se a linha acima do peão branco existir e tiver vazia, então ele faz o movimento
					if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					
					p.setValues(position.getRow() + 2, position.getColumn());
					Position p2 = new Position(position.getRow() + 1, position.getColumn());
					/*
					 * se a linha abaixo do peão preto existir, tiver vazia,
					 *  não tiver peça na posição p2 e  a 
					 * contagem de movimentos do peão for zero (primeiro movimento da peça)
					 *  então ele faz o movimento
					 * */
					if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) 
							&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
							getMoveCount() == 0) {
						mat[p.getRow()][p.getColumn()] = true;
					}
//					lógica de ataque do peão (diagonal)		
					p.setValues(position.getRow() + 1, position.getColumn()-1);
					
					/*
					 * se a linha na diagonal abaixo do peão preto existir
					 * e tiver um oponente, então ele faz o movimento
					 * */			 
					if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					
//					lógica de ataque do peão (na outra diagonal)		
					p.setValues(position.getRow() + 1, position.getColumn()+1);
					
					/*se a linha na diagonal abaixo do peão preto existir
					 * e tiver um oponente, então ele faz o movimento
					 * */			 
					if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
			}
		
		return mat;
		
		}
		
		
	
	@Override
	public String toString() {
		return "P";
	}
}

