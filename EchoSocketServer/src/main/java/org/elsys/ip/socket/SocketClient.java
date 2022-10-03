package org.elsys.ip.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    inputLoop();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    stopConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                System.exit(0);
            }
        });
        thread.start();
        consoleLoop();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public String readMessage() throws IOException {
        return in.readLine();
    }

    private void consoleLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        do {
            String line = scanner.nextLine();
            sendMessage(line);
        } while (true);
    }

    private void inputLoop() throws IOException {
        String response = null;
        do {
            response = readMessage();
            System.out.println(response);
        } while (response != null);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}