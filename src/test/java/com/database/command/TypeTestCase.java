package com.database.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TypeTestCase {

    @Test
    public void testTGetFromName() {
        for (final Type type : Type.values()) {
            Type result = Type.getFromName(type.name());
            assertNotNull("Result should not be null", result);
            assertEquals("Result should match", type, result);
        }

        Type result = Type.getFromName("random");
        assertNull("Result should not null", result);

        Type resultEmpty = Type.getFromName("");
        assertNull("Result should not null", resultEmpty);
    }
}
