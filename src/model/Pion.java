package model;

public class Pion extends AbstractPiece {
	
	Boolean firstMove = true;
	
	public Pion(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Pion";
		
		
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if (this.couleur == Couleur.BLANC) {
			if (this.firstMove) {
				if ((this.getX()==yFinal) && (xFinal -this.getX()<=2)) {
					this.firstMove=false;
					ret=true;
				}
			}
			else {
				if ((this.getX()==yFinal) && (xFinal -this.getX()==1)) {		
					ret=true;
				}
			}
		}
		else{
			if (this.firstMove) {
				if ((this.getX()==yFinal) && (this.getX()-xFinal <=2)) {
					this.firstMove=false;
					ret=true;
				}
			}
			else {
				if ((this.getX()==yFinal) && (this.getX()-xFinal ==1)) {
					
					ret=true;
				}
			}
		}
		
		
		return ret;
	}
}