package com.alcatelsbell.nms.common;


import java.util.Vector;

/**
     * <p>Title: </p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2011</p>
     *
     * <p>Company: asb</p>
     *
     * @author Ronnie
     * @version 1.0
     */
    public class ServiceException extends Exception
    {
        Vector errList = null;
        String errCode = null;
        String errMsg = null;
        public ServiceException(Exception ex) {
           this(ex.getMessage(), ex.getMessage(), ex);
        }
        public ServiceException( String _errCode, String _errMsg) {
            this(_errCode, _errMsg, null);
        }

        public ServiceException( String _errCode, String _errMsg, Exception ex )
        {
            errCode = _errCode;
            errMsg = _errMsg;
            if( ex != null )
            {
                if( errList == null )
                {
                    errList = new Vector();
                }
                errList.addElement( ex );
            }
        }

        public ServiceException( String _errCode )
        {
            errCode = _errCode;
        }

        public ServiceException( String _errCode, Exception ex )
        {
            errCode = _errCode;
            if( ex != null )
            {
                if( errList == null )
                {
                    errList = new Vector();
                }
                errList.addElement( ex );
            }
        }

        public String getErrCode()
        {
            return errCode;
        }

        public String getMessage()
        {
            String errMsg = "";
            String msg = null;//AppExceptionCodeMessage.instance().getMessage( errCode );
            if( msg == null || "".equals( msg ) )
            {
                errMsg = (this.errMsg != null ? this.errMsg : "");
            }
            else
            {
                errMsg = msg;
            }
            if(errList!=null) {
                for(int i=0;i<errList.size();i++) {
                    Exception ex = (Exception)errList.get(i);
                    errMsg += "\n( Err"+ (i+1)+ " : " + (ex)+")";
                }
            }
            return errCode + " : " + errMsg;
        }

//    public String getStackTrace()
//    {
//        return "";
//    }
        public Vector getErrList()
        {
            return errList;
        }
    }
