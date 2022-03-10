package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] array = in.readLine().split("=");
                    List<String> list = List.of(array[1].split(" "));
                    for (String s : array) {
                        if (list.contains("Bye")) {
                            server.close();
                            System.out.println("Close program");
                            break;
                        }
                        System.out.println(s);
                    }
                    out.flush();
                }
            }
        }
    }
}