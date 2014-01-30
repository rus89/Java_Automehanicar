/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.forme;

import domen.Intervencija;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import paneli.modeli.intervencija.UradjeneIntervencijeTableModel;
import uc.UseCase;

/**
 *
 * @author Administrator
 */
public class DetaljiIntervencijeKI {

    private static DetaljiIntervencijeKI objekat;
    private JDialog glavna;
    private JScrollPane jScrollPane1;
    private JTable jtblOpisIntervencije;
    private JTextField jtfDatumIntervencije;
    private JTextField jtfImeVlasnika;
    private JTextField jtfImeZaposlenog;
    private JTextField jtfJmbgVlasnika;
    private JTextField jtfJmbgZaposlenog;
    private JTextField jtfMarka;
    private JTextField jtfModel;
    private JTextField jtfPrezimeVlasnika;
    private JTextField jtfPrezimeZaposlenog;
    private JTextField jtfRegistracija;
    private JTextField jtfSifraIntervencije;

    public DetaljiIntervencijeKI() {
    }

    public static DetaljiIntervencijeKI vratiObjekat() {
        if (objekat == null) {
            objekat = new DetaljiIntervencijeKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JDialog glavna, JScrollPane jScrollPane1, JTable jtblOpisIntervencije, JTextField jtfDatumIntervencije, JTextField jtfImeVlasnika, JTextField jtfImeZaposlenog, JTextField jtfJmbgVlasnika, JTextField jtfJmbgZaposlenog, JTextField jtfMarka, JTextField jtfModel, JTextField jtfPrezimeVlasnika, JTextField jtfPrezimeZaposlenog, JTextField jtfRegistracija, JTextField jtfSifraIntervencije) {
        this.glavna = glavna;
        this.jScrollPane1 = jScrollPane1;
        this.jtblOpisIntervencije = jtblOpisIntervencije;
        this.jtfDatumIntervencije = jtfDatumIntervencije;
        this.jtfImeVlasnika = jtfImeVlasnika;
        this.jtfImeZaposlenog = jtfImeZaposlenog;
        this.jtfJmbgVlasnika = jtfJmbgVlasnika;
        this.jtfJmbgZaposlenog = jtfJmbgZaposlenog;
        this.jtfMarka = jtfMarka;
        this.jtfModel = jtfModel;
        this.jtfPrezimeVlasnika = jtfPrezimeVlasnika;
        this.jtfPrezimeZaposlenog = jtfPrezimeZaposlenog;
        this.jtfRegistracija = jtfRegistracija;
        this.jtfSifraIntervencije = jtfSifraIntervencije;
    }

    public void srediFormu() {
        glavna.setTitle("Detalji intervencije");
        jtfDatumIntervencije.setEditable(false);
        jtfImeVlasnika.setEditable(false);
        jtfImeZaposlenog.setEditable(false);
        jtfJmbgVlasnika.setEditable(false);
        jtfJmbgZaposlenog.setEditable(false);
        jtfMarka.setEditable(false);
        jtfModel.setEditable(false);
        jtfPrezimeVlasnika.setEditable(false);
        jtfPrezimeZaposlenog.setEditable(false);
        jtfRegistracija.setEditable(false);
        jtfSifraIntervencije.setEditable(false);
        jtfDatumIntervencije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfImeZaposlenog.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgZaposlenog.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfMarka.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfModel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeZaposlenog.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfRegistracija.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraIntervencije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
    }

    public void popuniFormu() {
        Intervencija i = (Intervencija) UseCase.vratiUC().vratiVrednost("detaljiIntervencije");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datum = sdf.format(i.getDatumIntervencije());
        jtfDatumIntervencije.setText(datum);
        jtfImeVlasnika.setText(i.getAutomobil().getVlasnik().getIme());
        jtfImeZaposlenog.setText(i.getZaposleni().getIme());
        jtfJmbgVlasnika.setText(i.getAutomobil().getVlasnik().getJmbg());
        jtfJmbgZaposlenog.setText(i.getZaposleni().getJmbg());
        jtfMarka.setText(i.getAutomobil().getModel().getMarkaAutomobila().getNazivMarke());
        jtfModel.setText(i.getAutomobil().getModel().getNazivModela());
        jtfPrezimeVlasnika.setText(i.getAutomobil().getVlasnik().getPrezime());
        jtfPrezimeZaposlenog.setText(i.getZaposleni().getPrezime());
        jtfRegistracija.setText(i.getAutomobil().getBrojRegistracije());
        jtfSifraIntervencije.setText(i.getSifraIntervencije() + "");
        UradjeneIntervencijeTableModel utm = new UradjeneIntervencijeTableModel(i);
        jtblOpisIntervencije.setModel(utm);
    }

    public void zatvori() {
        glavna.dispose();
    }
}
