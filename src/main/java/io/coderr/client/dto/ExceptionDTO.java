package io.coderr.client.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Represents an exception being transferred for analysis
 */
public class ExceptionDTO {

    ExceptionDTO() {
        Properties = new HashMap<>();
    }
    public ExceptionDTO(Throwable exception) {
        copy(exception, this);
    }
    public ExceptionDTO(ExceptionDTO exception) {
        copy(exception, this);
    }

    /**
     * Java library name
      */
    public String AssemblyName;

    /**
     * Exception base classes. Most specific first: ArgumentOutOfRangeException, ArgumentException, Exception.
     **/
    public String[] BaseClasses;

    /**
     * Full type name (namespace + class name)
     */
    public String FullName;

    /**
     * Inner exception (if any; otherwise null).
     */
    public ExceptionDTO InnerException;

    /**
     * Exception message
      */
    public String Message;

    /**
     * Type name (of the exception)
      */
    public String Name;

    /**
     * Package name
      */
    public String Namespace;

    /**
     * All exception properties (public and private)
      */
    public Map<String, String> Properties;

    /**
       *Stack trace, line numbers included if your app also distributes the PDB files.
     */
    public String StackTrace;


    private static void copy(Throwable source, ExceptionDTO destination) {
        createExceptionObject(source, source.getClass(), destination);
    }

    private static void copy(ExceptionDTO source, ExceptionDTO destination) {
        if (source == null)
            return;
        destination.BaseClasses = source.BaseClasses;
        destination.AssemblyName = source.AssemblyName;
        destination.FullName = source.FullName;
        destination.Message = source.Message;
        destination.Name = source.Name;
        destination.Namespace = source.Namespace;
        destination.StackTrace = source.StackTrace;

        if (source.InnerException != null)
            destination.InnerException = new ExceptionDTO(source.InnerException);
    }

    private static void createExceptionObject(Throwable exception, Class<? extends Throwable> aClass, ExceptionDTO destination) {
        if (exception == null)
            return;
        destination.BaseClasses = collectClasses(aClass);
        destination.AssemblyName = exception.getClass().getPackage().getName();
        destination.FullName = exception.getClass().getCanonicalName();
        destination.Message = exception.getMessage();
        destination.Name = exception.getClass().getSimpleName();
        destination.Namespace = exception.getClass().getPackage().getName();
        destination.StackTrace = buildStackTraceString(exception.getStackTrace());

        Throwable cause = exception.getCause();
        if (cause != null)
            destination.InnerException = new ExceptionDTO(cause);
    }

    private static <T extends Throwable> String[] collectClasses(Class<T> aClass) {
        List<String> names = new ArrayList<>();
        Class<T> cls = aClass;
        //noinspection unchecked
        do {
            names.add(cls.getName());
        } while ((cls = (Class<T>) cls.getSuperclass()) != null);
        return names.toArray(new String[0]);
    }

    private static String buildStackTraceString(StackTraceElement[] stackTrace) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            builder.append(buildStackTraceString(stackTraceElement));
        }
        return builder.toString();
    }
    private static String buildStackTraceString(StackTraceElement stackTrace) {
        return String.format("at %s->%s(%s:%d)\n", stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getFileName(), stackTrace.getLineNumber());
    }

}
