package com.epam.robot.url;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to find Key Words from link egz: http://jbc.bj.uj.edu.pl/dlibra/rdf.xml?type=e&id=342600
 * Created by jakub on 06.04.16.
 */
public class KeyWordsFinder {
    ArrayList<String> keyWords = new ArrayList<>();
    static final String WRONG_TAG_MSG  = "wrong field, no tag found";

    public ArrayList<String> getKeyWords(Scanner scanner) {
        ArrayList<String> xMLContentList = new ArrayList<>();
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();

            xMLContentList.add(parsePattern(str));
        }

        keyWords.addAll(xMLContentList);

        System.out.println();
        return keyWords;
    }

    String parsePattern(String pattern) {
        if (!pattern.endsWith("</dc:subject>")) {
            return WRONG_TAG_MSG;
        }
        String keyWord = pattern.substring(pattern.indexOf('>')+1, pattern.indexOf('/')-1);

            return keyWord;
    }
}
