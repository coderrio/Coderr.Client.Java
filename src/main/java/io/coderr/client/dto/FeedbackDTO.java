package io.coderr.client.dto;

/**
 * To be able to write feedback after the actual error have been sent.
 */
public class FeedbackDTO
{
    /**
     *     Description written by the user about what he/she did when the error occurred.
     */
    public String Description;

    /**
     * Email address to user (if he/she would like to get status updates)
     */
    public String EmailAddress;

    /**
     * Report that the user interaction should be attached to.
     */
    public String ReportId;
}