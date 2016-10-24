package message6.web.controller;

import message6.web.model.XMessage;
import message6.web.util.IdentityUtil;
import message6.web.util.JdbcTemplateUtil;
import message6.web.util.SqliteDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 2016/10/22
 * Time: 13:09
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Controller
@RequestMapping("/list/*")
public class MessageListAjaxController extends AbstractAjaxController{
    private Logger logger = LoggerFactory.getLogger(MessageListAjaxController.class);


    private SqliteDataSource dataSource = null;
    private JdbcTemplate jdbcTemplate = null;
    private File dbDir = new File("../db");
    public MessageListAjaxController() {
        if (!dbDir.exists()) dbDir.mkdirs();
        dataSource = new SqliteDataSource("../db/message.db");
        jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            JdbcTemplateUtil.createTable(jdbcTemplate,XMessage.class,"XMessage");
        } catch (SQLException e) {
            logger.error("Failed to create table : XMessage "+e.getMessage());
        }  catch (DataAccessException e){
            logger.error("Failed to create table : XMessage "+e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    @RequestMapping(value="all")
    public @ResponseBody
    List<XMessage> queryAll(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        List list = JdbcTemplateUtil.queryForList(jdbcTemplate, XMessage.class, "select * from XMessage order by status desc, id desc limit 0,100");
        return list;
    }


    @RequestMapping(value="delete")
    public @ResponseBody
    HashMap delete(HttpServletRequest request, HttpServletResponse response) throws Throwable {
         Long id = extractLong(request, "id");
        JdbcTemplateUtil.remove(jdbcTemplate,"XMessage",id);
        HashMap map = new HashMap();
        map.put("result","success");
        return map;
    }

    @RequestMapping(value="save")
    public @ResponseBody
     XMessage save(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        try {
            Long id = extractLong(request, "id");
            Integer status = extractInt(request,"status");
            String content = request.getParameter("content");
            XMessage message = null;
            if (id != null && id > -1) {
                message = (XMessage)JdbcTemplateUtil.queryForObjectById(jdbcTemplate, XMessage.class, id);
                if (message != null) {
                    if (content != null)
                        message.setContent(content);
                    message.setTime(new Date());
                    if (status != null)
                        message.setStatus(status);
                }
            } else
                message = new XMessage(IdentityUtil.getId(dataSource.getConnection(),"XMessage"),content);


            if (id != null && id > -1 && message != null) {
                JdbcTemplateUtil.update(jdbcTemplate, "XMessage", message);
                return message;
            }
            else {

                XMessage _xMessage = (XMessage)JdbcTemplateUtil.insert(jdbcTemplate, "XMessage", message);
                return message;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }


}
