package exceptions;

public class NumSecuException extends Exception {
    public NumSecuException() {
        super("Num de sec est non conforme ");
    }

    public NumSecuException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
