package com.database;

import com.database.command.Factory;
import com.database.command.ICommand;
import com.database.data.DataFrame;
import com.database.result.Handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by screspi.
 */
public class Main {

    public static void main(String[] args) {
        // setup
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final Function<String, ICommand> commandFactory = new Factory();
        final DB db = new DB(new DataFrame<>(null), new Stack<>());
        final Consumer resultHandler = new Handler();

        // stream
        in.lines().
                map(commandFactory).
                map(db::execute).
                forEach(resultHandler::accept);
    }
}
