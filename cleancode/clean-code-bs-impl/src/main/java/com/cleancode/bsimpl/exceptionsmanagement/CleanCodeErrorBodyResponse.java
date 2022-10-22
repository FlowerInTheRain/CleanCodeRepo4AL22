package com.cleancode.bsimpl.exceptionsmanagement;

import org.springframework.http.HttpStatus;

public record CleanCodeErrorBodyResponse(HttpStatus responseStatus, String message, String origin) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CleanCodeErrorBodyResponse that = (CleanCodeErrorBodyResponse) o;

        if (responseStatus != that.responseStatus) return false;
        if (!message.equals(that.message)) return false;
        return origin.equals(that.origin);
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
