/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.intervencije;

import domen.Automobil;
import domen.Intervencija;
import domen.Zaposleni;
import forme.FrmDetaljiIntervencije;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
import paneli.modeli.intervencija.IntervencijaPrikazTableModel;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;
import validator.ComponentValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Rus
 */
public class BrisanjeIntervencijaKI {

    private static BrisanjeIntervencijaKI objekat;
    private JPanel glavna;
    private ComponentValidator tekstValidator;
    private ArrayList<Intervencija> listaIntervencija;
    private JComboBox jcbRadniciPretraga;
    private JLabel jlObavestenje;
    private JMenuItem jmiDetaljiIntervencije;
    private JMenuItem jmiObrisiIntervenciju;
    private JPopupMenu jpmObrisiIntervenciju;
    private JRadioButton jrbRadnik;
    private JRadioButton jrbRegistracija;
    private JTable jtblPromenaIntervencije;
    private JTextField jtfPretraga;

    public BrisanjeIntervencijaKI() {
    }

    public static BrisanjeIntervencijaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new BrisanjeIntervencijaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JComboBox jcbRadniciPretraga, JLabel jlObavestenje, JMenuItem jmiDetaljiIntervencije, JMenuItem jmiObrisiIntervenciju, JPopupMenu jpmObrisiIntervenciju, JRadioButton jrbRadnik, JRadioButton jrbRegistracija, JTable jtblPromenaIntervencije, JTextField jtfPretraga) {
        this.glavna = glavna;
        this.jcbRadniciPretraga = jcbRadniciPretraga;
        this.jlObavestenje = jlObavestenje;
        this.jmiDetaljiIntervencije = jmiDetaljiIntervencije;
        this.jmiObrisiIntervenciju = jmiObrisiIntervenciju;
        this.jpmObrisiIntervenciju = jpmObrisiIntervenciju;
        this.jrbRadnik = jrbRadnik;
        this.jrbRegistracija = jrbRegistracija;
        this.jtblPromenaIntervencije = jtblPromenaIntervencije;
        this.jtfPretraga = jtfPretraga;
        tekstValidator = new PraznoPoljeValidator();
        listaIntervencija = new ArrayList<Intervencija>();
    }

    private void proveraParametara() {

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

    public void srediPopUpMenu() {
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jcbRadniciPretraga.setEnabled(false);
        jtfPretraga.requestFocusInWindow();
        jpmObrisiIntervenciju.setBorderPainted(true);
        jmiDetaljiIntervencije.setText("Detalji");
        jmiDetaljiIntervencije.setIcon(new ImageIcon("slike/info16.png"));
        jmiObrisiIntervenciju.setText("Obriši intervenciju");
        jmiObrisiIntervenciju.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
    }

    private void proveraParametraPretrage() {
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

    private boolean proveraUnosa() {
        String pretraga = jtfPretraga.getText().trim();

        try {
            tekstValidator.validate(pretraga);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void popuniTabelu() {
        postaviPrazanModelTabele();
        IntervencijaPrikazTableModel imt = new IntervencijaPrikazTableModel(listaIntervencija);
        jtblPromenaIntervencije.setModel(imt);
    }

    private void postaviPrazanModelTabele() {
        jtblPromenaIntervencije.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Sifra intervencije", "Radnik", "Automobil", "Datum intervencije"
                }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void napuniCombo() {
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_RADNIKE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            ArrayList<Zaposleni> lr = (ArrayList<Zaposleni>) tk.getServerObjekatOdgovor();
            jcbRadniciPretraga.removeAllItems();
            for (Zaposleni z : lr) {
                jcbRadniciPretraga.addItem(z);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(glavna, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jtfPretragaKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            proveraParametara();
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
                for (Intervencija intervencija : listaIntervencija) {
                    if (intervencija.getSifraIntervencije() == i.getSifraIntervencije()) {
                        listaIntervencija.clear();
                        return;
                    }
                }
                listaIntervencija.add(i);
                popuniTabelu();

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
                popuniTabelu();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jtblPromenaIntervencijeMousePressed(MouseEvent evt) {
        int klik = evt.getButton();
        if (klik == 3) {

            Point p = evt.getPoint();
            int red = jtblPromenaIntervencije.rowAtPoint(p);
            ListSelectionModel lsm = jtblPromenaIntervencije.getSelectionModel();
            lsm.setSelectionInterval(red, red);
            jpmObrisiIntervenciju.show(jtblPromenaIntervencije, p.x, p.y);

        }
    }

    public void jmiDetaljiIntervencijeActionPerformed() {
        int i = jtblPromenaIntervencije.getSelectedRow();
        Intervencija in = listaIntervencija.get(i);
        UseCase.vratiUC().ubaciDetaljeUMapu("detaljiIntervencije", in);
        new FrmDetaljiIntervencije(null, true).setVisible(true);
    }

    public void jmiObrisiIntervencijuActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da obrišete podatke?", "Brisanje podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {

            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblPromenaIntervencije.getSelectedRow();
                Intervencija in = listaIntervencija.get(i);
                tk.setOperacija(Konstante.OBRISI_INTERVENCIJU);
                tk.setKlijentObjekat(in);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspešno obrisani!", "Brisanje podataka", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaIntervencija.remove(i);
                popuniTabelu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jrbRegistracijaActionPerformed() {
        postaviPrazanModelTabele();
        jlObavestenje.setText("");
        jtfPretraga.setEnabled(true);
        jtfPretraga.requestFocusInWindow();
        jcbRadniciPretraga.setEnabled(false);
    }

    public void jrbRadnikActionPerformed() {
        jtfPretraga.setEnabled(false);
        postaviPrazanModelTabele();
        jlObavestenje.setText("");
        jcbRadniciPretraga.setEnabled(true);
    }

    public void jbtObrisiPodatkeActionPerformed() {
        if (jtblPromenaIntervencije.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(glavna, "Morate selektovati neku intervenciju!", "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            return;
        }

        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da obrišete podatke?", "Brisanje podataka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {

            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblPromenaIntervencije.getSelectedRow();
                Intervencija in = listaIntervencija.get(i);
                tk.setOperacija(Konstante.OBRISI_INTERVENCIJU);
                tk.setKlijentObjekat(in);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Podaci su uspešno obrisani!", "Brisanje podataka", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaIntervencija.remove(i);
                popuniTabelu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jbtPonistiActionPerformed() {
        postaviPrazanModelTabele();
        jtfPretraga.setEnabled(true);
        jtfPretraga.requestFocusInWindow();
        jcbRadniciPretraga.setEnabled(false);
        jtfPretraga.setText("");
        jrbRegistracija.setSelected(true);
    }

    public void jbtPrikaziSveIntervencijeActionPerformed() {
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_INTERVENCIJE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            listaIntervencija = (ArrayList<Intervencija>) tk.getServerObjekatOdgovor();
            popuniTabelu();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }
}
