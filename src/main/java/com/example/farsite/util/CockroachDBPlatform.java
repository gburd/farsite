package com.example.farsite.util;

import org.eclipse.persistence.platform.database.PostgreSQLPlatform;
import org.eclipse.persistence.queries.ValueReadQuery;

import javax.annotation.Generated;

public class CockroachDBPlatform extends PostgreSQLPlatform {

    public CockroachDBPlatform() {
        super();
    }

    @Override
    public boolean supportsIdentity() {
        return true;
    }

    @Override
    public boolean supportsSequenceObjects()
    {
        return false;
    }

    @Override
    public boolean canBuildCallWithReturning()
    {
        return true;
    }

    public boolean shouldPrintAliasForUpdate() {
        return true;
    }

    public boolean isCockroachDB() {
        return true;
    }

}
