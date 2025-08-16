package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.CanNotRedoException;

public class TestCanNotRedoException{
	@Test
	public void testCanNotRedoException() {
		CanNotRedoException exception = new CanNotRedoException();
		assertEquals("You cannot redo now! ", exception.getMessage());
	}
}