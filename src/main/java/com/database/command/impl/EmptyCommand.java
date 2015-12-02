package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class EmptyCommand<T> implements ICommand<T> {
    @Override
    public Optional<T> execute(IDB db) {
        return null;
    }
}
