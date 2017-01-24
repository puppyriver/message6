package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.SortUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午3:27
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class MessageInterpreter {
    List<Field> messageFields = new ArrayList<Field>();

    private MessageInterpreter(Class msgCls) {
        Field[] fields = msgCls.getDeclaredFields();
        for (Field field : fields) {
            MessageField messageField = field.getAnnotation(MessageField.class);
            if (messageField != null)
                addMesageField(field);

        }

        SortUtil.sort(messageFields,new SortUtil.CompareAdapter() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Field)o1).getAnnotation(MessageField.class).index() - ((Field)o2).getAnnotation(MessageField.class).index();
            }
        });


    }

    public List<Field> getMessageFields() {
        return messageFields;
    }

    public void setMessageFields(List<Field> messageFields) {
        this.messageFields = messageFields;
    }



    private void addMesageField(Field field) {
        messageFields.add(field);
    }

    public static MessageInterpreter wrap(Class msgCls) {
        return new MessageInterpreter(msgCls);
    }
}
