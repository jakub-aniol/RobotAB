package com.epam.robot.records;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class has only one static method responsible for parsing a record from xml Node item.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class RecordParser {
    /**
     * This method create Record object from Node element from xml file.
     * @param item <code>Node</code> element that contains children with information about the record in the library.
     * @return <code>Record</code> object.
     */
    public static Record parse(Node item) {
        NodeList children = item.getChildNodes();
        Node node;
        String url = "";
        String title = "";
        String description = "";
        String type = "";
        for (int x = 0; x < children.getLength(); x++) {
            node = children.item(x);
            if (node.getNodeName().equals("link"))
                url = node.getTextContent().replace("docmetadata?from=rss&", "rdf.xml?type=e&");
            else if (node.getNodeName().equals("title"))
                title = node.getTextContent();
            else if (node.getNodeName().equals("description"))
                description = node.getTextContent();
            else if(node.getNodeName().equals("type"))
                type = node.getTextContent();
        }
        return (new Record(title, url, description, type));
    }
}
