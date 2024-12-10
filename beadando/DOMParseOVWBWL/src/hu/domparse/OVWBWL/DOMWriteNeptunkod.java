package hu.domparse.OVWBWL;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteNeptunkod {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML dokumentum beolvasása
            Document document = builder.parse(new File("C:\\KMgit\\OVWBWLWebXML\\beadando\\XMLOVWBWL.xml"));
            document.getDocumentElement().normalize();

            System.out.println("Fa struktúra:");
            printTree(document.getDocumentElement(), 0);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLNeptunkod1.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz XML tartalma elmentve az XMLNeptunkod1.xml fájlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printTree(Node node, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print("<" + node.getNodeName());

            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }
            System.out.println(">");

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                printTree(children.item(i), indent + 1);
            }

            for (int i = 0; i < indent; i++) {
                System.out.print("  ");
            }
            System.out.println("</" + node.getNodeName() + ">");
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String textContent = node.getTextContent().trim();
            if (!textContent.isEmpty()) {
                System.out.println(textContent);
            }
        }
    }
}