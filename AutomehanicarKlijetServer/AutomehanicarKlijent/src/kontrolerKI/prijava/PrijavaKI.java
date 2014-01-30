/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.prijava;

import domen.Zaposleni;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import komunikacija.Komunikacija;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;
import validator.ComponentValidator;
import validator.PraznoPoljeValidator;

/**
 *
 * @author Rus
 */
public class PrijavaKI {

    private static PrijavaKI objekat;
    private JPanel glavna;
    private ComponentValidator tekstValidator;
    private JLabel jlKorisnickoIme;
    private JLabel jlSifra;
    private JPasswordField jpfSifra;
    private JTextField jtfKorisnicko;

    public PrijavaKI() {
    }

    public static PrijavaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new PrijavaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JPanel glavna, JLabel jlKorisnickoIme, JLabel jlSifra, JPasswordField jpfSifra, JTextField jtfKorisnicko) {
        this.glavna = glavna;
        this.jlKorisnickoIme = jlKorisnickoIme;
        this.jlSifra = jlSifra;
        this.jpfSifra = jpfSifra;
        this.jtfKorisnicko = jtfKorisnicko;
        tekstValidator = new PraznoPoljeValidator();
        glavna.repaint();
        glavna.validate();
        jtfKorisnicko.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        jpfSifra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
    }

    private void proveraParametara() {

        String korisnickoIme = jtfKorisnicko.getText().trim();
        String sifra = jpfSifra.getText().trim();

        try {

            tekstValidator.validate(korisnickoIme);
            jtfKorisnicko.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlKorisnickoIme.setText("");

        } catch (Exception e) {

            jtfKorisnicko.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlKorisnickoIme.setForeground(Color.RED);
            jlKorisnickoIme.setText(e.getMessage());
            jtfKorisnicko.requestFocusInWindow();

        }

        try {

            tekstValidator.validate(sifra);
            jpfSifra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            jlSifra.setText("");

        } catch (Exception e) {

            jpfSifra.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            jlSifra.setForeground(Color.RED);
            jlSifra.setText(e.getMessage());
            jpfSifra.requestFocusInWindow();

        }

    }

    private boolean proveraUnosa() {

        String korisnickoIme = jtfKorisnicko.getText().trim();
        String sifra = jpfSifra.getText().trim();

        try {

            tekstValidator.validate(korisnickoIme);
            tekstValidator.validate(sifra);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void jtfKorisnickoKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jpfSifra.requestFocusInWindow();
            jbtPrijavaActionPerformed();
        }
    }

    public void jbtPrijavaActionPerformed() {
        proveraParametara();
        if (!proveraUnosa()) {
            return;
        }

        try {

            TransferKlasa tk = new TransferKlasa();
            Zaposleni z = new Zaposleni();
            z.setUsername(jtfKorisnicko.getText().trim());
            z.setPassword(jpfSifra.getText().trim());
            tk.setOperacija(Konstante.PRIJAVA);
            tk.setKlijentObjekat(z);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }
            Zaposleni zaposleni = (Zaposleni) tk.getKlijentObjekat();

            if (zaposleni.getUsername().equalsIgnoreCase(jtfKorisnicko.getText().trim()) && zaposleni.getPassword().equalsIgnoreCase(jpfSifra.getText().trim())) {

                z = zaposleni;
                z.setUlogovan(true);
                tk.setOperacija(Konstante.ULOGUJ_ZAPOSLENOG);
                tk.setKlijentObjekat(z);
                Komunikacija.vratiKomunikaciju().posalji(tk);
                tk = Komunikacija.vratiKomunikaciju().procitaj();
                if (!tk.getServerObjekatPoruka().equals("")) {
                    throw new Exception(tk.getServerObjekatPoruka());
                }
                UseCase.vratiUC().ubaciDetaljeUMapu("ulogovan", z);
                jlKorisnickoIme.setIcon(new ImageIcon("slike/ok16.png"));
                jlSifra.setIcon(new ImageIcon("slike/ok16.png"));
                jtfKorisnicko.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                jpfSifra.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
                JOptionPane.showMessageDialog(glavna, "Uspešno ste se prijavili!", "Uspešna prijava", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/ok50.png"));
                JMenuBar glavni = (JMenuBar) UseCase.vratiUC().vratiVrednost("meni");
                glavni.getMenu(0).setEnabled(true);
                glavni.getMenu(1).setEnabled(true);
                glavni.getMenu(2).setEnabled(true);
                glavni.getMenu(3).setEnabled(true);
                glavna.removeAll();
                glavna.repaint();
                glavna.validate();
                return;

            }
        } catch (Exception e) {
            if (e.getMessage().equals("Pogresno korisnicko ime ili sifra!")) {
                jtfKorisnicko.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jpfSifra.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                jlKorisnickoIme.setIcon(new ImageIcon("slike/no16.png"));
                jlSifra.setIcon(new ImageIcon("slike/no16.png"));
                jtfKorisnicko.selectAll();
                jtfKorisnicko.requestFocusInWindow();
                return;
            }
            JOptionPane.showMessageDialog(glavna, e.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }
    }

    public void jpfSifraKeyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jpfSifra.requestFocusInWindow();
            jbtPrijavaActionPerformed();
        }
    }
}
