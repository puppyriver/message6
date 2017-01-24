package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.LogUtil;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-28
 * Time: 下午1:59
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class DITPSocketMessageFacotory extends SocketMessageFactory {


    public DITPSocketMessageFacotory() {



    }

    @Override
    public Object createMessageBodyForDecoding(SocketMessage message) {
        int commmand =  (((DITPSocketMessage)message).getCmd()) ;

        Class cls = Bodys.commands.get(commmand);

        try {
            if (cls != null) {
                Object body =  cls.newInstance();
                message.setMessageBody(body);
                if (body instanceof SocketMessageBody)
                    ((SocketMessageBody) body).setSocketMessage(message);
                return body;
            }
        } catch ( Exception e) {
            LogUtil.error(getClass(), e, e);
        }
        return null;
    }



    @Override
    public SocketMessage createMessageForDecoding() {
        return DITPSocketMessage.prepareDecoding(this);
    }


}
