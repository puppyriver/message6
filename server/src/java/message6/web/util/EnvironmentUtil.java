package message6.web.util;

import com.alcatelsbell.nms.util.SysProperty;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Ronnie.Chen
 * Date: 2016/9/14
 * Time: 14:12
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class EnvironmentUtil {
    private Logger logger = LoggerFactory.getLogger(EnvironmentUtil.class);
    public static final void initSpring() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    public static final void initJdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl(SysProperty.getString("env.db.url"));
        dataSource.setUsername(SysProperty.getString("env.db.mgr.user"));
        dataSource.setPassword(SysProperty.getString("env.db.mgr.password"));
        dataSource.setInitialSize(10);
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(10);
        dataSource.setValidationQuery("select count(1) from dual");

    //    DBServerUtils.setJdbcTemplate(new JdbcTemplate(dataSource));
    }

    public static void main(String[] args) throws Exception {
        EnvironmentUtil.initJdbcTemplate();

   //     JdbcTemplate jdbcTemplate = DBServerUtils.getJdbcTemplate();
     //   List list = JdbcTemplateUtil.queryForList(jdbcTemplate, R_Customer.class, "select * from r_customer where id < 740000");
       // System.out.println("list = " + list.size());
    }
}
