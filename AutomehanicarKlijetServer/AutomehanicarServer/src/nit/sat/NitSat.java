/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nit.sat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Rus
 */
public class NitSat extends Thread {
    
    private JTextField jtfSat;

    public NitSat(JTextField jtfSat) {
        this.jtfSat = jtfSat;
    }

    @Override
    public void run() {        
        
        while (true) {            
            String vreme = new SimpleDateFormat("HH:mm:ss").format(new Date());
            jtfSat.setText(vreme);
        }
        
    }
           
}
