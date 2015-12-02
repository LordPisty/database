package com.database.command;

import com.database.IDB;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public interface ICommand<T>{
    public Optional<T> execute(IDB db);
}
