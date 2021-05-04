package model;

public class Roi extends AbstractPiece {
	
	public Roi(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Roi";
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if ((Math.abs(this.getY()-yFinal)<=1) && (Math.abs(this.getX()-xFinal)<=1)) {
			ret = true;
		}
		return ret;
	}
}