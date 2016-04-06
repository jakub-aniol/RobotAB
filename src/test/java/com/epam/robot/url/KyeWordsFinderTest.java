package com.epam.robot.url;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jakub on 06.04.16.
 */
public class KyeWordsFinderTest {

    @Test
    public final void ifWrongFieldSaySoc(){
        // given
        String badlyEndedPattern = "whatever as long as it does end improperly: dc:NOTsubject>";
        KeyWordsFinder keyWordsFinder = new KeyWordsFinder();
        String tagExpected = keyWordsFinder.WRONG_TAG_MSG;

        //when
        String tag = keyWordsFinder.parsePattern(badlyEndedPattern);

        //then
        assertThat(tag).isEqualTo(tagExpected);
    }

    @Test
    public final void testParsingSubiectStringLogic(){
        //given
        String correctPatternNauka = "<dc:subject xml:lang=\"pl\">nauka</dc:subject>";
        KeyWordsFinder keyWordsFinder = new KeyWordsFinder();
        String naukaTag = "nauka";

        //when
        String tag = keyWordsFinder.parsePattern(correctPatternNauka);

        //then
        assertThat(tag).isEqualTo(naukaTag);
    }

    @Test
    public final void testNUllString(){
        //given
        String correctPatternNauka = "nauka</dc:subject>";
        KeyWordsFinder keyWordsFinder = new KeyWordsFinder();
        String naukaTag = "nauka";

        //when
        String tag = keyWordsFinder.parsePattern(correctPatternNauka);

        //then
        assertThat(tag).isEqualTo(naukaTag);
    }

}
