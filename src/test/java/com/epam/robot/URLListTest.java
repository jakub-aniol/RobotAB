package com.epam.robot;

import com.epam.robot.url.URLList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class URLListTest {

    private URLList list;
    private URL expectedURL, otherURL;
    private String libraryName;

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
