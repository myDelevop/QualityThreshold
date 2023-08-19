import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

import javax.swing.*;

/**
 * Classe QT che estende JApplet
 */
public class QT extends JApplet {
	/** Dichiarazione stream con le richieste del cliente */
	private ObjectOutputStream out;
	private ObjectInputStream in;

	/**
	 * Inner-class TabbedPane che divide la schermata in due Panel
	 */
	private class TabbedPane extends JPanel {
		/** Dichiarazione dei due panel */
		private JPanelCluster panelDB;
		private JPanelCluster panelFile;

		/**
		 * Inner-class JPanelCluster che contiene le dichiarazioni degli
		 * elementi dei panel
		 */
		private class JPanelCluster extends JPanel {

			/** TextField per l'inserimento del nome tabella */
			private JTextField tableText = new JTextField(20);

			/** TextField per l'inserimento del radius */
			private JTextField parameterText = new JTextField(10);

			/** TextField per l'inserimento della distanza */
			private JTextField distanceText = new JTextField(10);

			/** Corpo centrale per la stampa dei cluster */
			private JTextArea clusterOutput = new JTextArea(30, 60);

			/** Bottone di esecuzione per il panelDB */
			private JButton executeButton = new JButton("CLUSTER");

			/** TextField per l'inserimento del nome file */
			private JTextField fileText = new JTextField(20);

			/** Bottone di esecuzione per il panelFile */
			private JButton fileButton = new JButton("FILE");

			/**
			 * Costruttore JPanelCluster
			 *
			 * Aggiunge un listener ai bottoni del panelDB, e del panelFile
			 */
			JPanelCluster(String buttonName, java.awt.event.ActionListener a) {
				executeButton.addActionListener(a);
				fileButton.addActionListener(a);
			}
		}

