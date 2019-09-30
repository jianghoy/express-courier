package com.fcv.expressCourier.payload;

public class APIResponse {
    private Boolean success;

    public APIResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
