package com.example.farsite.util;

import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.queries.DatabaseQueryMechanism;
import org.eclipse.persistence.queries.Call;
import org.eclipse.persistence.queries.DatabaseQuery;

public class GeneratedIdentityValue implements Call {
    /**
     * INTERNAL:
     * Return the appropriate mechanism,
     * with the call set as necessary.
     *
     * @param query
     */
    @Override public DatabaseQueryMechanism buildNewQueryMechanism(DatabaseQuery query) {
        return null;
    }

    /**
     * INTERNAL:
     * Return the appropriate mechanism,
     * with the call added as necessary.
     *
     * @param query
     * @param mechanism
     */
    @Override public DatabaseQueryMechanism buildQueryMechanism(DatabaseQuery query, DatabaseQueryMechanism mechanism) {
        return null;
    }

    /**
     * INTERNAL:
     * Return a clone of the call.
     */
    @Override public Object clone() {
        return null;
    }

    /**
     * INTERNAL:
     * Return a string appropriate for the session log.
     *
     * @param accessor
     */
    @Override public String getLogString(Accessor accessor) {
        return null;
    }

    /**
     * INTERNAL:
     * Return whether the call is finished returning
     * all of its results (e.g. a call that returns a cursor
     * will answer false).
     */
    @Override public boolean isFinished() {
        return false;
    }

    /**
     * The return type is one of, NoReturn, ReturnOneRow or ReturnManyRows.
     */
    @Override public boolean isNothingReturned() {
        return false;
    }

    /**
     * The return type is one of, NoReturn, ReturnOneRow or ReturnManyRows.
     */
    @Override public boolean isOneRowReturned() {
        return false;
    }
}
