/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.intervencije;

import domen.Automobil;
import domen.Intervencija;
import domen.VlasnikAutomobila;
import domen.Zaposleni;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
 * @author Rus
 */
public class PromenaIntervencijeKI {

    private static PromenaIntervencijeKI objekat;
    private JPanel glavna;
    private JButton jbtPonisti;
    private JButton jbtPromeniPodatke;
    private JButton jbtPronadji;
    private JComboBox jcbAutomobil;
    private JComboBox jcbRadniciPretraga;
    private JComboBox jcbRadnik;
    private JFormattedTextField jftfDatumIntervencije;
    private JLabel jlIme;
    private JLabel jlJmbg;
    private JLabel jlObavestenje;
    private JLabel jlPrezime;
    private JMenuItem jmiPromeniPodatke;
    private JPopupMenu jpmPromeniPodatke;
    private JRadioButton jrbRadnik;
    private JRadioButton jrbRegistracija;
    private JTextField jtfImeVlasnika;
    private JTextField jtfJmbgVlasnika;
    private JTextField jtfPretraga;
    private JTextField jtfPrezimeVlasnika;
    private JTextField jtfSifraIntervencije;
    private ComponentValidator tekstValidator;
    private ArrayList<Intervencija> listaIntervencija;
    private Intervencija intervencija;

    public PromenaIntervencijeKI() {
    }

