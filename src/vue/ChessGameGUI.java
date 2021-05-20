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
	boolean SetUpDepart;
	int xAdjustment;
	int yAdjustment;
	List<Integer>DeplacementCoord = Arrays.asList(0,0,0,0);
	
	public ChessGameGUI ( String _name, ChessGameControlers _chessGameControler, Dimension _boardSize) {
		this.name = _name;
		this.chessGameControler=_chessGameControler;
		this.boardSize =_boardSize;
		this.SetUpDepart=true;
		
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
		 
	}
		  


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
//		for(int i = 0 ;i<64;i++) {
//			JPanel panel = (JPanel) chessBoard.getComponent(i);
//			for(i =0;i<5;i++) {
//				Component c= panel.getComponent(i);
//				if (c instanceof JLabel){
//					c.disable();
//				}
//				
//			}
//			
//		}
			
		System.out.println(chessGameControler.getMessage() + "\n");	

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;

		
		// création d'un tableau 2D avec les noms des pièces
		if (this.SetUpDepart) {
			System.out.println("ttata");
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

		  this.DeplacementCoord.set(0, (parentLocation.x-2)/87 ); 
		  
		  this.DeplacementCoord.set(1, (parentLocation.y-2)/87 ); 
		  
		  //System.out.print(this.CoordDeplacement.get(0) + " "+ this.CoordDeplacement.get(1) + " " + xAdjustment+ " " + yAdjustment +"\n");
		  

		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(chessPiece == null) return;
		 
		  
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JLabel){
			  
			  this.DeplacementCoord.set(2, (c.getParent().getLocation().x-2)/87 ); 
			  
			  this.DeplacementCoord.set(3, (c.getParent().getLocation().y-2)/87 );
		  
		  //System.out.print(this.DeplacementCoord + "\n");
			  boolean isDeplacementAutorise = chessGameControler.move(new Coord(this.DeplacementCoord.get(0),this.DeplacementCoord.get(1)), new Coord(this.DeplacementCoord.get(2),this.DeplacementCoord.get(3)));
			  System.out.print(isDeplacementAutorise + ""+this.DeplacementCoord+ "\n");
			  
		  }
		  else {
			 

			  Point parentLocation = c.getLocation();
			  
			  this.DeplacementCoord.set(2, (parentLocation.x-2)/87 ); 
			  
			  this.DeplacementCoord.set(3, (parentLocation.y-2)/87 );
			  boolean isDeplacementAutorise = chessGameControler.move(new Coord(this.DeplacementCoord.get(0),this.DeplacementCoord.get(1)), new Coord(this.DeplacementCoord.get(2),this.DeplacementCoord.get(3)));
			  System.out.print("toto");
			  System.out.print(isDeplacementAutorise + ""+this.DeplacementCoord+ "\n");
			  
		  }
		 
		 
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
	
}
