package exception;

public class CanNotUndoException extends Exception {
    public CanNotUndoException()
    {
        // abc
        super("You cannot undo now! ");
    }
}