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

public class UnsetCommandTestCase {

    private static final String TEST = "TEST";

    private ICommand instance;
    private IDataFrame dataFrame;

    @Before
    public void setup() {
        instance = new UnsetCommand(TEST);
        dataFrame = mock(IDataFrame.class);
    }

    @Test
    public void testGetType() {
        assertEquals("Type should match", Type.UNSET, instance.getType());
    }

    @Test
    public void testExecute() {
        Optional result = instance.execute(dataFrame);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataFrame).unset(captor.capture());
        assertNull("Result should be null", result);
        assertEquals("Argument should match", TEST, captor.getValue());
    }
}
