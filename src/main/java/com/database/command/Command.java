package com.database.command;

import com.database.IDB;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class Command implements ICommand<String>{
    @Override
    public Optional<String> execute(IDB db) {
        return Optional.of("3");
    }
}
