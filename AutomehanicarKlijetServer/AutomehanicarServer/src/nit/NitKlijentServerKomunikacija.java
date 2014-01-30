/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nit;

import domen.Automobil;
import domen.Dugovanja;
import domen.Intervencija;
import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import domen.OpisIntervencije;
import domen.VlasnikAutomobila;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTable;
import kontroler.Kontroler;
import table.model.ListaKlijenataTableModel;
import transfer.TransferKlasa;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class NitKlijentServerKomunikacija extends Thread {

    private Socket socket;
    private boolean kraj;
    private JTable jtblListaUlogovanih;

    public NitKlijentServerKomunikacija(Socket socket, JTable jtblListaUlogovanih) {
        this.jtblListaUlogovanih = jtblListaUlogovanih;
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Server je povezan sa klijentom");
        kraj = false;

        while (!kraj) {

            try {

                ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
                TransferKlasa tk = (TransferKlasa) inSocket.readObject();
                obradiZahtevKlijenta(tk);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void obradiZahtevKlijenta(TransferKlasa tk) throws IOException {

        try {

            switch (tk.getOperacija()) {
                case Konstante.VRATI_SVE_RADNIKE:
                    System.out.println("operacija: vrati sve radnike");
                    ArrayList<Zaposleni> lzr = Kontroler.vratiInstancu().vratiListuRadnika();
                    tk.setServerObjekatOdgovor(lzr);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_ZAPOLSENE:
                    System.out.println("operacija: vrati sve zaposlene");
                    ArrayList<Zaposleni> lz = Kontroler.vratiInstancu().vratiListuZaposlenih();
                    tk.setServerObjekatOdgovor(lz);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_INTERVENCIJE:
                    System.out.println("operacija: vrati sve intervencije");
                    ArrayList<Intervencija> li = Kontroler.vratiInstancu().vratiListuIntervencija();
                    tk.setServerObjekatOdgovor(li);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_AUTOMOBILE:
                    System.out.println("operacija: vrati sve automobile");
                    ArrayList<Automobil> la = Kontroler.vratiInstancu().vratiListuAutomobila();
                    tk.setServerObjekatOdgovor(la);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_MARKE_AUTOMOBILA:
                    System.out.println("operacija: vrati sve marke automobila");
                    ArrayList<MarkaAutomobila> lma = Kontroler.vratiInstancu().vratiListuMarkeAutomobila();
                    tk.setServerObjekatOdgovor(lma);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_MODELE_AUTMOBILA:
                    System.out.println("operacija: vrati sve modele automobila");
                    ArrayList<ModelAutomobila> lmoa = Kontroler.vratiInstancu().vratiListuSvihModelaAutomobila();
                    tk.setServerObjekatOdgovor(lmoa);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA:
                    System.out.println("operacija: vrati sve modele za marku automobila");
                    MarkaAutomobila ma = (MarkaAutomobila) tk.getKlijentObjekat();
                    ArrayList<ModelAutomobila> lmm = Kontroler.vratiInstancu().vratiListuSvihModelaAutomobilaZaOdredjenuMarku(ma);
                    tk.setServerObjekatOdgovor(lmm);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_DUGOVE:
                    System.out.println("operacija: vrati sve dugove");
                    ArrayList<Dugovanja> ld = Kontroler.vratiInstancu().vratiListuDugovanja();
                    tk.setServerObjekatOdgovor(ld);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.SACUVAJ_INTERVENCIJU:
                    System.out.println("Operacija: sacuvaj intervenciju");
                    Intervencija i = (Intervencija) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().sacuvajIntervenciju(i);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.SACUVAJ_AUTOMOBIL:
                    System.out.println("operacija: sacuvaj automobil");
                    Automobil a = (Automobil) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().sacuvajAutomobil(a);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.SACUVAJ_DUG:
                    System.out.println("operacija: sacuvaj dug");
                    Dugovanja d = (Dugovanja) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().sacuvajDugovanja(d);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_REGISTRACIJE:
                    System.out.println("operacija: pronadji intervenciju na osnovu registracije");
                    Automobil autoRegistracija = (Automobil) tk.getKlijentObjekat();
                    tk.setServerObjekatOdgovor(Kontroler.vratiInstancu().pronadjiIntervencijuNaOsnovuRegistracije(autoRegistracija));
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_RADNIKA:
                    System.out.println("operacija: pronadji intervenciju na osnovu radnika");
                    Zaposleni radnik = (Zaposleni) tk.getKlijentObjekat();
                    tk.setServerObjekatOdgovor(Kontroler.vratiInstancu().pronadjiIntervencijuNaOsnovuRadnika(radnik));
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE:
                    System.out.println("operacija: pronadji automobi na osnovu registracije");
                    Automobil auto = (Automobil) tk.getKlijentObjekat();
                    tk.setServerObjekatOdgovor(Kontroler.vratiInstancu().pronadjiAutomobil(auto));
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA:
                    System.out.println("operacija: pronadji automobila na osnovu jmbg vlasnika automobila");
                    VlasnikAutomobila valasnik = (VlasnikAutomobila) tk.getKlijentObjekat();
                    ArrayList<Automobil> listaPronadjenih = Kontroler.vratiInstancu().pronadjiListuAutomobilaNaOsnovuJmbgVlasnika(valasnik);
                    tk.setServerObjekatOdgovor(listaPronadjenih);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_DUG:
                    System.out.println("operacija: pronadji dug");
                    VlasnikAutomobila vlasnik = (VlasnikAutomobila) tk.getKlijentObjekat();
                    ArrayList<Dugovanja> listaDugovanja = Kontroler.vratiInstancu().pronadjiDugovanjeNaOsnovuJmbgVlasnika(vlasnik);
                    tk.setServerObjekatOdgovor(listaDugovanja);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.DODAJ_NOVOG_VLASNIKA_AUTOMOBILA:
                    System.out.println("operacija: dodaj novog vlasnika");
                    VlasnikAutomobila va = (VlasnikAutomobila) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().dodajNovogVlasnikaAutomobila(va);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_VLASNIKE_AUTOMOBILA:
                    System.out.println("operacija: vrati sve vlasnike automobila");
                    ArrayList<VlasnikAutomobila> listaVlasnika = Kontroler.vratiInstancu().vratiListuVlasnikaAutomobila();
                    tk.setServerObjekatOdgovor(listaVlasnika);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.OBRISI_AUTOMOBIL:
                    Automobil ao = (Automobil) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().obrisiAutomobil(ao);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.OBRISI_INTERVENCIJU:
                    Intervencija in = (Intervencija) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().obrisiIntervenciju(in);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.OBRISI_DUG:
                    Dugovanja dug = (Dugovanja) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().obrisiDug(dug);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PROMENA_PODATAKA_AUTOMOBILA:
                    System.out.println("operacija: promena podataka automobila");
                    Kontroler.vratiInstancu().promeniPodatkeOAutomobilu((Automobil) tk.getKlijentObjekat());
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRIJAVA:
                    System.out.println("operacija: prijava");
                    Zaposleni ulogovani = (Zaposleni) tk.getKlijentObjekat();
                    tk.setServerObjekatOdgovor(Kontroler.vratiInstancu().ulogujSe(ulogovani));
                    posaljiOdgovor(tk);
                    ArrayList<Zaposleni> listaz = Kontroler.vratiInstancu().vratiListuZaposlenih();
                    for (int i1 = 0; i1 < listaz.size(); i1++) {
                        if (ulogovani.getUsername().equals(listaz.get(i1).getUsername())) {
                            ulogovani.setUlogovan(true);
                        }                        
                    }
                    jtblListaUlogovanih.setModel(new ListaKlijenataTableModel(listaz));
                    break;
                case Konstante.ODJAVA:
                    System.out.println("operacija: odjava");
                    Zaposleni izlogovani = (Zaposleni) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().izlogujSe(izlogovani);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.OBRISI_SVE_DUGOVE_NEKOG_KORISNIKA:
                    System.out.println("operacija: obrisi sve dugove");
                    VlasnikAutomobila autoVlasnik = (VlasnikAutomobila) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().obrisiDugoveNekogVlasnika(autoVlasnik);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.PRONADJI_ZAPOSLENOG_NA_OSNOVU_KORISNICKOG_IMENA:
                    System.out.println("operacija: pronadji zaposlenog na osnovu korisnickog imena");
                    Zaposleni zapos = (Zaposleni) tk.getKlijentObjekat();
                    tk.setServerObjekatOdgovor(Kontroler.vratiInstancu().pronadjiZaposlenogNaOsnovuKorisnickogImena(zapos));
                    posaljiOdgovor(tk);
                    break;
                case Konstante.VRATI_SVE_OPISE_INTERVENCIJE:
                    System.out.println("operacija: vrati sve opise intervencije");
                    ArrayList<OpisIntervencije> listaOpisa = Kontroler.vratiInstancu().vratiListuOpisaIntervencije();
                    tk.setServerObjekatOdgovor(listaOpisa);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.ULOGUJ_ZAPOSLENOG:
                    System.out.println("operacija: logovanje korisnika");
                    Zaposleni zap = (Zaposleni) tk.getKlijentObjekat();
                    Kontroler.vratiInstancu().promeniStatusZaposlenog(zap);
                    posaljiOdgovor(tk);
                    break;
                case Konstante.OBRISI_URADJENE_INTERVENCIJE:
                    break;
                case Konstante.PROMENA_PODATAKA_INTERVENCIJE:
                    System.out.println("operacija: promena podataka intervencije");
                    Kontroler.vratiInstancu().promeniPodatkeOIntervenciji((Intervencija)tk.getKlijentObjekat());
                    posaljiOdgovor(tk);
                    break;
            }
        } catch (Exception e) {
            tk.setServerObjekatPoruka(e.getMessage());
            posaljiOdgovor(tk);
        }
    }

    private void posaljiOdgovor(TransferKlasa odgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(odgovor);
    }
}
