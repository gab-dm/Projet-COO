package model;

public interface Pieces {
	boolean move (int _xFinal, int _yFinal) ;
	boolean isMoveOk (int _xFinal, int _yFinal);
	int getX ();
	int getY ();
	Couleur getCouleur ();
	
}
