package com.epam.robot.url;

import com.epam.robot.records.Record;
import com.epam.robot.records.RecordParser;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for parsing a list of <code>Record</code> objects based on <code>Downloader</code> object.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class XMLHandler implements Parser {
    private Downloader downloader;
    private List<Record> records;

    /**
     * This constructor initialize an object but no connections is made (it is lightweight).
     * @param downloader - <code>Downloader</code> with stream to rss file.
     */
    public XMLHandler(Downloader downloader) {
        this.downloader = downloader;
    }

    /**
     * This method make connections and computes rss file to extract <code>List</code> of <code>Record</code> objects.
     * @return <code>List</code> of <code>Record</code> objects.
     */
    public List<Record> getRecords() {
        if (records == null) load();
        return records;
    }

    private void load() {
        records = new ArrayList<>();
        try {
            NodeList items = getNodeList(downloader.getStream(), "item");
            for (int x=0; x<items.getLength(); x++){
                records.add(RecordParser.parse(items.item(x)));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
