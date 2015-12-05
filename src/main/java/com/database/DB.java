package com.database;

import com.database.command.ICommand;
import com.database.command.Type;
import com.database.data.DataFrame;
import com.database.data.IDataFrame;

import java.util.*;

/**
 * Implementation of the {@link com.database.IDB} interface,
 * supporting transactions, and returning {@link java.lang.Object}
 * as results to encompass {@link java.lang.Integer} and {@link java.lang.String}
 * results.
 *
 * @param <K> the type of keys maintained by this database
 * @param <V> the type of mapped values
 */
public class DB<K, V> implements IDB<K, V, Object> {

    private static final Set<Type> TX_MODIFIERS = new HashSet<>(Arrays.asList(Type.SET, Type.UNSET));

    private final IDataFrame<K, V, Object> base;
    private final Stack<IDataFrame<K, V, Object>> txs;

    /**
     * Constructs a db starting from a base data frame
     * and a {@link java.util.Stack} of transactions.
     *
     * @param base the base data frame
     * @param txs the {@link java.util.Stack} of transactions
     */
    public DB(IDataFrame base, Stack<IDataFrame<K, V, Object>> txs) {
        this.base = base;
        this.txs = txs;
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
