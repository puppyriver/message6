package message6.web.controller;

import message6.web.common.QueryResult;
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
import java.util.*;

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

        executeTolerableSql("alter table xmessage add parentid bigint");
        executeTolerableSql("alter table xmessage add level integer");

    }

    private void executeTolerableSql(String sql) {
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e){
            logger.error("Failed to create table : XMessage "+e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

    }



    @RequestMapping(value="all")
    public @ResponseBody
    QueryResult queryAll(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String category = request.getParameter("category");
        Integer pageSzie =  extractInt(request,"pageSize");
        Integer currentPage = extractInt(request,"currentPage");
        String whereSql = "where parentid is null "+(category == null ? "":" and category = '"+category+"'")+" order by status desc, id desc";
        Map<String, Object> map = jdbcTemplate.queryForMap("select count(*) from XMessage " + whereSql);
        Object next = map.values().iterator().next();
        int count = ((Number) next).intValue();
        List list = JdbcTemplateUtil.queryForList(jdbcTemplate, XMessage.class, "select * from XMessage "+whereSql+((pageSzie != null && currentPage != null) ? " limit "+pageSzie*(currentPage-1)+","+pageSzie :""));
        fillChildren(list);
        return new QueryResult(count,10,currentPage,list);
    }


    private void fillChildren(List<XMessage> messages) throws Exception {
        StringBuffer sb = new StringBuffer(" (");
        int idx = 0;
        for (XMessage message : messages) {
            sb.append(message.getId());
            if (++idx < messages.size())
                sb.append(",");
        }
        sb.append(")");

        List<XMessage> list = JdbcTemplateUtil.queryForList(jdbcTemplate, XMessage.class, "select * from xmessage where parentid in " + sb.toString());

        HashMap<Long,XMessage> map = new HashMap<>();

        if (list != null && list.size() > 0) {
            for (XMessage message : messages) {
                map.put(message.getId(), message);
            }

            for (XMessage message : list) {
                XMessage pm = map.get(message.getParentId());
                HashMap attributes = pm.getAttributes();
                ArrayList children = (ArrayList) attributes.get("children");
                if (children == null) {
                    children = new ArrayList<>();
                    attributes.put("children", children);
                }
                children.add(message);
            }
        }
    }

    @RequestMapping(value="sub")
    public @ResponseBody
    List querySub(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Long parentId = extractLong(request,"parentId");
        if (parentId != null) {
            return JdbcTemplateUtil.queryForList(jdbcTemplate,XMessage.class,"select * from xmessage where parentid = "+parentId);
        }
        return new ArrayList();
    }

    @RequestMapping(value="queryCategory")
    public @ResponseBody
    List queryCategory(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select distinct category from xmessage");
        List<String> categorys = new ArrayList<>();
        for (Map<String, Object> map : maps) {

            categorys.add((String)map.values().toArray()[0]);
        }
        return categorys;
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
