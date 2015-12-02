package com.database.result;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public interface IHandler<T> {
    public void handle(Optional<T> result);
}
