package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
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
                    String input = in.readLine();
                    String[] array = input.split("=");
                    List<String> word = List.of(array[1].split(" "));
                    String string = word.get(0);
                    if ("Hello".equals(string)) {
                        System.out.println("Hello my friend");
                    }
                    if ("Exit".equals(string)) {
                        server.close();
                        System.out.println("The end");
                    }
                    if (!"Hello".equals(string) && !"Exit".equals(string)) {
                        System.out.println(string);
                    }
                    out.flush();
                }

            }
        }
    }
}