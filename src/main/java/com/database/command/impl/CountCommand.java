package com.database.command.impl;

import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Implementation of an {@link com.database.command.impl.AbstractCommand} that
 * counts the number of variables set to {@code value}.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 */
public class CountCommand<K, V> extends AbstractCommand<K, V, Integer> {
    private final V value;

    /**
     * Constructs a command to count the occurrences of
     * the specified {@code value}.
     *
     * @param value the value to count on
     */
    public CountCommand(V value) {
        super(Type.NUMEQUALTO);
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> execute(IDataFrame<K, V, Integer> dataFrame) {
        return Optional.of(dataFrame.count(value));
    }
}
