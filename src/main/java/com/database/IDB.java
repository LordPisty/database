package com.database;

import com.database.command.ICommand;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public interface IDB<K, V, R> {
    public Optional<R> execute(ICommand<K, V, R> command);
}
