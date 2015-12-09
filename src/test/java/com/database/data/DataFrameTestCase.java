package com.database.data;

import com.database.command.ICommand;
import com.database.command.impl.EmptyCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by screspi.
 */
public class DataFrameTestCase {

    private static final String KEY_A = "key_A";
    private static final String KEY_B = "key_B";
    private static final String VALUE_A = "A";
    private static final String VALUE_B = "B";

    private DataFrame<String, String, String> base;
    private DataFrame<String, String, String> instance;

    @Before
    public void setup() {
        base = new DataFrame(null);
        instance = new DataFrame(base);
    }

    @Test
    public void testTxModifiers() {
        ICommand<String, String, String> cmd = new EmptyCommand<>(null);
        instance.addTxModifier(cmd);
        Iterator<ICommand<String, String, String>> txModifiers = instance.getTxModifiers();
        assertNotNull("Tx modifiers should not be null", txModifiers);
        assertTrue("Tx modifiers should have an element", txModifiers.hasNext());
        assertEquals("Tx modifier should match", cmd, txModifiers.next());
    }

    @Test
    public void testSet() {
        instance.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testMultipleSetSameValue() {
        instance.set(KEY_A, VALUE_A);
        instance.set(KEY_A, VALUE_A);
        instance.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testMultipleSetDifferentValue() {
        instance.set(KEY_A, VALUE_A);
        instance.set(KEY_A, VALUE_B);
        instance.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testUnset() {
        instance.set(KEY_A, VALUE_A);
        instance.unset(KEY_A);
        String result = instance.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testUnsetEmpty() {
        instance.unset(KEY_A);
        String result = instance.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testGet() {
        instance.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testGetLatestValueSet() {
        instance.set(KEY_A, VALUE_A);
        instance.set(KEY_A, VALUE_B);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_B, result);
    }

    @Test
    public void testGetEmpty() {
        String result = instance.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testCount1() {
        instance.set(KEY_A, VALUE_A);
        Integer result = instance.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(1), result);
    }

    @Test
    public void testCount0() {
        Integer result = instance.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(0), result);
    }

    @Test
    public void testCount2() {
        instance.set(KEY_A, VALUE_A);
        instance.set(KEY_B, VALUE_A);
        Integer result = instance.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(2), result);
    }

    @Test
    public void testGetAncestor() {
        IDataFrame<String, String, String> result = instance.getAncestor();
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", base, result);
    }

    // base
    @Test
    public void testTxModifiersAncestor() {
        ICommand<String, String, String> cmd = new EmptyCommand<>(null);
        base.addTxModifier(cmd);
        Iterator<ICommand<String, String, String>> txModifiers = base.getTxModifiers();
        assertNotNull("Tx modifiers should not be null", txModifiers);
        assertFalse("Tx modifiers should not have elements", txModifiers.hasNext());
    }

    @Test
    public void testSetAncestor() {
        base.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testMultipleSetSameValueAncestor() {
        base.set(KEY_A, VALUE_A);
        base.set(KEY_A, VALUE_A);
        base.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testMultipleSetDifferentValueAncestor() {
        base.set(KEY_A, VALUE_A);
        base.set(KEY_A, VALUE_B);
        base.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testUnsetAncestor() {
        base.set(KEY_A, VALUE_A);
        base.unset(KEY_A);
        String result = instance.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testUnsetEmptyAncestor() {
        base.unset(KEY_A);
        String result = instance.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testGetUsingAncestor() {
        base.set(KEY_A, VALUE_A);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_A, result);
    }

    @Test
    public void testGetLatestValueSetAncestor() {
        base.set(KEY_A, VALUE_A);
        base.set(KEY_A, VALUE_B);
        String result = instance.get(KEY_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", VALUE_B, result);
    }

    @Test
    public void testGetEmptyAncestor() {
        String result = base.get(KEY_A);
        assertNull("Result should be null", result);
    }

    @Test
    public void testCount1Ancestor() {
        base.set(KEY_A, VALUE_A);
        Integer result = instance.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(1), result);
    }

    @Test
    public void testCount0Ancestor() {
        Integer result = base.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(0), result);
    }

    @Test
    public void testCount2Ancestor() {
        base.set(KEY_A, VALUE_A);
        base.set(KEY_B, VALUE_A);
        Integer result = instance.count(VALUE_A);
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match", Integer.valueOf(2), result);
    }

    @Test
    public void testGetAncestorAncestor() {
        IDataFrame<String, String, String> result = base.getAncestor();
        assertNull("Result should be null", result);
    }
}
