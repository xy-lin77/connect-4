package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.NoMoreSuggestionException;

public class TestNoMoreSuggestionException{
	@Test
	public void testCanNotRedoException() {
		NoMoreSuggestionException exception = new NoMoreSuggestionException();
		assertEquals("You have no more chance to get Suggestion!", exception.getMessage());
	}
}