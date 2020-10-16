package io.coderr.client.reporter;

import io.coderr.client.dto.ContextCollectionDTO;

import javax.print.attribute.standard.Severity;
import java.util.*;

/**
 * Default implementation of @see {@link ErrorReporterContext}
 */
public class DefaultErrorReporterContext implements ErrorReporterContext {
    private Map<String, String> coderrCollection = new HashMap<>();
    private List<ContextCollectionDTO> collections = new LinkedList<>();
    private Object source;
    private Throwable exception;

    public DefaultErrorReporterContext(Object source, Throwable exception){
        if (exception == null){
            throw new IllegalArgumentException("exception");
        }

        this.source=source;
        this.exception = exception;
    }

    @Override
    public List<ContextCollectionDTO> getCollections() {
        return collections;
    }

    @Override
    public void addCollection(ContextCollectionDTO collection) {
        if (collection == null) {
            throw new IllegalArgumentException("collection");
        }

collections.add(collection);
    }

    @Override
    public void addTag(String tag) {
        if (tag == null){
            return;
        }

        tag=tag.replace(' ', '_');
        String current = coderrCollection.get("ErrTags");
        if (current == null){
            coderrCollection.put("ErrTags", tag);
        }else{
            coderrCollection.put("ErrTags", current + "," + tag);
        }
    }

    @Override
    public void suggestSeverity(Severity severity) {
        coderrCollection.put("Severity", severity.toString());
    }

    @Override
    public void setApplicationVersion(String version) {
        if (version == null || version == ""){
            return;
        }

        coderrCollection.put("AppAssemblyVersion", version);
    }

    @Override
    public void setEnvironment(String environment) {
        if (environment == null || environment == ""){
            return;
        }

        coderrCollection.put("Environment", environment);
    }

    @Override
    public void setLocation(Double latitude, Double longitude) {
        coderrCollection.put("Latitude", latitude.toString());
        coderrCollection.put("Longitude", longitude.toString());
    }

    @Override
    public Throwable getException() {
        return this.exception;
    }

    @Override
    public Object getReporter() {
        return source;
    }
}
