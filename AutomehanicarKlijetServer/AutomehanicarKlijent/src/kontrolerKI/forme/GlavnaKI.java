/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerKI.forme;

import domen.Zaposleni;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import komunikacija.Komunikacija;
import paneli.prijava.PrijavaPanel;
import transfer.TransferKlasa;
import uc.UseCase;
import util.Konstante;

/**
 *
 * @author Administrator
 */
public class GlavnaKI {

    private static GlavnaKI objekat;
    private JFrame glavna;
    private JPanel aktivanPanel;
    private JMenuBar jMenuBar1;
    private JMenu jmAutomobili;
    private JMenu jmDugovanja;
    private JMenu jmIntervencije;
    private JMenu jmIzlogujSe;
    private JMenuItem jmiBrisanjeAutomobila;
    private JMenuItem jmiBrisanjeIntervencije;
    private JMenuItem jmiIzlogujSe;
    private JMenuItem jmiNaplataDugovanja;
    private JMenuItem jmiPrikazSvihAutomobila;
    private JMenuItem jmiPrikazSvihIntervencija;
    private JMenuItem jmiPromenaPodatakaAutomobila;
    private JMenuItem jmiPromenaPodatakaIntervencije;
    private JMenuItem jmiUnosAutomobila;
    private JMenuItem jmiUnosDugovanja;
    private JMenuItem jmiUnosIntervencije;

    public GlavnaKI() {
    }

    public static GlavnaKI vratiObjekat() {
        if (objekat == null) {
            objekat = new GlavnaKI();
        }
        return objekat;
    }

    public void postaviSveKomponente(JFrame glavna, JMenuBar jMenuBar1, JMenu jmAutomobili, JMenu jmDugovanja, JMenu jmIntervencije, JMenu jmIzlogujSe, JMenuItem jmiBrisanjeAutomobila, JMenuItem jmiBrisanjeIntervencije, JMenuItem jmiIzlogujSe, JMenuItem jmiNaplataDugovanja, JMenuItem jmiPrikazSvihAutomobila, JMenuItem jmiPrikazSvihIntervencija, JMenuItem jmiPromenaPodatakaAutomobila, JMenuItem jmiPromenaPodatakaIntervencije, JMenuItem jmiUnosAutomobila, JMenuItem jmiUnosDugovanja, JMenuItem jmiUnosIntervencije) {
        this.glavna = glavna;
        this.jMenuBar1 = jMenuBar1;
        this.jmAutomobili = jmAutomobili;
        this.jmDugovanja = jmDugovanja;
        this.jmIntervencije = jmIntervencije;
        this.jmIzlogujSe = jmIzlogujSe;
        this.jmiBrisanjeAutomobila = jmiBrisanjeAutomobila;
        this.jmiBrisanjeIntervencije = jmiBrisanjeIntervencije;
        this.jmiIzlogujSe = jmiIzlogujSe;
        this.jmiNaplataDugovanja = jmiNaplataDugovanja;
        this.jmiPrikazSvihAutomobila = jmiPrikazSvihAutomobila;
        this.jmiPrikazSvihIntervencija = jmiPrikazSvihIntervencija;
        this.jmiPromenaPodatakaAutomobila = jmiPromenaPodatakaAutomobila;
        this.jmiPromenaPodatakaIntervencije = jmiPromenaPodatakaIntervencije;
        this.jmiUnosAutomobila = jmiUnosAutomobila;
        this.jmiUnosDugovanja = jmiUnosDugovanja;
        this.jmiUnosIntervencije = jmiUnosIntervencije;
    }

    public void postaviAktivanPanel(JPanel noviPanel) {

        if (aktivanPanel != null) {
            glavna.remove(aktivanPanel);
        }

        aktivanPanel = noviPanel;
        glavna.add(aktivanPanel, null);
        noviPanel.setVisible(true);
        glavna.validate();
        glavna.repaint();
        glavna.pack();

    }

    public void pripremiGlavnu() {

        PrijavaPanel p = new PrijavaPanel();
        postaviAktivanPanel(p);

        UseCase.vratiUC().ubaciDetaljeUMapu("meni", jMenuBar1);

        jMenuBar1.getMenu(0).setEnabled(false);
        jMenuBar1.getMenu(1).setEnabled(false);
        jMenuBar1.getMenu(2).setEnabled(false);
        jMenuBar1.getMenu(3).setEnabled(false);

    }

