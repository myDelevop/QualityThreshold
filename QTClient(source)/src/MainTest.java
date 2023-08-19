import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import keyboardinput.Keyboard;

public class MainTest implements Serializable {

	/**
	 * @param args
	 */
	private ObjectOutputStream out;
	private ObjectInputStream in; // stream con richieste del client

	public MainTest(String ip, int port) throws IOException {
		InetAddress addr = InetAddress.getByName(ip); // ip
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, port); // Port
		System.out.println(socket);

		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		; // stream con richieste del client
	}

	private int menu() {
		int answer;

		do {
			System.out.println("(1) Load clusters from file");
			System.out.println("(2) Load data from db");
			System.out.print("(1/2):");
			answer = Keyboard.readInt();
		} while (answer <= 0 || answer > 2);
		return answer;

	}

	private String clusteringFromFile() throws SocketException,
			ServerException, IOException, ClassNotFoundException {
		out.writeObject(3);

		System.out.print("File Name:");
		String fileName = Keyboard.readString();

		out.writeObject(fileName);

		String centroids = (String) in.readObject();
		/*
		 * double r=1.0; do{ System.out.print("Radius:");
		 * r=Keyboard.readDouble(); } while(r<=0); out.writeObject(r);
		 */
		String result = (String) in.readObject();
		if (result.equals("OK"))
			return centroids;
		else
			throw new ServerException(result);

	}

	private void storeTableFromDb() throws SocketException, ServerException,
			IOException, ClassNotFoundException {
		out.writeObject(0);

		System.out.print("Table name:");
		String tabName = Keyboard.readString();
		out.writeObject(tabName);
		String datstampa = in.readObject().toString();
		System.out.println(datstampa);

		String result = (String) in.readObject();
		if (!result.equals("OK"))
			throw new ServerException(result);

	}

	private String clusteringFromDbTable() throws SocketException,
			ServerException, IOException, ClassNotFoundException {
		out.writeObject(1);

		Double r = 1.0;
		String distance = new String();
		do {
			System.out.print("Radius:");
			r = Keyboard.readDouble();
		} while (r <= 0);
		out.writeDouble(r);
		do {
			System.out.print("Distance:");
			distance = Keyboard.readString();
		} while (distance.compareToIgnoreCase("edit") != 0
				&& distance.compareToIgnoreCase("euclidea") != 0);
		out.writeObject(distance);
		String result = (String) in.readObject();
		if (result.equals("OK")) {
			System.out.println("Number of Clusters:" + in.readObject());
			return (String) in.readObject();
		} else
			throw new ServerException(result);

	}

	private void storeClusterInFile() throws SocketException, ServerException,
			IOException, ClassNotFoundException {
		out.writeObject(2);
		System.out.print("Backup file name:");
		String fileName = Keyboard.readString() + ".dmp";
		out.writeObject(fileName);

		String result = (String) in.readObject();
		if (!result.equals("OK"))
			throw new ServerException(result);
		System.out.println("Saving clusters in " + fileName);
	}

	public static void main(String[] args) {
		String ip = args[0];
		// String ip = "127.0.0.1";
		int port = new Integer(args[1]).intValue();
		// int port = 8080;
		MainTest main = null;
		try {
			main = new MainTest(ip, port);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

		do {

			int menuAnswer = main.menu();
			switch (menuAnswer) {
			case 1:
				try {
					String kmeans = main.clusteringFromFile();
					System.out.println(kmeans);
				} catch (SocketException e) {
					System.out.println(e);
					return;
				} catch (FileNotFoundException e) {
					System.out.println(e);
					return;
				} catch (IOException e) {
					System.out.println(e);
					return;
				} catch (ClassNotFoundException e) {
					System.out.println(e);
					return;
				} catch (ServerException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2: // learning from db

				char answer = 'y';// itera per learning al variare di k
				do {

					while (true) {
						try {
							main.storeTableFromDb();
							break; // esce fuori dal while
						}

						catch (SocketException e) {
							System.out.println(e);
							return;
						} catch (FileNotFoundException e) {
							System.out.println(e);
							return;

						} catch (IOException e) {
							System.out.println(e);
							return;
						} catch (ClassNotFoundException e) {
							System.out.println(e);
							return;
						} catch (ServerException e) {
							System.out.println(e.getMessage());
						}
					} // end while [viene fuori dal while con un db (in
						// alternativa il programma termina)

					try {
						String clusterSet = main.clusteringFromDbTable();
						System.out.println(clusterSet);

						main.storeClusterInFile();
					} catch (SocketException e) {
						System.out.println(e);
						return;
					} catch (FileNotFoundException e) {
						System.out.println(e);
						return;
					} catch (ClassNotFoundException e) {
						System.out.println(e);
						return;
					} catch (IOException e) {
						System.out.println(e);
						return;
					} catch (ServerException e) {
						System.out.println(e.getMessage());
					}
					System.out.print("Would you repeat?(y/n)");
					answer = Keyboard.readChar();
				} while (Character.toLowerCase(answer) == 'y');
				break; // fine case 2

			default:
				System.out.println("Invalid option!");
			}

			System.out
					.print("would you choose a new operation from menu?(y/n)");
			if (Keyboard.readChar() != 'y')
				break;
		} while (true);
	}
}
