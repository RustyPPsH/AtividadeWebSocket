import java.net.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class Principal {
    public static void main(String[] args) {
        String host = "www.google.com";
        int port = 80;

        //JOptionPane.showMessageDialog(null, "Digite o host e a porta do servidor");
        //host = JOptionPane.showInputDialog("Host");
        //port = Integer.parseInt(JOptionPane.showInputDialog("Porta"));

        JFrame janela = new JFrame("JEditorPane");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500,500);
        JEditorPane pagina = new JEditorPane();
        JTextField enderecoTxt = new JTextField(host);
        enderecoTxt.setBounds(5, 5, 300, 20);
        JButton botaoFechar, botaoIr;
        botaoFechar = new JButton("Fechar");
        botaoFechar.setBounds(400, 5, 80, 20);
        botaoIr = new JButton("Ir");
        botaoIr.setBounds(310, 5, 80, 20);
        botaoIr.addActionListener(null);
        janela.add(enderecoTxt);
        janela.add(botaoFechar);
        janela.add(botaoIr);
        janela.add(pagina);
        pagina.setBounds(5, 45, 475, 400);
        janela.setSize(500, 500);
        janela.setLayout(null);
        janela.setVisible(true);

        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        botaoIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    pagina.setContentType("text/html");
                    pagina.setPage(new URL(enderecoTxt.getText()));
                } catch (Exception e) {
                    System.out.println("Erro");
                }
            }
        });


        try {
            Socket sock = new Socket(host, port);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            String linha = "";
            out.println("GET / HTTP/1.0\n");

            
            while ((linha = in.readLine()) != null) {
                pagina.setContentType("text/html");
                pagina.setPage(new URL("http://www.google.com"));
                System.out.println("echo: " + linha);
            }
        } catch (IOException e) {
            System.err.println("Problemas de IO");
        }
    }
}
