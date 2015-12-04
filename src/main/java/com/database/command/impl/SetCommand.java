package com.database.command.impl;

import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Implementation of an {@link AbstractCommand} that
 * sets the {@code value} of a {@code key}.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command
 */
public class SetCommand<K, V, R> extends AbstractCommand<K, V, R> {

    private final K key;
    private final V value;

    /**
     * Constructs a command to set the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to set
     * @param value the value to set
     */
    public SetCommand(K key, V value) {
        super(Type.SET);
        this.key = key;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        dataFrame.set(key, value);
        return null;
    }
}
