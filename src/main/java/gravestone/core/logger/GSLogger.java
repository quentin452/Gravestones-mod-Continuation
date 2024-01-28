package gravestone.core.logger;

import gravestone.core.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static gravestone.config.GraveStoneConfig.logsEnabled;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSLogger {

    private static final Logger logger = LogManager.getLogger(ModInfo.ID);
    private static final Logger graveLogger = new GravesLogger();

    public static void log(Level logLevel, String message) {
        if (logsEnabled) {
            logger.log(logLevel, message);
        }
    }

    public static void logInfo(String message) {
        if (logsEnabled) {
        log(Level.INFO, message);
    } }

    public static void logError(String message) {
        if (logsEnabled) {
        log(Level.ERROR, message);
    }}

    public static void logGrave(Level logLevel, String message) {
        if (logsEnabled) {
            graveLogger.log(logLevel, message);
        }
    }

    public static void logInfoGrave(String message) {
        if (logsEnabled) {
        logGrave(Level.INFO, message);
    }}

    public static void logErrorGrave(String message) {
        if (logsEnabled) {
        logGrave(Level.ERROR, message);
    }}
}