		/**
		 * Costruttore TabbedPane
		 * 
		 * Divide la schermata in due pannelli(PanelDB,PanelFile) Dichiarandone
		 * gli elementi di appartenenza e le varie posizioni.
		 * 
		 * 
		 */
		TabbedPane() {
			super(new GridLayout(1, 1));
			JTabbedPane tabbedPane = new JTabbedPane();

			// copy img in src Directory and bin directory
			java.net.URL imgURL = getClass().getResource("/img/db.jpg");
			ImageIcon iconDB = new ImageIcon(imgURL);

			panelDB = new JPanelCluster("MINE",
					new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								clusteringFromDBAction();
							} catch (SocketException e1) {
								System.out.println(e1);

							} catch (FileNotFoundException e1) {
								System.out.println(e1);

							} catch (IOException e1) {
								System.out.println(e1);

							} catch (ClassNotFoundException e1) {
								System.out.println(e1);

							} catch (ServerException e1) {
								System.out.println(e1);
							}

						}
					});

			panelDB.setLayout(new BorderLayout(0, 0));

			JPanel upPanel = new JPanel();
			panelDB.add(upPanel, BorderLayout.NORTH);

			JLabel tableLabel = new JLabel("Table:");
			upPanel.add(tableLabel);

			upPanel.add(panelDB.tableText);
			panelDB.tableText.setColumns(10);

			JLabel radiusLabel = new JLabel("Radius:");
			upPanel.add(radiusLabel);

			upPanel.add(panelDB.parameterText);
			panelDB.parameterText.setColumns(10);

			JLabel distanceLabel = new JLabel("Distance:");
			upPanel.add(distanceLabel);

			upPanel.add(panelDB.distanceText);
			panelDB.distanceText.setColumns(10);

			JLabel fileLabel = new JLabel("backup file name:");
			upPanel.add(fileLabel);

			upPanel.add(panelDB.fileText);
			panelDB.fileText.setColumns(10);

			JPanel centralPanel = new JPanel();
			panelDB.add(centralPanel, BorderLayout.CENTER);

			panelDB.clusterOutput.setEditable(false);

			JScrollPane scrollingArea = new JScrollPane(panelDB.clusterOutput);
			centralPanel.add(scrollingArea);

			JPanel downPanel = new JPanel();
			panelDB.add(downPanel, BorderLayout.SOUTH);

			downPanel.add(panelDB.executeButton);

			tabbedPane.addTab("DB", iconDB, panelDB, "Does nothing");

			imgURL = getClass().getResource("/img/file.jpg");
			ImageIcon iconFile = new ImageIcon(imgURL);

			panelFile = new JPanelCluster("STORE FROM FILE",
					new java.awt.event.ActionListener() {

						public void actionPerformed(ActionEvent e) {
							try {
								clusteringFromFileAction();
							} catch (SocketException e1) {
								System.out.println(e1);

							} catch (FileNotFoundException e1) {
								System.out.println(e1);

							} catch (IOException e1) {
								System.out.println(e1);

							} catch (ClassNotFoundException e1) {
								System.out.println(e1);

							}

						}
					});

			panelFile.setLayout(new BorderLayout(0, 0));

			JPanel upfilePanel = new JPanel();
			panelFile.add(upfilePanel, BorderLayout.NORTH);

			JLabel file2Label = new JLabel("File:");
			upfilePanel.add(file2Label);

			upfilePanel.add(panelFile.fileText);
			panelFile.fileText.setColumns(10);

			JPanel centralfilePanel = new JPanel();
			panelFile.add(centralfilePanel, BorderLayout.CENTER);

			panelFile.clusterOutput.setEditable(false);

			JScrollPane scrollingfileArea = new JScrollPane(
					panelFile.clusterOutput);
			centralfilePanel.add(scrollingfileArea);

			JPanel downfilePanel = new JPanel();
			panelFile.add(downfilePanel, BorderLayout.SOUTH);

			downfilePanel.add(panelFile.fileButton);

			tabbedPane.addTab("FILE", iconFile, panelFile, "Does nothing");
			// Add the tabbed pane to this panel.
			add(tabbedPane);
			// The following line enables to use scrolling tabs.
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}

		/**
		 * Clustering from db action.
		 * 
		 * Dopo l'inserimento di nome tabella,radius,nome file,distance,
		 * clusterizza(nel lato PanelDB) e stampa nel corpo centrale.
		 *
		 * @throws SocketException
		 *             the socket exception
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 * @throws ClassNotFoundException
		 *             the class not found exception
		 * @throws ServerException
		 *             the server exception
		 */
		private void clusteringFromDBAction() throws SocketException,
				IOException, ClassNotFoundException, ServerException {

			// StoreTableFromDB
			try {
				out.flush();

				out.writeObject(0);
				panelDB.clusterOutput.setText("");
				String tabName = panelDB.tableText.getText();
				out.writeObject(tabName);

				String result = (String) in.readObject();

				if (!result.equals("OK"))
					throw new ServerException(result);

				String datstampa = in.readObject().toString();
				panelDB.clusterOutput.append(datstampa);

				// ClusteringFromDbTable
				out.writeObject(1);

				Double r = 1.0;
				String distance = new String();

				r = new Double(panelDB.parameterText.getText()).doubleValue();
				out.writeDouble(r);
				distance = panelDB.distanceText.getText();
				out.writeObject(distance);

				result = (String) in.readObject();

				if (result.equals("OK")) {
					String numc = (String) in.readObject();
					String clusters = (String) in.readObject();

					panelDB.clusterOutput.append("Number of Clusters:" + numc
							+ "\n");
					panelDB.clusterOutput.append(clusters);

				} else
					throw new ServerException(result);

				// StoreClusterInFile
				out.writeObject(2);
				panelDB.clusterOutput.append("Backuping file...\n");
				String fileName = panelDB.fileText.getText() + ".dmp";
				out.writeObject(fileName);

				result = (String) in.readObject();
				if (!result.equals("OK"))
					throw new ServerException(result);
				panelDB.clusterOutput.append("Saving centroids in " + fileName
						+ "\n");

			} catch (ServerException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}

		}

		/**
		 * Clustering from file action.
		 * 
		 * 
		 * Dopo l'inserimento del nome file, si caricano i centroidi e li si
		 * stampano nel corpo centrale dell'applicazione.
		 *
		 * @throws SocketException
		 *             the socket exception
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 * @throws ClassNotFoundException
		 *             the class not found exception
		 */
		private void clusteringFromFileAction() throws SocketException,
				IOException, ClassNotFoundException {

			out.flush();

			out.writeObject(3);
			String filename = panelFile.fileText.getText();
			out.writeObject(filename);

			try {
				panelFile.clusterOutput.setText("");
				String result = (String) in.readObject();

				if (!result.equals("OK"))
					throw new ServerException(result);

				panelFile.clusterOutput.append(in.readObject().toString());

			} catch (ServerException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
			}

		}
	}

	/**
	 * Inizializza Applet.
	 * 
	 */
	public void init() {

		TabbedPane tab = new TabbedPane();
		getContentPane().setLayout(new GridLayout(1, 1));
		getContentPane().add(tab);

		resize(800, 600);

		String ip = this.getParameter("ServerIP");

		int port = new Integer(this.getParameter("Port")).intValue();
		// int port=8080;
		try {
			InetAddress addr = InetAddress.getByName(ip); // ip
			System.out.println("addr = " + addr);
			Socket socket = new Socket(addr, port); // Port
			System.out.println(socket);

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.exit(0);
		}

	}
}
