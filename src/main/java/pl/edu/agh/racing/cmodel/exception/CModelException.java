package pl.edu.agh.racing.cmodel.exception;

public class CModelException extends RuntimeException {

    public CModelException(String exMessage) {
        super(exMessage);
    }

    public CModelException(String exMessage, Throwable cause) {
        super(exMessage, cause);
    }
}
