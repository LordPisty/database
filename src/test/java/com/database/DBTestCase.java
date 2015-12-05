package com.database;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.command.impl.EmptyCommand;
import com.database.data.DataFrame;
import com.database.data.IDataFrame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


/**
 * Created by screspi on 12/5/15.
 */
public class DBTestCase {

    private IDB instance;
    private IDataFrame base;
    private Stack txs;

    @Before
    public void setup() {
        base = mock(IDataFrame.class);
        txs = mock(Stack.class);
        instance = new DB<>(base, txs);
    }

    @Test
    public void testExecuteBegin() {
        ICommand cmd = new EmptyCommand(Type.BEGIN);
        when(txs.peek()).thenThrow(new EmptyStackException());
        ArgumentCaptor<DataFrame> captor = ArgumentCaptor.forClass(DataFrame.class);
        Optional result = instance.execute(cmd);
        verify(txs).push(captor.capture());
        assertNull("Result should be null", result);
        assertNotNull("New frame ancestor should not be null", captor.getValue().getAncestor());
        assertEquals("New frame ancestor should match", base, captor.getValue().getAncestor());
    }

    @Test
    public void testExecuteRollbackEmpty() {
        ICommand cmd = new EmptyCommand(Type.ROLLBACK);
        when(txs.pop()).thenThrow(new EmptyStackException());
        Optional result = instance.execute(cmd);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should have value", result.isPresent());
        assertEquals("Result value should match", "NO TRANSACTION", result.get());
    }

    @Test
    public void testExecuteRollback() {
        ICommand cmd = new EmptyCommand(Type.ROLLBACK);
        when(txs.pop()).thenReturn(new Object());
        Optional result = instance.execute(cmd);
        assertNull("Result should be null", result);
    }

    @Test
    public void testExecuteCommit() {
        ICommand cmd = new EmptyCommand(Type.COMMIT);
        ICommand cmd1 = mock(ICommand.class);
        ICommand cmd2 = mock(ICommand.class);
        List<ICommand> cmds = new ArrayList<>();
        cmds.add(cmd1);
        cmds.add(cmd2);
        IDataFrame tx = mock(IDataFrame.class);
        when(tx.getTxModifiers()).thenReturn(cmds.iterator());
        List<IDataFrame> txsIt = new ArrayList<>();
        txsIt.add(tx);
        when(txs.iterator()).thenReturn(txsIt.iterator());
        ArgumentCaptor<IDataFrame> captor1 = ArgumentCaptor.forClass(IDataFrame.class);
        ArgumentCaptor<IDataFrame> captor2 = ArgumentCaptor.forClass(IDataFrame.class);
        Optional result = instance.execute(cmd);
        assertNull("Result should be null", result);
        verify(cmd1).execute(captor1.capture());
        verify(cmd2).execute(captor2.capture());
        assertEquals("Data frame for execution should match", base, captor1.getValue());
        assertEquals("Data frame for execution should match", base, captor2.getValue());
        verify(txs).removeAllElements();
    }

    @Test
    public void testExecuteEnd() {
        ICommand cmd = mock(ICommand.class);
        when(cmd.getType()).thenReturn(Type.END);
        ArgumentCaptor<IDataFrame> captor = ArgumentCaptor.forClass(IDataFrame.class);
        Optional result = instance.execute(cmd);
        verify(cmd).execute(captor.capture());
        assertNull("Result should be null", result);
        assertNull("Data frame should be null", captor.getValue());
    }

    @Test
    public void testExecuteFwd() {
        final Set<Type> covered = EnumSet.of(Type.BEGIN, Type.COMMIT, Type.ROLLBACK, Type.END);
        when(txs.peek()).thenThrow(new EmptyStackException());

        for (Type type : Type.values()) {
            if (!covered.contains(type)) {
                ICommand cmd = mock(ICommand.class);
                when(cmd.getType()).thenReturn(type);
                ArgumentCaptor<IDataFrame> captor = ArgumentCaptor.forClass(IDataFrame.class);
                Optional result = instance.execute(cmd);
                verify(cmd).execute(captor.capture());
                assertEquals("Data frame should match", base, captor.getValue());
            }
        }
    }
}
