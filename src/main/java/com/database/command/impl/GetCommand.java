package com.database.command.impl;

import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Implementation of an {@link com.database.command.impl.AbstractCommand} that
 * returns the {@code value} associated with a {@code key},
 * or {@code null} if not present.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 */
public class GetCommand<K, V> extends AbstractCommand<K, V, V> {
    private final K key;

    /**
     * Constructs a command to get the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to lookup
     */
    public GetCommand(K key) {
        super(Type.GET);
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<V> execute(IDataFrame<K, V, V> dataFrame) {
        return Optional.ofNullable(dataFrame.get(key));
    }
}
