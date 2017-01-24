package com.alcatelsbell.nms.util.protocol;

/**
 * User: Ronnie
 * Date: 11-10-14
 * Time: 下午3:53
 */

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FtpFunc {
	private String server;
	private String username;
	private String password;
    protected int port;
	private FTPClient ftpClient;
	private boolean binaryTransfer = true;

	private static Logger logger = Logger.getLogger(FtpFunc.class.getName());

	public FtpFunc(String server, String username, String password) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.ftpClient = new FTPClient();
	}
    public FtpFunc(String server, String username, String password,int port) {
        this.server = server;
        this.username = username;
        this.password = password;
        this.port = port;
        this.ftpClient = new FTPClient();
    }
	public boolean connect() throws IOException {
		try {
            if (port > 0)
                this.ftpClient.connect(this.server,port);
			else this.ftpClient.connect(this.server);

			if (FTPReply.isPositiveCompletion(this.ftpClient.getReplyCode())) {
				if (this.ftpClient.login(this.username, this.password)) {
					this.ftpClient.enterLocalPassiveMode();
					return true;
				}
			} else {
				this.ftpClient.disconnect();
				logger.log(Level.SEVERE, "FTP server refused connection.");
			}
		} catch (IOException e) {
			if (this.ftpClient.isConnected())
				try {
					this.ftpClient.disconnect();
				} catch (IOException f) {
					f.printStackTrace();
				}
			logger.log(Level.SEVERE, "FTP server refused connection.server url :" + this.server, e);
			throw e;
		}
		return false;
	}

	public void downloadFile(String remoteAbsoluteFile, String localAbsoluteFile)
			throws Exception {
		OutputStream output = null;
		try {
			if (this.binaryTransfer)
				this.ftpClient.setFileType(2);
			else {
				this.ftpClient.setFileType(0);
			}

			createLocalFile(localAbsoluteFile);
			output = new FileOutputStream(localAbsoluteFile);

			InputStream is = this.getFileStream(remoteAbsoluteFile);
			int b;
			while ((b = is.read()) != -1) {
				output.write(b);
			}

			try {
				is.close();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}

			try {
				if (output != null)
					output.close();
			} catch (IOException e2) {
				logger.log(Level.SEVERE, "Could get file from server.", e2);
				throw e2;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e1) {
			throw e1;
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e2) {
				logger.log(Level.SEVERE, "Could get file from server.", e2);
				throw e2;
			}
		}
	}
    public void downloadFile2(String remoteAbsoluteFile, String localAbsoluteFile)
            throws Exception {
        OutputStream output = null;
        try {
            if (this.binaryTransfer)
                this.ftpClient.setFileType(2);
            else {
                this.ftpClient.setFileType(0);
            }

            createLocalFile(localAbsoluteFile);
            output = new FileOutputStream(localAbsoluteFile);

            ftpClient.retrieveFile(remoteAbsoluteFile,output);



            try {
                if (output != null)
                    output.close();
            } catch (IOException e2) {
                logger.log(Level.SEVERE, "Could get file from server.", e2);
                throw e2;
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e1) {
            throw e1;
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e2) {
                logger.log(Level.SEVERE, "Could get file from server.", e2);
                throw e2;
            }
        }
    }

	protected void createLocalFile(String localAbsoluteFile) throws Exception {
		String fileDir = localAbsoluteFile.substring(0,
				localAbsoluteFile.lastIndexOf(File.separator) + 1);
		File dirFile = new File(fileDir);
		if (!(dirFile.exists())) {
			boolean bool = dirFile.mkdirs();
		}
		File file = new File(localAbsoluteFile);
		if (!(file.exists()))
			file.createNewFile();
	}

	public boolean resumeDownloadFile(String remoteAbsoluteFile,
			String localAbsoluteFile) throws Exception {
		boolean result = true;
		createLocalFile(localAbsoluteFile);
		File file = new File(localAbsoluteFile);
		long pos = file.length();
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(pos);
		this.ftpClient.setRestartOffset(pos);

		InputStream is = this.ftpClient.retrieveFileStream(remoteAbsoluteFile);
		if (is == null) {
			logger.log(Level.SEVERE, "no such file:" + remoteAbsoluteFile);
			result = false;
		} else {
			logger.log(Level.INFO, "start getting file:" + remoteAbsoluteFile);
			int b;
			while ((b = is.read()) != -1) {

				raf.write(b);
			}
			is.close();
			if (this.ftpClient.completePendingCommand()) {
				logger.log(Level.INFO, "done!");
			} else {
				logger.log(Level.INFO, "can't get file:" + remoteAbsoluteFile);
				result = false;
			}
		}
		raf.close();
		return result;
	}

	/**
	 *   在ftp创建目录。支持嵌套创建，创建子目录
	 * @param remote 需要创建的目录 例如  /FTP/LONGFEI/  支持中文目录
	 * @param ftpClient 
	 * @return
	 * @throws IOException
	 */
	public boolean createDirecroty(String remote, FTPClient ftpClient)
			throws IOException {
		boolean status = true;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(new String(directory
						.getBytes("GBK"), "iso-8859-1"))) {
			// 如果远程目录不存在，则递归创建远程服务器目录
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end)
						.getBytes("GBK"), "iso-8859-1");
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("创建目录失败");
						return false;
					}
				}

				start = end + 1;
				end = directory.indexOf("/", start);

				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return status;
	}

	public void uploadFile(String remotePath, String fileName,
			String localAbsoluteFile) throws IOException {

		FTPFile[] remotefiles = null;
		try {
			remotefiles = this.ftpClient.listFiles(remotePath);
			for (FTPFile ftpFile : remotefiles) {
				System.out.println(ftpFile);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not list file from server."
					+ remotePath);

			throw e;
		}

		// String name = localAbsoluteFile;
		OutputStream os = this.ftpClient.storeFileStream(fileName);
		if (os != null) {
			RandomAccessFile raf = new RandomAccessFile(localAbsoluteFile, "rw");

			FTPFile remoteFile = existsFile(remotefiles, fileName);
			if ((remoteFile != null) && (raf.length() >= remoteFile.getSize())) {
				raf.seek(remoteFile.getSize());
			}

			logger.log(Level.INFO, "start putting file:" + fileName);
			int b;
			while ((b = raf.read()) != -1) {

				os.write(b);
			}
			os.flush();
			raf.close();
			os.close();

			if (this.ftpClient.completePendingCommand())
				logger.log(Level.INFO, "done!");
			else
				logger.log(Level.INFO, "can't get file:" + remotePath
						+ fileName);
		}
	}

	private FTPFile existsFile(FTPFile[] remoteFiles, String fileName) {
		for (FTPFile remoteFile : remoteFiles) {
			if (fileName.equals(remoteFile.getName())) {
				return remoteFile;
			}
		}
		return null;
	}

    /**
     * ftpClient.listFiles(path) has issue with some ftp server
     * @param remotePath
     * @return
     * @throws IOException
     */
    @Deprecated
	public List<FTPFile> getAllFiles(String remotePath) throws IOException {
		List fileList = new ArrayList();
		try {
			FTPFile[] remotefiles = this.ftpClient.listFiles(remotePath);
			for (int i = 0; i < remotefiles.length; ++i)
				if (remotefiles[i].isFile())
					fileList.add(remotefiles[i]);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not list file from server.", e);

			throw e;
		}
		return fileList;
	}


    public String[] getAllFileNames(String remotePath) throws IOException {
        List fileList = new ArrayList();
        try {
            String[] remotefiles = this.ftpClient.listNames(remotePath);
            return remotefiles;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not list file from server.", e);

            throw e;
        }

    }

	private InputStream getFileStream(String remotePath, FTPFile ftpFile)
			throws IOException {
		InputStream result = null;

		String directory = remotePath;
		if (!(directory.endsWith("/")))
			directory = directory + "/";
		try {
			result = this.ftpClient.retrieveFileStream(directory
					+ ftpFile.getName());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not get file from server.", e);
			throw e;
		}
		return result;
	}

	private InputStream getFileStream(String remoteFile) throws IOException {
		InputStream result = null;
		try {
			result = this.ftpClient.retrieveFileStream(remoteFile);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not get file from server.", e);
			throw e;
		}
		return result;
	}

	public void deleteFile(String remoteFile) throws IOException {
		this.ftpClient.deleteFile(remoteFile);
	}

	public void disconnect() throws IOException {
		if (this.ftpClient == null)
			return;
		try {
			this.ftpClient.logout();
			if (this.ftpClient.isConnected())
				this.ftpClient.disconnect();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not disconnect from server.", e);
			throw e;
		}
	}

	public boolean isBinaryTransfer() {
		return this.binaryTransfer;
	}

	public void setBinaryTransfer(boolean binaryTransfer) {
		this.binaryTransfer = binaryTransfer;
	}

	public FTPClient getFtpClient() {
		return this.ftpClient;
	}

	public static void main(String[] args) throws IOException {
		FtpFunc ftp = new FtpFunc("135.251.223.111", "root", "root123");
		ftp.connect();
		
		
//		ftp.createDirecroty("/你好/longfei/", ftp.getFtpClient());
//		ftp.uploadFile("/home/oracle/test", "apache-camel-2.8.1.zip",
//				"D:/ganzhou/ganzhoucorbaAllEquipments.txt");
//		ftp.getFtpClient().makeDirectory("FTP_DATA");
		System.out.println(ftp.getAllFiles("/PBOSS/"));
		ftp.createDirecroty("/longfei/", ftp.getFtpClient());
		ftp.disconnect();
		System.out.println("UPLOAD SUECCESS");
	}
}