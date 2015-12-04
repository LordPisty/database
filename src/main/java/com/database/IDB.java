package com.database;

import com.database.command.ICommand;

import java.util.Optional;

/**
 * Interface for a simple key-value database,
 * executing commands and returning results.
 *
 * @param <K> the type of keys maintained by this database
 * @param <V> the type of mapped values
 * @param <R> the type of results of commands execution
 */
public interface IDB<K, V, R> {

    /**
     * Executes the specified command and returns the related
     * result.
     *
     * @param command the command to execute
     * @return the result of the computation wrapped into an
     * {@link java.util.Optional} or eventually {@code null}
     */
    Optional<R> execute(ICommand<K, V, R> command);
}
