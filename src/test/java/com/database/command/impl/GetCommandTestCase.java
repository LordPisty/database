package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class GetCommandTestCase {

    private static final String TEST = "TEST";
    private static final String VALUE = "VALUE";

    private ICommand instance;
    private IDataFrame dataFrame;

    @Before
    public void setup() {
        instance = new GetCommand(TEST);
        dataFrame = mock(IDataFrame.class);
    }

    @Test
    public void testGetType() {
        assertEquals("Type should match", Type.GET, instance.getType());
    }

    @Test
    public void testExecute() {
        when(dataFrame.get(isA(String.class))).thenReturn(VALUE);
        Optional result = instance.execute(dataFrame);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataFrame).get(captor.capture());
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be present", result.isPresent());
        assertEquals("Result value should match", VALUE, result.get());
        assertEquals("Argument should match", TEST, captor.getValue());
    }
}
