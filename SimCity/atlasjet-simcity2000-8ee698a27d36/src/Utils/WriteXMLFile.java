/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author anormal
 */

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public static void main(String argv[]) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Settings");
		doc.appendChild(rootElement);

		// staff elements
		Element video = doc.createElement("Video_Settings");
		rootElement.appendChild(video);
                
		video.setAttribute("V_id", "1");
                video.setAttribute("B_value", "0.5");
                video.setAttribute("T_id", "1");
                video.setAttribute("FullScreen", "1");
                 
                //Sound Settings
                Element sound = doc.createElement("Sound_Settings");
                rootElement.appendChild(sound);
                
                sound.setAttribute("Master_Sound", "100");
                
                //Language Settings
                Element lang = doc.createElement("Language");
                rootElement.appendChild(lang);
                
                lang.setAttribute("Language", "TR");

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("/home/anormal/Setting2.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("Settings saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}