package io.coderr.client.reporter;

/**
 * Defines how severe an error is.
 */
public enum ErrorSeverity {
    /**
     * Use Coders normal prioritization algorithm.
     */
    Normal,

    /**
     * This error needs higher attention (prioritize it before other errors).
     */
    High,

    /**
     * This error needs immediate attention.
     */
    Panic
}
