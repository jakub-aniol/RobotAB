package com.epam.robot.url;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This interface provides the ability to extract a node list from xml stream based on item local name.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public interface Parser {
    /**
     * This method extract a node list from xml stream based on item local name
     * @param stream - stream from xml file.
     * @param item - local name of wanted nodes list?
     * @return <code>NodeList</code> object found in xml stream.
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    default NodeList getNodeList(InputStream stream, String item) throws SAXException, IOException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
        return doc.getElementsByTagName(item);
    }

}
