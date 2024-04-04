package project.library.exception;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookNotFoundException extends Exception {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Level NOT_FOUND = Level.forName("NOT FOUND", 404);

    public BookNotFoundException(String message) {
        LOGGER.log(NOT_FOUND, NOT_FOUND.intLevel() + " " + message);
    }
}
