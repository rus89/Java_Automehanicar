/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.forme;

import domen.Dugovanja;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JTextField;
import uc.UseCase;

/**
 *
 * @author Administrator
 */
public class DetaljiDugovaKI {

    private static DetaljiDugovaKI objekat;
    private JDialog glavna;
    private JTextField jtfIme;
    private JTextField jtfJmbg;
    private JTextField jtfPrezime;
    private JTextField jtfSifraDugovanja;
    private JTextField jtfVrednost;

    public DetaljiDugovaKI() {
    }

    public static DetaljiDugovaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new DetaljiDugovaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JDialog glavna, JTextField jtfIme, JTextField jtfJmbg, JTextField jtfPrezime, JTextField jtfSifraDugovanja, JTextField jtfVrednost) {
        this.glavna = glavna;
        this.jtfIme = jtfIme;
        this.jtfJmbg = jtfJmbg;
        this.jtfPrezime = jtfPrezime;
        this.jtfSifraDugovanja = jtfSifraDugovanja;
        this.jtfVrednost = jtfVrednost;
    }

    public void srediFormu() {
        jtfSifraDugovanja.setEditable(false);
        jtfIme.setEditable(false);
        jtfJmbg.setEditable(false);
        jtfPrezime.setEditable(false);
        jtfVrednost.setEditable(false);
        jtfIme.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbg.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezime.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraDugovanja.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfVrednost.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
    }

    public void popuniFormu() {
        Dugovanja d = (Dugovanja) UseCase.vratiUC().vratiVrednost("detaljiDuga");
        jtfIme.setText(d.getVlasnik().getIme());
        jtfJmbg.setText(d.getVlasnik().getJmbg());
        jtfPrezime.setText(d.getVlasnik().getPrezime());
        jtfSifraDugovanja.setText(d.getSifraDugovanja() + "");
        jtfVrednost.setText(d.getVrednost() + " din.");
    }

    public void zatvori() {
        glavna.dispose();
    }
}
