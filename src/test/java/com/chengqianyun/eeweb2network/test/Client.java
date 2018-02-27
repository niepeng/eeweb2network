package com.chengqianyun.eeweb2network.test;

import com.chengqianyun.eeweb2network.common.util.IoUtil;
import com.chengqianyun.eeweb2network.common.util.RandomUtil;
import com.chengqianyun.eeweb2network.core.InstructionManager;
import com.chengqianyun.eeweb2network.core.ServerNormal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;  
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/** 
 * 阻塞式I/O创建的客户端 
 * @version 1.0
 */
@Slf4j
public class Client {

    //默认的端口号
    private static int DEFAULT_SERVER_PORT = ServerNormal.DEFAULT_PORT;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    private String address;

    Socket socket;
    BufferedReader in = null;
    PrintWriter out = null;

    public Client(String address) {
        this.address = address;
    }



    public void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public  void send(int port, String expression) {
        System.out.println("客户端发送消息：" + expression);
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            System.out.println("客户端收到结果为：" + in.readLine());


            while(true) {
                String response = in.readLine();
                // 获取地址指令
                if(InstructionManager.genGetAddress().equalsIgnoreCase(response)) {
                    out.println(address);
                    continue;
                }

                // 获取设备数据
//                if(InstructionManager.genGetInfo(address).equalsIgnoreCase(address)) {
//                    out.println(address+ ",wen=" + RandomUtil.random(100, 300) + ",shi=" + RandomUtil.random(500, 800));
//                    continue;
//                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(in);
            IoUtil.close(out);
            IoUtil.close(socket);
            in = null;
            out = null;
            socket = null;
        }
    }
}  