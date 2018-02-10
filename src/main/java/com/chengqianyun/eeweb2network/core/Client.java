package com.chengqianyun.eeweb2network.core;

import com.chengqianyun.eeweb2network.common.util.IoUtil;
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

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public static void send(int port, String expression) {
        System.out.println("客户端发送消息：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            System.out.println("客户端收到结果为：" + in.readLine());
//            log.info("___结果为：" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一下必要的清理工作  
            IoUtil.close(in);
            IoUtil.close(out);
            IoUtil.close(socket);
            in = null;
            out = null;
            socket = null;
        }
    }
}  