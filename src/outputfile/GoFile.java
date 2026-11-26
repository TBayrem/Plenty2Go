package outputfile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GoFile {

	/** Absenderdaten stehen in absender.properties (nicht im Repository). */
	private static java.util.Properties cfg;

	private static String config(String key) {
		if (cfg == null) {
			cfg = new java.util.Properties();
			File f = new File(System.getProperty("absender.datei", "absender.properties"));
			try (java.io.InputStream in = new java.io.FileInputStream(f)) {
				cfg.load(new java.io.InputStreamReader(in, java.nio.charset.StandardCharsets.UTF_8));
			} catch (java.io.IOException e) {
				throw new IllegalStateException("absender.properties fehlt: Vorlage absender.properties.example kopieren", e);
			}
		}
		return cfg.getProperty(key, "");
	}

	
	private File gofile;

	private DocumentBuilder doc_builder;
	private Document doc;
	private Element main_node,
	                sub_node,
	                KDNR,
	                UserName,
	                AbsFirma,
	                AbsStrasse,
	                AbsLKZ,
	                AbsPLZ,
	                AbsOrt,
	                EmpfFirma,
	                EmpfName,
	                EmpfTelLand,
	                EmpfTel,
	                EmpfEmail,
	                EmpfStrasse,
	                EmpfHnr,
	                EmpfLKZ,
	                EmpfPLZ,
	                EmpfOrt,
	                AbholZeitVon,
	                AbholZeitBis,
	                AbholDatum,
	                KZSelbstAnl,
	                AnzahlPack,
	                Gewicht,
	                Inhalt,
	                Service,
	                BesAnwAbh,
	                BesAnw;
	private Transformer tr;
	
	
	
	public GoFile(String gofile) throws ParserConfigurationException, TransformerConfigurationException, TransformerFactoryConfigurationError {
		this.gofile = new File (gofile);
		this.doc_builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		this.doc = this.doc_builder.newDocument();
		this.main_node = this.doc.createElement("ABC");
		this.doc.appendChild((Node)this.main_node);
		this.sub_node = this.doc.createElement("GAImportRecord");
		this.main_node.appendChild((Node)this.sub_node);
		this.KDNR = this.doc.createElement("KDNR");
		this.UserName = this.doc.createElement("Username");
		this.AbsFirma = this.doc.createElement("AbsFirma");
		this.AbsStrasse = this.doc.createElement("AbsStrasse");
		this.AbsLKZ = this.doc.createElement("AbsLKZ");
		this.AbsPLZ = this.doc.createElement("AbsPLZ");
		this.AbsOrt = this.doc.createElement("AbsOrt");
		this.EmpfFirma = this.doc.createElement("EmpfFirma");
		this.EmpfName = this.doc.createElement("EmpfName");
		this.EmpfTelLand = this.doc.createElement("EmpfTelLand");
		this.EmpfTel = this.doc.createElement("EmpfTel");
		this.EmpfEmail = this.doc.createElement("EmpfEmail");
		this.EmpfStrasse = this.doc.createElement("EmpfStrasse");
		this.EmpfHnr = this.doc.createElement("EmpfHnr");
		this.EmpfLKZ = this.doc.createElement("EmpfLKZ");
		this.EmpfPLZ = this.doc.createElement("EmpfPLZ");
		this.EmpfOrt = this.doc.createElement("EmpfOrt");
		this.AbholZeitVon = this.doc.createElement("AbholZeitVon");
		this.AbholZeitBis = this.doc.createElement("AbholZeitBis");
		this.AbholDatum = this.doc.createElement("AbholDatum");
		this.KZSelbstAnl = this.doc.createElement("KZSelbstAnl");
		this.AnzahlPack = this.doc.createElement("AnzahlPack");
		this.Gewicht = this.doc.createElement("Gewicht");
		this.Inhalt = this.doc.createElement("Inhalt");
		this.Service = this.doc.createElement("Service");
		this.BesAnwAbh = this.doc.createElement("BesAnwAbh");
		this.BesAnw = this.doc.createElement("BesAnw");
		
		
		this.tr = TransformerFactory.newInstance().newTransformer();
	}
	
	public void writedatatofile(String[] data_tabel) throws TransformerException {
		
		
		this.KDNR.setTextContent(config("KDNR"));
		this.sub_node.appendChild(this.KDNR);
		this.UserName.setTextContent(config("Username"));
		this.sub_node.appendChild(this.UserName);
		this.AbsFirma.setTextContent(config("AbsFirma"));
		this.sub_node.appendChild(this.AbsFirma);
		this.AbsStrasse.setTextContent(config("AbsStrasse"));
		this.sub_node.appendChild(this.AbsStrasse);
		this.AbsLKZ.setTextContent(config("AbsLKZ"));
		this.sub_node.appendChild(AbsLKZ);
		this.AbsPLZ.setTextContent(config("AbsPLZ"));
		this.sub_node.appendChild(this.AbsPLZ);
		this.AbsOrt.setTextContent(config("AbsOrt"));
		this.sub_node.appendChild(this.AbsOrt);
		this.EmpfFirma.setTextContent(data_tabel[1]+" "+data_tabel[2]);
		this.sub_node.appendChild(this.EmpfFirma);
		this.EmpfName.setTextContent("#NBZ "+data_tabel[10]);
		this.sub_node.appendChild(this.EmpfName);
		this.EmpfTelLand.setTextContent("0");
		this.sub_node.appendChild(this.EmpfTelLand);
		this.EmpfTel.setTextContent("0"+ data_tabel[10]);
		this.sub_node.appendChild(this.EmpfTel);
		this.EmpfEmail.setTextContent(data_tabel[9]);
		this.sub_node.appendChild(this.EmpfEmail);
		this.EmpfStrasse.setTextContent(data_tabel[4]);
		this.sub_node.appendChild(this.EmpfStrasse);
		this.EmpfHnr.setTextContent(data_tabel[5]);
		this.sub_node.appendChild(this.EmpfHnr);
		this.EmpfLKZ.setTextContent(data_tabel[8]);
		this.sub_node.appendChild(this.EmpfLKZ);
		this.EmpfPLZ.setTextContent(data_tabel[6]);
		this.sub_node.appendChild(this.EmpfPLZ);
		this.EmpfOrt.setTextContent(data_tabel[7]);
		this.sub_node.appendChild(this.EmpfOrt);
		this.AbholZeitVon.setTextContent("18:00");
		this.sub_node.appendChild(this.AbholZeitVon);
		this.AbholZeitBis.setTextContent("19:00");
		this.sub_node.appendChild(this.AbholZeitBis);
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String abholdatum = formatter.format(date);
		this.AbholDatum.setTextContent(abholdatum);
		this.sub_node.appendChild(this.AbholDatum);
		this.KZSelbstAnl.setTextContent("0");
		this.sub_node.appendChild(this.KZSelbstAnl);
		this.AnzahlPack.setTextContent("1");
		this.sub_node.appendChild(this.AnzahlPack);
		this.Gewicht.setTextContent("1.40");
		this.sub_node.appendChild(this.Gewicht);
		this.Inhalt.setTextContent("Garnelen");
		this.sub_node.appendChild(this.Inhalt);
		if (this.EmpfLKZ.getTextContent().compareToIgnoreCase("DE")==0) {
		this.Service.setTextContent("0");
		}else {		
			this.Service.setTextContent("2");
		}
		this.sub_node.appendChild(this.Service);
		this.BesAnwAbh.setTextContent("ACHTUNG!!! WARM HALTEN");
		this.sub_node.appendChild(this.BesAnwAbh);
		this.BesAnw.setTextContent("TIERVERSAND #NBZ erlaubt");
		this.sub_node.appendChild(this.BesAnw);

		this.tr.setOutputProperty(OutputKeys.INDENT, "yes");
		this.tr.transform(new DOMSource(this.doc), new StreamResult(this.gofile));
	}

}
