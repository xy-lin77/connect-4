package exception;

public class CanNotUndoException extends Exception {
    public CanNotUndoException()
    {
        super("You cannot undo now! ");
    }
}
