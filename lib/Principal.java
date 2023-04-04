import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class Principal {
    public static void main(String[] args) {
        String host;
        int port;

        JOptionPane.showMessageDialog(null, "Digite o host e a porta do servidor");
        host = JOptionPane.showInputDialog("Host");
        port = Integer.parseInt(JOptionPane.showInputDialog("Porta"));

        try {
            Socket sock = new Socket(host, port);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String linha = "";
            out.println("GET / HTTP/1.0\n");
            while ((linha = in.readLine()) != null) {
                System.out.println("echo: " + linha);
            }
        } catch (IOException e) {
            System.err.println("Problemas de IO");
        }
    }
}
