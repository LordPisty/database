package com.database.command;

import java.util.function.Function;

/**
 * Created by screspi on 12/1/15.
 */
public interface IFactory extends Function<String, ICommand> {
}
