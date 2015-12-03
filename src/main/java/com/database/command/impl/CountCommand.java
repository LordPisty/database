package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class CountCommand<K, V> extends AbstractCommand<K, V, Integer> {
    private final V value;

    public CountCommand(Type type, V value) {
        super(type);
        this.value = value;
    }

    @Override
    public Optional<Integer> execute(IDataFrame<K, V, Integer> dataFrame) {
        return Optional.of(dataFrame.count(value));
    }
}
