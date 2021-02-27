package inputfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlentyFile {

	private File plentyfile;
	private Scanner filescanner;

	public PlentyFile(String plentyfile) throws FileNotFoundException {

		this.plentyfile = new File(plentyfile);
		this.filescanner = new Scanner(this.plentyfile, "UTF-8");

	}

	public String[] readdatafromfile() {
		this.filescanner.nextLine();
		String[] data_tabel = this.filescanner.nextLine().split(";");
		this.filescanner.close();
		return data_tabel;
//		 = null;
//		String data_line ;
//		while (this.filescanner.hasNextLine()) {
//			this.filescanner.nextLine();
//			data_line = this.filescanner.nextLine();
//		}
//		
//		return data_tabel=data_line.split(";");
	}

	public void deletefile() {
		this.plentyfile.delete();
	}

}
