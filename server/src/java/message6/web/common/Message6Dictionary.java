package message6.web.common;

import message6.web.common.annotation.DicClass;
import message6.web.common.annotation.DicItem;

import java.lang.reflect.Field;

/**
 * Author: Ronnie.Chen
 * Date: 2016/9/14
 * Time: 15:51
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class Message6Dictionary {

    @DicClass
    public static class Boolean {
        @DicItem(desc = "是",code = "TRUE")
        public static int yes = 1;

        @DicItem(desc = "否",code = "FALSE")
        public static int no = 0;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field yes = Boolean.class.getField("yes");
        Object o = yes.get(Boolean.class);
        System.out.println("o = " + o);
    }
}
