package server.controller;

import server.view.ServerGUI;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

public class ServerWindowController {
    private ServerSocket servSocket = null;
    private static Server server;
    private static boolean isStart;

    public ServerWindowController(ServerGUI view) {
        server = new Server("localhost", 8025, "/ws", null, ServerSocket.class);
        isStart = false;
    }

    public static void changeStatus() {
        if (isStart) {
            server.stop();
            isStart = false;
        } else {
            try {
                server.start();
                isStart = true;
            } catch (DeploymentException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerGUI view = new ServerGUI();
        ServerWindowController ctrl = new ServerWindowController(view);
    }

}