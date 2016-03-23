package com.epam.robot;

import com.epam.robot.url.URLList;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.*;

public class URLListTest {

    private URLList list;
    private URL expectedURL, otherURL;
    private String libraryName;
    private File renamed;
    private File properties;

    @BeforeTest
    public void setUp() throws Exception {
        properties = new File("urlList.properties");
        renamed = new File("org-urlList.properties");
        if (renamed.exists()) Files.delete(renamed.toPath());
        properties.renameTo(renamed);
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (properties.exists() && renamed.exists()) {
            Files.delete(properties.toPath());
            renamed.renameTo(properties);
        }
    }

    @BeforeMethod
    public void testPrepareList() {
        libraryName = "Example";
        list = new URLList();
        try {
            expectedURL = new URL("http://example");
            otherURL = new URL("http://otherURL");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddURLToList() {
        //given
        int expectedListSize = 1;
        //when
        try {
            list.add(libraryName, new URL("http://example"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //then
        assertThat(list.size()).isEqualTo(expectedListSize);
    }

    @Test
    public void testGetURLFromList() {
        //given
        //when
        list.add(libraryName, expectedURL);
        URL urlFromList = list.get(libraryName);
        //then
        assertThat(urlFromList).isEqualTo(expectedURL);
    }

    @Test
    public void testChangeURLInTheList() {
        //given
        list.add(libraryName, expectedURL);
        URL expectedURL = otherURL;
        //when
        list.set(libraryName, otherURL);
        URL actualURL = list.get(libraryName);
        //then
        assertThat(actualURL).isEqualTo(expectedURL);
    }

    @Test
    public void testRemoveURLFromTheList() {
        //given
        list.add(libraryName, expectedURL);
        int expectedSize = 0;
        //when
        list.remove(libraryName);
        int actualSize = list.size();
        //then
        assertThat(actualSize).isEqualTo(expectedSize);
    }
}
