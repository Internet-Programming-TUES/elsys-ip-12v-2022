package org.elsys.ip.socket;

public class Main {
    /**
     * Command line:
     * java -jar JAR.jar server port
     * java -jar JAR.jar client ip port
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        if (args.length >= 2 && args.length <= 3) {
            String mode = args[0];
            if (mode.equals("server")) {
                if (args.length != 2) {
                    throw new Exception("Arguments must be server port");
                }
                int port = Integer.parseInt(args[1]);

                EchoSocketServer server = new EchoSocketServer();
                server.start(port);
            } else if (mode.equals("client")) {
                if (args.length != 3) {
                    throw new Exception("Arguments must be client ip port");
                }
                String ip = args[1];
                int port = Integer.parseInt(args[2]);

                SocketClient client = new SocketClient();
                client.startConnection(ip, port);
            } else {
                throw new Exception("The first argument must be server or client.");
            }
        } else {
            throw new Exception("The arguments must be 2 or 3.");
        }
    }
}
