package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class GetCommand implements ICommand<String> {
    private final String key;

    public GetCommand(String key) {
        this.key = key;
    }

    @Override
    public Optional<String> execute(IDB db) {
        return Optional.ofNullable(db.get(key));
    }
}
