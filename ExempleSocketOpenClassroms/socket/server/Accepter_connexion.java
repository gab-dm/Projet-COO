package socket.server;
import java.io.*;
import java.net.*;


public class Accepter_connexion implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;

	public Thread t1;
	public Accepter_connexion(ServerSocket ss){
		socketserver = ss;
	}

	public void run() {

		try {
			while(true){

				socket = socketserver.accept();
				System.out.println("Un zéro veut se connecter  ");


				String login = null;
				t1 = new Thread(new Chat_ClientServeur(socket, login ));
				t1.start();

			}
		} catch (IOException e) {

			System.err.println("Erreur serveur");
		}

	}
}