package com.chengqianyun.eeweb2network.common.util;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/2/10
 */

public class IoUtil {

  public static void close(Reader in) {
    if (in != null) {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(Writer out) {
    if (out != null) {
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(Socket socket) {
    if(socket != null){
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}