package io.coderr.client.reporter;

import io.coderr.client.dto.ContextCollectionDTO;
import javax.print.attribute.standard.Severity;
import java.util.List;

/**
 * Context used when gathering information for the error report.
 */
public interface ErrorReporterContext {

    /**
     * Collections which have been collected so far.
     * @return All added collections.
     */
    List<ContextCollectionDTO> getCollections();

    /**
     * Add a new context collection to the report
     * @param collection
     */
    void addCollection(ContextCollectionDTO collection);

    /**
     * Add a tag to the incident.
     * @param tag A string, one word.
     */
    void addTag(String tag);

    /**
     * Override Coderrs prioritization algorithm by manually specify severity.
     * @param severity Suggested severity.
     */
    void suggestSeverity(Severity severity);

    /**
     * Set application version.
     *
     * To allow Coderr to ignore error reports for older version, the versioning must be incremental (such as semantic versioning).
     *
     * @param version Version of the application (typically the entry jar).
     */
    void setApplicationVersion(String version);

    /**
     * In which environment the error occurred, as "Development", "Test" and "Production".
     *
     * @param environment Environment that the application is running in.
     */
    void setEnvironment(String environment);

    /**
     * Set where the error occurred.
     *
     * Allows Coderr to show on a map where most reports occurred.
     *
     * @param latitude Latitude
     * @param longitude Longitude
     */
    void setLocation(Double latitude, Double longitude);

    /**
     * Exception that the we are collecting information for.
     * @return exception.
     */
    Throwable getException();

    /**
     * Class that initiated the error reporting pipeline.
     * @return instance if other than manual reporting; otherwise null;
     */
    Object getReporter();
}

