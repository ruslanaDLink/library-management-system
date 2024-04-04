package project.library.exception;


public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        System.out.println("BookNotFoundException: " + message);
    }
}
