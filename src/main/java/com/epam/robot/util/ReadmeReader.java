package com.epam.robot.util;

import java.io.IOException;
import java.io.InputStreamReader;

public class ReadmeReader {
    public static String getReadme(){
        StringBuilder content= new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(ReadmeReader.class.getResource("/README.txt").openStream());
            int myChar;
            while ((myChar=reader.read())!=-1){
                content.append(((char)myChar));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
