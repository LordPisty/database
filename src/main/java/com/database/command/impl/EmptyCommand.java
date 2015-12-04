package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * No-op implementation of an {@link AbstractCommand}
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command          
 */
public class EmptyCommand<K, V, R> extends AbstractCommand<K, V, R> {

    /**
     * Constructs a no-op command with the specified type.
     *
     * @param type the type of the command
     */
    public EmptyCommand(Type type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        return null;
    }

}
