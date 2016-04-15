package com.epam.robot.url;

import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.FinishedQueryMessage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class RSSParserTest implements Subscriber<FinishedQueryMessage> {

    private File properties;
    private File renamed;

    @BeforeTest
    public void setUp() throws Exception {
        properties = new File("urlList.properties");
        renamed = new File("org-urlList.properties");
        if (renamed.exists()) Files.delete(renamed.toPath());
        properties.renameTo(renamed);
        subscribe(FinishedQueryMessage.class);
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (properties.exists()) {
            Files.delete(properties.toPath());
        }
        if (renamed.exists()){
            renamed.renameTo(properties);
        }
    }

    @Test
    public void testGetNewestTitles() throws MalformedURLException {
        URLList list = new URLList();
        list.add("Example", RSSParserTest.class.getResource("/example.rss"));
        RSSParser rssParser = new RSSParser(list);
        int expectedListSize = 0;
        int booksListSize = rssParser.getNewestBooks().size();
        assertThat(booksListSize).isEqualTo(expectedListSize);
    }

    @Override
    public void receiveMessage(FinishedQueryMessage message) {
    }
}
