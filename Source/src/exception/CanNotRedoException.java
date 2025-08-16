package exception;

public class CanNotRedoException extends Exception {
    public CanNotRedoException()
    {
        super("You cannot redo now! ");
    }
}
