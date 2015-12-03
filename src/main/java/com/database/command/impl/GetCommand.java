package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class GetCommand<K, V> extends AbstractCommand<K, V, V> {
    private final K key;

    public GetCommand(Type type, K key) {
        super(type);
        this.key = key;
    }

    @Override
    public Optional<V> execute(IDataFrame<K, V, V> dataFrame) {
        return Optional.ofNullable(dataFrame.get(key));
    }
}
