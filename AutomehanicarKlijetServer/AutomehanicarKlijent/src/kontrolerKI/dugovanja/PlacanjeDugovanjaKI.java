/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.dugovanja;

import domen.Dugovanja;
import domen.VlasnikAutomobila;
import forme.FrmDetaljiDugova;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import komunikacija.Komunikacija;
import ogranicenja.JTextFieldOgranicenje;
import paneli.modeli.dugovanja.DugovanjaPrikazTableModel;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;

/**
 *
 * @author Administrator
 */
public class PlacanjeDugovanjaKI {

    private static PlacanjeDugovanjaKI objekat;
    private JPanel glavna;
    private JLabel jlObavestenje;
    private JMenuItem jmiDetalji;
    private JMenuItem jmiNaplataDugovanja;
    private JMenuItem jmiNaplataDugovanjaNekogKorisnika;
    private JPopupMenu jpmDugovanja;
    private JTable jtblDugovanja;
    private JTextField jtfPretraga;
    private JTextField jtfUkupanDug;
    private ArrayList<Dugovanja> listaDugovanja;

    public PlacanjeDugovanjaKI() {
    }

    public static PlacanjeDugovanjaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PlacanjeDugovanjaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JLabel jlObavestenje, JMenuItem jmiDetalji, JMenuItem jmiNaplataDugovanja, JMenuItem jmiNaplataDugovanjaNekogKorisnika, JPopupMenu jpmDugovanja, JTable jtblDugovanja, JTextField jtfPretraga, JTextField jtfUkupanDug) {
        this.glavna = glavna;
        this.jlObavestenje = jlObavestenje;
        this.jmiDetalji = jmiDetalji;
        this.jmiNaplataDugovanja = jmiNaplataDugovanja;
        this.jmiNaplataDugovanjaNekogKorisnika = jmiNaplataDugovanjaNekogKorisnika;
        this.jpmDugovanja = jpmDugovanja;
        this.jtblDugovanja = jtblDugovanja;
        this.jtfPretraga = jtfPretraga;
        this.jtfUkupanDug = jtfUkupanDug;
        listaDugovanja = new ArrayList<Dugovanja>();
        jtfPretraga.setDocument(new JTextFieldOgranicenje(13));
    }

    public void srediFormu() {
        jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfUkupanDug.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jtfPretraga.requestFocusInWindow();
        jpmDugovanja.setBorderPainted(true);
        jmiDetalji.setText("Detalji");
        jmiNaplataDugovanja.setText("Naplata dugovanja");
        jmiDetalji.setIcon(new ImageIcon("slike/info16.png"));
        jmiNaplataDugovanja.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jmiNaplataDugovanjaNekogKorisnika.setText("Naplata svih dugova korisnika");
        jmiNaplataDugovanjaNekogKorisnika.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jtfUkupanDug.setEditable(false);
    }

    public void popuniTabelu() {
        postaviPrazanModelTabele();
        DugovanjaPrikazTableModel dptm = new DugovanjaPrikazTableModel(listaDugovanja);
        jtblDugovanja.setModel(dptm);
    }

    private void postaviPrazanModelTabele() {
        jtblDugovanja.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "Sifra dugovanja", "Vrednost dugovanja", "JMBG vlasnika", "Ime", "Prezime"
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

    public void jbtPrikaziSvaDugovanjaActionPerformed() {
        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.VRATI_SVE_DUGOVE);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            listaDugovanja = (ArrayList<Dugovanja>) tk.getServerObjekatOdgovor();
            double ukupanDug = 0;
            for (Dugovanja dugovanja : listaDugovanja) {
                ukupanDug += dugovanja.getVrednost();
            }
            jtfUkupanDug.setText(ukupanDug + "");
            popuniTabelu();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jbtPonistiActionPerformed() {
        postaviPrazanModelTabele();
        jtfPretraga.setText("");
        jtfUkupanDug.setText("");
    }

    public void jbtPronadjiActionPerformed() {
        try {
            if (jtfPretraga.getText().trim().equals("")) {
                jlObavestenje.setForeground(Color.red);
                jtfPretraga.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jlObavestenje.setText("Morate popuniti polje");
                jtfPretraga.requestFocusInWindow();
                return;
            }

            TransferKlasa tk = new TransferKlasa();
            String jmbg = jtfPretraga.getText().trim();
            VlasnikAutomobila va = new VlasnikAutomobila(jmbg, "", "");
            tk.setOperacija(Konstante.PRONADJI_DUG);
            tk.setKlijentObjekat(va);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            listaDugovanja = (ArrayList<Dugovanja>) tk.getServerObjekatOdgovor();
            double ukupanDug = 0;
            for (Dugovanja dugovanja : listaDugovanja) {
                ukupanDug += dugovanja.getVrednost();
            }
            jtfUkupanDug.setText(ukupanDug + "");
            popuniTabelu();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jtblDugovanjaMouseReleased(MouseEvent evt) {
        int klik = evt.getButton();
        if (klik == 3) {

            Point p = evt.getPoint();
            int red = jtblDugovanja.rowAtPoint(p);
            ListSelectionModel lsm = jtblDugovanja.getSelectionModel();
            lsm.setSelectionInterval(red, red);
            jpmDugovanja.show(jtblDugovanja, p.x, p.y);
        }
    }

    public void jmiDetaljiActionPerformed() {
        int i = jtblDugovanja.getSelectedRow();
        Dugovanja d = listaDugovanja.get(i);
        UseCase.vratiUC().ubaciDetaljeUMapu("detaljiDuga", d);
        new FrmDetaljiDugova(null, true).setVisible(true);
    }

    public void jbtNaplataDugaActionPerformed() {
        if (jtblDugovanja.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(glavna, "Morate izabrati dug za naplatu!", "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            return;
        }

        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da izvršite naplatu?", "Naplata duga", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {
            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblDugovanja.getSelectedRow();
                Dugovanja d = listaDugovanja.get(i);
                tk.setOperacija(Konstante.OBRISI_DUG);
                tk.setKlijentObjekat(d);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Dug je uspešno naplaćen!", "Naplata duga", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaDugovanja.remove(i);
                popuniTabelu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jbtNaplataSvihDugovanjaActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da izvršite naplatu svih dugova?", "Naplata svih dugova", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {
            try {

                TransferKlasa tk = new TransferKlasa();
                int i = jtblDugovanja.getSelectedRow();
                Dugovanja d = listaDugovanja.get(i);
                String jmbg = d.getVlasnik().getJmbg();
                VlasnikAutomobila va = new VlasnikAutomobila(jmbg, "", "");
                tk.setOperacija(Konstante.OBRISI_SVE_DUGOVE_NEKOG_KORISNIKA);
                tk.setKlijentObjekat(va);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Dugovi korisnika " + d.getVlasnik() + " su uspešno naplaćeni!", "Naplata duga", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaDugovanja.remove(i);


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }

        }
    }

    public void jmiNaplataDugovanjaNekogKorisnikaActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da izvršite naplatu svih dugova?", "Naplata svih dugova", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {
            try {

                TransferKlasa tk = new TransferKlasa();
                int i = jtblDugovanja.getSelectedRow();
                Dugovanja d = listaDugovanja.get(i);
                String jmbg = d.getVlasnik().getJmbg();
                VlasnikAutomobila va = new VlasnikAutomobila(jmbg, "", "");
                tk.setOperacija(Konstante.OBRISI_SVE_DUGOVE_NEKOG_KORISNIKA);
                tk.setKlijentObjekat(va);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Dugovi korisnika " + d.getVlasnik() + " su uspešno naplaćeni!", "Naplata duga", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaDugovanja.remove(i);
                popuniTabelu();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jmiNaplataDugovanjaActionPerformed() {
        Object[] opcija = {"DA", "NE"};
        int izbor = JOptionPane.showOptionDialog(glavna, "Da li ste sigurni da želite da izvršite naplatu?", "Naplata duga", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("slike/info50.png"), opcija, opcija[0]);

        if (izbor == 0) {
            try {
                TransferKlasa tk = new TransferKlasa();
                int i = jtblDugovanja.getSelectedRow();
                Dugovanja d = listaDugovanja.get(i);
                tk.setOperacija(Konstante.OBRISI_DUG);
                tk.setKlijentObjekat(d);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                JOptionPane.showMessageDialog(glavna, "Dug je uspešno naplaćen!", "Naplata duga", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                jtfPretraga.setText("");
                listaDugovanja.remove(i);
                popuniTabelu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
            }
        }
    }

    public void jtfPretragaKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jbtPronadjiActionPerformed();
        }
    }
}
