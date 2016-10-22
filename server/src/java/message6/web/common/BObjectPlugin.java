package message6.web.common;

import com.alcatelsbell.nms.valueobject.BObject;

import message6.web.controller.AbstractAjaxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 2016/10/19
 * Time: 8:27
 * rongrong.chen@alcatel-sbell.com.cn
 */
public abstract class BObjectPlugin {
    private Logger logger = LoggerFactory.getLogger(BObjectPlugin.class);
    private BObjectHelper crudHelper = null;

    public BObjectHelper getCrudHelper() {
        return crudHelper;
    }

    public void setCrudHelper(BObjectHelper crudHelper) {
        this.crudHelper = crudHelper;
    }

    public abstract Class getJavaClass();

    public abstract void onEvent(BObjectEvent event);
    public Object interceptQuery(HttpServletRequest request, HttpServletResponse response) {
        HashMap extend = AbstractAjaxController.extractSubMap(request.getParameterMap(), "extend");
        if (extend != null && extend.size() > 0) {
            String txt = ((String[]) extend.get("searchTxt"))[0];
            if (txt != null && txt.trim().length() > 0) {
                if (this instanceof BObjectPluginTxtSearchSupport) {
                    Integer start = AbstractAjaxController.extractInt(request, "start");
                    Integer limit = AbstractAjaxController.extractInt(request, "limit");
                    return ((BObjectPluginTxtSearchSupport)this).search(txt,start,limit,request,response);
                }

            }
        }
        return null;
    }

    public Object interceptQueryCount(HttpServletRequest request, HttpServletResponse response) {
        HashMap extend = AbstractAjaxController.extractSubMap(request.getParameterMap(), "extend");
        if (extend != null && extend.size() > 0) {
            String txt = ((String[]) extend.get("searchTxt"))[0];
            if (txt != null && txt.trim().length() > 0) {
                if (this instanceof BObjectPluginTxtSearchSupport) {
                    return ((BObjectPluginTxtSearchSupport)this).searchCount(txt,request,response);
                }

            }
        }
        return null;
    }
}
