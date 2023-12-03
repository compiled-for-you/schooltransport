package com.vbcodes.schooltransport.responses;

public class SuccessResponse {
    private String successMessage;
    private String redirectUrl;

    public SuccessResponse(String successMessage, String redirectUrl) {
        this.successMessage = successMessage;
        this.redirectUrl = redirectUrl;
    }

    public String getsuccessMessage() {
        return successMessage;
    }

    public void setsuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
