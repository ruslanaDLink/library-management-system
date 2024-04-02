package project.library.exception;

public class UserInfoMissingException extends Exception{
    @Override
    public String getMessage() {
        return "Nullable User. Fulfil additional information.";
    }
}
