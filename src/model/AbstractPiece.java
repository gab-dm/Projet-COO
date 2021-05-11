package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public abstract	class AbstractPiece extends java.lang.Object implements Pieces {
	
	//String type;
	String name;
	Couleur couleur;
	Coord coordonnee;
	
	public AbstractPiece (Couleur _couleur, Coord _coordonnee) {
		this.coordonnee  = _coordonnee ;
		this.couleur = _couleur;
		//this.type= _type;
	}
	
	public boolean move (int _x, int _y) {
		boolean deplacement = isMoveOk(_x, _y);

		if (deplacement) {
			this.coordonnee.x = _x;
			this.coordonnee.y = _y;
		}
		return deplacement;
		
	}
	
	public abstract boolean isMoveOk (int _xFinal, int _yFinal) ;
	
	
	
	public int getX () {
		return this.coordonnee.x;
	}
	public int getY () {
		return this.coordonnee.y;
	}
	public Couleur getCouleur () {
		return this.couleur;
	}
	
	//public java.lang.String toString() {
	@Override 
	public String toString() {
		
		return this.name + " : "+ "ccord x : "+ getX()+ " coord y : "+getY();
	}
	
	//}
	/*public boolean capture() {
		boolean pieceCapturee = false;
		
	}*/
	public static void main(String[] args) {
		
		Scanner saisieUtilisateur = new Scanner(System.in); 
		Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
		System.out.println("Coo en X du deplacemnt :");
		int DeplacemntX = saisieUtilisateur.nextInt(); 
		System.out.println("Coo en Y du deplacemnt :"); 
		int DeplacemntY = saisieUtilisateur.nextInt();
		
		System.out.println(maTour.isMoveOk(DeplacemntX, DeplacemntY));
		
	}
	
	
}