import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
//import keyboardinput.Keyboard;
import data.Data;
import data.EmptyDatasetException;
import data.TypeDistanceException;
import data.RadiusException;
import database.InexistentTableException;
import database.NoValueException;
import mining.*;

// TODO: Auto-generated Javadoc
/**
 * Classe ServerOneClient.
 */
public class ServerOneClient extends Thread implements Serializable {

	/** Dichiaro socket */
	private Socket socket;

	/** Stream con le richieste dell'utente */
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/** Variabile qt di tipo QTMiner */
	private QTMiner qt;

	/**
	 * Costruttore di classe
	 * 
	 * Inizializza gli attributi socket,in e out. Avvia il thread.
	 *
	 * @param s
	 *            the s
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ServerOneClient(Socket s) throws IOException {
		socket = s;
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());

		start();// Avvia il Thread (chiama run())

	}

	/**
	 * Riscrive il meto della superclasse al fine di gestire le richieste del
	 * client
	 *
	 */
	@Override
	public void run() {
		try {
			while (true) {
				out.flush();
				String str = in.readObject().toString();

				// Gestisco richieste
				if (str.equals("0")) {
					String tabname = in.readObject().toString();
					try {
						Data data = new Data(tabname);

						out.writeObject("OK");

						out.writeObject(data.toString());

						str = in.readObject().toString();

						if (str.equals("1")) {

							Double radius = in.readDouble();

							if (radius <= 0)
								throw new RadiusException();

							String distancetype = in.readObject().toString();

							qt = new QTMiner(radius, distancetype);

							Integer numC = qt.compute(data);
							out.writeObject("OK");

							out.writeObject(numC.toString());
							out.writeObject(qt.getC().toString(data,
									distancetype));

							str = in.readObject().toString();
							if (str.equals("2")) {

								qt.salva(in.readObject().toString());

								out.writeObject("OK");
							}
						}

					} catch (InexistentTableException | EmptyDatasetException
							| ClusteringRadiusException | TypeDistanceException
							| IOException | NoValueException | RadiusException e) {
						out.writeObject(e.getMessage());
					}

				} else if (str.equals("3")) {
					String fileName = in.readObject().toString() + ".dmp";
					try {
						String str1 = new QTMiner(fileName).toString();
						out.writeObject("OK");

						out.writeObject(str1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						out.writeObject(e.getMessage());
					}
				}

			}
		} catch (Exception e) {
			try {
				out.writeObject(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}

	}

}
