package com.database.command;

import com.database.command.impl.EmptyCommand;
import com.database.command.impl.EndCommand;
import com.database.command.impl.GetCommand;
import com.database.command.impl.SetCommand;

import static com.database.command.Type.SET;
import static com.database.command.Type.GET;

/**
 * Created by screspi on 12/1/15.
 */
public class Factory implements IFactory{

    @Override
    public ICommand apply(String command) {
        final String[] tokens = command.split(" ");
        final Type type = Type.getFromName(tokens[0]);
        if (type == null) {
            return new EmptyCommand();
        }
        switch(type) {
            case SET:
                return new SetCommand(tokens[1], tokens[2]);
            case GET:
                return new GetCommand(tokens[1]);
            case END:
                return new EndCommand();
            default:
                return new EmptyCommand();
        }
    }
}
