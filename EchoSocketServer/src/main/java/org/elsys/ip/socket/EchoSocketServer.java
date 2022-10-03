package org.elsys.ip.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EchoSocketServer {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<ClientThread>();
    private Random random = new Random();
    private static final List<String> usernamePart1 = Arrays.asList(
            "sick", "green", "smart", "beautiful", "young", "lil", "smol", "fat", "smelly", "bold", "cheap");

    private static final List<String> usernamePart2 = Arrays.asList(
            "brain", "slipper", "lucho", "chick", "car", "nigga", "rapper", "beep", "fish", "banana"
    );

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            String part1 = usernamePart1.get(random.nextInt(usernamePart1.size()));
            String part2 = usernamePart2.get(random.nextInt(usernamePart2.size()));
            String part3 = String.valueOf(random.nextInt(100));

            String username = new StringBuilder().append(part1).append("_").append(part2).append("_").append(part3).toString();
            ClientThread clientThread = new ClientThread(clientSocket, username);
            clients.add(clientThread);
            new Thread(clientThread).start();
        }
    }

    class ClientThread implements Runnable {
        private Socket socket;
        public String username;
        public PrintWriter out = null;

        public ClientThread(Socket clientSocket, String username) {
            this.username = username;
            this.socket = clientSocket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = null;

                out.println(String.format("Your name is: %s", username));
                do {
                    message = in.readLine();
                    for (ClientThread client : clients) {
                        if (client.out != null)
                            client.out.println(String.format("%s: %s", username, message));
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