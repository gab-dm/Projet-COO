package vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;


import controler.ChessGameControlers;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;

@SuppressWarnings("deprecation")
public class ChessGameGUI extends JFrame implements MouseListener,MouseMotionListener, Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name; 
	ChessGameControlers chessGameControler;
	Dimension boardSize;
	
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	
	int xAdjustment;
	int yAdjustment;
	
	List<Integer> CoordDeplacement=Arrays.asList(0,0,0,0);
	
	
	public ChessGameGUI ( String _name, ChessGameControlers _chessGameControler, Dimension _boardSize) {
		this.name = _name;
		this.chessGameControler=_chessGameControler;
		this.boardSize =_boardSize;
		
		 //  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(this.boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		  //Add a chess board to the Layered Pane 
		 
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(8, 8) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		 
		for (int i = 0; i < 64; i++) {
		  JPanel square = new JPanel( new BorderLayout() );
		  chessBoard.add( square );
		 
		  int row = (i / 8) % 2;
		  if (row == 0)
		  square.setBackground( i % 2 == 0 ? Color.white : Color.pink );
		  else
		  square.setBackground( i % 2 == 0 ? Color.pink : Color.white );
		}
		
		 //Add a few pieces to the board
		 /*
		  JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Tour", Couleur.NOIR)));				  									
		  JPanel panel = (JPanel)chessBoard.getComponent(0);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Tour", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(7);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Cavalier", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(1);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Cavalier", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(6);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Fou", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(2);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Fou", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(5);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Reine", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(3);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Roi", Couleur.NOIR)));				  									
		  panel = (JPanel)chessBoard.getComponent(4);
		  panel.add(piece);
		  
		  for (int i=0; i<8;i++) {
			  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Pion", Couleur.NOIR)));				  									
			  panel = (JPanel)chessBoard.getComponent(8+i);
			  panel.add(piece);
		  }
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Tour", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(56);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Tour", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(63);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Cavalier", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(57);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Cavalier", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(62);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Fou", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(58);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Fou", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(61);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Reine", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(59);
		  panel.add(piece);
		  
		  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Roi", Couleur.BLANC)));				  									
		  panel = (JPanel)chessBoard.getComponent(60);
		  panel.add(piece);
		  
		  for (int i=0; i<8;i++) {
			  piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Pion", Couleur.BLANC)));				  									
			  panel = (JPanel)chessBoard.getComponent(48+i);
			  panel.add(piece);
		  } */
	}
		  


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
			
		System.out.println(chessGameControler.getMessage() + "\n");	

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;

		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			String  type = pieceIHM.getTypePiece();
			for(Coord coord : pieceIHM.getList()) {
				
			  JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(type, color)));				  									
			  JPanel panel = (JPanel)chessBoard.getComponent(coord.x+coord.y*8);
			  panel.add(piece);
			}	
		}
				
	}
			
	


	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		if (chessPiece == null) return;
		 chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
		 
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		  chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JPanel) 
		  return;
		 
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  this.CoordDeplacement.set(0, (parentLocation.x-2)/87 ); 
		  
		  this.CoordDeplacement.set(1, (parentLocation.y-2)/87 ); 
		  
		  System.out.print(this.CoordDeplacement.get(0) + " "+ this.CoordDeplacement.get(1) + " " + xAdjustment+ " " + yAdjustment +"\n");
		  
		  
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(chessPiece == null) return;
		 
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

		  
		  if (c instanceof JLabel){
		  Container parent = c.getParent();
		  Point parentLocation = c.getParent().getLocation();
		  
		  this.CoordDeplacement.set(2, (parentLocation.x-2)/87 ); 
		  
		  this.CoordDeplacement.set(3, (parentLocation.y-2)/87 );
		  
		  System.out.print(this.CoordDeplacement + "\n");
		  
		  parent.remove(0);
		  parent.add( chessPiece );
		  }
		  else {
		  Container parent = (Container)c;		  
		  Point parentLocation = c.getLocation();
		  
		  this.CoordDeplacement.set(2, (parentLocation.x-2)/87 ); 
		  
		  this.CoordDeplacement.set(3, (parentLocation.y-2)/87 );
		  
		  System.out.print(this.CoordDeplacement + "\n");
		  parent.add( chessPiece );
		  
		  
		  }
		 
		  chessPiece.setVisible(true);
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*public static void main(String[] args) {
		  JFrame frame = new ChessGameGUI();
		  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		  frame.pack();
		  frame.setResizable(true);
		  frame.setLocationRelativeTo( null );
		  frame.setVisible(true);
	}*/
	public void go() {

		System.out.print("\n Déplacement de 3,6 vers 3,4 = ");
		
		chessGameControler.move(new Coord(3,6), new Coord(3, 4));	// true
		
		// dans ce cas, update non appelé et pas d'affichage 
		// controleur empêche le move car pas le bon joueur
		System.out.print("\n Déplacement de 3,4 vers 3,3 = ");		
		chessGameControler.move(new Coord(3,4), new Coord(3, 6));	// false 

		System.out.print("\n Déplacement de 4,1 vers 4,3 = ");
		chessGameControler.move(new Coord(4, 1), new Coord(4, 3));	// true

		System.out.print("\n Déplacement de 3,4 vers 3,4 = ");
		chessGameControler.move(new Coord(3, 4), new Coord(3, 4));	// false

		System.out.print("\n Déplacement de 3,4 vers 4,3 = ");
		chessGameControler.move(new Coord(3, 4), new Coord(4, 3));	// true		
		
		System.out.print("\n Déplacement de 3,4 vers 3,3 = ");
		chessGameControler.move(new Coord(3, 4), new Coord(3, 3));	// true		

	}
}
