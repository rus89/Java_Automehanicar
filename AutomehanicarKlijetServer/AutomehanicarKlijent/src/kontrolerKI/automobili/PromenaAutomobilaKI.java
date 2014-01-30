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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import ogranicenja.JTextFieldOgranicenje;
import transfer.TransferKlasa;
import util.Konstante;
import validator.ComponentValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Administrator
 */
public class PromenaAutomobilaKI {

    private static PromenaAutomobilaKI objekat;
    private JPanel glavna;
    private JComboBox jcbMarka;
    private JComboBox jcbModel;
    private JLabel jlIme;
    private JLabel jlJmbg;
    private JLabel jlObavestenja;
    private JLabel jlPrezime;
    private JLabel jlRegistracija;
    private JMenuItem jmiPromeniPodatke;
    private JPopupMenu jpmPromeniPodatke;
    private JRadioButton jrbJmbgVlasnika;
    private JRadioButton jrbRegistracija;
    private JTextField jtfImeVlasnika;
    private JTextField jtfJmbgVlasnika;
    private JTextField jtfPretraga;
    private JTextField jtfPrezimeVlasnika;
    private JTextField jtfRegistracija;
    private JTextField jtfSifraAutomobila;
    private JButton jbtPonisti;
    private JButton jbtPrethodni;
    private JButton jbtPromeniPodatke;
    private JButton jbtPronadji;
    private JButton jbtSledeci;
    private ComponentValidator tekstValidator;
    private ArrayList<Automobil> la;
    private int brojAutomobila;

    public PromenaAutomobilaKI() {
    }

