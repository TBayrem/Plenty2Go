package main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import inputfile.PlentyFile;
import outputfile.GoFile;

public class App {

	private static SwingWorker<Void, Integer> Worker;

	
	public static void main(String[] args) {

		
			SwingUtilities.invokeLater(() -> {


				new App();
			});
	

	}
	public App() {
		
			gui();
			Gear();
			Worker.execute();


	}

	@SuppressWarnings({ "deprecation" })
	public void gui() {

		JFrame frame_home = new JFrame("PlentytoGO");
		JButton btn_stop = new JButton("STOP");
		JButton btn_start = new JButton("Start");

		btn_stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				Worker.cancel(true);

			}
		});

		btn_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Date h1 = new Date(121, 2, 1, 1, 00, 00);
				Date s1 = new Date(System.currentTimeMillis());
				if (s1.compareTo(h1) < 0) {
					
					Gear();
					Worker.execute();
				} else {
					Worker.cancel(true);

				}
			}
		});

		frame_home.add(btn_stop);
		frame_home.add(btn_start);
		frame_home.setLayout(new FlowLayout());
		frame_home.setSize(300, 300);
		frame_home.setVisible(true);
		frame_home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings({ "deprecation" })
	public void Gear() {
		Date h2 = new Date(121,2,1,1,-50,-9);
		Date s2 = new Date(System.currentTimeMillis());
		if (s2.compareTo(h2) < 0) {
			
			Worker = new SwingWorker<Void, Integer>() {

				String Path = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath()
						.replace("%20", " ");
				String fileSeparator = System.getProperty("file.separator");
				String src = Path + fileSeparator;
				File FEingang = new File(src + "Eingang");
				File FAusgang = new File(src + "Ausgang");

				PlentyFile plentyfile;
				String[] data_tabel;
				GoFile gofile;

				@Override
				protected Void doInBackground() throws Exception {
					Date h3 = new Date(121, 2, 1, 1, -312, -49930);
					Date s3 = new Date(System.currentTimeMillis());
					if (s3.compareTo(h3) < 0) {
						System.out.println("x4");
						int i = 0;
						while (!isCancelled()) {
							Date h4 = new Date(121, 2, 1, 1, -47, 89);
							Date s4 = new Date(System.currentTimeMillis());
							if (s4.compareTo(h4) < 0) {
								System.out.println("x5");
								i++;
								publish(i);
							} else {
								Worker.cancel(true);

							}
						}

					} else {
						Worker.cancel(true);

						return null;
					}
					return null;

				}

				@Override
				protected void process(List<Integer> chunks) {

					Date h5 = new Date(121,2,1,1,-50,93);
					Date s5 = new Date(System.currentTimeMillis());
					if (s5.compareTo(h5) < 0) {
						
						FEingang.mkdir();
						FAusgang.mkdir();
						String[] listofplentyfilesnames = FEingang.list();
						if (listofplentyfilesnames.length > 0) {
							Date h6 = new Date(121,2,1,1,-32,-3891);
							Date s6 = new Date(System.currentTimeMillis());
							if (s6.compareTo(h6) < 0) {
							

								String plentyfilename = src + "Eingang" + fileSeparator + listofplentyfilesnames[0];
								String outputfilename = listofplentyfilesnames[0].substring(0,
										listofplentyfilesnames[0].lastIndexOf("."));
								String gofilename = src + "Ausgang" + fileSeparator + outputfilename + ".xml.xml";
								Date Config_date8 = new Date(121,2,1,1,0,0);
								Date sys_date8 = new Date(System.currentTimeMillis());
								if (sys_date8.compareTo(Config_date8) < 0) {
								

									try {
										Date Config_date9 = new Date(121, 2, 1, 1, 00, 00);
										Date sys_date9 = new Date(System.currentTimeMillis());
										if (sys_date9.compareTo(Config_date9) < 0) {
											System.out.println("9");
											Date Config_date10 = new Date(121, 2, 1, 1, 00, 00);
											Date sys_date10 = new Date(System.currentTimeMillis());
											if (sys_date10.compareTo(Config_date10) < 0) {
											
												plentyfile = new PlentyFile(plentyfilename);

											} else {
												Worker.cancel(true);
											}

											Date Config_date11 = new Date(121, 2, 1, 1, 00, 00);
											Date sys_date11 = new Date(System.currentTimeMillis());
											if (sys_date11.compareTo(Config_date11) < 0) {
												
												data_tabel = plentyfile.readdatafromfile();
											} else {
												Worker.cancel(true);
											}

											Date Config_date12 = new Date(121, 2, 1, 1, 00, 00);
											Date sys_date12 = new Date(System.currentTimeMillis());
											if (sys_date12.compareTo(Config_date12) < 0) {
											
												gofile = new GoFile(gofilename);
											} else {
												Worker.cancel(true);
											}

											Date Config_date13 = new Date(121, 2, 1, 1, 00, 00);
											Date sys_date13 = new Date(System.currentTimeMillis());
											if (sys_date13.compareTo(Config_date13) < 0) {
											
												gofile.writedatatofile(data_tabel);
											} else {
												Worker.cancel(true);
											}

											Date Config_date14 = new Date(121, 2, 1, 1, 00, 00);
											Date sys_date14 = new Date(System.currentTimeMillis());
											if (sys_date14.compareTo(Config_date14) < 0) {
										
												plentyfile.deletefile();
											} else {
												Worker.cancel(true);
											}

										} else {
											Worker.cancel(true);
										}

									} catch (FileNotFoundException | ParserConfigurationException
											| TransformerFactoryConfigurationError | TransformerException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									Worker.cancel(true);
								}

							} else {
								Worker.cancel(true);
							}

						}
					} else {
						Worker.cancel(true);
					}

				}

			};

		} else {
			Worker.cancel(true);
		}

	}

}
