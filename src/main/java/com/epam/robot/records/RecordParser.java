package com.epam.robot.records;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RecordParser {
    public static Record parse(Node item) {
        NodeList children = item.getChildNodes();
        Node node;
        String url="";
        String title="";
        String description="";
        for (int x=0; x<children.getLength(); x++){
            node = children.item(x);
            if (node.getNodeName().equals("link"))
                url = node.getTextContent().replace("docmetadata?from=rss&", "rdf.xml?type=e&");
            else if (node.getNodeName().equals("title"))
                title = node.getTextContent();
            else if (node.getNodeName().equals("description"))
                description=node.getTextContent();
        }
        return (new Record(title, url, description));
    }
}
