package com.database.data;

import com.database.command.ICommand;

import java.util.*;

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
        if (getAncestor() != null) {
            unset = new HashSet<>();
            txModifiers = new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTxModifier(ICommand<K, V, R> command) {
        if (getAncestor() != null) {
            txModifiers.add(command);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<ICommand<K, V, R>> getTxModifiers() {
        return (getAncestor() != null) ? txModifiers.iterator() : Collections.emptyIterator();
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

        if (getAncestor() != null) {
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

        if (getAncestor() != null) {
            unset.add(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key) {
        V retVal = values.get(key);
        if (retVal == null && getAncestor() != null && !unset.contains(key)) {
            retVal = getAncestor().get(key);
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
        if (retVal == 0 && getAncestor() != null) {
            retVal = getAncestor().count(value);
            // cache locally
            counts.put(value, retVal);
        }
        return retVal;
    }

    /**
     * Returns the frame ancestor.
     *
     * @return the frame ancestor
     */
    public IDataFrame<K, V, R> getAncestor() {
        return ancestor;
    }
}
