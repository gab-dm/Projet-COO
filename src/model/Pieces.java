package model;

import java.util.List;

public interface Pieces {
	
	public List<Coord> casesParcourues(int xFinal, int yFinal);
	void move (int _xFinal, int _yFinal) ;
	boolean isMoveOk (int _xFinal, int _yFinal);
	int getX ();
	int getY ();
	Couleur getCouleur ();
	
}
