package io.coderr.client.providers;

import io.coderr.client.reporter.ErrorReporterContext;

/**
 * Defines the interface for classes that adds telemetry data to the error report.
 */
public interface ContextCollectionProvider {
    /**
     * Collect information from the provider.
     */
    void collect(ErrorReporterContext context);
}
