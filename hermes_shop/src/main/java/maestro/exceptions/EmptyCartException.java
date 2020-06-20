package maestro.exceptions;

public class EmptyCartException extends CustomNotValidException {
    public EmptyCartException() {
        super("Not empty", "cart", "items");
    }
}
