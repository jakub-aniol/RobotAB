package com.epam.robot;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class URLListTests {

    private URLList list;

    @BeforeMethod
    public void testPrepareList() {
        list = new URLList();
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
        URL expectedURL = null;
        try {
            expectedURL = new URL("http://example");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //when
        list.add(expectedURL);
        URL urlFromList = list.get(0);
        //then
        assertThat(urlFromList).isEqualTo(expectedURL);
    }
}
