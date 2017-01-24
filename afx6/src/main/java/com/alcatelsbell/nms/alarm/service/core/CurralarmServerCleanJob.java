package com.alcatelsbell.nms.alarm.service.core;

//import com.alcatelsbell.nms.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;

/**
 * User: Ronnie
 * Date: 12-1-10
 * Time: 下午2:40
 */
public class CurralarmServerCleanJob
        implements Job {
    private Log logger = LogFactory.getLog(getClass());
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            CurralarmServer.getInstance().runCleanCurralarm();
         } catch (Exception e) {
            logger.error(e,e);
        }
        try {
            CurralarmServer.getInstance().runClearAlamrinformation();
        } catch (Exception e) {
            logger.error(e,e);
        }

//        try {
//            File currDir = new File(".").getCanonicalFile();
//            File[] files = currDir.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isDirectory() && file.getName().startsWith("temp_") &&
//                            file.lastModified() < (System.currentTimeMillis() - 5 * 3600 * 1000l))
//                        FileUtil.deleteFile(file);
//                }
//            }
//        } catch (IOException e) {
//            logger.error(e, e);
//        }

    }


}
