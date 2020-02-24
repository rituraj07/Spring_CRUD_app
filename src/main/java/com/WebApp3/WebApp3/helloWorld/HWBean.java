package com.WebApp3.WebApp3.helloWorld;

public class HWBean {
    private String message;
    public HWBean(String message) {
        this.message = message;
    }

   /* public void setMessage(String message) {
        this.message = message;
    }
*/
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("HWBean [message=%s]",message);
    }
}
