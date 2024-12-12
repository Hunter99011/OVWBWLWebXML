package SaXOVWBWL1203;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaXOVWBWL {

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SaXHandler handler = new SaXHandler();
            saxParser.parse("C:\\KMgit\\OVWBWLWebXML\\OVWBWL_1203\\SaXOVWBWL\\OVWBWL_kurzusfelvetel.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