    public static PromenaIntervencijeKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PromenaIntervencijeKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JButton jbtPonisti, JButton jbtPromeniPodatke, JButton jbtPronadji, JComboBox jcbAutomobil, JComboBox jcbRadniciPretraga, JComboBox jcbRadnik, JFormattedTextField jftfDatumIntervencije, JLabel jlIme, JLabel jlJmbg, JLabel jlObavestenje, JLabel jlPrezime, JMenuItem jmiPromeniPodatke, JPopupMenu jpmPromeniPodatke, JRadioButton jrbRadnik, JRadioButton jrbRegistracija, JTextField jtfImeVlasnika, JTextField jtfJmbgVlasnika, JTextField jtfPretraga, JTextField jtfPrezimeVlasnika, JTextField jtfSifraIntervencije) {
        this.glavna = glavna;
        this.jbtPonisti = jbtPonisti;
        this.jbtPromeniPodatke = jbtPromeniPodatke;
        this.jbtPronadji = jbtPronadji;
        this.jcbAutomobil = jcbAutomobil;
        this.jcbRadniciPretraga = jcbRadniciPretraga;
        this.jcbRadnik = jcbRadnik;
        this.jftfDatumIntervencije = jftfDatumIntervencije;
        this.jlIme = jlIme;
        this.jlJmbg = jlJmbg;
        this.jlObavestenje = jlObavestenje;
        this.jlPrezime = jlPrezime;
        this.jmiPromeniPodatke = jmiPromeniPodatke;
        this.jpmPromeniPodatke = jpmPromeniPodatke;
        this.jrbRadnik = jrbRadnik;
        this.jrbRegistracija = jrbRegistracija;
        this.jtfImeVlasnika = jtfImeVlasnika;
        this.jtfJmbgVlasnika = jtfJmbgVlasnika;
        this.jtfPretraga = jtfPretraga;
        this.jtfPrezimeVlasnika = jtfPrezimeVlasnika;
        this.jtfSifraIntervencije = jtfSifraIntervencije;
        tekstValidator = new PraznoPoljeValidator();
        intervencija = new Intervencija();
        listaIntervencija = new ArrayList<Intervencija>();
        jtfJmbgVlasnika.setDocument(new JTextFieldOgranicenje(13));
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

    public void srediFormu(boolean b) {

        jftfDatumIntervencije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jftfDatumIntervencije.setEnabled(b);
        jtfImeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfJmbgVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPrezimeVlasnika.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfSifraIntervencije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPretraga.setText("");
        jlObavestenje.setText("");
        jbtPromeniPodatke.setEnabled(b);
        jbtPonisti.setEnabled(b);
        jtfImeVlasnika.setEnabled(b);
        jtfJmbgVlasnika.setEnabled(b);
        jtfPrezimeVlasnika.setEnabled(b);
        jtfSifraIntervencije.setEnabled(false);
        jcbAutomobil.setEnabled(b);
        jcbRadnik.setEnabled(b);
        jcbRadniciPretraga.setEnabled(false);

    }

    public void napuniCombo() {

        TransferKlasa tk = new TransferKlasa();

        try {

            tk.setOperacija(Konstante.VRATI_SVE_AUTOMOBILE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Automobil> la = (ArrayList<Automobil>) tk.getServerObjekatOdgovor();
            jcbAutomobil.removeAllItems();
            for (Automobil automobil : la) {
                jcbAutomobil.addItem(automobil);
            }

            tk.setOperacija(Konstante.VRATI_SVE_RADNIKE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            ArrayList<Zaposleni> listaRadnika = (ArrayList<Zaposleni>) tk.getServerObjekatOdgovor();
            jcbRadniciPretraga.removeAllItems();
            jcbRadnik.removeAllItems();
            for (Zaposleni zaposleni : listaRadnika) {
                jcbRadniciPretraga.addItem(zaposleni);
                jcbRadnik.addItem(zaposleni);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void proveraParametraPretrage() {

        String pretraga = jtfPretraga.getText().trim();

        try {
            tekstValidator.validate(pretraga);
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlObavestenje.setText("");
        } catch (Exception ex) {
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlObavestenje.setForeground(Color.RED);
            jlObavestenje.setText(ex.getMessage());
            jtfPretraga.requestFocusInWindow();
        }
    }

    public void obrisiPolja() {
        jtfImeVlasnika.setText("");
        jtfJmbgVlasnika.setText("");
        jtfPretraga.setText("");
        jtfPrezimeVlasnika.setText("");
        jtfSifraIntervencije.setText("");
        jcbAutomobil.setSelectedIndex(0);
        jcbRadnik.setSelectedIndex(0);
        jcbRadniciPretraga.setSelectedIndex(0);
    }

    public void proveraParametraZaIntervenciju() {

        String jmbgVlasnika = jtfJmbgVlasnika.getText().trim();
        String imeVlasnika = jtfImeVlasnika.getText().trim();
        String prezimeVlasnika = jtfPrezimeVlasnika.getText().trim();

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

    public boolean proveraUnosaZaIntervenciju() {
        String jmbgVlasnika = jtfJmbgVlasnika.getText().trim();
        String imeVlasnika = jtfImeVlasnika.getText().trim();
        String prezimeVlasnika = jtfPrezimeVlasnika.getText().trim();
        try {
            tekstValidator.validate(jmbgVlasnika);
            tekstValidator.validate(imeVlasnika);
            tekstValidator.validate(prezimeVlasnika);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void jtfPretragaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            proveraParametraPretrage();
            jtfPretraga.requestFocusInWindow();
            jbtPronadjiActionPerformed();
        }
    }

    public void jbtPronadjiActionPerformed() {
        TransferKlasa tk = new TransferKlasa();
        try {

            if (jrbRegistracija.isSelected()) {

                proveraParametraPretrage();
                if (!proveraUnosa()) {
                    return;
                }

                String registracija = jtfPretraga.getText().trim();
                Automobil a = new Automobil(-1, registracija, null, null);
                tk.setOperacija(Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_REGISTRACIJE);
                tk.setKlijentObjekat(a);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                Intervencija i = (Intervencija) tk.getServerObjekatOdgovor();
                jtfSifraIntervencije.setText(i.getSifraIntervencije() + "");
                jcbRadnik.setSelectedItem(i.getZaposleni());
                jcbAutomobil.setSelectedItem(i.getAutomobil());
                jtfJmbgVlasnika.setText(i.getAutomobil().getVlasnik().getJmbg());
                jtfImeVlasnika.setText(i.getAutomobil().getVlasnik().getIme());
                jtfPrezimeVlasnika.setText(i.getAutomobil().getVlasnik().getPrezime());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String datum = sdf.format(i.getDatumIntervencije());
                jftfDatumIntervencije.setText(datum);
                srediFormu(true);
            }
            if (jrbRadnik.isSelected()) {

                Zaposleni radnik = (Zaposleni) jcbRadniciPretraga.getSelectedItem();
                tk.setOperacija(Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_RADNIKA);
                tk.setKlijentObjekat(radnik);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                listaIntervencija = (ArrayList<Intervencija>) tk.getServerObjekatOdgovor();
                Intervencija i = listaIntervencija.get(0);
                jtfSifraIntervencije.setText(i.getSifraIntervencije() + "");
                jcbRadnik.setSelectedItem(i.getZaposleni());
                jcbAutomobil.setSelectedItem(i.getAutomobil());
                jtfJmbgVlasnika.setText(i.getAutomobil().getVlasnik().getJmbg());
                jtfImeVlasnika.setText(i.getAutomobil().getVlasnik().getIme());
                jtfPrezimeVlasnika.setText(i.getAutomobil().getVlasnik().getPrezime());
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String datum = sdf.format(i.getDatumIntervencije());
                jftfDatumIntervencije.setText(datum);
                srediFormu(true);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jrbRegistracijaActionPerformed() {
        obrisiPolja();
        srediFormu(false);
        jtfPretraga.setEnabled(true);
        jlObavestenje.setText("");
        jtfPretraga.requestFocusInWindow();
        jcbRadniciPretraga.setEnabled(false);
    }

    public void jrbRadnikActionPerformed() {
        srediFormu(false);
        jcbRadniciPretraga.setEnabled(true);
        jtfPretraga.setEnabled(false);
        obrisiPolja();
        jlObavestenje.setText("");
    }

    public void jbtPonistiActionPerformed() {
        obrisiPolja();
        srediFormu(false);
    }

    public void jbtPromeniPodatkeActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da promenite podatke?", "Promena podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {
            try {
                proveraParametraZaIntervenciju();
                if (!proveraUnosaZaIntervenciju()) {
                    return;
                }

                TransferKlasa tk = new TransferKlasa();
                Zaposleni radnik = (Zaposleni) jcbRadnik.getSelectedItem();
                Automobil auto = (Automobil) jcbAutomobil.getSelectedItem();
                int sifraIntervencije = Integer.parseInt(jtfSifraIntervencije.getText().trim());
                String vJmbg = jtfJmbgVlasnika.getText().trim();
                String vIme = jtfImeVlasnika.getText().trim();
                String vPrezime = jtfPrezimeVlasnika.getText().trim();
                VlasnikAutomobila va = new VlasnikAutomobila(vJmbg, vIme, vPrezime);
                String datum = jftfDatumIntervencije.getText().trim();
                auto.setVlasnik(va);
                Intervencija i = new Intervencija();
                i.setDatumIntervencije(new SimpleDateFormat("dd.MM.yyyy").parse(datum));
                i.setAutomobil(auto);
                i.setSifraIntervencije(sifraIntervencije);
                i.setZaposleni(radnik);

                tk.setOperacija(Konstante.PROMENA_PODATAKA_INTERVENCIJE);
                tk.setKlijentObjekat(i);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspešno izmenjeni!", "Izmena Intervencije", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                srediFormu(false);
                obrisiPolja();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }
}
