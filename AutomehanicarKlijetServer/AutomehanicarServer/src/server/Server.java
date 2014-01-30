/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTable;
import nit.NitKlijentServerKomunikacija;

/**
 *
 * @author Rus
 */
public class Server {

    private Socket socket;
    private JTable jtblListaUlogovanih;

    public Server(JTable jtblListaUlogovanih) {
        this.jtblListaUlogovanih = jtblListaUlogovanih;
    }

    public void start() throws IOException, ClassNotFoundException {
        
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server je pokrenut i spreman za rad");
        
        while (true) {            
            socket = serverSocket.accept();
            new NitKlijentServerKomunikacija(socket, jtblListaUlogovanih).start();
        }
    }
}
