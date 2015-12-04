package com.database.command;

import com.database.command.impl.EmptyCommand;
import com.database.command.impl.EndCommand;
import com.database.command.impl.GetCommand;
import com.database.command.impl.SetCommand;
import com.database.command.impl.CountCommand;
import com.database.command.impl.UnsetCommand;

import java.util.function.Function;


/**
 * Implementation of a {@link Function<String, ICommand>} to
 * serve as factory for command instances. It parses the input
 * according to this grammar:
 * <p>{@code cmd_string: cmd_key [arg1 [arg2]]}
 * <p>{@code cmd_key: SET|GET|UNSET|NUMEQUALTO|END|BEGIN|COMMIT|ROLLBACK
 */
public class Factory implements Function<String, ICommand> {

    private static final String SEPARATOR = " ";

    /**
     * Parses the input and returns the related command
     * instance or an {@link EmptyCommand} if the {@code cmd_key}
     * is not recognized.
     *
     * @param command the {@code cmd_string} to parse
     * @return the parsed command instance or an {@link EmptyCommand}
     * if the {@code cmd_key} is not recognized
     */
    @Override
    public ICommand apply(String command) {
        final String[] tokens = command.split(SEPARATOR);
        final Type type = Type.getFromName(tokens[0]);
        if (type == null) {
            return new EmptyCommand(Type.EMPTY);
        }
        switch(type) {
            case SET:
                return new SetCommand(tokens[1], tokens[2]);
            case GET:
                return new GetCommand(tokens[1]);
            case UNSET:
                return new UnsetCommand(tokens[1]);
            case NUMEQUALTO:
                return new CountCommand(tokens[1]);
            case END:
                return new EndCommand();
            default:
                return new EmptyCommand<>(type);
        }
    }
}
