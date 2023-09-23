package com.java.actions.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebbugUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebbugUtils.class);

    public static void doLog(String logMessage) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(logMessage);
        }
    }
}
