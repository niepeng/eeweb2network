package com.chengqianyun.eeweb2network.core;

import com.chengqianyun.eeweb2network.common.util.IoUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;  
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端线程 
 * 用于处理一个客户端的Socket链路
 */
@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;  

    public ServerHandler(Socket socket) {
        this.socket = socket;  
    }  

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;
            while (true) {
                //通过BufferedReader读取一行  
                //如果已经读到输入流尾部，返回null,退出循环  
                //如果得到非空值，就尝试计算结果并返回  
                if ((expression = in.readLine()) == null) {
                    break;
                }
                System.out.println("服务器收到消息：" + expression);
                try {
                    result = expression + "计算结果来响应";
                } catch (Exception e) {
                    result = "计算错误：" + e.getMessage();
                }
                out.println(result);
            }
        } catch (Exception e) {
           log.error("ServerHandler.runError", e);
        } finally {
            //一些必要的清理工作
            IoUtil.close(in);
            in = null;
            IoUtil.close(out);
            out = null;
            IoUtil.close(socket);
            socket = null;
        }
    }  
}  