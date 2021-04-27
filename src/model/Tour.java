package model;

public class Tour extends AbstractPiece {
	
	public Tour(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Tour";
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if ((this.getY()==yFinal) || (this.getX()==xFinal)) {
			ret = true;
		}
		return ret;
	}
}