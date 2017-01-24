package com.alcatelsbell.nms.util.protocol;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

/**
 * Author: Ronnie.Chen
 * Date: 13-9-16
 * Time: 上午10:52
 * rongrong.chen@alcatel-sbell.com.cn
 */

/**
 * @author YangHua
 * 转载请注明出处：http://www.xfok.net/2009/10/124485.html
 */
public class SFtpFunc {

    /**
     * 连接sftp服务器
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public ChannelSftp connect(String host, int port, String username,
                               String password) throws  Exception {
        ChannelSftp sftp = null;

            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");

        return sftp;
    }

    /**
     * 上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) throws  Exception {

            sftp.cd(directory);
            File file=new File(uploadFile);
        FileInputStream fis = new FileInputStream(file);
        sftp.put(fis, file.getName());
        fis.close();

    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) throws Exception {

            sftp.cd(directory);
            File file=new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));

    }


    public void mkdir(String path,ChannelSftp sftp) throws SftpException {
        sftp.mkdir(path);
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {

            sftp.cd(directory);
            sftp.rm(deleteFile);

    }

    /**
     * 列出目录下的文件
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
        return sftp.ls(directory);
    }

    public static void main(String[] args) throws  Exception {
        SFtpFunc sf = new SFtpFunc();
        String host = "135.251.223.204";
        int port = 22;
        String username = "oracle";
        String password = "oracle";
        String directory = "cdcp/PTN";
        String uploadFile = "D:\\work\\db\\2013-09-03-192122-HZ-OTNM2000-1-PTN-DayMigration.db";
        String downloadFile = "2013-09-03-192122-HZ-OTNM2000-1-PTN-DayMigration.db";
        String saveFile = "D:\\temp.db";
        String deleteFile = "delete.txt";
        ChannelSftp sftp=sf.connect(host, port, username, password);
        //sftp.mkdir("ddd/ccc -p");
       // Vector cdcp2 = sftp.("cdcp2");
        sf.mkdir("cdcp/PTN/test1/test2/test3", sftp);
        Vector vector = sf.listFiles(directory, sftp);
        //    sf.upload(directory, uploadFile, sftp);
        sf.download(directory, downloadFile, saveFile, sftp);
        sf.delete(directory, deleteFile, sftp);
        try{
            sftp.cd(directory);
            sftp.mkdir("ss");
            System.out.println("finished");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


