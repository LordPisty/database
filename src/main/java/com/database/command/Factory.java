package com.database.command;

import static com.database.command.Type.*;

/**
 * Created by screspi on 12/1/15.
 */
public class Factory implements IFactory{

    @Override
    public ICommand apply(String s) {
        switch(valueOf(s)) {
            case DEFAULT:
                return new Command();
        }
        return null;
    }
}
