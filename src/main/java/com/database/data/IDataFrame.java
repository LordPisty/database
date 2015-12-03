package com.database.data;

import com.database.command.ICommand;

import java.util.Iterator;

/**
 * Created by crespis on 12/3/2015.
 */
public interface IDataFrame<K, V, R> {

    void addTxModifier(ICommand<K, V, R> command);
    Iterator<ICommand<K, V, R>> getTxModifiers();
    void set(K key, V value);
    void unset(K key);
    V get(K key);
    Integer count(V value);

}
