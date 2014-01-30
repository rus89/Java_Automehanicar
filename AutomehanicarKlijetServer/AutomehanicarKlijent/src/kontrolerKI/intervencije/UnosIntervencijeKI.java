/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.intervencije;

import domen.Automobil;
import domen.Intervencija;
import domen.OpisIntervencije;
import domen.Zaposleni;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import komunikacija.Komunikacija;
import paneli.modeli.opis.intervencija.OpisIntervencijaTableModel;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;
import validator.ComponentValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Rus
 */
public class UnosIntervencijeKI {

    private static UnosIntervencijeKI objekat;
    private JPanel glavna;
    private ComponentValidator tekstValidator;
    private ArrayList<OpisIntervencije> listaOpisa;
    private ArrayList<OpisIntervencije> listaUradjenih;
    private Intervencija intervencija;
    private JComboBox jcbAutomobil;
    private JFormattedTextField jftfDatum;
    private JLabel jlDatum;
    private JTable jtblOpisIntervencije;
    private JTable jtblUradjeneIntervencije;
    private JTextField jtfRadnik;
    private JTextField jtfSifraIntervencije;

    public UnosIntervencijeKI() {
    }

    public static UnosIntervencijeKI vratiObjekat() {
        if (objekat == null) {
            objekat = new UnosIntervencijeKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JComboBox jcbAutomobil, JFormattedTextField jftfDatum, JLabel jlDatum, JTable jtblOpisIntervencije, JTable jtblUradjeneIntervencije, JTextField jtfRadnik, JTextField jtfSifraIntervencije) {
        this.glavna = glavna;
        this.jcbAutomobil = jcbAutomobil;
        this.jftfDatum = jftfDatum;
        this.jlDatum = jlDatum;
        this.jtblOpisIntervencije = jtblOpisIntervencije;
        this.jtblUradjeneIntervencije = jtblUradjeneIntervencije;
        this.jtfRadnik = jtfRadnik;
        this.jtfSifraIntervencije = jtfSifraIntervencije;
        tekstValidator = new PraznoPoljeValidator();
        listaOpisa = new ArrayList<OpisIntervencije>();
        listaUradjenih = new ArrayList<OpisIntervencije>();
        intervencija = new Intervencija();
    }

    public void srediKomponente() {
        try {
            jtfRadnik.setEditable(false);
            jtfSifraIntervencije.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jtfSifraIntervencije.setEditable(false);
            jtfRadnik.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jftfDatum.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            MaskFormatter mf = new MaskFormatter("##.##.####");
            mf.setPlaceholderCharacter('_');
            jftfDatum.setFormatterFactory(new DefaultFormatterFactory(mf, mf, mf, mf));
            sifraIntervencije();

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }

    }

    public void napuniCombo() {

        try {

            jtfRadnik.setText(UseCase.vratiUC().vratiVrednost("ulogovan").toString());

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_AUTOMOBILE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            TransferKlasa odgovorMarke = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            ArrayList<Automobil> lma = (ArrayList<Automobil>) odgovorMarke.getServerObjekatOdgovor();
            jcbAutomobil.removeAllItems();
            for (Automobil automobil : lma) {
                jcbAutomobil.addItem(automobil);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void proveraParametara() {

        String datum = jftfDatum.getText().trim();

        try {
            tekstValidator.validate(datum);
            jftfDatum.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlDatum.setText("");
        } catch (Exception ex) {
            jftfDatum.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlDatum.setForeground(Color.RED);
            jlDatum.setText(ex.getMessage());
            jftfDatum.requestFocusInWindow();
        }

    }

    public boolean proveraUnosa() {

        String datum = jftfDatum.getText().trim();

        try {
            tekstValidator.validate(datum);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public void popuniTabeluSvimIntervencijama() {

        postaviPrazanModelTabele();
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_OPISE_INTERVENCIJE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            listaOpisa = (ArrayList<OpisIntervencije>) tk.getServerObjekatOdgovor();
            DefaultTableModel dtm = (DefaultTableModel) jtblOpisIntervencije.getModel();
            for (OpisIntervencije oi : listaOpisa) {
                Object[] red = new Object[1];
                red[0] = oi.getOpis();
                dtm.addRow(red);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void postaviPrazanModelTabele() {
        jtblOpisIntervencije.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Opis"
                }) {
            boolean[] canEdit = new boolean[]{
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void sifraIntervencije() {
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_INTERVENCIJE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            ArrayList<Intervencija> li = (ArrayList<Intervencija>) tk.getServerObjekatOdgovor();
            int sifra = 0;
            for (Intervencija i : li) {
                sifra = i.getSifraIntervencije() + 1;
            }
            jtfSifraIntervencije.setText(sifra + "");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }

    }

    public void obrisiPolja() {
        listaUradjenih.clear();
        jftfDatum.setText("");
        jcbAutomobil.setSelectedIndex(0);
        popuniTabeluSvimIntervencijama();
        postaviPrazanModelTabeleUradjenigIntervencija();
    }

    public void postaviPrazanModelTabeleUradjenigIntervencija() {
        jtblUradjeneIntervencije.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Opis"
                }) {
            boolean[] canEdit = new boolean[]{
                false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void jbtUbaciIntervencijuActionPerformed() {
        proveraParametara();
        if (!proveraUnosa()) {
            return;
        }

        try {

            TransferKlasa tk = new TransferKlasa();

            Zaposleni z = (Zaposleni) UseCase.vratiUC().vratiVrednost("ulogovan");
            Automobil a = (Automobil) jcbAutomobil.getSelectedItem();
            String jftfdatum = jftfDatum.getText().trim();
            Date datum = new SimpleDateFormat("dd.MM.yyyy").parse(jftfdatum);
            intervencija.setAutomobil(a);
            intervencija.setZaposleni(z);
            intervencija.setDatumIntervencije(datum);
            intervencija.setSifraIntervencije(-1);

            tk.setOperacija(Konstante.SACUVAJ_INTERVENCIJU);
            tk.setKlijentObjekat(intervencija);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }

            JOptionPane.showMessageDialog(glavna, "Intervencija je uspesno uneta!", "Unos intervencije", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
            obrisiPolja();

        } catch (ParseException ex) {
            jlDatum.setForeground(Color.red);
            jlDatum.setText("Morate popuniti polje sa datumom");
            jftfDatum.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));

        }
    }

    public void jtblOpisIntervencijeMousePressed(MouseEvent evt) {
        int klik = evt.getClickCount();
        if (klik == 2) {
            try {
                if (listaOpisa.isEmpty()) {
                    TransferKlasa tk = new TransferKlasa();
                    tk.setOperacija(Konstante.VRATI_SVE_OPISE_INTERVENCIJE);
                    Komunikacija.vratiKomunikaciju().posalji(tk);
                    tk = Komunikacija.vratiKomunikaciju().procitaj();
                    if (!tk.getServerObjekatPoruka().equals("")) {
                        throw new Exception(tk.getServerObjekatPoruka());
                    }
                    listaOpisa = (ArrayList<OpisIntervencije>) tk.getServerObjekatOdgovor();
                }

                int i = jtblOpisIntervencije.getSelectedRow();
                OpisIntervencije oi = listaOpisa.get(i);
                intervencija.dodajNovuStavkuSaKlijentskeStrane(oi);
                listaUradjenih.add(oi);
                listaOpisa.remove(oi);
                OpisIntervencijaTableModel oitm = new OpisIntervencijaTableModel(listaUradjenih);
                jtblUradjeneIntervencije.setModel(oitm);
                OpisIntervencijaTableModel oit = new OpisIntervencijaTableModel(listaOpisa);
                jtblOpisIntervencije.setModel(oit);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jtblUradjeneIntervencijeMousePressed(MouseEvent evt) {
        int klik = evt.getClickCount();
        if (klik == 2) {
            try {

                int i = jtblUradjeneIntervencije.getSelectedRow();
                OpisIntervencije oi = listaUradjenih.get(i);
                intervencija.obrisiUradjenuIntervenciju(i);
                listaUradjenih.remove(oi);
                listaOpisa.add(oi);
                OpisIntervencijaTableModel oitm = new OpisIntervencijaTableModel(listaUradjenih);
                jtblUradjeneIntervencije.setModel(oitm);
                OpisIntervencijaTableModel oit = new OpisIntervencijaTableModel(listaOpisa);
                jtblOpisIntervencije.setModel(oit);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(glavna, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }
}
