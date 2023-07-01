package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginThread extends Thread {
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(8001);
            while (true) {
                Socket client = ss.accept();
                LoginController loginController = new LoginController();
                loginController.handle(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
