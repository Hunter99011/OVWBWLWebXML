package dom2ovwbwl1105;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

public class DOMQueryOVWBWL {

    public static void main(String[] args) {
        try {
            File inputFile = new File("hallgatok5.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);

            Node hallgatok = doc.getFirstChild();

            Node hallgat = doc.getElementsByTagName("hallgato").item(0);

            NamedNodeMap attr = hallgat.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            nodeAttr.setTextContent("02");

            NodeList list = doc.getElementsByTagName("hallgato");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if (eElement.getNodeName().equals("keresztnev")) {
                        if ("Piroska".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Olivia");
                        }
                    }

                    if ("vezeteknev".equals(eElement.getNodeName())) {
                        if ("Kiss".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Vigh");
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            System.out.println("--- Módosított fájl ---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
