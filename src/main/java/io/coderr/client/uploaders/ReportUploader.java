package io.coderr.client.uploaders;

import io.coderr.client.dto.ErrorReportDTO;

/**
 * Defines the class used to upload reports to a server for further analysis.
 */
public interface ReportUploader {
    /**
     * Enqueue a report for upload.
     *
     * Report might be enqueued to batch reports, or to wait for an internet connection.
     * @param report Report to be uploaded.
     */
    void upload(ErrorReportDTO report);

}

