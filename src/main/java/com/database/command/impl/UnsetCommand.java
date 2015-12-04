package com.database.command.impl;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Implementation of an {@link AbstractCommand} that
 * unsets the {@code value} of a {@code key}.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command
 */
public class UnsetCommand<K, V, R> extends AbstractCommand<K, V, R> {
    private final K key;

    /**
     * Constructs a command to unset the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to set
     */
    public UnsetCommand(K key) {
        super(Type.UNSET);
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        dataFrame.unset(key);
        return null;
    }
}
