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
        Date expectedDate = new Date(2016 - 1900, 03 - 1, 21);//"2016-03-21"
        Date actualDate = record.getDate();
        assertThat(actualDate).isEqualTo(expectedDate);
    }

    @Test
    public void testIfWrongUrldoesNotAllowToCreateObiect() {
        String title = "tytul";
        String addressUrl = "ewewww";
        String description = "Podtarnowskie Wieści : miesięcznik Gminy Tarnów. 1994, nr 1\n" + "Data publikacji w Bibliotece cyfrowej: 2016-03-21";


        Record record = new Record(title, addressUrl, description, title);
        //when
        boolean status = false;
        try {
            InputStream result = record.stream();
        } catch(NullPointerException e) {
            status = true;
        }
        //then
        assertThat(status).isTrue();
    }

    /*@Test
    public void testsGetStreamShouldThrowException_ForWrongURLAddress() {
        //given
        boolean status = false;
        Downloader downloader = null;

        try {
            downloader = new Downloader(new URL("http://www.dsdssdsd.pl"));
        } catch (MalformedURLException e) {
        }
        //when
        InputStream is = downloader.getStream();

        //then
        assertThat(is).isEqualTo(null);
    }*/

}
