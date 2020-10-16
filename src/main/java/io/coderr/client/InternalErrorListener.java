package io.coderr.client;

/**
 * Tells when something goes wrong in the Coderr client.
 * <p>
 * Used since we do not want the client to throw unhandled exceptions.
 */
public interface InternalErrorListener {
    void signal(Object source, Throwable exception, String message);
}
