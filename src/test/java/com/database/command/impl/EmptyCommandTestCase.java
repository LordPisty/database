package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by screspi.
 */
public class EmptyCommandTestCase {

    private ICommand instance;
    private IDataFrame dataFrame;

    @Before
    public void setup() {
        instance = new EmptyCommand(Type.EMPTY);
        dataFrame = mock(IDataFrame.class);
    }

    @Test
    public void testExecute() {
        Optional result = instance.execute(dataFrame);
        assertNull("Result should be null", result);
    }
}
