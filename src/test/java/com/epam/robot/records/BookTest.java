package com.epam.robot.records;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    private Node item;

    @BeforeMethod
    public void setUp() throws Exception {
        try (InputStream stream = RecordParserTest.class.getResource("/example.rss").openStream()) {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            item = doc.getElementsByTagName("item").item(0);
        }
    }


    @Test
    public void testBooksEquality() {
        Book book = new Book("A|A");
        Book otherBook = new Book("A|A");
        assertThat(book).isEqualTo(otherBook);
    }

    @Test
    public void testBooksNonEquality() {
        Book book = new Book("A|A");
        Book otherBook = new Book("B|A");
        assertThat(book).isNotEqualTo(otherBook);
    }
    @Test
    public void isTitleReturned(){
        //given
        Record record = RecordParser.parse(item);
        Book book = new Book(record, "Biblioteka");
        String expected = record.getTitle();
        //when
        String result;
        result = book.getTitle();
        //then
        assertThat(expected).isEqualTo(result);
    }



}
