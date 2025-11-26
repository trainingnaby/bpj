package room4u.domain;

public class SalleIndisponibleException extends RuntimeException {
    public SalleIndisponibleException(String message) {
        super(message);
    }
}
