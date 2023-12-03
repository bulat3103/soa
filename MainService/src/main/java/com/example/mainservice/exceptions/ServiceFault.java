package com.example.mainservice.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFault", propOrder = {
        "code",
        "message",
        "time"
})
public class ServiceFault {
    private String code;
    private String message;
    private String time;

    public ServiceFault() {
    }

    public ServiceFault(String code, String message, String time) {
        this.code = code;
        this.message = message;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
