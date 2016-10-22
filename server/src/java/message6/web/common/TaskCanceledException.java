package message6.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Ronnie.Chen
 * Date: 2016/10/21
 * Time: 9:58
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class TaskCanceledException extends Exception{

        public TaskCanceledException(String group) {
            super("Task :"+group+" canceled !");
        }

}
