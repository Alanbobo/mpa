package com.commandcenter.model.mess;

import java.util.List;

public class TaskForApp {
    private String errorMessage;

    private String exception;

    private List<ActivityTask> result;

    private boolean success;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<ActivityTask> getResult() {
        return result;
    }

    public void setResult(List<ActivityTask> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
