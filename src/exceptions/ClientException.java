package exceptions;

public class ClientException extends Exception {
    public ClientException() {
        super("client est non conforme ");
    }

    public ClientException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
