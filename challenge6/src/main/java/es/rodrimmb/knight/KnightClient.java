package es.rodrimmb.knight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KnightClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        StringBuilder resp = new StringBuilder();
        String data = null;
        for (int i = 0; i < 7; i++) {
            data = in.readLine();
            if(!data.isEmpty() && data.length() < 6) {
                resp.append(data);
                resp.append("\n");
                //System.out.println(data);
            }
        }
        //System.out.println("---");
        return resp.toString();
    }

    public String firstRead() throws IOException {
        StringBuilder resp = new StringBuilder();
        String data = null;
        for (int i = 0; i < 6; i++) {
            data = in.readLine();
            if(!data.isEmpty() && data.length() < 6) {
                resp.append(data);
                resp.append("\n");
                //System.out.println(data);
            }
        }
        //System.out.println("---");
        return resp.toString();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
