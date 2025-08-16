package testException;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import exception.IndexOutOfRangeException;

public class TestIndexOutOfRangeException{
	
	@Test
	public void testCanNotRedoException2() {
		IndexOutOfRangeException exception = new IndexOutOfRangeException(1, 2);
		assertEquals("Please enter a valid index! The index should be from 1 to 2.", exception.getMessage());
	}
}