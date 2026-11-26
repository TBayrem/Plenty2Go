package main;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import inputfile.PlentyFile;
import outputfile.GoFile;

/**
 * Wandelt Plentymarkets-Exportdateien (CSV, Semikolon) im Ordner "Eingang" in
 * XML-Importdateien fuer den Paketdienst im Ordner "Ausgang" um.
 * Verarbeitete Dateien werden nach "Eingang/verarbeitet" verschoben.
 */
public class App {

	private static final long POLL_MILLIS = 2000;

	private final File baseDir;
	private final JLabel status = new JLabel("Bereit");
	private SwingWorker<Void, String> worker;

	public static void main(String[] args) {
		File base = args.length > 0 ? new File(args[0]) : new File(System.getProperty("user.dir"));
		SwingUtilities.invokeLater(() -> new App(base));
	}

	public App(File baseDir) {
		this.baseDir = baseDir;
		new File(baseDir, "Eingang").mkdirs();
		new File(baseDir, "Ausgang").mkdirs();
		new File(baseDir, "Eingang/verarbeitet").mkdirs();
		buildGui();
		start();
	}

	private void buildGui() {
		JFrame frame = new JFrame("Plenty2Go");
		JButton stop = new JButton("Stopp");
		JButton start = new JButton("Start");
		stop.addActionListener(e -> stop());
		start.addActionListener(e -> start());
		frame.setLayout(new FlowLayout());
		frame.add(start);
		frame.add(stop);
		frame.add(status);
		frame.setSize(320, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void start() {
		if (worker != null && !worker.isDone()) {
			return;
		}
		worker = new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (!isCancelled()) {
					String msg = processOne();
					if (msg != null) {
						publish(msg);
					}
					Thread.sleep(POLL_MILLIS);
				}
				return null;
			}

			@Override
			protected void process(List<String> chunks) {
				status.setText(chunks.get(chunks.size() - 1));
			}
		};
		status.setText("Laeuft ...");
		worker.execute();
	}

	private void stop() {
		if (worker != null) {
			worker.cancel(true);
		}
		status.setText("Angehalten");
	}

	/** Verarbeitet die erste Datei im Eingang. Gibt eine Statusmeldung oder null zurueck. */
	String processOne() {
		File in = new File(baseDir, "Eingang");
		File[] files = in.listFiles(f -> f.isFile() && f.getName().toLowerCase().endsWith(".csv"));
		if (files == null || files.length == 0) {
			return null;
		}
		File src = files[0];
		String name = src.getName().substring(0, src.getName().lastIndexOf('.'));
		File out = new File(new File(baseDir, "Ausgang"), name + ".xml");
		try {
			PlentyFile plenty = new PlentyFile(src.getPath());
			String[] data = plenty.readdatafromfile();
			new GoFile(out.getPath()).writedatatofile(data);
			Path target = new File(new File(in, "verarbeitet"), src.getName()).toPath();
			Files.move(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
			return "Umgewandelt: " + src.getName();
		} catch (Exception e) {
			// fehlerhafte Datei beiseitelegen, damit sie nicht endlos neu versucht wird
			try {
				Files.move(src.toPath(), new File(new File(in, "verarbeitet"), src.getName() + ".fehler").toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ignored) {
			}
			return "Fehler bei " + src.getName() + ": " + e.getMessage();
		}
	}
}
