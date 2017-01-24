package com.alcatelsbell.nms.db.components.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.hibernate.jdbc.Work;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Ronnie
 * Date: 11-9-28
 * Time: 上午9:08
 */
public class OracleUtil {
    public static void truncateTable(JPASupport ctx,final String tableName) throws SQLException{
        EntityManager entityManager = ctx.getEntityManager();

        HibernateEntityManager hibernateEntityManager = (HibernateEntityManager)entityManager;
        entityManager.getTransaction().begin();
       hibernateEntityManager.getSession().doWork(new Work() {
           @Override
           public void execute(Connection connection) throws SQLException {
               connection.prepareStatement("truncate table "+tableName).execute();

           }
       });

    }

    public static void executeNativeSql(JPASupport ctx,String sql) throws Exception{
        EntityManager entityManager = ctx.getEntityManager();

        entityManager.getTransaction().begin();
     //   Connection connection =(Connection) EntityManagerImpl.class.getMethod("getSession").invoke(entityManager);
    //    Session session = entityManager.unwrap(Session.class);

     //   Connection connection = session.connection();
        // Object o = entityManager.unwrap(Connection.class);
         entityManager.createNativeQuery(sql).executeUpdate();
       // java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
        try {
//            connection.createStatement().executeUpdate(sql);
//            connection.commit();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
             
                entityManager.getTransaction().rollback();
           throw e;
            
        }
    }

    public static void main(String[] args) throws Exception {
        String[] locations = {"appserver-spring.xml"};
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext(locations);

        JPASupport ctx = JPASupportFactory.createJPASupport();
        try {
            EntityManager entityManager = ctx.getEntityManager();
            if (entityManager instanceof HibernateEntityManager) {
                Session session = ((HibernateEntityManager) entityManager).getSession();
                session.doWork(new Work() {
                    @Override
                    public void execute(Connection connection) throws SQLException {
                        CallableStatement cstmt = connection.prepareCall("{call callIrmAutoMigrate(?)}");
                        cstmt.setString(1,"ems2");
                        cstmt.execute();
                       // logger.info("callIrmAutoMigrate sucess");
                    }
                });
            }
        } catch (HibernateException e) {
           e.printStackTrace();
        }

        finally {
            ctx.release();
        }

    }
}
