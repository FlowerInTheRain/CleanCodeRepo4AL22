package com.cleancode.bsimpl.exceptionsmanagement;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CleanCodeBodyResponse {
    private String origin;
    private HttpStatus responseStatus;
    private String message;


    public CleanCodeBodyResponse(HttpStatus responseStatus, String message, String origin) {
        this.responseStatus = responseStatus;
        this.message = message;
        this.origin = origin;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanCodeBodyResponse that = (CleanCodeBodyResponse) o;

        if (responseStatus != that.responseStatus) return false;
        if (!message.equals(that.message)) return false;
        return origin.equals(that.origin);
    }

    @Override
    public int hashCode() {
        int result = responseStatus.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + origin.hashCode();
        return result;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "CleanCodeBodyResponse{" +
                "responseStatus=" + responseStatus +
                ", message='" + message + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
