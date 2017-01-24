/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alcatelsbell.nms.util.protocol.tl1;

/**
 *
 * @author Ronnie.Chen
 */
public class ErrorReturnException extends Exception{
    public ErrorReturnException(String msg) {
        super(msg);
    }

    public ErrorReturnException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorReturnException(Throwable cause) {
        super(cause);
    }    
}
