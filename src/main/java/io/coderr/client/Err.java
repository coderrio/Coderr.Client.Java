package io.coderr.client;

import io.coderr.client.dto.ContextCollectionDTO;
import io.coderr.client.reporter.DefaultErrorReporterContext;
import io.coderr.client.reporter.ErrorReporterContext;
import io.coderr.client.utilities.LogicalErrorException;
import java.util.*;

/**
 * Facade for the Coderr Client.
 */
public class Err {
    /**
     * Report a new error
     * @param exception Exception to report.
     */
    public static void report(Throwable exception) {
        try {
            reportInternal(new DefaultErrorReporterContext(null, exception));
        } catch (Exception ignore) {
            ErrorDispatcher.Instance.publish(null, ignore, "Failed to report error");
        }
    }

    /**
     * Report a new error and attach context data.
     * @param exception Exception to report.
     * @param contextData Information which will aid in solving the error. Can contain complext objects (like a User class etc).
     */
    public static void report(Throwable exception, Map<String, Object> contextData) {
        try {
            ContextCollectionDTO contextCollection = createContextData(contextData);
            ErrorReporterContext context=new DefaultErrorReporterContext(null, exception);
            context.addCollection(contextCollection);
            reportInternal(context);
        } catch (Exception ignore) {
            ErrorDispatcher.Instance.publish(null, ignore, "Failed to report error");
        }
    }

    /**
     * Report an error
     * @param context
     */
    public static void report(ErrorReporterContext context) {
        try {
            reportInternal(context);
        } catch (Exception ignore) {
            ErrorDispatcher.Instance.publish(null, ignore, "Failed to report error");
        }
    }

    private static ContextCollectionDTO createContextData(Map<String, Object> contextData){
        return null;
    }

    public static void reportLogicalError(String message, Map<String, Object> contextData) {
        Throwable exception = new LogicalErrorException(message);
        ErrorReporterContext context = new DefaultErrorReporterContext(null, exception);
        reportInternal(context);
    }

    public static void reportInternal(ErrorReporterContext context) {
    }

}
