package io.coderr.client;

import java.util.LinkedList;

/**
 * Used to notify external parties of internal errors in the Coderr client.
 */
public class ErrorDispatcher {
    public static ErrorDispatcher Instance = new ErrorDispatcher();
    private final LinkedList<InternalErrorListener> listeners = new LinkedList<>();

    /**
     * Add a new listener.
     * @param listener Object which want to receive internal errors.
     */
    public void addErrorListener(InternalErrorListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener");
        }

        this.listeners.add(listener);
    }

    /**
     * Publish a new error.
     * @param source Object that the exception was caught in.
     * @param ex Exception
     * @param message What the code tried to do when the exception occurred.
     */
    public void publish(Object source, Exception ex, String message) {
        for (InternalErrorListener listener : listeners) {
            listener.signal(source, ex, message);
        }
    }
}