    public void izlogujSe() {

        try {

            TransferKlasa tk = new TransferKlasa();
            tk.setOperacija(Konstante.ODJAVA);
            tk.setKlijentObjekat(UseCase.vratiUC().vratiVrednost("ulogovan"));
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }

            Zaposleni z = (Zaposleni) UseCase.vratiUC().vratiVrednost("ulogovan");

            z.setUlogovan(false);
            tk.setOperacija(Konstante.ULOGUJ_ZAPOSLENOG);
            tk.setKlijentObjekat(z);
            Komunikacija.vratiKomunikaciju().posalji(tk);
            tk = Komunikacija.vratiKomunikaciju().procitaj();
            if (!tk.getServerObjekatPoruka().equals("")) {
                throw new Exception(tk.getServerObjekatPoruka());
            }

            UseCase.vratiUC().ubaciDetaljeUMapu("ulogovan", null);

            PrijavaPanel p = new PrijavaPanel();
            postaviAktivanPanel(p);

            UseCase.vratiUC().ubaciDetaljeUMapu("meni", jMenuBar1);

            jMenuBar1.getMenu(0).setEnabled(false);
            jMenuBar1.getMenu(1).setEnabled(false);
            jMenuBar1.getMenu(2).setEnabled(false);
            jMenuBar1.getMenu(3).setEnabled(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(glavna, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("slike/info50.png"));
        }

    }

    public void izgledForme() {
        glavna.setResizable(false);
        glavna.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        glavna.pack();
        glavna.repaint();
        glavna.validate();

        glavna.setTitle("Automehanicar");
        glavna.setIconImage(new ImageIcon("slike/car.png").getImage());

        jmiUnosIntervencije.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        jmiPrikazSvihIntervencija.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jmiPromenaPodatakaIntervencije.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        jmiBrisanjeIntervencije.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
        jmiUnosAutomobila.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
        jmiPrikazSvihAutomobila.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
        jmiPromenaPodatakaAutomobila.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        jmiBrisanjeAutomobila.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.ALT_MASK));
        jmiUnosDugovanja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
        jmiNaplataDugovanja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
        jmiIzlogujSe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));

        jmiUnosIntervencije.setIcon(new ImageIcon("slike/novaIntervencija.png"));
        jmiUnosAutomobila.setIcon(new ImageIcon("slike/noviAutomobil.png"));
        jmiUnosDugovanja.setIcon(new ImageIcon("slike/novaIntervencija.png"));
        jmiBrisanjeIntervencije.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jmiBrisanjeAutomobila.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jmiNaplataDugovanja.setIcon(new ImageIcon("slike/Recycle-Bin.png"));
        jmiPrikazSvihIntervencija.setIcon(new ImageIcon("slike/info16.png"));
        jmiPrikazSvihAutomobila.setIcon(new ImageIcon("slike/info16.png"));
        jmiPromenaPodatakaIntervencije.setIcon(new ImageIcon("slike/Gear.png"));
        jmiPromenaPodatakaAutomobila.setIcon(new ImageIcon("slike/Gear.png"));
        jmiIzlogujSe.setIcon(new ImageIcon("slike/no16.png"));
    }

    public void precice() {
        jmIntervencije.setMnemonic(KeyEvent.VK_I);
        jmAutomobili.setMnemonic(KeyEvent.VK_A);
        jmDugovanja.setMnemonic(KeyEvent.VK_D);
        jmIzlogujSe.setMnemonic(KeyEvent.VK_L);
        jmiUnosIntervencije.setMnemonic(KeyEvent.VK_U);
        jmiPrikazSvihIntervencija.setMnemonic(KeyEvent.VK_P);
        jmiPromenaPodatakaIntervencije.setMnemonic(KeyEvent.VK_R);
        jmiBrisanjeIntervencije.setMnemonic(KeyEvent.VK_B);
        jmiUnosAutomobila.setMnemonic(KeyEvent.VK_U);
        jmiPrikazSvihAutomobila.setMnemonic(KeyEvent.VK_P);
        jmiPromenaPodatakaAutomobila.setMnemonic(KeyEvent.VK_R);
        jmiBrisanjeAutomobila.setMnemonic(KeyEvent.VK_B);
        jmiUnosDugovanja.setMnemonic(KeyEvent.VK_U);
        jmiNaplataDugovanja.setMnemonic(KeyEvent.VK_N);
        jmiIzlogujSe.setMnemonic(KeyEvent.VK_I);
    }
}
