package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class SetCommand<K, V, R> extends AbstractCommand<K, V, R> {

    private final K key;
    private final V value;

    public SetCommand(Type type, K key, V value) {
        super(type);
        this.key = key;
        this.value = value;
    }

    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        dataFrame.set(key, value);
        return null;
    }
}
