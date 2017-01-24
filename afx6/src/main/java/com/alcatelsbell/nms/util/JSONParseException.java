package com.alcatelsbell.nms.util;

/**
 * User: change Via
 * Date: 12-6-26
 * Time: 下午12:51
 */
public class JSONParseException extends RuntimeException {
    private Throwable cause  = null;

    public JSONParseException(){
        super();
    }
    public JSONParseException(String message){
        super(message);
    }
    public JSONParseException(String message, Throwable cause) {
        super(message,cause);
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public <T extends Throwable> JSONParseException(T message){
         super(message);
         this.cause =message;
    }
}
