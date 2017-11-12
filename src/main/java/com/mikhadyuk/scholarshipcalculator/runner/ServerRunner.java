package com.mikhadyuk.scholarshipcalculator.runner;

import com.mikhadyuk.scholarshipcalculator.connection.AcceptedConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;

public class ServerRunner {
    private static ServerSocket serverSocket;
    private static int count;
    private static int port;

    public static void main(String[] args) {
        try {
            Socket socket;

            FileInputStream inputStream;
            Properties property = new Properties();

            try {
                inputStream = new FileInputStream("src/main/resources/config.properties");
                property.load(inputStream);

                port = Integer.valueOf(property.getProperty("server.port"));
            } catch(NumberFormatException e){
                System.err.println("ОШИБКА: Неверный формат порта в файле конфигураций!");
                throw e;
            } catch (IOException e) {
                System.err.println("ОШИБКА: Файл свойств отсуствует!");
                throw e;
            }

            serverSocket = new ServerSocket(port);

            System.out.println("PORT: " + port + ", IP: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Дата и время начала работы сервера: " + new Date() + "\n");

            while (true) {
                socket = serverSocket.accept();
                count++;

                System.out.println(String.format("Клиент #%d присоединился, IP = %s, время = %s",
                        count, socket.getInetAddress().getHostName(), new Date()));

                Thread acceptedThread = new Thread(new AcceptedConnection(socket, count));
                acceptedThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
