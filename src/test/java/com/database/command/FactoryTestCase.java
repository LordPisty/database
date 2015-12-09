package com.database.command;

import com.database.command.impl.*;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by screspi.
 */
public class FactoryTestCase {

    private Factory instance;

    @Before
    public void setup() {
        instance = new Factory();
    }

    @Test
    public void testApplyEmpty() {
        ICommand cmd = instance.apply("");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplyRandom() {
        ICommand cmd = instance.apply("random");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplySet() {
        ICommand cmd = instance.apply("SET a 3");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", SetCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.SET, cmd.getType());
    }

    @Test
    public void testApplySetArgs() {
        ICommand cmd = instance.apply("SET a");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplyGet() {
        ICommand cmd = instance.apply("GET a ");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", GetCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.GET, cmd.getType());
    }

    @Test
    public void testApplyGetArgs() {
        ICommand cmd = instance.apply("GET");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplyUnset() {
        ICommand cmd = instance.apply("UNSET a");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", UnsetCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.UNSET, cmd.getType());
    }

    @Test
    public void testApplyUnsetArgs() {
        ICommand cmd = instance.apply("UNSET");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplyNumequalto() {
        ICommand cmd = instance.apply("NUMEQUALTO 3");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", CountCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.NUMEQUALTO, cmd.getType());
    }

    @Test
    public void testApplyNumequlatoArgs() {
        ICommand cmd = instance.apply("NUMEQUALTO");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.EMPTY, cmd.getType());
    }

    @Test
    public void testApplyEnd() {
        ICommand cmd = instance.apply("END");
        assertNotNull("Command should not be null", cmd);
        assertEquals("Command class should match", EndCommand.class, cmd.getClass());
        assertEquals("Command type should match", Type.END, cmd.getType());
    }

    @Test
    public void testApplyMarkers() {
        for (Type type : EnumSet.of(Type.BEGIN, Type.COMMIT, Type.ROLLBACK)) {
            ICommand cmd = instance.apply(type.name());
            assertNotNull("Command should not be null", cmd);
            assertEquals("Command class should match", EmptyCommand.class, cmd.getClass());
            assertEquals("Command type should match", type, cmd.getType());
        }
    }
}
