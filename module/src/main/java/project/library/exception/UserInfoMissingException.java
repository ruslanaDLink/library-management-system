package project.library.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserInfoMissingException extends Exception {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "USER WITHOUT INFO. PLEASE, COMPLY UNTIL ACCESS IS DENIED.";

    @Override
    public String getMessage() {
        LOGGER.error("> fulfil additional information.");
        return ERROR_MESSAGE;
    }
}
