import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe MultiServer.
 */
public class MultiServer {

	/** Inizializzo un porta PORT a 8080 */
	private int PORT = 8080;

	/**
	 * Istanzio un MultiServer
	 *
	 * @param port
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public MultiServer(int port) throws IOException {
		this.PORT = port;
		run();
	}

	/**
	 * Metodo Main e inizializzo un MultiServer
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		MultiServer multiServer = new MultiServer(8080);
	}

	/**
	 * Metodo Run
	 * 
	 * Avvia la connessione attraverso il socket, e al termine lo chiude
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void run() throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started");
		try {
			while (true) {
				// Si blocca finchè non si verifica una connessione:
				Socket socket = s.accept();
				try {
					new ServerOneClient(socket);
				} catch (IOException e) {
					// Se fallisce chiude il socket,
					// altrimenti il thread la chiuderà:
					socket.close();
				}
			}
		} finally {
			s.close();
		}

	}
}
