package com.database;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.DataFrame;
import com.database.data.IDataFrame;

import java.util.*;

/**
 * Implementation of the {@link IDB} interface,
 * supporting transactions, and returning {@link Object}
 * as results to encompass {@link Integer} and {@link String}
 * results.
 *
 * @param <K> the type of keys maintained by this database
 * @param <V> the type of mapped values
 */
public class DB<K, V> implements IDB<K, V, Object> {

    private static final Set<Type> TX_MODIFIERS = new HashSet<>(Arrays.asList(Type.SET, Type.UNSET));

    private IDataFrame<K, V, Object> base;
    private Stack<IDataFrame<K, V, Object>> txs;

    /**
     * Basic constructor.
     */
    public DB() {
        base = new DataFrame<>(null);
        txs = new Stack<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Object> execute(ICommand<K, V, Object> command) {
        if (TX_MODIFIERS.contains(command.getType())) {
            getCurrentFrame().addTxModifier(command);
        }
        switch(command.getType()) {
            case BEGIN:
                txs.push(new DataFrame<>(getCurrentFrame()));
                return null;
            case ROLLBACK:
                try {
                    txs.pop();
                    return null;
                } catch (EmptyStackException e) {
                    return Optional.of("NO TRANSACTION");
                }
            case COMMIT:
                txs.iterator().forEachRemaining(tx -> tx.getTxModifiers().forEachRemaining(cmd -> cmd.execute(base)));
                txs.removeAllElements();
                return null;
            case END:
                return command.execute(null);
            default:
                return command.execute(getCurrentFrame());
        }
    }

    private IDataFrame<K, V, Object> getCurrentFrame() {
        IDataFrame<K, V, Object> retVal = base;
        try {
            retVal = txs.peek();
        } catch (EmptyStackException e) {
            // debug
        }
        return retVal;
    }
}
