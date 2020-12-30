package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading chess position.");
		}

	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece>captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: "+chessMatch.getTurn());
		if(!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: "+chessMatch.getCurrentPlayer());
		
			if(chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: "+ chessMatch.getCurrentPlayer());
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j],false);
			}

			System.out.println();
		}

		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][]possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}

			System.out.println();
		}
		

		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(piece);
			} else {

				System.out.print(piece);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
//		fILTRANDO DA LISTA TODAS AS PEÇAS BRANCAS		
		List<ChessPiece> white = captured.stream().filter(x-> x.getColor() == Color.WHITE).collect(Collectors.toList());
//		fILTRANDO DA LISTA TODAS AS PEÇAS BRANCAS		
		List<ChessPiece> black = captured.stream().filter(x-> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured Pieces: ");
		System.out.print("White: ");
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print("Black: ");
		System.out.println(Arrays.toString(black.toArray()));
		
	}
}
