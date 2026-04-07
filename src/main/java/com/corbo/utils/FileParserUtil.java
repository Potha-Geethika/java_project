package com.corbo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class FileParserUtil {

    public static int simulateProcessing() {

        log.info("Parsing file content...");

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            throw new RuntimeException("Processing interrupted");

        }

        return new Random().nextInt(1000);
    }

}