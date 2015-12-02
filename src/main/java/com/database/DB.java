package com.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by screspi on 12/1/15.
 */
public class DB implements IDB {

    private Map<String, String> storage;

    public DB() {
        storage = new HashMap<>();
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public String get(String key) {
        return storage.get(key);
    }

    @Override
    public Integer count(String key) {
        return null;
    }

    @Override
    public void beginTx() {

    }

    @Override
    public void commitTx() {

    }

    @Override
    public void rollbackTx() {

    }
}
