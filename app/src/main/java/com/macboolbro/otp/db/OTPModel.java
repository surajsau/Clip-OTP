package com.macboolbro.otp.db;

/**
 * Created by MacboolBro on 11/02/16.
 */
public class OTPModel {

    private long id;
    private String otp;
    private String message;
    private String sender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        OTPModel obj = (OTPModel)o;
        return (id == obj.getId())
                && (otp.equals(obj.getOtp()))
                && (message.equals(obj.getMessage()))
                && (sender.equals(obj.getSender()));
    }

    @Override
    public int hashCode() {
        Long _id = new Long(id);
        return _id.hashCode() + otp.hashCode() + message.hashCode() + sender.hashCode();
    }
}
