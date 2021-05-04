package model;

public class Dame extends AbstractPiece {
	
	public Dame(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Dame";
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if ((Math.abs(this.getY()-yFinal)== Math.abs(this.getX()-xFinal)) || (this.getY()==yFinal) || (this.getX()==xFinal)) {
			ret = true;
		}
		return ret;
	}
}