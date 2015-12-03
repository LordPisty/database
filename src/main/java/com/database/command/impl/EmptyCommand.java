package com.database.command.impl;

import com.database.IDB;
import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class EmptyCommand<K, V, R> extends AbstractCommand<K, V, R> {

    public EmptyCommand(Type type) {
        super(type);
    }

    @Override
    public Optional<R> execute(IDataFrame<K, V, R> dataFrame) {
        return null;
    }

}
