package io.coderr.client.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The error report which will be uploaded to the Coderr server for further analysis
 */
public class ErrorReportDTO {
    private List<ContextCollectionDTO> errorContextCollections = new ArrayList<>();

    public ErrorReportDTO(ExceptionDTO exception) {
        if (exception == null){
            throw new IllegalArgumentException("exception");
        }

        ReportId = newUUID();
        Environment = null;
        ReportVersion = "1.0";
        CreatedAtUtc = new Date();
        this.Exception = exception;
    }

    // Summary:
    //     A collection of context information such as HTTP request information or computer
    //     hardware info.
    public ContextCollectionDTO[] ContextCollections;

    // Summary:
    //     To get exact date
    public Date CreatedAtUtc;

    // Summary:
    //     Exception which was caught.
    public ExceptionDTO Exception;

    // Summary:
    //     Gets report id (unique identifier used in communication with the customer to
    //     identify this error)
    public String ReportId;

    // Summary:
    //     Version of the report API
    public String ReportVersion;

    // Summary:
    //     environment that the error was detected in (dev, test, prod)
    public String Environment;


    private static String newUUID() {
        Random random = new Random(System.currentTimeMillis());
        char[] chars = "0123456789012345678901".toCharArray();

        for (int i = 0; i < 22; i++) {
            chars[i] = (char)(random.nextInt(123-65)+65);
            if(chars[i] < 97 && chars[i] > 90)
                chars[i] = (char)(chars[i] - 41);
        }
        return new String(chars);
    }
}
