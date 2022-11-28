package org.elsys.ip.rest2;

public class ErrorModel {
    private final String reason;

    public ErrorModel(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
