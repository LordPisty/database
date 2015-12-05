package com.database.command;

import com.database.IDB;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Interface for a command to execute on an {@link com.database.IDB}.
 *
 * @param <K> the type of keys maintained by the database
 * @param <V> the type of mapped values
 * @param <R> the type of results of this command
 */
public interface ICommand<K, V, R>{

    /**
     * Executes the command on a specific {@link com.database.data.IDataFrame}
     *
     * @param dataFrame the data frame to execute on
     * @return the result of the execution wrapped into an {@link java.util.Optional}
     */
    Optional<R> execute(IDataFrame<K, V, R> dataFrame);

    /**
     * Returns the type of the command.
     *
     * @return the type of the command
     */
    Type getType();
}
