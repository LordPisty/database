package com.database.result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.PrintStream;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrintHandlerTestCase {

    private static final String TEST = "test";
    private static final String NULL = "NULL";

    private Consumer<Optional<Object>> instance;
    private PrintStream printStream;

    @Before
    public void setup() {
        printStream = mock(PrintStream.class);
        instance = new PrintHandler(printStream);
    }

    @Test
    public void testAcceptStringValue() {
        Optional<Object> result = Optional.of(TEST);
        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        instance.accept(result);
        verify(printStream).println(captor.capture());
        assertNotNull("Printed objected should not be null", captor.getValue());
        assertEquals("Printed value should match", TEST, captor.getValue());
    }

    @Test
    public void testAcceptIntegerValue() {
        Optional<Object> result = Optional.of(3);
        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        instance.accept(result);
        verify(printStream).println(captor.capture());
        assertNotNull("Printed objected should not be null", captor.getValue());
        assertEquals("Printed value should match", 3, captor.getValue());
    }

    @Test
    public void testAcceptEmptyValue() {
        Optional<Object> result = Optional.empty();
        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        instance.accept(result);
        verify(printStream).println(captor.capture());
        assertNotNull("Printed objected should not be null", captor.getValue());
        assertEquals("Printed value should match", NULL, captor.getValue());
    }
}