    public static PromenaAutomobilaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PromenaAutomobilaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JComboBox jcbMarka, JComboBox jcbModel, JLabel jlIme, JLabel jlJmbg, JLabel jlObavestenja, JLabel jlPrezime, JLabel jlRegistracija, JMenuItem jmiPromeniPodatke, JPopupMenu jpmPromeniPodatke, JRadioButton jrbJmbgVlasnika, JRadioButton jrbRegistracija, JTextField jtfImeVlasnika, JTextField jtfJmbgVlasnika, JTextField jtfPretraga, JTextField jtfPrezimeVlasnika, JTextField jtfRegistracija, JTextField jtfSifraAutomobila, JButton jbtPrethodni, JButton jbtPromeniPodatke, JButton jbtPronadji, JButton jbtSledeci, JButton jbtPonisti) {
        this.glavna = glavna;
        this.jcbMarka = jcbMarka;
        this.jcbModel = jcbModel;
        this.jlIme = jlIme;
        this.jlJmbg = jlJmbg;
        this.jlObavestenja = jlObavestenja;
        this.jlPrezime = jlPrezime;
        this.jlRegistracija = jlRegistracija;
        this.jmiPromeniPodatke = jmiPromeniPodatke;
        this.jpmPromeniPodatke = jpmPromeniPodatke;
        this.jrbJmbgVlasnika = jrbJmbgVlasnika;
        this.jrbRegistracija = jrbRegistracija;
        this.jtfImeVlasnika = jtfImeVlasnika;
        this.jtfJmbgVlasnika = jtfJmbgVlasnika;
        this.jtfPretraga = jtfPretraga;
        this.jtfPrezimeVlasnika = jtfPrezimeVlasnika;
        this.jtfRegistracija = jtfRegistracija;
        this.jtfSifraAutomobila = jtfSifraAutomobila;
        this.jbtPrethodni = jbtPrethodni;
        this.jbtPromeniPodatke = jbtPromeniPodatke;
        this.jbtPronadji = jbtPronadji;
        this.jbtSledeci = jbtSledeci;
        this.jbtPonisti = jbtPonisti;
        tekstValidator = new PraznoPoljeValidator();
        jtfJmbgVlasnika.setDocument(new JTextFieldOgranicenje(13));
    }

    public void napuniCombo() {

        try {

            TransferKlasa tkMarke = new TransferKlasa();
            tkMarke.setOperacija(Konstante.VRATI_SVE_MARKE_AUTOMOBILA);
            Komunikacija.vratiKomunikaciju().posalji(tkMarke);
            TransferKlasa odgovorMarke = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<MarkaAutomobila> listaMarke = (ArrayList<MarkaAutomobila>) odgovorMarke.getServerObjekatOdgovor();

            jcbMarka.removeAllItems();
            for (MarkaAutomobila marka : listaMarke) {
                jcbMarka.addItem(marka);
            }

            TransferKlasa tkmom = new TransferKlasa();
            tkmom.setOperacija(Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA);
            tkmom.setKlijentObjekat(jcbMarka.getSelectedItem());
            Komunikacija.vratiKomunikaciju().posalji(tkmom);
            TransferKlasa odgovorModelaZaMarke = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<ModelAutomobila> lmom = (ArrayList<ModelAutomobila>) odgovorModelaZaMarke.getServerObjekatOdgovor();
            jcbModel.removeAllItems();
            for (ModelAutomobila modelAutomobila : lmom) {
                jcbModel.addItem(modelAutomobila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void srediFormu(boolean b) {

        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPretraga.setText("");
        jtfPretraga.setEditable(true);
        jlObavestenja.setText("");
        jbtPromeniPodatke.setEnabled(b);
        jbtPonisti.setEnabled(b);
        jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfRegistracija.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraAutomobila.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfImeVlasnika.setEnabled(b);
        jtfJmbgVlasnika.setEnabled(b);
        jtfPrezimeVlasnika.setEnabled(b);
        jtfRegistracija.setEnabled(b);
        jtfSifraAutomobila.setEnabled(false);
        jcbMarka.setEnabled(b);
        jcbModel.setEnabled(b);

    }

    public void proveraParametraPretrage() {

        String pretraga = jtfPretraga.getText().trim();

        try {
            tekstValidator.validate(pretraga);
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlObavestenja.setText("");
        } catch (Exception ex) {
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlObavestenja.setForeground(Color.RED);
            jlObavestenja.setText(ex.getMessage());
            jtfPretraga.requestFocusInWindow();
        }
    }

    public void obrisiPolja() {
        jtfImeVlasnika.setText("");
        jtfJmbgVlasnika.setText("");
        jtfPrezimeVlasnika.setText("");
        jtfRegistracija.setText("");
        jtfSifraAutomobila.setText("");
        jcbMarka.setSelectedIndex(0);
        jcbModel.setSelectedIndex(0);
    }

    public void proveraParametraZaAutomobil() {

        String registracija = jtfRegistracija.getText().trim();
        String jmbgVlasnika = jtfJmbgVlasnika.getText().trim();
        String imeVlasnika = jtfImeVlasnika.getText().trim();
        String prezimeVlasnika = jtfPrezimeVlasnika.getText().trim();

        try {
            tekstValidator.validate(registracija);
            jtfRegistracija.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlRegistracija.setText("");
        } catch (Exception ex) {
            jtfRegistracija.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jtfRegistracija.requestFocusInWindow();
            jlRegistracija.setForeground(Color.RED);
            jlRegistracija.setText(ex.getMessage());
        }

        try {
            tekstValidator.validate(jmbgVlasnika);
            jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlJmbg.setText("");
        } catch (Exception ex) {
            jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jtfJmbgVlasnika.requestFocusInWindow();
            jlJmbg.setForeground(Color.RED);
            jlJmbg.setText(ex.getMessage());
        }

        try {
            tekstValidator.validate(imeVlasnika);
            jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlIme.setText("");
        } catch (Exception ex) {
            jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jtfImeVlasnika.requestFocusInWindow();
            jlIme.setForeground(Color.RED);
            jlIme.setText(ex.getMessage());
        }

        try {
            tekstValidator.validate(prezimeVlasnika);
            jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlPrezime.setText("");
        } catch (Exception ex) {
            jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jtfPrezimeVlasnika.requestFocusInWindow();
            jlPrezime.setForeground(Color.RED);
            jlPrezime.setText(ex.getMessage());
        }
    }

    public boolean proveraUnosaZaAutomobil() {
        String registracija = jtfRegistracija.getText().trim();
        String jmbgVlasnika = jtfJmbgVlasnika.getText().trim();
        String imeVlasnika = jtfImeVlasnika.getText().trim();
        String prezimeVlasnika = jtfPrezimeVlasnika.getText().trim();
        try {
            tekstValidator.validate(registracija);
            tekstValidator.validate(jmbgVlasnika);
            tekstValidator.validate(imeVlasnika);
            tekstValidator.validate(prezimeVlasnika);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean proveraUnosa() {
        String pretraga = jtfPretraga.getText().trim();

        try {
            tekstValidator.validate(pretraga);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public void jrbRegistracija() {
        obrisiPolja();
        jtfPretraga.setEnabled(true);
        jlObavestenja.setText("");
        jtfPretraga.requestFocusInWindow();
    }

    public void jtfPretragaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            try {
                tekstValidator.validate(jtfPretraga.getText().trim());
                jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                jlObavestenja.setText("");
                jtfPretraga.requestFocusInWindow();
                jbtPronadji();
            } catch (Exception ex) {
                jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jlObavestenja.setForeground(Color.red);
                jlObavestenja.setText(ex.getMessage());
                jtfPretraga.requestFocusInWindow();
            }
        }
    }

    public void jbtPronadji() {
        proveraParametraPretrage();
        if (!proveraUnosa()) {
            return;
        }
        TransferKlasa tk = new TransferKlasa();
        try {

            if (jrbRegistracija.isSelected()) {

                String registracija = jtfPretraga.getText().trim().toUpperCase();
                Automobil auto = new Automobil(-1, registracija, null, null);
                tk.setOperacija(Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE);
                tk.setKlijentObjekat(auto);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                auto = (Automobil) tk.getServerObjekatOdgovor();

                jtfSifraAutomobila.setText(String.valueOf(auto.getSifraAutomobila()));
                jtfRegistracija.setText(auto.getBrojRegistracije());
                jcbMarka.setSelectedItem(auto.getModel().getMarkaAutomobila());
                jcbModel.setSelectedItem(auto.getModel());
                jtfJmbgVlasnika.setText(auto.getVlasnik().getJmbg());
                jtfImeVlasnika.setText(auto.getVlasnik().getIme());
                jtfPrezimeVlasnika.setText(auto.getVlasnik().getPrezime());
                srediFormu(true);

            }
            if (jrbJmbgVlasnika.isSelected()) {

                String jmbgVlasnika = jtfPretraga.getText().trim();
                VlasnikAutomobila va = new VlasnikAutomobila(jmbgVlasnika, "", "");
                tk.setOperacija(Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA);
                tk.setKlijentObjekat(va);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }

                ArrayList<Automobil> listaA = (ArrayList<Automobil>) tk.getServerObjekatOdgovor();
                la = listaA;
                brojAutomobila = 1;
                Automobil aut = listaA.get(0);
                jtfSifraAutomobila.setText(String.valueOf(aut.getSifraAutomobila()));
                jtfRegistracija.setText(aut.getBrojRegistracije());
                jcbMarka.setSelectedItem(aut.getModel().getMarkaAutomobila());
                jcbModel.setSelectedItem(aut.getModel());
                jtfJmbgVlasnika.setText(aut.getVlasnik().getJmbg());
                jtfImeVlasnika.setText(aut.getVlasnik().getIme());
                jtfPrezimeVlasnika.setText(aut.getVlasnik().getPrezime());
                if (listaA.size() > 1) {
                    jbtPrethodni.setEnabled(false);
                    jbtSledeci.setEnabled(true);
                }
                srediFormu(true);
            }

        } catch (Exception e) {
            srediFormu(false);
            obrisiPolja();
            JOptionPane.showMessageDialog(glavna, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }

    }

    public void jbtPromeniPodatkeActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da promenite podatke?", "Promena podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {

            try {
                proveraParametraZaAutomobil();
                if (!proveraUnosaZaAutomobil()) {
                    return;
                }

                Automobil auto = new Automobil();
                ModelAutomobila model = (ModelAutomobila) jcbModel.getSelectedItem();
                MarkaAutomobila marka = (MarkaAutomobila) jcbMarka.getSelectedItem();
                VlasnikAutomobila va = new VlasnikAutomobila();
                model.setMarkaAutomobila(marka);
                va.setJmbg(jtfJmbgVlasnika.getText().trim());
                va.setIme(jtfImeVlasnika.getText().trim());
                va.setPrezime(jtfPrezimeVlasnika.getText().trim());
                auto.setBrojRegistracije(jtfRegistracija.getText().trim().toUpperCase());
                auto.setSifraAutomobila(Integer.parseInt(jtfSifraAutomobila.getText().trim()));
                auto.setModel(model);
                auto.setVlasnik(va);

                TransferKlasa tk = new TransferKlasa();
                tk.setOperacija(Konstante.PROMENA_PODATAKA_AUTOMOBILA);
                tk.setKlijentObjekat(auto);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspešno izmenjeni!", "Izmena automobila", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                srediFormu(false);
                obrisiPolja();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jbtPonisti() {
        obrisiPolja();
        srediFormu(false);
        jbtPrethodni.setEnabled(false);
        jbtSledeci.setEnabled(false);
    }

    public void jrbJmbgVlasnikaActionPerformed() {
        obrisiPolja();
        jtfPretraga.setDocument(new JTextFieldOgranicenje(13));
        jtfPretraga.setEnabled(true);
        jlObavestenja.setText("");
        jtfPretraga.requestFocusInWindow();

    }

    public void jtfPretragaFocusGained() {
        obrisiPolja();
        srediFormu(false);
    }

    public void jcbMarkaActionPerformed() {
        try {

            TransferKlasa tkmom = new TransferKlasa();
            tkmom.setOperacija(Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA);
            tkmom.setKlijentObjekat(jcbMarka.getSelectedItem());
            Komunikacija.vratiKomunikaciju().posalji(tkmom);
            TransferKlasa odgovorModelaZaMarke = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<ModelAutomobila> lmom = (ArrayList<ModelAutomobila>) odgovorModelaZaMarke.getServerObjekatOdgovor();
            jcbModel.removeAllItems();
            for (ModelAutomobila modelAutomobila : lmom) {
                jcbModel.addItem(modelAutomobila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jbtSledeciActionPerformed() {
        if (la.size() > brojAutomobila) {
            Automobil a = la.get(brojAutomobila);
            jtfSifraAutomobila.setText(String.valueOf(a.getSifraAutomobila()));
            jtfRegistracija.setText(a.getBrojRegistracije());
            jcbMarka.setSelectedItem(a.getModel().getMarkaAutomobila());
            jcbModel.setSelectedItem(a.getModel());
            jtfJmbgVlasnika.setText(a.getVlasnik().getJmbg());
            jtfImeVlasnika.setText(a.getVlasnik().getIme());
            jtfPrezimeVlasnika.setText(a.getVlasnik().getPrezime());
            brojAutomobila++;
            jbtPrethodni.setEnabled(true);
        } else {
            jbtSledeci.setEnabled(false);
            brojAutomobila = la.size() - 1;
        }
    }

    public void jbtPrethodniActionPerformed() {
        if (brojAutomobila > 0) {
            Automobil a = la.get(brojAutomobila - 1);
            jtfSifraAutomobila.setText(String.valueOf(a.getSifraAutomobila()));
            jtfRegistracija.setText(a.getBrojRegistracije());
            jcbMarka.setSelectedItem(a.getModel().getMarkaAutomobila());
            jcbModel.setSelectedItem(a.getModel());
            jtfJmbgVlasnika.setText(a.getVlasnik().getJmbg());
            jtfImeVlasnika.setText(a.getVlasnik().getIme());
            jtfPrezimeVlasnika.setText(a.getVlasnik().getPrezime());
            brojAutomobila--;
            jbtSledeci.setEnabled(true);
        } else {
            jbtPrethodni.setEnabled(false);
            brojAutomobila = 1;
        }
    }
}
