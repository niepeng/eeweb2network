package com.chengqianyun.eeweb2network.core;

import com.chengqianyun.eeweb2network.common.util.CalcCRC;
import com.chengqianyun.eeweb2network.common.util.Char55util;
import com.chengqianyun.eeweb2network.common.util.DateUtil;
import com.chengqianyun.eeweb2network.common.util.FunctionUnit;
import com.chengqianyun.eeweb2network.common.util.IoUtil;
import com.chengqianyun.eeweb2network.common.util.PrintUtil;
import com.chengqianyun.eeweb2network.common.util.SystemClock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端线程 用于处理一个客户端的Socket链路
 * @author niepeng
 */
@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    private String address;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
      /**
       * 1.接收到第一次客户端连接上来
       * 2.建立sokcet和地址的关系
       * 3.持续发送和获取数据(一段时间发送和接收),如果连接断了,释放当前链路,重新尝试
       */
        InputStream in = null;
        PrintWriter out = null;
        try {
            in = socket.getInputStream();
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;

            // 1.接收到第一次客户端连接上来
            char[] readData = read(in);
            String tmpData1 = FunctionUnit.bytesToHexString(readData);
            System.out.println("tmpData1==>(" + tmpData1 + ")");

            // 2.建立sokcet和地址的关系
            System.out.println("发送获取地址指令：" + FunctionUnit.bytesToHexString(readAddress));

            writePort(readAddress, socket);

            SystemClock.sleep(6000);

            char[] readData2 = read(in);
            String tmpData2 = FunctionUnit.bytesToHexString(readData2);
            System.out.println("tmpData2==>(" + tmpData2 + ")");
            readData2 = Char55util.dealwith55NewV2(readData2);
            System.out.println("tmpData2==>(" + tmpData2 + ")");
            int address = readAddress(readData2);
            PrintUtil.println("address====>" + address);

            //  3.持续发送和获取数据(一段时间发送和接收),如果连接断了,释放当前链路,重新尝试
            while(true) {
                SystemClock.sleep(10000);
            }

//            String bizData;
//            while(true) {
//                System.out.println(DateUtil.format(new Date()) + "  发送获取数据指令:" + InstructionManager.genGetInfo(address));
//                out.println(InstructionManager.genGetInfo(address));
//                if ((expression = in.readLine()) == null) {
//                    return;
//                }
//                bizData = InstructionManager.parseGetInfo(expression);
//                System.out.println(DateUtil.format(new Date()) + "  处理数据:" + bizData);
//                SystemClock.sleep(4 * 1000);
//            }

        } catch (Exception e) {
            log.error("ServerHandler.runError", e);
        } finally {
            IoUtil.close(in);
            in = null;
            IoUtil.close(out);
            out = null;
            IoUtil.close(socket);
            socket = null;
        }
    }

    public char[] read(InputStream in) throws IOException {
        byte[] readBuffer = new byte[200];
        char[] msgPack = null;
        int numBytes = 0;
        if (in == null) {
            return msgPack;
        }

        while (in.available() > 0) {
            numBytes = in.read(readBuffer);
            msgPack = new char[numBytes];
            for (int i = 0; i < numBytes; i++) {
                msgPack[i] = (char) (readBuffer[i] & 0xFF);
            }// end for
        }// end while
        return msgPack;
    }

    public int readAddress(char[] rsByte) {
        // FC 67 09 00 15 00 00 00 00 01 15 08 9C EC
        if (rsByte == null || rsByte.length != 14) {
            return 0;
        }
        boolean isSuccess = CalcCRC.checkCrc16(rsByte);
        if (isSuccess) {
            return (int) rsByte[9];
        }
        return 0;
    }




    public void writePort(char[] bytes, Socket socket) throws IOException {
        //  根据地址得到对应的socket通道，然后修改in和out
        for (char b : bytes) {
            writePort(b , socket);
        }
    }

    /**
     * @describe: 向串口写数据 char bytes
     * @date:2009-11-5
     */
    public void writePort(char b, Socket socket) throws IOException {
        if(socket == null) {
            return;
        }
        OutputStream out = socket.getOutputStream();
        if (out == null) {
            return;
        }
        out.write(b);
        out.flush();
    }


    // 读取设备的地址
    public static char[] readAddress = {
        0xFB,0x67,0x06,0x00,0x00,0x00,0x00,0x00,0x00,0x5C,0x0A
    };





}