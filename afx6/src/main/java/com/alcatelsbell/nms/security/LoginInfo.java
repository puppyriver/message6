package com.alcatelsbell.nms.security;

import com.alcatelsbell.nms.valueobject.domain.Operator;

import java.io.Serializable;

/**
 * User: Ronnie
 * Date: 12-3-19
 * Time: 下午1:37
 */
public class LoginInfo implements Serializable {
    private Operator operator = null;
    public String securitykey;
    public int  result;
    public String version;

    //public Vector securitys; //maintain the Security infomation,including its parameter.
    public long curTime;

   // private SecurityInfo securityInfo;                         //

   /* *         loginResultState_Normal:Number=0;//一般状态，等待用户输入 public static
    *         const loginResultState_PswExp:Number=1;//密码过期 public static const
    *         loginResultState_AccountExp:Number=2;//账号过期 public static const
    *         loginResultState_WrongPsw:Number=3;//密码/账号错误 public static const
    *         loginResultStateLoginSucc:Number=4;//登录成功 public static const
    *         loginResultState_SysError:Number=5;//系统错误 public static const
    *         loginResultState_Disable:Number=6;//登录界面关闭
    *         **/
    public static final int PwdExpire=1;
    public static final int AccountExpire=2;
    public static final int NamePwdErr=3;
    public static final int Success=4;
    public static final int SystemErr=5;
    public static final int AccountDisable=6;



    public static final int ACCOUNTSTATE_ABLE=0;//启用状态
    public static final int ACCOUNTSTATE_DISABLE=1;//禁用状态






    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getSecuritykey() {
        return securitykey;
    }

    public void setSecuritykey(String securitykey) {
        this.securitykey = securitykey;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }
}
