package com.database.result;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Implementation of a {@link java.util.function.Consumer} to handle
 * results wrapped into an {@link java.util.Optional}, which prints
 * the value or {@code NULL} to the specified {@link java.io.PrintStream}.
 */
public class PrintHandler implements Consumer<Optional<Object>> {

    private static final String NULL = "NULL";

    private final PrintStream printStream;

    /**
     * Constructs a print handler starting from a
     * {@link java.io.PrintStream}.
     *
     * @param printStream the print stream to use
     */
    public PrintHandler(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Handles the specified result, printing
     * the value or {@code NULL} to {@code stdout}.
     *
     * @param result the result to handle
     */
    @Override
    public void accept(Optional<Object> result) {
        if (result != null) {
            printStream.println(result.orElse(NULL));
        }
    }
}
