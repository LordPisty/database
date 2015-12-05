package com.database.result;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Implementation of a {@link java.util.function.Consumer} to handle
 * results wrapped into an {@link java.util.Optional}, which
 * prints the value or {@code NULL} to {@code stdout}.
 */
public class Handler implements Consumer<Optional<Object>> {

    private static final String NULL = "NULL";

    /**
     * Handles the specified result, printing
     * the value or {@code NULL} to {@code stdout}.
     *
     * @param result the result to handle
     */
    @Override
    public void accept(Optional<Object> result) {
        if (result != null) {
            System.out.println(result.orElse(NULL));
        }
    }
}
