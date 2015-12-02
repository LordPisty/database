package com.database;

/**
 * Created by screspi on 12/1/15.
 */
public interface IDB {
    public void set(String key, String value);
    public String get(String key);
    public Integer count(String key);
    public void beginTx();
    public void commitTx();
    public void rollbackTx();
}
