package com.epam.robot.url;

import org.testng.annotations.Test;

import java.awt.print.Book;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RSSParserTest {
    @Test
    public void testGetNewestTitles() throws MalformedURLException {
        URLList list = new URLList();
        list.add(RSSParserTest.class.getResource("/example.rss"));
        RSSParser rssParser = new RSSParser(list);
        int expectedListSize = 1;
        int booksListSize = rssParser.getNewestBooks().size();
        assertThat(booksListSize).isEqualTo(expectedListSize);
    }
}
