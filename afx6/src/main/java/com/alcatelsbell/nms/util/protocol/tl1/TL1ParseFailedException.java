/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alcatelsbell.nms.util.protocol.tl1;

/**
 *
 * @author Ronnie.Chen
 */
public class TL1ParseFailedException extends Exception{
    public TL1ParseFailedException(String msg) {
        super(msg);
    }
    
    public TL1ParseFailedException(String message, Throwable cause) {
        super(message, cause);
    }    
    
    public TL1ParseFailedException(Throwable cause) {
        super(cause);
    }    
    
}
