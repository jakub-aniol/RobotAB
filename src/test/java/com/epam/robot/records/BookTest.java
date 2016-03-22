package com.epam.robot.records;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {
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
}
