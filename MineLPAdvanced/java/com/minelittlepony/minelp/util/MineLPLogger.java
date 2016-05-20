
package com.minelittlepony.minelp.util;

import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MineLPLogger {
    private static Logger logger = LogManager.getLogger("MineLittlePony");

    public static Logger getLogger() {
        return logger;
    }

    private static /* varargs */ void log(Level level, String format, Object... data) {
        logger.log(level, "MineLittlePony> " + String.format(format, data));
    }

    private static /* varargs */ void log(Level level, Throwable t, String format, Object... data) {
        logger.log(level, "MineLittlePony> " + String.format(format, data), t);
    }

    public static void info(String message) {
        MineLPLogger.log(Level.INFO, message);
    }

    public static /* varargs */ void info(String message, Object... data) {
        MineLPLogger.log(Level.INFO, message, data);
    }

    public static void debug(String message) {
        if (LiteLoaderLogger.DEBUG) {
            MineLPLogger.log(Level.INFO, message);
        }
    }

    public static /* varargs */ void debug(String message, Object... data) {
        if (LiteLoaderLogger.DEBUG) {
            MineLPLogger.log(Level.INFO, message, data);
        }
    }

    public static /* varargs */ void debug(Throwable t, String message, Object... data) {
        if (LiteLoaderLogger.DEBUG) {
            MineLPLogger.log(Level.INFO, message, data, t);
        }
    }

    public static void warn(String message) {
        MineLPLogger.log(Level.WARN, message);
    }

    public static /* varargs */ void warn(String message, Object... data) {
        MineLPLogger.log(Level.WARN, message, data);
    }

    public static /* varargs */ void warn(Throwable t, String message, Object... data) {
        MineLPLogger.log(Level.WARN, t, message, data);
    }

    public static void error(String message) {
        MineLPLogger.log(Level.ERROR, message);
    }

    public static /* varargs */ void error(String message, Object... data) {
        MineLPLogger.log(Level.ERROR, message, data);
    }

    public static /* varargs */ void error(Throwable t, String message, Object... data) {
        MineLPLogger.log(Level.ERROR, t, message, data);
    }
}

