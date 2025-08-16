package exception;

public class NoMoreUndoException extends Exception {
    public NoMoreUndoException()
    {
        super("You have no more chance to undo!");
    }
}
