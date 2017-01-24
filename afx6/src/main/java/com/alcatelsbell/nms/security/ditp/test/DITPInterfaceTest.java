package com.alcatelsbell.nms.security.ditp.test;

import com.alcatelsbell.nms.security.ditp.DITPInterface;
import com.alcatelsbell.nms.util.ByteUtil;
import org.junit.*;

import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 13-3-1
 * Time: 下午3:46
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class DITPInterfaceTest {
    public static DITPInterface ditpInterface = null;
    private static boolean  init = false;
    @Before
    public void  init() {
        if (!init) {
            ditpInterface = new DITPInterface();
            ditpInterface.start();
            ditpInterface.waitForNewConnect();
            assert ditpInterface.isReady;
            init = true;
        }
    }

    @Test
    public void testAddDevice() throws Exception {
        System.out.println("***********************testAddDevice*******************");
         ditpInterface.notifyAddDevice(ByteUtil.objectToBytes("ronnie1",64));
        ditpInterface.notifyAddDevice(ByteUtil.objectToBytes("ronnie2",64));

    }

    @Test
    public void testRemoveDevice()  throws  Exception {
        System.out.println("***********************testRemoveDevice*******************");
        ditpInterface.notifyRemoveDevice(ByteUtil.objectToBytes("ronnie2",64));
    }

    @Test
    public void testList()  throws Exception {
        System.out.println("***********************testList*******************");
        List<byte[]> bytes = ditpInterface.listDevice();
       assert bytes.size() == 1;
        System.out.println(bytes.get(0));
        assert new String(bytes.get(0)).equals("ronnie1");
    }




}
