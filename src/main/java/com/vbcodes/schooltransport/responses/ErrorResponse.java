package com.vbcodes.schooltransport.responses;

public class ErrorResponse {
    private String errorMessage;
    private String redirectUrl;

    public ErrorResponse(String errorMessage, String redirectUrl) {
        this.errorMessage = errorMessage;
        this.redirectUrl = redirectUrl;
    }

    public String geterrorMessage() {
        return errorMessage;
    }

    public void seterrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
