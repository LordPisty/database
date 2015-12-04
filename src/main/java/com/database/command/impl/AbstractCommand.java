package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Abstract implementation of an {@link ICommand}
 * with a specific type.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command
 */
public abstract class AbstractCommand<K, V, R> implements ICommand<K, V, R> {

    private Type type;

    /**
     * Constructs a command with the specified type.
     *
     * @param type the type of the command
     */
    public AbstractCommand(Type type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    public abstract Optional<R> execute(IDataFrame<K, V, R> dataFrame);

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return type;
    }
}
