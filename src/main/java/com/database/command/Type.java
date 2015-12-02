package com.database.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by screspi on 12/1/15.
 */
public enum Type {
    SET,
    GET,
    UNSET,
    NUMEQUALTO,
    END,
    BEGIN,
    COMMIT,
    ROLLBACK;

    private static Map<String, Type> map = new HashMap<>();

    static {
        for (Type tEnum : Type.values()) {
            map.put(tEnum.name(), tEnum);
        }
    }

    public static Type getFromName(final String name) {
        return map.get(name);
    }
}
