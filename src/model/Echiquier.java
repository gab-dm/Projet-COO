package model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import model.Jeu;
import model.PieceIHM;


public class Echiquier implements BoardGames {
	
	Jeu jeuNoir;
	Jeu jeuBlanc;
	Jeu jeuCourant;
	
	
	public Echiquier() {
		
		jeuNoir = new Jeu(Couleur.NOIR);
		jeuBlanc = new Jeu(Couleur.BLANC);
		jeuCourant = jeuBlanc;
		
		
		
	}
	
	public boolean move (int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		boolean isThisMoveOk = this.isMoveOk(xInit, yInit, xFinal, yFinal);
		if (isThisMoveOk) {
			Pieces pieceABouger = jeuCourant.findPiece(xInit, yInit);
			ret =pieceABouger.move(xFinal, yFinal);
		}
		return ret;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = true;
		
		if(xFinal <0 ||xFinal>7 || yFinal <0 ||yFinal>7) {
			ret =false;
		}
		
		
		return ret ;
		
	}
	public String toString() {
		
		return "Blancs : "+this.jeuBlanc.toString() +"\n"+
		"Noirs : "+this.jeuNoir.toString();
		
	}
	public void switchUser() {
		if( this.getColorCurrentPlayer()==Couleur.NOIR) {
			
			this.jeuNoir=this.jeuCourant;
			this.jeuCourant=this.jeuBlanc;
		}
		else {
			this.jeuBlanc=this.jeuCourant;
			this.jeuCourant=this.jeuNoir;
		}
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		
		return this.jeuCourant.couleurJeu;
				
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		
		Couleur ret = null;
		Pieces piece = this.jeuBlanc.findPiece(x, y);
		if (piece !=null) {
			ret =piece.getCouleur();
		}
		piece = this.jeuNoir.findPiece(x, y);
		if (piece !=null) {
			ret =piece.getCouleur();
		}
		
		return ret;
	}
	
	public List<PieceIHM> getPieceIHM () {
		List<PieceIHM> listPiecesIHM = this.jeuBlanc.getPiecesIHM();
		listPiecesIHM.addAll(this.jeuNoir.getPiecesIHM());
		return listPiecesIHM;
	
		
		
	}

}
