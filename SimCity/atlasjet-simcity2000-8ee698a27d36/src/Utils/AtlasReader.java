package Utils;

import Graphics.Image;
import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AtlasReader
{
    public static HashMap<String , Image> getImages()
    {
        HashMap<String , Image> map = new HashMap<>();
        javafx.scene.image.Image sprite = new javafx.scene.image.Image("file:Resources/Sprites/sprites.png");
        try {
            File fXmlFile = new File("Resources/Sprites/sprites.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(fXmlFile); 
            doc.getDocumentElement().normalize();
            NodeList temp = doc.getElementsByTagName("SubTexture");
            for (int i = 0; i < temp.getLength(); i++)
            {
                Element element = (Element)temp.item(i);
                int x = Integer.parseInt(element.getAttribute("x"));
                int y = Integer.parseInt(element.getAttribute("y"));
                int width = Integer.parseInt(element.getAttribute("width"));
                int height = Integer.parseInt(((Element)(temp.item(i))).getAttribute("height"));
                byte[] arr = new byte[width * height * 4];
                sprite.getPixelReader().getPixels(x, y, width, height, Image.format, arr, 0 , width * 4);
                Image img = new Image(width , height , arr );
                map.put(((Element)(temp.item(i))).getAttribute("name"), img);  
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }
}
