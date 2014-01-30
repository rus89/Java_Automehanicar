/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.automobili;

import domen.Automobil;
import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import domen.VlasnikAutomobila;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import ogranicenja.JTextFieldOgranicenje;
import transfer.TransferKlasa;
import util.Konstante;
import validator.ComponentValidator;
import validator.JMBGValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Administrator
 */
public class UnosAutomobilaKI {

    private static UnosAutomobilaKI objekat;
    private JPanel glavna;
    private JComboBox jcbMarkaAutomobila;
    private JComboBox jcbModelAutomobila;
    private JComboBox jcbVlasnik;
    private JLabel jlBrojRegistracije;
    private JLabel jlIme;
    private JLabel jlJmbg;
    private JLabel jlLogo;
    private JLabel jlPrezime;
    private JLabel jlSifraAutomobila;
    private JPanel jplNoviKlijent;
    private JTextField jtfBrojRegistracije;
    private JTextField jtfImeKlijenta;
    private JTextField jtfJmbgKlijenta;
    private JTextField jtfPrezimeKlijenta;
    private JTextField jtfSifraAutomobila;
    private ComponentValidator tekstValidator;
    private ComponentValidator jmbgValidator;
    private KeyEvent evt;

    public UnosAutomobilaKI() {
    }

    public static UnosAutomobilaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new UnosAutomobilaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JComboBox jcbMarkaAutomobila, JComboBox jcbModelAutomobila, JComboBox jcbVlasnik, JLabel jlBrojRegistracije, JLabel jlIme, JLabel jlJmbg, JLabel jlLogo, JLabel jlPrezime, JLabel jlSifraAutomobila, JPanel jplNoviKlijent, JTextField jtfBrojRegistracije, JTextField jtfImeKlijenta, JTextField jtfJmbgKlijenta, JTextField jtfPrezimeKlijenta, JTextField jtfSifraAutomobila) {
        this.glavna = glavna;
        this.jcbMarkaAutomobila = jcbMarkaAutomobila;
        this.jcbModelAutomobila = jcbModelAutomobila;
        this.jcbVlasnik = jcbVlasnik;
        this.jlBrojRegistracije = jlBrojRegistracije;
        this.jlIme = jlIme;
        this.jlJmbg = jlJmbg;
        this.jlLogo = jlLogo;
        this.jlPrezime = jlPrezime;
        this.jlSifraAutomobila = jlSifraAutomobila;
        this.jplNoviKlijent = jplNoviKlijent;
        this.jtfBrojRegistracije = jtfBrojRegistracije;
        this.jtfImeKlijenta = jtfImeKlijenta;
        this.jtfJmbgKlijenta = jtfJmbgKlijenta;
        this.jtfPrezimeKlijenta = jtfPrezimeKlijenta;
        this.jtfSifraAutomobila = jtfSifraAutomobila;
        tekstValidator = new PraznoPoljeValidator();
        jmbgValidator = new JMBGValidator();
    }

    public void srediFormu() {

        jplNoviKlijent.setVisible(false);
        jtfBrojRegistracije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraAutomobila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfImeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraAutomobila.setEditable(false);
        jtfJmbgKlijenta.setDocument(new JTextFieldOgranicenje(13));
        sifraAutomobila();

    }

    public void proveraParametaraAutomobila() {

        String brojRegistracije = jtfBrojRegistracije.getText().trim();

        try {

            tekstValidator.validate(brojRegistracije);
            jtfBrojRegistracije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlBrojRegistracije.setText("");

        } catch (Exception ex) {

            jtfBrojRegistracije.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlBrojRegistracije.setForeground(Color.RED);
            jlBrojRegistracije.setText(ex.getMessage());
            jtfBrojRegistracije.requestFocusInWindow();

        }
    }

    public boolean proveraUnosaRegistracije() {

        String brojRegistracije = jtfBrojRegistracije.getText().trim();

        try {

            tekstValidator.validate(brojRegistracije);
            return true;

        } catch (Exception ex) {
            return false;
        }

    }

    private void sifraAutomobila() {

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_AUTOMOBILE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovor = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Automobil> listaAutomobila = (ArrayList<Automobil>) odgovor.getServerObjekatOdgovor();

            int sifraAutomobila = 0;

            for (Automobil automobil : listaAutomobila) {
                sifraAutomobila = automobil.getSifraAutomobila() + 1;
            }
            jtfSifraAutomobila.setText(sifraAutomobila + "");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void comboMarke() {

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_MARKE_AUTOMOBILA);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovor = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<MarkaAutomobila> listaMarke = (ArrayList<MarkaAutomobila>) odgovor.getServerObjekatOdgovor();

            jcbMarkaAutomobila.removeAllItems();
            for (MarkaAutomobila marka : listaMarke) {
                jcbMarkaAutomobila.addItem(marka);
            }

            TransferKlasa tkmom = new TransferKlasa();
            tkmom.setOperacija(Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA);
            tkmom.setKlijentObjekat(jcbMarkaAutomobila.getSelectedItem());
            Komunikacija.vratiKomunikaciju().posalji(tkmom);
            TransferKlasa odgovorModelaZaMarke = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<ModelAutomobila> lmom = (ArrayList<ModelAutomobila>) odgovorModelaZaMarke.getServerObjekatOdgovor();
            jcbModelAutomobila.removeAllItems();
            for (ModelAutomobila modelAutomobila : lmom) {
                jcbModelAutomobila.addItem(modelAutomobila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void comboKorisnici() {

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_VLASNIKE_AUTOMOBILA);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovor = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<VlasnikAutomobila> listaVlasnika = (ArrayList<VlasnikAutomobila>) odgovor.getServerObjekatOdgovor();

            jcbVlasnik.removeAllItems();
            for (VlasnikAutomobila vlasnik : listaVlasnika) {
                jcbVlasnik.addItem(vlasnik);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }

    }

    public void ocistiPolja() {

        sifraAutomobila();
        jtfBrojRegistracije.setText("");
        jtfImeKlijenta.setText("");
        jtfJmbgKlijenta.setText("");
        jtfPrezimeKlijenta.setText("");
        jcbMarkaAutomobila.setSelectedIndex(0);

    }

    public boolean proveraUnosaKlijenta() {

        String jmbg = jtfJmbgKlijenta.getText().trim();
        String ime = jtfImeKlijenta.getText().trim();
        String prezime = jtfPrezimeKlijenta.getText().trim();

        try {

            tekstValidator.validate(jmbg);
            tekstValidator.validate(ime);
            tekstValidator.validate(prezime);
            return true;

        } catch (Exception ex) {
            return false;
        }

    }

    public void proveraParametaraVlasnika() {

        String jmbg = jtfJmbgKlijenta.getText().trim();
        String ime = jtfImeKlijenta.getText().trim();
        String prezime = jtfPrezimeKlijenta.getText().trim();

        try {

            tekstValidator.validate(jmbg);
            jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlJmbg.setText("");

        } catch (Exception ex) {

            jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlJmbg.setForeground(Color.RED);
            jlJmbg.setText(ex.getMessage());
            jtfJmbgKlijenta.requestFocusInWindow();

        }

        try {

            tekstValidator.validate(ime);
            jtfImeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlIme.setText("");

        } catch (Exception ex) {

            jtfImeKlijenta.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlIme.setForeground(Color.RED);
            jlIme.setText(ex.getMessage());
            jtfImeKlijenta.requestFocusInWindow();

        }

        try {

            tekstValidator.validate(prezime);
            jtfPrezimeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlPrezime.setText("");

        } catch (Exception ex) {

            jtfPrezimeKlijenta.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlPrezime.setForeground(Color.RED);
            jlPrezime.setText(ex.getMessage());
            jtfPrezimeKlijenta.requestFocusInWindow();

        }

    }

    public void postaviSlikeZaSvakuMarkuAutomobila() throws Exception {

        MarkaAutomobila marka = (MarkaAutomobila) jcbMarkaAutomobila.getSelectedItem();
        switch (marka.getSifraMarke()) {
            case 1:
                jlLogo.setIcon(new ImageIcon("slike/logo/alfa.png"));
                break;
            case 2:
                jlLogo.setIcon(new ImageIcon("slike/logo/audi.png"));
                break;
            case 3:
                jlLogo.setIcon(new ImageIcon("slike/logo/bmw.png"));
                break;
            case 4:
                jlLogo.setIcon(new ImageIcon("slike/logo/chevrolet.png"));
                break;
            case 5:
                jlLogo.setIcon(new ImageIcon("slike/logo/citroen.png"));
                break;
            case 6:
                jlLogo.setIcon(new ImageIcon("slike/logo/ferrari.png"));
                break;
            case 7:
                jlLogo.setIcon(new ImageIcon("slike/logo/fiat.png"));
                break;
            case 8:
                jlLogo.setIcon(new ImageIcon("slike/logo/ford.png"));
                break;
            case 9:
                jlLogo.setIcon(new ImageIcon("slike/logo/honda.png"));
                break;
            case 10:
                jlLogo.setIcon(new ImageIcon("slike/logo/lada.png"));
                break;
            case 11:
                jlLogo.setIcon(new ImageIcon("slike/logo/maserati.png"));
                break;
            case 12:
                jlLogo.setIcon(new ImageIcon("slike/logo/maybach.png"));
                break;
            case 13:
                jlLogo.setIcon(new ImageIcon("slike/logo/mercedes.png"));
                break;
            case 14:
                jlLogo.setIcon(new ImageIcon("slike/logo/mitsubishi.png"));
                break;
            case 15:
                jlLogo.setIcon(new ImageIcon("slike/logo/moszkvics.gif"));
                break;
            case 16:
                jlLogo.setIcon(new ImageIcon("slike/logo/nissan.png"));
                break;
            case 17:
                jlLogo.setIcon(new ImageIcon("slike/logo/opel.png"));
                break;
            case 18:
                jlLogo.setIcon(new ImageIcon("slike/logo/peugeot.png"));
                break;
            case 19:
                jlLogo.setIcon(new ImageIcon("slike/logo/porsche.png"));
                break;
            case 20:
                jlLogo.setIcon(new ImageIcon("slike/logo/renault.png"));
                break;
            case 21:
                jlLogo.setIcon(new ImageIcon("slike/logo/seat.png"));
                break;
            case 22:
                jlLogo.setIcon(new ImageIcon("slike/logo/subaru.png"));
                break;
            case 23:
                jlLogo.setIcon(new ImageIcon("slike/logo/suzuki.png"));
                break;
            case 24:
                jlLogo.setIcon(new ImageIcon("slike/logo/toyota.png"));
                break;
            case 25:
                jlLogo.setIcon(new ImageIcon("slike/logo/vw.png"));
                break;
            case 26:
                jlLogo.setIcon(new ImageIcon("slike/logo/volvo.png"));
                break;
            case 27:
                jlLogo.setIcon(new ImageIcon("slike/logo/wartburg.png"));
                break;
            case 28:
                jlLogo.setIcon(new ImageIcon("slike/logo/yugo.png"));
                break;
            case 29:
                jlLogo.setIcon(new ImageIcon("slike/logo/zastava.png"));
                break;
            default:
                throw new Exception("Greska u switch metodi za prikaz slika marki automobila!");
        }

    }

    public void dodajNovogKlijenta() {
        jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfImeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jlJmbg.setText("");
        jlIme.setText("");
        jlPrezime.setText("");
        jplNoviKlijent.setVisible(true);
        jtfJmbgKlijenta.requestFocusInWindow();
    }

    public void dodaj() {
        proveraParametaraVlasnika();
        if (!proveraUnosaKlijenta()) {
            return;
        }

        try {

            String jmbg = jtfJmbgKlijenta.getText();
            String imeKlijenta = jtfImeKlijenta.getText();
            String prezimeKlijenta = jtfPrezimeKlijenta.getText();

            VlasnikAutomobila va = new VlasnikAutomobila(jmbg, imeKlijenta, prezimeKlijenta);

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.DODAJ_NOVOG_VLASNIKA_AUTOMOBILA);
            tk.setKlijentObjekat(va);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }

            JOptionPane.showMessageDialog(glavna, "Vlasnik automobila je uspešno dodat!", "Unos vlasnika", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
            jtfImeKlijenta.setText("");
            jtfJmbgKlijenta.setText("");
            jtfPrezimeKlijenta.setText("");
            jtfJmbgKlijenta.requestFocus();
            comboKorisnici();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void brojRegistracijeKeyRelease(KeyEvent evt) {
        jtfBrojRegistracije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jlBrojRegistracije.setText("");
        jplNoviKlijent.setVisible(false);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jcbMarkaAutomobila.requestFocusInWindow();
        }
    }

    public void jcbMarkaAutomobilaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jcbModelAutomobila.requestFocusInWindow();
        }
    }

    public void jcbModelAutomobilaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jcbVlasnik.requestFocusInWindow();
        }
    }

    public void jbtDodajNovogKlijentaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jtfImeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jtfPrezimeKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlJmbg.setText("");
            jlIme.setText("");
            jlPrezime.setText("");
            jplNoviKlijent.setVisible(true);
            jtfJmbgKlijenta.requestFocusInWindow();
        }
    }

    public void jtfJmbgKlijentaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {

                jmbgValidator.validate(jtfJmbgKlijenta.getText().trim());
                jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                jlJmbg.setText("");
                jtfImeKlijenta.requestFocusInWindow();

            } catch (Exception ex) {

                jtfJmbgKlijenta.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jlJmbg.setForeground(Color.red);
                jlJmbg.setText(ex.getMessage());
                jtfJmbgKlijenta.requestFocusInWindow();

            }
        }
    }

    public void jtfImeKlijentaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtfPrezimeKlijenta.requestFocusInWindow();
        }
    }

    public void markaAutomobila() {
        try {

            postaviSlikeZaSvakuMarkuAutomobila();

            TransferKlasa tkmom = new TransferKlasa();
            tkmom.setOperacija(Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA);
            tkmom.setKlijentObjekat(jcbMarkaAutomobila.getSelectedItem());
            Komunikacija.vratiKomunikaciju().posalji(tkmom);
            TransferKlasa odgovorModelaZaMarke = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<ModelAutomobila> lmom = (ArrayList<ModelAutomobila>) odgovorModelaZaMarke.getServerObjekatOdgovor();
            jcbModelAutomobila.removeAllItems();
            for (ModelAutomobila modelAutomobila : lmom) {
                jcbModelAutomobila.addItem(modelAutomobila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void ubaciPodatke() {
        proveraParametaraAutomobila();
        if (!proveraUnosaRegistracije()) {
            return;
        }

        try {

            String brojRegistracije = jtfBrojRegistracije.getText().trim().toUpperCase();
            MarkaAutomobila marka = (MarkaAutomobila) jcbMarkaAutomobila.getSelectedItem();
            ModelAutomobila model = (ModelAutomobila) jcbModelAutomobila.getSelectedItem();
            VlasnikAutomobila vlasnik = (VlasnikAutomobila) jcbVlasnik.getSelectedItem();

            Automobil a = new Automobil(-1, brojRegistracije, model, vlasnik);

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.SACUVAJ_AUTOMOBIL);
            tk.setKlijentObjekat(a);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }

            JOptionPane.showMessageDialog(glavna, "Automobil je uspešno unet!", "Unos automobila", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
            ocistiPolja();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jcbVlasnikKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ubaciPodatke();
        }
    }

    public void jtfPrezimeKlijentaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dodaj();
        }
    }
}
