package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.CanNotUndoException;

public class TestCanNotUndoException{
	@Test
	public void testCanNotRedoException() {
		CanNotUndoException exception = new CanNotUndoException();
		assertEquals("You cannot undo now! ", exception.getMessage());
	}
}