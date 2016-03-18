package com.epam.robot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {
    private static final Logger log = LogManager.getLogger("Starting logger");

    public static void main(String[] args) {
        log.info("first log");
    }
}
