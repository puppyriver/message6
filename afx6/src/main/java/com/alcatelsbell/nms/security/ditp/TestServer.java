package com.alcatelsbell.nms.security.ditp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-28
 * Time: 下午4:28
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class TestServer {

    //@org.junit.Test
    public void testConnection() throws InterruptedException {
        final DITPSocketServer server = new DITPSocketServer();
        server.registerMessageHandler(Bodys.getCmd(Bodys.DITP_CONNECT.class)
                ,new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
                if (inMessagee instanceof DITPSocketMessage) {
                    DITPSocketMessage respMessage = DITPSocketMessage.prepareEncoding
                            (  Bodys.getCmd(Bodys.DITP_CONNECT_RESP.class),
                              ((DITPSocketMessage)inMessagee).getSequence() );


                    Bodys.DITP_CONNECT_RESP respMessageBody = new Bodys.DITP_CONNECT_RESP();
                    respMessage.setMessageBody(respMessageBody);
                    respMessageBody.result = 0;
                    respMessageBody.authenticatorDMS = ((Bodys.DITP_CONNECT)inMessagee.getMessageBody()).authenticator;

                    return respMessage;
                }

                return null;
            }
        });
        server.start();
        Thread.sleep(10000000l);
    }

    public void testSimpleServer () throws IOException {
        ServerSocket serverSocket = new ServerSocket(6900);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        while (reader.ready()) {
       //     char c = reader.
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TestServer().testConnection();
    }
}
