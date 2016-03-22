package com.epam.robot.url;

import com.epam.robot.records.Record;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DCMetadataParser implements Parser{
    public boolean isBook(Record record) {
        try {
            NodeList types = getNodeList(record.stream(), "dc:type");
            for (int i=0; i<types.getLength(); i++){
                if (types.item(i).getTextContent().contains("książka")) return true;
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
