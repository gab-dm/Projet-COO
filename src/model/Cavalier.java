package model;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends AbstractPiece {
	
	
	
	public Cavalier(Couleur _couleur, Coord _coordonnee ) {
		super(_couleur, _coordonnee);
		this.name = "Cavalier";
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveOk(int xFinal, int yFinal) {
		
		 
		boolean ret = false;
		if ((this.getX() != xFinal) || (this.getY() != yFinal)){
			
		
		
			if (((Math.abs(this.getY()-yFinal)==2)&&(Math.abs(this.getX()-xFinal)==1)) || ((Math.abs(this.getY()-yFinal)==1)&&(Math.abs(this.getX()-xFinal)==2))) {
				ret = true;
			}
		}
		
		return ret;
	}
	@Override
	public List<Coord> casesParcourues(int xFinal, int yFinal) {
		List<Coord> casesParcourues = new ArrayList<Coord>();
		Coord coo = new Coord(xFinal, yFinal);
		casesParcourues.add(coo);
		
		return casesParcourues;
	}
	
	
	
	
	
	
	
	
}