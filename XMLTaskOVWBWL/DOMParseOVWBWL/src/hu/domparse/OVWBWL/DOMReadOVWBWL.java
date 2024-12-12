package hu.domparse.OVWBWL;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DOMReadOVWBWL {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("XMLOVWBWL.xml");

            // Gyökérelem kiírása
            Element root = document.getDocumentElement();
            System.out.println("Gyökérelem: " + root.getNodeName());
            System.out.println();

            // Gyermekelemek feldolgozása
            NodeList children = root.getChildNodes();
            processNodes(children, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processNodes(NodeList nodes, int depth) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (!hasTextNode(node)) {
                    System.out.println(node.getNodeName() + ":");
                } else {
                    System.out.print(node.getNodeName() + ": ");
                }

                NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        System.out.print(attribute.getNodeName() + " = " + attribute.getNodeValue() + " ");
                    }
                }

                if (node.hasChildNodes()) {
                    NodeList childNodes = node.getChildNodes();
                    StringBuilder textContentBuilder = new StringBuilder();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);

                        if (childNode.getNodeType() == Node.TEXT_NODE) {
                            String textContent = childNode.getTextContent().trim();
                            if (!textContent.isEmpty()) {
                                textContentBuilder.append(textContent).append(" ");
                            }
                        }
                    }

                    if (textContentBuilder.length() > 0) {
                        System.out.println(textContentBuilder.toString().trim());
                    } else {
                        processNodes(childNodes, depth + 1);
                    }
                }

                if (depth == 2) {
                    System.out.println();
                }
            }
        }
    }

    private static boolean hasTextNode(Node node) {
        if (node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node child = childNodes.item(i);
                if (child.getNodeType() == Node.TEXT_NODE && !child.getTextContent().trim().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}