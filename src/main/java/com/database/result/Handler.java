package com.database.result;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class Handler implements IHandler<Object> {

    private static final String NULL = "NULL";

    @Override
    public void handle(Optional<Object> result) {
        if (result != null) {
            System.out.println(result.orElse(NULL));
        }
    }
}
