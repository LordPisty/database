package com.database.command;

import com.database.IDB;
import com.database.data.IDataFrame;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public interface ICommand<K, V, R>{
    Optional<R> execute(IDataFrame<K, V, R> dataFrame);
    Type getType();
}
