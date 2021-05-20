package model;

import java.util.ArrayList;
import java.util.List;

public class Fou extends AbstractPiece {
	
	public Fou(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Fou";
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean ret = false;
		if ((this.getX() != xFinal) || (this.getY() != yFinal)){
			if (Math.abs(this.getY()-yFinal)== Math.abs(this.getX()-xFinal)) {
					
				ret = true;
			}
		}
		return ret;
	}
	
	
}