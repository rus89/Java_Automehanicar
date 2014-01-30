/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nit;

import javax.swing.JTable;
import server.Server;

/**
 *
 * @author Rus
 */
public class NitServer extends Thread {

    private JTable jtbliListaUlogovanih;

    public NitServer(JTable jtbliListaUlogovanih) {
        this.jtbliListaUlogovanih = jtbliListaUlogovanih;
    }
    
    @Override
    public void run() {
        try {
            Server s = new Server(jtbliListaUlogovanih);
            s.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
