package com.database.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command types
 */
public enum Type {

    /**
     * Set Command
     */
    SET,

    /**
     * Get Command
     */
    GET,

    /**
     * Unset Command
     */
    UNSET,

    /**
     * Numequalto Command
     */
    NUMEQUALTO,

    /**
     * End Command
     */
    END,

    /**
     * Begin Command
     */
    BEGIN,

    /**
     * Commit Command
     */
    COMMIT,

    /**
     * Rollback Command
     */
    ROLLBACK,

    /**
     * Empty Command
     */
    EMPTY;

    private static Map<String, Type> map = new HashMap<>();

    static {
        for (Type tEnum : Type.values()) {
            map.put(tEnum.name(), tEnum);
        }
    }

    /**
     * Returns the command type from its name.
     *
     * @param name the desired command name
     * @return the specified command type, or
     * {@code null} if the name is not recognized
     */
    public static Type getFromName(final String name) {
        return map.get(name);
    }
}
