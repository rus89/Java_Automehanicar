/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.automobili;

import domen.Automobil;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import komunikacija.Komunikacija;
import paneli.modeli.automobili.AutomobiliPrikazTableModel;
import transfer.TransferKlasa;
import util.Konstante;

/**
 *
 * @author Administrator
 */
public class PrikazAutomobilaKI {

    private static PrikazAutomobilaKI objekat;
    private JPanel glavna;
    private JMenuItem jmiPrikazAutomobila;
    private JPopupMenu jpmPrikazAutomobila;
    private JTable jtblPrikazAutomobila;

    public PrikazAutomobilaKI() {
    }

    public static PrikazAutomobilaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PrikazAutomobilaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JMenuItem jmiPrikazAutomobila, JPopupMenu jpmPrikazAutomobila, JTable jtblPrikazAutomobila) {
        this.glavna = glavna;
        this.jmiPrikazAutomobila = jmiPrikazAutomobila;
        this.jpmPrikazAutomobila = jpmPrikazAutomobila;
        this.jtblPrikazAutomobila = jtblPrikazAutomobila;
    }

    public  void prikaziSveAutomobile() {

        postaviPrazanModelTabele();

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_AUTOMOBILE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovor = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Automobil> listaAutomobila = (ArrayList<Automobil>) odgovor.getServerObjekatOdgovor();

            AutomobiliPrikazTableModel a = new AutomobiliPrikazTableModel(listaAutomobila);
            jtblPrikazAutomobila.setModel(a);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage());
        }

    }

    private void postaviPrazanModelTabele() {

        jtblPrikazAutomobila.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}));

    }
}
