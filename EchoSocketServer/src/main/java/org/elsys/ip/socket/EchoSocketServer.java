package org.elsys.ip.socket;

import com.sun.xml.internal.ws.client.ClientTransportException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoSocketServer {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<ClientThread>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(clientSocket);
            clients.add(clientThread);
            new Thread(clientThread).start();
        }
    }

    class ClientThread implements Runnable {
        private Socket socket;
        public PrintWriter out = null;

        public ClientThread(Socket clientSocket) {
            this.socket = clientSocket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = null;
                do {
                    message = in.readLine();
                    for (ClientThread client : clients) {
                        if (client.out != null)
                            client.out.println(message);
                    }
                } while (!message.equals("exit"));

                in.close();
                out.close();
                socket.close();
            } catch (Exception ex) {
                // Do nothing;
            }
        }
    }
}