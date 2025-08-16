package exception;

public class NoMoreSuggestionException extends Exception {
    public NoMoreSuggestionException(){
        super("You have no more chance to get Suggestion!");
    }
}
