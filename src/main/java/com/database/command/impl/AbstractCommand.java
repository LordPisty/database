package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by crespis on 12/3/2015.
 */
public abstract class AbstractCommand<K, V, R> implements ICommand<K, V, R> {

    private Type type;

    public AbstractCommand(Type type) {
        this.type = type;
    }

    public abstract Optional<R> execute(IDataFrame<K, V, R> dataFrame);

    @Override
    public Type getType() {
        return type;
    }
}
