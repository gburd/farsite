package com.example.farsite.util;

import com.google.common.collect.Lists;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.tools.profiler.PerformanceProfiler;

public class CrDBSessionCustomizer implements SessionCustomizer {

    public void customize(Session session) throws Exception {

        // Enable concurrent processing of result sets and concurrent loading of load groups.
        ((AbstractSession)session).setIsConcurrent(true);

        // Add sequence generators.
        Lists.newArrayList(
                new UUIDSequence("uuid-seq"),
                new PSRNSequence("psrn-seq"),
                new FlakeSequence("flake-seq"))
        .forEach(sequence -> {session.getLogin().addSequence(sequence);});
    }

}
