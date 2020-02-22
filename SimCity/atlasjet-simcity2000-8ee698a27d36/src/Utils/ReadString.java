/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author anormal
 */
public class ReadString {
    static Map<Integer,String> words = null;
    
    
    public static void getXML() {
        words = new HashMap();
        try {
            System.out.println("Reading File");
            
            File fXmlFile = new File("./ReadString.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile); 
            doc.getDocumentElement().normalize(); 


            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            //System.out.println("----------------------------");
            //System.out.println("dede");
            
            
            NodeList nList = doc.getElementsByTagName("String");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode; 
                    //System.out.println("Word id : " + eElement.getAttribute("id"));
                    //System.out.println("Word    : " + eElement.getAttribute("word"));
                    words.put(new Integer(eElement.getAttribute("id")),eElement.getAttribute("word"));

                }
            }
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getString(int x){
        if(words == null){
            getXML();
        }
        return words.get(x);
    }
}
