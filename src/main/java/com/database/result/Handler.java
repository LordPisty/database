package com.database.result;

import java.util.Optional;

/**
 * Created by screspi on 12/1/15.
 */
public class Handler implements IHandler<String> {

    private static final String NULL = "NULL";

    @Override
    public void handle(Optional<String> result) {
        if (result != null) {
            System.out.println(result.orElse(NULL));
        }
    }
}
