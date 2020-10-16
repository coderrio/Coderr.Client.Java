package io.coderr.client.utilities;

public class LogicalErrorException extends Exception {
    public LogicalErrorException(String message) {
        super(message);
        try {
            StackTraceElement[] stackTrace = this.getStackTrace();
            StackTraceElement ste = stackTrace[2];
            this.setStackTrace(new StackTraceElement[]{ste});
        } catch (Exception ignore) {
        }
    }
}
