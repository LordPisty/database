package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountCommandTestCase {

    private static final String TEST = "TEST";

    private ICommand instance;
    private IDataFrame dataFrame;

    @Before
    public void setup() {
        instance = new CountCommand(TEST);
        dataFrame = mock(IDataFrame.class);
    }

    @Test
    public void testGetType() {
        assertEquals("Type should match", Type.NUMEQUALTO, instance.getType());
    }

    @Test
    public void testExecute() {
        when(dataFrame.count(isA(String.class))).thenReturn(1);
        Optional result = instance.execute(dataFrame);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(dataFrame).count(captor.capture());
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be present", result.isPresent());
        assertEquals("Result value should match", 1, result.get());
        assertEquals("Argument should match", TEST, captor.getValue());
    }
}
