package com.chengqianyun.eeweb2network.core;

import com.chengqianyun.eeweb2network.common.enums.DataStatusEnum;
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
//            out = new PrintWriter(socket.getOutputStream(), true);
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
            readData2 = Char55util.dealwith55NewV2(readData2);
            System.out.println("tmpData2==>(" + tmpData2 + ")");
            int address = readAddress(readData2);
            PrintUtil.println("address====>" + address);

            //  3.持续发送和获取数据(一段时间发送和接收),如果连接断了,释放当前链路,重新尝试
            int dataLen = 4;
            while(true) {
                char[] writeData = InstructionManager.genGetInfo(address, dataLen);
                PrintUtil.println("发送获取数据指令:" + FunctionUnit.bytesToHexString(writeData));
                writePort(writeData, socket);
                SystemClock.sleep(5000);
                char[] readData3 = read(in);
                PrintUtil.println("接收到数据结果====>" + FunctionUnit.bytesToHexString(readData3));
                readData3 = Char55util.dealwith55NewV2(readData3);
                if(!DataStatusEnum.isSuccess(checkReturn(readData3, address))) {
                   continue;
                }

                int humi = 0, temp = 0, dew = 0, power = 0;
                if (dataLen >= 1) {
                    humi = (int) ((readData3[3] << 8) + readData3[4]); //  equip.getHumiDev() * 100
                }

                if (dataLen >= 2) {
                    temp = (int) (((readData3[5] << 8) + readData3[6] - 27315)); // equip.getTempDev() * 100
                }

                if (dataLen >= 3) {
                    dew = ((readData3[7] << 8) + readData3[8] - 27315);
                }

                if (dataLen >= 4) {
                    power = (readData3[9] << 8) + readData3[10];
                }
                PrintUtil.println("接收到数据结果解析==>humi=" + humi + ",temp=" + temp + ",dew=" + dew + ", power=" + power);
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

    /**
     * @describe: 检查读取的值: address:-1(不检查头字节)
     * @param rsByte 待检查的char数组
     * @param address 用来判断地址是否一致. 不需要时,赋值为 -1
     * @return: 返回CRC检查结果. 只有返回 DataStatusEnum.right_frame ,为正常成功的帧,其他都为失败帧
     */
    public DataStatusEnum checkReturn(char[] rsByte, int address) {
        boolean rsOut = true;
        DataStatusEnum result = DataStatusEnum.right_frame;

        //帧判断
        if (rsByte == null) {
            //判断是否丢帧
            result = DataStatusEnum.lose_frame;
        } else {
            if (address != -1) {
                //检测地址头
                rsOut = rsByte[0] == address;
            }
            if (rsOut == false) {
                result = DataStatusEnum.wrong_address;
            } else {
                //对接收的数据进行crc校验，检查是否通讯故障
                rsOut = CalcCRC.checkCrc16(rsByte);
                if (rsOut == false) {
                    result = DataStatusEnum.wrong_frame;
                }
            }
        }
        return result;
    }




    // 读取设备的地址
    public static char[] readAddress = {
        0xFB,0x67,0x06,0x00,0x00,0x00,0x00,0x00,0x00,0x5C,0x0A
    };





}