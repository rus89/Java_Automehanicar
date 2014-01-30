/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.intervencije;

import domen.Intervencija;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import komunikacija.Komunikacija;
import paneli.modeli.intervencija.IntervencijaPrikazTableModel;
import transfer.TransferKlasa;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PrikazIntervencijaKI {

    private static PrikazIntervencijaKI objekat;
    private JPanel glavna;
    private JTable jtblPrikazIntervencija;

    public PrikazIntervencijaKI() {
    }

    public static PrikazIntervencijaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PrikazIntervencijaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JTable jtblPrikazIntervencija) {
        this.glavna = glavna;
        this.jtblPrikazIntervencija = jtblPrikazIntervencija;
    }
    
    public  void prikaziSveIntervencije() {

        postaviPrazanModelTabele();

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_INTERVENCIJE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovor = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Intervencija> listaIntervencija = (ArrayList<Intervencija>) odgovor.getServerObjekatOdgovor();
                        
            IntervencijaPrikazTableModel i = new IntervencijaPrikazTableModel(listaIntervencija);
            jtblPrikazIntervencija.setModel(i);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage());
        }

    }

    private void postaviPrazanModelTabele() {

        jtblPrikazIntervencija.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}));

    }
}
