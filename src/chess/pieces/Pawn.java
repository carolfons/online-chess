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
		
//		MOVIMENTOS PARA A PE�A BRANCA		
		if(getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
//			se a linha acima do pe�o branco existir e tiver vazia, ent�o ele faz o movimento
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			/*
			 * se a linha acima do pe�o branco existir, tiver vazia,
			 *  n�o tiver pe�a na posi��o p2 e  a 
			 * contagem de movimentos do pe�o for zero (primeiro movimento da pe�a)
			 *  ent�o ele faz o movimento
			 * */
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) 
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
					getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
//			l�gica de ataque do pe�o (diagonal)		
			p.setValues(position.getRow() - 1, position.getColumn()-1);
			
			/*
			 * se a linha na diagonal acima do pe�o branco existir
			 * e tiver um oponente, ent�o ele faz o movimento
			 * */			 
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
//			l�gica de ataque do pe�o (na outra diagonal)		
			p.setValues(position.getRow() - 1, position.getColumn()+1);
			
			/*se a linha na diagonal acima do pe�o branco existir
			 * e tiver um oponente, ent�o ele faz o movimento
			 * */			 
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
			
//			MOVIMENTOS PARA A PE�A PRETA
			
			else {
					p.setValues(position.getRow() + 1, position.getColumn());
//					se a linha acima do pe�o branco existir e tiver vazia, ent�o ele faz o movimento
					if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					
					p.setValues(position.getRow() + 2, position.getColumn());
					Position p2 = new Position(position.getRow() + 1, position.getColumn());
					/*
					 * se a linha abaixo do pe�o preto existir, tiver vazia,
					 *  n�o tiver pe�a na posi��o p2 e  a 
					 * contagem de movimentos do pe�o for zero (primeiro movimento da pe�a)
					 *  ent�o ele faz o movimento
					 * */
					if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) 
							&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && 
							getMoveCount() == 0) {
						mat[p.getRow()][p.getColumn()] = true;
					}
//					l�gica de ataque do pe�o (diagonal)		
					p.setValues(position.getRow() + 1, position.getColumn()-1);
					
					/*
					 * se a linha na diagonal abaixo do pe�o preto existir
					 * e tiver um oponente, ent�o ele faz o movimento
					 * */			 
					if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
						mat[p.getRow()][p.getColumn()] = true;
					}
					
//					l�gica de ataque do pe�o (na outra diagonal)		
					p.setValues(position.getRow() + 1, position.getColumn()+1);
					
					/*se a linha na diagonal abaixo do pe�o preto existir
					 * e tiver um oponente, ent�o ele faz o movimento
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

