package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.NoMoreUndoException;

public class TestNoMoreUndoException{
	@Test
	public void testCanNotRedoException() {
		NoMoreUndoException exception = new NoMoreUndoException();
		assertEquals("You have no more chance to undo!", exception.getMessage());
	}
}