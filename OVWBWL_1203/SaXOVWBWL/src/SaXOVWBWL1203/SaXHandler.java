package SaXOVWBWL1203;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaXHandler extends DefaultHandler {
    private String currentElement;
    private StringBuilder currentText = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        currentText.setLength(0);

        // Hallgató és kurzus adatok kiírása
        if ("hallgato".equals(qName)) {
            System.out.println("Hallgató ID: " + attributes.getValue("id"));
        } else if ("szak".equals(qName)) {
            System.out.println("Szak évfolyam: " + attributes.getValue("evf"));
        } else if ("kurzus".equals(qName)) {
            System.out.println("Kurzus ID: " + attributes.getValue("id"));
            if (attributes.getValue("nyelv") != null) {
                System.out.println("Nyelv: " + attributes.getValue("nyelv"));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "hnev":
                System.out.println("Hallgató neve: " + currentText.toString().trim());
                break;
            case "szulev":
                System.out.println("Születési év: " + currentText.toString().trim());
                break;
            case "szak":
                System.out.println("Szak: " + currentText.toString().trim());
                break;
            case "kurzusnev":
                System.out.println("Kurzus neve: " + currentText.toString().trim());
                break;
            case "kredit":
                System.out.println("Kredit: " + currentText.toString().trim());
                break;
            case "hely":
                System.out.println("Helyszín: " + currentText.toString().trim());
                break;
            case "idopont":
                System.out.println("Időpont: " + currentText.toString().trim());
                break;
            case "oktato":
                System.out.println("Oktató: " + currentText.toString().trim());
                break;
        }
        currentElement = null;
    }
}