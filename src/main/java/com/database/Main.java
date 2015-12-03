package com.database;

import com.database.command.Factory;
import com.database.command.IFactory;
import com.database.result.Handler;
import com.database.result.IHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by screspi.
 */
public class Main {

    public static void main(String[] args) {
        // setup
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final IFactory commandFactory = new Factory();
        final IHandler resultHandler = new Handler();
        final DB db = new DB();

        // stream
        in.lines().
                map(commandFactory).
                map(db::execute).
                forEach(resultHandler::handle);
    }
}
