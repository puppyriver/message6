package message6.web.util;

/**
 * Author: Ronnie.Chen
 * Date: 2015/7/15
 * Time: 10:49
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SqlUtil {
    public static String getPagerSQL(String originalSQL, Integer start,
                                   Integer limit) {
        return "select * from (select rownum as r,t.* from( " + originalSQL
                + ") t where rownum<= " + (start + limit) + ") where r>"
                + start;
    }

    public static String getCountSQL(String sql){
        String s = " select count(1) as COUNT from ( "+ sql + " )";
        return s;
    }
}
