/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.automobili;

import domen.Automobil;
import domen.VlasnikAutomobila;
import forme.FrmDetaljiAutomobila;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import komunikacija.Komunikacija;
import ogranicenja.JTextFieldOgranicenje;
import paneli.modeli.automobili.AutomobiliPrikazTableModel;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;
import validator.ComponentValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Administrator
 */
public class BrisanjeAutomobilaKI {

    private static BrisanjeAutomobilaKI objekat;
    private JPanel glavna;
    private JLabel jlObavestenja;
    private JMenuItem jmiDetaljiAutomobila;
    private JMenuItem jmiObrisiAutomobil;
    private JPopupMenu jpmObrisiAutomobil;
    private JRadioButton jrbJmbgVlasnika;
    private JRadioButton jrbRegistracija;
    private JTable jtblPromenaAutomobila;
    private JTextField jtfPretraga;
    private ComponentValidator tekstValidator;
    private ArrayList<Automobil> listaAutomobila;

    public BrisanjeAutomobilaKI() {
    }

    public static BrisanjeAutomobilaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new BrisanjeAutomobilaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JLabel jlObavestenja, JMenuItem jmiDetaljiAutomobila, JMenuItem jmiObrisiAutomobil, JPopupMenu jpmObrisiAutomobil, JRadioButton jrbJmbgVlasnika, JRadioButton jrbRegistracija, JTable jtblPromenaAutomobila, JTextField jtfPretraga) {
        this.glavna = glavna;
        this.jlObavestenja = jlObavestenja;
        this.jmiDetaljiAutomobila = jmiDetaljiAutomobila;
        this.jmiObrisiAutomobil = jmiObrisiAutomobil;
        this.jpmObrisiAutomobil = jpmObrisiAutomobil;
        this.jrbJmbgVlasnika = jrbJmbgVlasnika;
        this.jrbRegistracija = jrbRegistracija;
        this.jtblPromenaAutomobila = jtblPromenaAutomobila;
        this.jtfPretraga = jtfPretraga;
        tekstValidator = new PraznoPoljeValidator();
        listaAutomobila = new ArrayList<Automobil>();
    }

    public void proveraParametraPretrage() {

        String pretraga = jtfPretraga.getText().trim();

        try {
            tekstValidator.validate(pretraga);
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlObavestenja.setText("");
            jtfPretraga.requestFocusInWindow();
        } catch (Exception ex) {
            jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlObavestenja.setForeground(Color.RED);
            jlObavestenja.setText(ex.getMessage());
            jtfPretraga.requestFocusInWindow();
            return;
        }
    }

    public void popuniTabelu(ArrayList<Automobil> la) {
        postaviPrazanModelTabele();
        AutomobiliPrikazTableModel atm = new AutomobiliPrikazTableModel(la);
        jtblPromenaAutomobila.setModel(atm);
    }

    public void postaviPrazanModelTabele() {
        jtblPromenaAutomobila.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "Sifra automobila", "Broj registracije", "Marka automobila", "Model automobila", "Vlasnik automobila"
        }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void srediPopUpMenu() {
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jpmObrisiAutomobil.setBorderPainted(true);
        jmiDetaljiAutomobila.setText("Detalji");
        jmiDetaljiAutomobila.setIcon(new ImageIcon("slike/info16.png"));
        jmiObrisiAutomobil.setText("Obriši automobil");
        jmiObrisiAutomobil.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jmiDetaljiAutomobila.setMnemonic('D');
    }

    public void jbtPronadjiActionPerformed() {
        proveraParametraPretrage();
        TransferKlasa tk = new TransferKlasa();
        try {

            if (jrbRegistracija.isSelected()) {

                String registracija = jtfPretraga.getText().trim().toUpperCase();
                Automobil auto = new Automobil(-1, registracija, null, null);
                auto.setBrojRegistracije(registracija);
                tk.setOperacija(Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE);
                tk.setKlijentObjekat(auto);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                auto = (Automobil) tk.getServerObjekatOdgovor();
                for (Automobil automobil : listaAutomobila) {
                    if (automobil.getSifraAutomobila() == auto.getSifraAutomobila()) {
                        listaAutomobila.clear();
                        return;
                    }
                }
                listaAutomobila.add(auto);
                popuniTabelu(listaAutomobila);

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
                listaAutomobila = (ArrayList<Automobil>) tk.getServerObjekatOdgovor();
                popuniTabelu(listaAutomobila);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(glavna, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jrbRegistracijaActionPerformed() {
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        postaviPrazanModelTabele();
        jlObavestenja.setText("");
        jtfPretraga.requestFocusInWindow();
    }

    public void jtfPretragaKeyReleased(KeyEvent evt) {
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jlObavestenja.setText("");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                tekstValidator.validate(jtfPretraga.getText().trim());
                jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                jlObavestenja.setText("");
                jtfPretraga.requestFocusInWindow();
                jbtPronadjiActionPerformed();
            } catch (Exception ex) {
                jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jlObavestenja.setForeground(Color.red);
                jlObavestenja.setText(ex.getMessage());
                jtfPretraga.requestFocusInWindow();
                return;
            }
        }
    }

    public void jrbJmbgVlasnikaActionPerformed() {
        postaviPrazanModelTabele();
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPretraga.setDocument(new JTextFieldOgranicenje(13));
        jlObavestenja.setText("");
        jtfPretraga.requestFocusInWindow();
    }

    public void jbtObrisiActionPerformed() {
        if (jtblPromenaAutomobila.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(glavna, "Morate selektovati neki automobil!", "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            return;
        }

        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da obrišete podatke?", "Brisanje podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {

            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblPromenaAutomobila.getSelectedRow();
                Automobil a = listaAutomobila.get(i);
                tk.setOperacija(Konstante.OBRISI_AUTOMOBIL);
                tk.setKlijentObjekat(a);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspesno obrisani!", "Brisanje podataka", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                listaAutomobila.remove(i);
                popuniTabelu(listaAutomobila);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jtblPromenaAutomobilaMouseReleased(MouseEvent evt) {
        int klik = evt.getButton();
        if (klik == 3) {

            Point p = evt.getPoint();
            int red = jtblPromenaAutomobila.rowAtPoint(p);
            ListSelectionModel lsm = jtblPromenaAutomobila.getSelectionModel();
            lsm.setSelectionInterval(red, red);
            jpmObrisiAutomobil.show(jtblPromenaAutomobila, p.x, p.y);

        }
    }

    public void jmiDetaljiAutomobilaActionPerformed() {
        int i = jtblPromenaAutomobila.getSelectedRow();
        Automobil a = listaAutomobila.get(i);
        UseCase.vratiUC().ubaciDetaljeUMapu("automobilDetalji", a);
        new FrmDetaljiAutomobila(null, true).setVisible(true);
    }

    public void jmiObrisiAutomobilActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da obrišete podatke?", "Brisanje podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {

            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblPromenaAutomobila.getSelectedRow();
                Automobil a = listaAutomobila.get(i);
                tk.setOperacija(Konstante.OBRISI_AUTOMOBIL);
                tk.setKlijentObjekat(a);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspešno obrisani!", "Brisanje podataka", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaAutomobila.remove(i);
                popuniTabelu(listaAutomobila);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jbtPonistiActionPerformed() {
        postaviPrazanModelTabele();
        jtfPretraga.setText("");
        jrbRegistracija.setSelected(true);
    }

    public void jbtPrikaziSveAutomobileActionPerformed() {
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_AUTOMOBILE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            listaAutomobila = (ArrayList<Automobil>) tk.getServerObjekatOdgovor();
            popuniTabelu(listaAutomobila);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }
}
