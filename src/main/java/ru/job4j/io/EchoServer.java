package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello my friend \r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str != null && !str.isEmpty()) {
                        String[] array = str.split("=");
                        List<String> list = List.of(array[array.length - 1].split(" "));
                        if (list.contains("Hello")) {
                            System.out.println("Hello my friend");
                        } else if (list.contains("Exit")) {
                            System.out.println("Good Bye");
                            server.close();
                        } else {
                            System.out.println(list.get(0));
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException method", e);
        }
    }
}