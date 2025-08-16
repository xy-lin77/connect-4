package exception;

public class NoGameRecordException extends Exception {
    public NoGameRecordException(){
        super("No record found! Start a game first.");
    }
}
