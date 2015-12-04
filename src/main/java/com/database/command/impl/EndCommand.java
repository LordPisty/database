package com.database.command.impl;

import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Implementation of an {@link AbstractCommand} that
 * terminates the program.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command
 */
public class EndCommand<K, V, R> extends AbstractCommand<K, V, R> {

    /**
     * Constructs a command to terminate the program.
     */
    public EndCommand() {
        super(Type.END);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        System.exit(0);
        return null;
    }
}
