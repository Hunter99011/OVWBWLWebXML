package hu.domparse.OVWBWL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DOMReadOVWBWL {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            KonyvtarSAXHandler handler = new KonyvtarSAXHandler();

            saxParser.parse("C:\\KMgit\\OVWBWLWebXML\\beadando\\XMLOVWBWL.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
