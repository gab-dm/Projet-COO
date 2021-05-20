package model;

import java.util.LinkedList;
import java.util.List;

import model.Pieces;
import tools.ChessPiecesFactory;


public class Jeu {
	

	private List<Pieces> pieces = new LinkedList<Pieces>();
	Couleur couleurJeu;
	
	public Jeu( Couleur _couleur) {
		pieces = ChessPiecesFactory.newPieces(_couleur);
		couleurJeu = _couleur;
	}
	
	public Pieces findPiece ( int _coX, int _coY) {
		Pieces pieceRetournee = null;
		for(Pieces piece:this.pieces) {
			if((piece.getX()==_coX)&&(piece.getY()==_coY)) {
				pieceRetournee = piece;
			}
		}
		return pieceRetournee;
	}
	public void removePiece(Pieces piece) {
		this.pieces.remove(piece);
	}
	/**
	* @return une vue de la liste des pièces en cours
	* ne donnant que des accès en lecture sur des PieceIHM
	* (type piece + couleur + liste de coordonnées)
	*/
	public List<PieceIHM> getPiecesIHM(){
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();
		for (Pieces piece : pieces){
			boolean existe = false;
			// si le type de piece existe déjà dans la liste de PieceIHM
			// ajout des coordonnées de la pièce dans la liste de Coord de ce type
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
						existe = true;
						if (piece.getX() != -1){
								pieceIHM.add(new Coord(piece.getX(), piece.getY()));
						}
				}
			}
			// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
			if (! existe) {
				if (piece.getX() != -1){
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
							piece.getCouleur());
					newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
					list.add(newPieceIHM);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu(Couleur.BLANC);
		System.out.println(jeu.pieces);
		System.out.println(jeu.findPiece(4, 4));
	}
	
	
}
