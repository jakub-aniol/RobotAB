package com.epam.robot;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class URLListTest {

    private URLList list;
    private URL expectedURL, otherURL;

    @BeforeMethod
    public void testPrepareList() {
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
            list.add(new URL("http://example"));
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
        list.add(expectedURL);
        URL urlFromList = list.get(0);
        //then
        assertThat(urlFromList).isEqualTo(expectedURL);
    }

    @Test
    public void testChangeURLInTheList() {
        //given
        list.add(expectedURL);
        URL expectedURL = otherURL;
        //when
        list.set(0, otherURL);
        URL actualURL = list.get(0);
        //then
        assertThat(actualURL).isEqualTo(expectedURL);
    }

    @Test
    public void testRemoveURLFromTheList() {
        //given
        list.add(expectedURL);
        int expectedSize = 0;
        //when
        list.remove(0);
        int actualSize = list.size();
        //then
        assertThat(actualSize).isEqualTo(expectedSize);
    }
}
