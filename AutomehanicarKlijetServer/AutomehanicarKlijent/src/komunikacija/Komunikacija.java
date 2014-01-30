/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import transfer.TransferKlasa;

/**
 *
 * @author Rus
 */
public class Komunikacija {

    private static Komunikacija instance;
    private Socket socket;

    private Komunikacija() throws UnknownHostException, IOException {

        socket = new Socket("127.0.0.1", 9090);
        System.out.println("Klijent se povezao sa serverom");

    }

    public static Komunikacija vratiKomunikaciju() throws UnknownHostException, IOException {

        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void posalji(TransferKlasa tk) throws IOException {

        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(tk);

    }

    public TransferKlasa procitaj() throws IOException, ClassNotFoundException {

        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferKlasa) inSocket.readObject();

    }
}
