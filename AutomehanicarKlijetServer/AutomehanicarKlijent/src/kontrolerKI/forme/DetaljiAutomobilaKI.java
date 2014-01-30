/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.forme;

import domen.Automobil;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JTextField;
import uc.UseCase;

/**
 *
 * @author Administrator
 */
public class DetaljiAutomobilaKI {

    private static DetaljiAutomobilaKI objekat;
    private JDialog glavna;
    private JTextField jtfImeVlasnika;
    private JTextField jtfJmbgVlasnika;
    private JTextField jtfMarkaAutomobila;
    private JTextField jtfModelAutomobila;
    private JTextField jtfPrezimeVlasnika;
    private JTextField jtfRegistracija;
    private JTextField jtfSifraAutomobila;

    public DetaljiAutomobilaKI() {
    }

    public static DetaljiAutomobilaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new DetaljiAutomobilaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JDialog glavna, JTextField jtfImeVlasnika, JTextField jtfJmbgVlasnika, JTextField jtfMarkaAutomobila, JTextField jtfModelAutomobila, JTextField jtfPrezimeVlasnika, JTextField jtfRegistracija, JTextField jtfSifraAutomobila) {
        this.glavna = glavna;
        this.jtfImeVlasnika = jtfImeVlasnika;
        this.jtfJmbgVlasnika = jtfJmbgVlasnika;
        this.jtfMarkaAutomobila = jtfMarkaAutomobila;
        this.jtfModelAutomobila = jtfModelAutomobila;
        this.jtfPrezimeVlasnika = jtfPrezimeVlasnika;
        this.jtfRegistracija = jtfRegistracija;
        this.jtfSifraAutomobila = jtfSifraAutomobila;
    }

    public void srediFormu() {
        jtfImeVlasnika.setEditable(false);
        jtfJmbgVlasnika.setEditable(false);
        jtfMarkaAutomobila.setEditable(false);
        jtfModelAutomobila.setEditable(false);
        jtfPrezimeVlasnika.setEditable(false);
        jtfRegistracija.setEditable(false);
        jtfSifraAutomobila.setEditable(false);
        jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfMarkaAutomobila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfModelAutomobila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfRegistracija.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraAutomobila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
    }

    public void popuniFormu() {
        Automobil a = (Automobil) UseCase.vratiUC().vratiVrednost("automobilDetalji");
        jtfSifraAutomobila.setText(a.getSifraAutomobila() + "");
        jtfRegistracija.setText(a.getBrojRegistracije());
        jtfMarkaAutomobila.setText(a.getModel().getMarkaAutomobila().getNazivMarke());
        jtfModelAutomobila.setText(a.getModel().getNazivModela());
        jtfJmbgVlasnika.setText(a.getVlasnik().getJmbg());
        jtfImeVlasnika.setText(a.getVlasnik().getIme());
        jtfPrezimeVlasnika.setText(a.getVlasnik().getPrezime());
    }

    public void zatvori() {
        glavna.dispose();
    }
}
