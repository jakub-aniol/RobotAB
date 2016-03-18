package com.epam.robot;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

public class URLListTests {
    @Test
    public void testAddURLToList() {
        //given
        URLList list = new URLList();
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
}
