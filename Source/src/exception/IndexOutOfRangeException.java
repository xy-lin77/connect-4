package exception;

public class IndexOutOfRangeException extends Exception {
    public IndexOutOfRangeException(int start, int finish)
    {
        super("Please enter a valid index! The index should be from " + start + " to " + finish + ".");
    }
}
