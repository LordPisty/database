package com.database.command;

import com.database.command.impl.EmptyCommand;
import com.database.command.impl.EndCommand;
import com.database.command.impl.GetCommand;
import com.database.command.impl.SetCommand;
import com.database.command.impl.CountCommand;
import com.database.command.impl.UnsetCommand;


/**
 * Created by screspi on 12/1/15.
 */
public class Factory implements IFactory{

    @Override
    public ICommand apply(String command) {
        final String[] tokens = command.split(" ");
        final Type type = Type.getFromName(tokens[0]);
        if (type == null) {
            return new EmptyCommand(Type.EMPTY);
        }
        switch(type) {
            case SET:
                return new SetCommand(Type.SET, tokens[1], tokens[2]);
            case GET:
                return new GetCommand(Type.GET, tokens[1]);
            case UNSET:
                return new UnsetCommand(Type.UNSET, tokens[1]);
            case NUMEQUALTO:
                return new CountCommand(Type.NUMEQUALTO, tokens[1]);
            case END:
                return new EndCommand(Type.END);
            default:
                return new EmptyCommand<>(type);
        }
    }
}
