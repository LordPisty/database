package com.database.data;

import com.database.command.ICommand;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Implementation of the {@link com.database.data.IDataFrame} interface,
 * based on maps for values and counts, and keeping an ordered {@link java.util.List}
 * of modifiers {@link com.database.command.ICommand} to replay on commit.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of commands execution
 */
public class DataFrame<K, V, R> implements IDataFrame<K, V, R> {

    private IDataFrame<K, V, R> ancestor;
    private List<ICommand<K, V, R>> txModifiers = null;
    private Map<K, V> values = new HashMap<>();
    private Map<V, Integer> counts = new HashMap<>();
    private Set<K> unset = null;

    /**
     * Constructs a data frame with the specified ancestor.
     *
     * @param ancestor the ancestor data frame
     */
    public DataFrame(IDataFrame<K, V, R> ancestor) {
        this.ancestor = ancestor;
        if (this.ancestor != null) {
            unset = new HashSet<>();
            txModifiers = new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTxModifier(ICommand<K, V, R> command) {
        if (ancestor != null) {
            txModifiers.add(command);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<ICommand<K, V, R>> getTxModifiers() {
        return txModifiers.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(K key, V value) {
        final V oldValue = get(key);
        if (oldValue != null) {
            counts.put(oldValue, count(oldValue) - 1);
        }

        values.put(key, value);
        counts.put(value, count(value) + 1);

        if (ancestor != null) {
            unset.remove(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unset(K key) {
        final V oldValue = get(key);
        if (oldValue != null) {
            counts.put(oldValue, count(oldValue) - 1);
        }

        values.put(key, null);

        if (ancestor != null) {
            unset.add(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key) {
        V retVal = values.get(key);
        if (retVal == null && ancestor != null && !unset.contains(key)) {
            retVal = ancestor.get(key);
            // cache locally
            values.put(key, retVal);
        }
        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer count(V value) {
        Integer retVal = counts.getOrDefault(value, 0);
        if (retVal == 0 && ancestor != null) {
            retVal = ancestor.count(value);
            // cache locally
            counts.put(value, retVal);
        }
        return retVal;
    }
}
