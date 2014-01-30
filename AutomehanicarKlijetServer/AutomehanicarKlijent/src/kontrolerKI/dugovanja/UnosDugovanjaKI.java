/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.dugovanja;

import domen.Dugovanja;
import domen.VlasnikAutomobila;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import transfer.TransferKlasa;
import util.Konstante;

/**
 *
 * @author Administrator
 */
public class UnosDugovanjaKI {

    private static UnosDugovanjaKI objekat;
    private JPanel glavna;
    private JComboBox jcbVlasnikAutomobila;
    private JLabel jlVrednost;
    private JTextField jtfSifraDugovanja;
    private JTextField jtfVrednostDugovanja;

    public UnosDugovanjaKI() {
    }

    public static UnosDugovanjaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new UnosDugovanjaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JComboBox jcbVlasnikAutomobila, JLabel jlVrednost, JTextField jtfSifraDugovanja, JTextField jtfVrednostDugovanja) {
        this.glavna = glavna;
        this.jcbVlasnikAutomobila = jcbVlasnikAutomobila;
        this.jlVrednost = jlVrednost;
        this.jtfSifraDugovanja = jtfSifraDugovanja;
        this.jtfVrednostDugovanja = jtfVrednostDugovanja;
    }

    public void srediFormu() {
        jtfSifraDugovanja.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfVrednostDugovanja.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraDugovanja.setEditable(false);
        sifraDugovanja();
        napuniCombo();
    }

    public void sifraDugovanja() {
        try {
            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_DUGOVE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Dugovanja> ld = (ArrayList<Dugovanja>) tk.getServerObjekatOdgovor();
            int sifra = 0;
            for (Dugovanja dugovanja : ld) {
                sifra = dugovanja.getSifraDugovanja() + 1;
            }
            jtfSifraDugovanja.setText(sifra + "");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void ocistiPolja() {
        jtfVrednostDugovanja.setText("");
        jcbVlasnikAutomobila.setSelectedIndex(0);
    }

    public void napuniCombo() {
        try {
            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_VLASNIKE_AUTOMOBILA);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<VlasnikAutomobila> lva = (ArrayList<VlasnikAutomobila>) tk.getServerObjekatOdgovor();
            jcbVlasnikAutomobila.removeAllItems();
            for (VlasnikAutomobila va : lva) {
                jcbVlasnikAutomobila.addItem(va);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jbtUnosDugovanjaActionPerformed() {
        try {

            TransferKlasa tk = new TransferKlasa();
            int sifraDugovanja = Integer.parseInt(jtfSifraDugovanja.getText().trim());
            double vrednostDuga = Double.parseDouble(jtfVrednostDugovanja.getText().trim());
            VlasnikAutomobila va = (VlasnikAutomobila) jcbVlasnikAutomobila.getSelectedItem();
            Dugovanja d = new Dugovanja(sifraDugovanja, vrednostDuga, va);
            tk.setOperacija(Konstante.SACUVAJ_DUG);
            tk.setKlijentObjekat(d);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            JOptionPane.showMessageDialog(glavna, "Dug je uspešno unet!", "Unos duga", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
            ocistiPolja();
            srediFormu();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }
}
