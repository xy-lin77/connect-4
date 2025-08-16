package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.NoGameRecordException;

public class TestNoGameRecordException{
	@Test
	public void testCanNotRedoException() {
		NoGameRecordException exception = new NoGameRecordException();
		assertEquals("No record found! Start a game first.", exception.getMessage());
	}
}