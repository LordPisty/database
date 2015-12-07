package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SetCommandTestCase {

    private static final String TEST = "TEST";
    private static final String VALUE = "VALUE";

    private ICommand instance;
    private IDataFrame dataFrame;

    @Before
    public void setup() {
        instance = new SetCommand(TEST, VALUE);
        dataFrame = mock(IDataFrame.class);
    }

    @Test
    public void testGetType() {
        assertEquals("Type should match", Type.SET, instance.getType());
    }

    @Test
    public void testExecute() {
        Optional result = instance.execute(dataFrame);
        ArgumentCaptor<String> captorKey = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captorValue = ArgumentCaptor.forClass(String.class);
        verify(dataFrame).set(captorKey.capture(), captorValue.capture());
        assertNull("Result should be null", result);
        assertEquals("Argument should match", TEST, captorKey.getValue());
        assertEquals("Argument should match", VALUE, captorValue.getValue());
    }
}
