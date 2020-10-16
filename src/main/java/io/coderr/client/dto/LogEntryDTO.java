package io.coderr.client.dto;

import java.util.Date;

public class LogEntryDTO {
    /**
     * @param timestampUtc when
     * @param logLevel     0 = trace, 1 = debug, 2 = info, 3 = warning, 4 = error, 5 = critical
     * @param message      Message
     */
    public LogEntryDTO(Date timestampUtc, int logLevel, String message) {
        TimestampUtc = timestampUtc;
        LogLevel = logLevel;
        Message = message;
    }

    /**
     * Serialization constructor
     */
    protected LogEntryDTO() {
    }

    /**
     * Exception as string (if any was attached to this log entry)
     */
    public String Exception;

    /**
     * 0 = trace, 1 = debug, 2 = info, 3 = warning, 4 = error, 5 = critical
     */
    public int LogLevel;

    /**
     * Logged message
     */
    public String Message;

    /**
     * Location in the code that generated this log entry. Can be null.
     */
    public String Source;

    /**
     * When this log entry was written
     */
    public Date TimestampUtc;
}