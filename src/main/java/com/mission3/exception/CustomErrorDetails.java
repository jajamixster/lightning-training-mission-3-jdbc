package com.mission3.exception;

import java.util.Date;

//Simple custom error details bean
public class CustomErrorDetails {
    private Date timestamp;
    private String message;
    private String errordetails;

    //Fields Constructor
    public CustomErrorDetails(Date timestamp,
                              String message,
                              String errordetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errordetails = errordetails;
    }

    //GETTER
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrordetails() {
        return errordetails;
    }
}
