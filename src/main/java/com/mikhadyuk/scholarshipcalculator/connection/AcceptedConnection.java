package com.mikhadyuk.scholarshipcalculator.connection;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.exception.ActionTypeNotFoundException;
import com.mikhadyuk.scholarshipcalculator.factory.ActionFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class AcceptedConnection implements Runnable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private int clientNumber;

    private ActionFactory actionFactory;

    public AcceptedConnection(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        actionFactory = new ActionFactory();
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                ActionType actionType = ActionType.valueOf((String) inputStream.readObject());
                Action action;
                try {
                    action = actionFactory.createAction(actionType);
                } catch (ActionTypeNotFoundException e) {
                    System.out.println("Неизвестное сообщение от клиента");
                    break;
                }
                System.out.print(String.format("Клиент #%d: %s"
                        , clientNumber, actionType.getActionDescription()));
                action.doAction(outputStream, inputStream);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(String.format("Клиент #%d отключился, время = %s", clientNumber, new Date()));
    }
}
