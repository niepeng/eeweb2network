package com.chengqianyun.eeweb2network.core;

import com.chengqianyun.eeweb2network.common.util.DateUtil;
import com.chengqianyun.eeweb2network.common.util.IoUtil;
import com.chengqianyun.eeweb2network.common.util.SystemClock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端线程 用于处理一个客户端的Socket链路
 * @author niepeng
 */
@Slf4j
public class ServerHandlerTest implements Runnable {

    private Socket socket;

    private String address;

    public ServerHandlerTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
      /**
       * 1.接收到第一次客户端连接上来
       * 2.建立sokcet和地址的关系
       * 3.持续发送和获取数据(一段时间发送和接收),如果连接断了,释放当前链路,重新尝试
       */
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;

            // 1.接收到第一次客户端连接上来
            if ((expression = in.readLine()) == null) {
                return;
            }
            System.out.println("服务器收到第一个消息：" + expression);
            result = expression + "来响应";
            out.println(result);


            // 2.建立sokcet和地址的关系
            System.out.println("发送获取地址指令：" + InstructionManager.genGetAddress());
            out.println(InstructionManager.genGetAddress());
            if ((expression = in.readLine()) == null) {
                return;
            }
            this.address = null;
//            this.address = InstructionManager.parseGetAddress(expression);
            System.out.println(DateUtil.format(new Date()) + "  接受到获取地址的结果：" + expression + ",地址配合得到:" + address);


            //  3.持续发送和获取数据(一段时间发送和接收),如果连接断了,释放当前链路,重新尝试
            String bizData;
            while(true) {
//                System.out.println(DateUtil.format(new Date()) + "  发送获取数据指令:" + InstructionManager.genGetInfo(address));
//                out.println(InstructionManager.genGetInfo(address));
                if ((expression = in.readLine()) == null) {
                    return;
                }
                bizData = InstructionManager.parseGetInfo(expression);
                System.out.println(DateUtil.format(new Date()) + "  处理数据:" + bizData);
                SystemClock.sleep(4 * 1000);
            }

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

}