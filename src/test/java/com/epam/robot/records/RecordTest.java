package com.epam.robot.records;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordTest {

    private Node item;

    @BeforeMethod
    public void setUp() throws Exception {
        try (InputStream stream = RecordParserTest.class.getResource("/example.rss").openStream()) {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            item = doc.getElementsByTagName("item").item(0);
        }
    }

    @Test
    public void testRecordFromXMLItem() throws ParserConfigurationException, IOException, SAXException {
        Record record = RecordParser.parse(item);
        assertThat(record).isNotNull();
    }

    @Test
    public void testRecordDate() {
        Record record = RecordParser.parse(item);
        Date expectedDate = new Date(2016-1900, 03-1, 21);//"2016-03-21"
        Date actualDate = record.getDate();
        assertThat(actualDate).isEqualTo(expectedDate);
    }
}
