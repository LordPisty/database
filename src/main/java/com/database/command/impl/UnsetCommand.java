package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class UnsetCommand<K, V, R> extends AbstractCommand<K, V, R> {
    private final K key;

    public UnsetCommand(Type type, K key) {
        super(type);
        this.key = key;
    }

    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        dataFrame.unset(key);
        return null;
    }
}
