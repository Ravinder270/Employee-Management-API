package com.global_groupware.employeemanagement.exception.managerException;

import java.time.LocalDateTime;
import java.util.List;

public class ManagerErrorResponse {
    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime timestamp;

    public ManagerErrorResponse()
    {

    }

    public ManagerErrorResponse(int status, String error, List<String> messages, LocalDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
