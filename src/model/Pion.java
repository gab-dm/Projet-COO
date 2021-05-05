package model;

public class Pion extends AbstractPiece {
	
	Boolean firstMove;
	
	public Pion(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Pion";
		this.firstMove =true;
		
		
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if (this.couleur == Couleur.NOIR) {
			if (this.firstMove) {
				if ((this.getX()==xFinal) && (yFinal -this.getY()<=2)) {
					this.firstMove=false;
					ret=true;
				}
			}
			else {
				if ((this.getX()==xFinal) && (yFinal -this.getY()==1)) {		
					ret=true;
				}
			}
		}
		else{
			if (this.firstMove) {
				if ((this.getX()==xFinal) && (this.getY()-yFinal <=2)) {
					this.firstMove=false;
					ret=true;
				}
			}
			else {
				if ((this.getX()==xFinal) && (this.getY()-yFinal ==1)) {
					
					ret=true;
				}
			}
		}
		
		
		return ret;
	}
}