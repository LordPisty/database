package com.database.data;

import com.database.command.ICommand;

import java.util.Iterator;

/**
 * Interface for a stackable data frame to support
 * nested transactions in an {@link com.database.IDB}.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of commands execution
 */
public interface IDataFrame<K, V, R> {

    /**
     * Add the specified command to the modifiers
     * of this transaction frame.
     *
     * @param command the command to add
     */
    void addTxModifier(ICommand<K, V, R> command);

    /**
     * Returns the modifiers of this transaction
     * frame.
     *
     * @return an {@link java.util.Iterator} of the
     * modifiers commands
     */
    Iterator<ICommand<K, V, R>> getTxModifiers();

    /**
     * Sets the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to set
     * @param value the value to set
     */
    void set(K key, V value);

    /**
     * Unsets the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to set
     */
    void unset(K key);

    /**
     * Gets the {@code value} of
     * the specified {@code key}.
     *
     * @param key the key to lookup
     * @return the {@code value} of the {@code key}
     */
    V get(K key);

    /**
     * Counts the occurrences of
     * the specified {@code value}.
     *
     * @param value the value to count on
     * @return the number of occurrences of
     * the {@code value}
     */
    Integer count(V value);

}
