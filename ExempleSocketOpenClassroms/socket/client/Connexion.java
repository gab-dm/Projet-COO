package socket.client;
import java.net.Socket;

public class Connexion implements Runnable {

	private Socket socket = null;
	public static Thread t2;

	public Connexion(Socket s){

		socket = s;
	}

	public void run() {

		t2 = new Thread(new Chat_ClientServeur(socket));
		t2.start();

	}

}