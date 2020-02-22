package Utils;

import java.io.File;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Screen;
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

public final class SettingRes {
    public static int screenWidth;
    public static int screenHeigth;
    public static DoubleProperty bValue;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;
    private static Document doc;
    
    
    public static void firstSetup() 
    {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Settings");
            doc.appendChild(rootElement);

            // staff elements
            Element video = doc.createElement("Video_Settings");
            rootElement.appendChild(video);

            video.setAttribute("V_id", "1");
            
          
            video.setAttribute("V_X", String.valueOf((int)Screen.getPrimary().getBounds().getWidth()));
            video.setAttribute("V_Y", String.valueOf((int)Screen.getPrimary().getBounds().getHeight()));
            video.setAttribute("B_value", "0.5");
            video.setAttribute("T_id", "1");
            video.setAttribute("SCROLL_SPEED", "10");
            video.setAttribute("FullScreen", "1");

            //Sound Settings
            Element sound = doc.createElement("Sound_Settings");
            rootElement.appendChild(sound);

            sound.setAttribute("Master_Sound", "100");

            //Language Settings
            Element lang = doc.createElement("Language");
            rootElement.appendChild(lang);

            lang.setAttribute("Language", System.getProperty("user.language"));

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Setting2.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("Settings saved!");

      } 
      catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
      }
    }
    public static void initSettings()
    {
        try {
            File fXmlFile = new File("Setting2.xml");
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(fXmlFile); 
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize(); 
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
        }
        catch (Exception e) {
        firstSetup();
        }
        screenWidth = Integer.valueOf(((Element)doc.getElementsByTagName("Video_Settings").item(0)).getAttribute("V_X"));
        screenHeigth = Integer.valueOf(((Element)doc.getElementsByTagName("Video_Settings").item(0)).getAttribute("V_Y"));
        bValue = new SimpleDoubleProperty(Double.valueOf(((Element)doc.getElementsByTagName("Video_Settings").item(0)).getAttribute("B_value")));
    }
    public static int getInt(String element , String attribute)
    {
        return Integer.parseInt(((Element)doc.getElementsByTagName(element).item(0)).getAttribute(attribute));
    }
    public static double getDouble(String element , String attribute)
    {
        return Double.parseDouble(((Element)doc.getElementsByTagName(element).item(0)).getAttribute(attribute));
    }
          
}