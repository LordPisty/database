package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class SetCommand<T> implements ICommand<T> {

    private final String key;
    private final String value;

    public SetCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Optional<T> execute(IDB db) {
        db.set(key, value);
        return null;
    }
}
