package org.elsys.ip.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoSocketServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(clientSocket);
            new Thread(clientThread).start();
        }
    }

    class ClientThread implements Runnable {
        private Socket socket;

        public ClientThread(Socket clientSocket) {
            this.socket = clientSocket;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = null;
                do {
                    message = in.readLine();
                    out.println(message);
                } while (!message.equals("exit"));

                in.close();
                out.close();
                socket.close();
            } catch (Exception ex) {
                // Do nothing;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoSocketServer server = new EchoSocketServer();
        server.start(6666);
    }
}