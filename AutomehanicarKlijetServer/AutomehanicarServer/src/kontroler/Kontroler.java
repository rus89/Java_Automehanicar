/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Automobil;
import domen.Dugovanja;
import domen.Intervencija;
import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import domen.OpisIntervencije;
import domen.VlasnikAutomobila;
import domen.Zaposleni;
import java.util.ArrayList;
import so.OpstaSO;
import so.automobili.BrisanjeAutomobilaSO;
import so.automobili.PromenaPodatakaOAutomobiluSO;
import so.automobili.PronadjiAutomobilNaOsnovuJmbgVlasnikaSO;
import so.automobili.PronadjiAutomobilNaOsnovuRegistracijeSO;
import so.automobili.PronadjiListuAutomobilaNaOsnovuJmbgVlasnikaSO;
import so.automobili.SacuvajAutomobilSO;
import so.automobili.VratiSveAutomobileSO;
import so.dugovanja.BrisanjeDugaSO;
import so.dugovanja.BrisanjeSvihDugovaNekogKorisnikaSO;
import so.dugovanja.PronadjiDugovanjaNaOsnovuJmbgSO;
import so.dugovanja.UnosDugovanjaSO;
import so.dugovanja.VratiSvaDugovanjaSO;
import so.intervencije.BrisanjeIntervencijeSO;
import so.intervencije.PromenaPodatakaOIntervencijiSO;
import so.intervencije.PronadjiIntervencijuNaOsnovuRadnikaSO;
import so.intervencije.PronadjiIntervencijuNaOsnovuRegistracijeSO;
import so.intervencije.SacuvajIntervencijuSO;
import so.intervencije.VratiSveIntervencijeSO;
import so.marke.VratiSveMarkeAutomobilaSO;
import so.modeli.VratiSveModeleAutomobilaSO;
import so.modeli.VratiSveModeleAutomobilaZaMarkuSO;
import so.opisIntervencije.VratiSveOpiseIntervencijaSO;
import so.vlasnikAutomobila.DodajNovogVlasnikaSO;
import so.vlasnikAutomobila.VratiSveVlasnikeAutomobilaSO;
import so.zaposleni.IzlogujSeSO;
import so.zaposleni.PromeniStatusZaposlenogSO;
import so.zaposleni.PronadjiZaposlenogNaOsnovuKorisnickogImenaSO;
import so.zaposleni.UlogujSeSO;
import so.zaposleni.VratiSveRadnikeSO;
import so.zaposleni.VratiSveZaposleneSO;

/**
 *
 * @author Rus
 */
public class Kontroler {

    private static Kontroler instanca;

    public static Kontroler vratiInstancu() {

        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void sacuvajIntervenciju(Intervencija i) throws Exception {
        OpstaSO o = new SacuvajIntervencijuSO();
        o.izvrsiOperaciju(i);
    }

    public ArrayList<Intervencija> vratiListuIntervencija() throws Exception {
        OpstaSO o = new VratiSveIntervencijeSO();
        o.izvrsiOperaciju(new Intervencija());
        ArrayList<Intervencija> li = (ArrayList<Intervencija>) o.getObjekat();
        return li;
    }

    public ArrayList<Automobil> vratiListuAutomobila() throws Exception {
        OpstaSO o = new VratiSveAutomobileSO();
        o.izvrsiOperaciju(new Automobil());
        ArrayList<Automobil> la = (ArrayList<Automobil>) o.getObjekat();
        return la;
    }

    public ArrayList<Zaposleni> vratiListuZaposlenih() throws Exception {
        OpstaSO o = new VratiSveZaposleneSO();
        o.izvrsiOperaciju(new Zaposleni());
        ArrayList<Zaposleni> lz = (ArrayList<Zaposleni>) o.getObjekat();
        return lz;
    }

    public ArrayList<Dugovanja> vratiListuDugovanja() throws Exception {
        OpstaSO o = new VratiSvaDugovanjaSO();
        o.izvrsiOperaciju(new Dugovanja());
        ArrayList<Dugovanja> ld = (ArrayList<Dugovanja>) o.getObjekat();
        return ld;
    }

    public ArrayList<Zaposleni> vratiListuRadnika() throws Exception {
        OpstaSO o = new VratiSveRadnikeSO();
        o.izvrsiOperaciju(new Zaposleni());
        ArrayList<Zaposleni> lr = (ArrayList<Zaposleni>) o.getObjekat();
        return lr;
    }

    public ArrayList<MarkaAutomobila> vratiListuMarkeAutomobila() throws Exception {
        OpstaSO o = new VratiSveMarkeAutomobilaSO();
        o.izvrsiOperaciju(new MarkaAutomobila());
        ArrayList<MarkaAutomobila> lm = (ArrayList<MarkaAutomobila>) o.getObjekat();
        return lm;
    }

    public ArrayList<ModelAutomobila> vratiListuSvihModelaAutomobila() throws Exception {
        OpstaSO o = new VratiSveModeleAutomobilaSO();
        o.izvrsiOperaciju(new ModelAutomobila());
        ArrayList<ModelAutomobila> lm = (ArrayList<ModelAutomobila>) o.getObjekat();
        return lm;
    }

    public ArrayList<ModelAutomobila> vratiListuSvihModelaAutomobilaZaOdredjenuMarku(MarkaAutomobila ma) throws Exception {
        OpstaSO o = new VratiSveModeleAutomobilaZaMarkuSO();
        o.izvrsiOperaciju(ma);
        ArrayList<ModelAutomobila> lm = (ArrayList<ModelAutomobila>) o.getObjekat();
        return lm;
    }

    public ArrayList<VlasnikAutomobila> vratiListuVlasnikaAutomobila() throws Exception {
        OpstaSO o = new VratiSveVlasnikeAutomobilaSO();
        o.izvrsiOperaciju(new VlasnikAutomobila());
        ArrayList<VlasnikAutomobila> lva = (ArrayList<VlasnikAutomobila>) o.getObjekat();
        return lva;
    }

    public void sacuvajAutomobil(Automobil a) throws Exception {
        OpstaSO o = new SacuvajAutomobilSO();
        o.izvrsiOperaciju(a);
    }

    public void dodajNovogVlasnikaAutomobila(VlasnikAutomobila va) throws Exception {
        OpstaSO o = new DodajNovogVlasnikaSO();
        o.izvrsiOperaciju(va);
    }

    public Object pronadjiAutomobil(Automobil auto) throws Exception {
        OpstaSO o = new PronadjiAutomobilNaOsnovuRegistracijeSO();
        o.izvrsiOperaciju(auto);
        Automobil a = (Automobil) o.getObjekat();
        return a;
    }

    public void sacuvajDugovanja(Dugovanja d) throws Exception {
        OpstaSO o = new UnosDugovanjaSO();
        o.izvrsiOperaciju(d);
    }

    public Object pronadjiAutomobilNaOsnovuJmbgVlanika(Automobil autoJmbg) throws Exception {
        OpstaSO o = new PronadjiAutomobilNaOsnovuJmbgVlasnikaSO();
        o.izvrsiOperaciju(autoJmbg);
        Automobil a = (Automobil) o.getObjekat();
        return a;
    }

    public void promeniPodatkeOAutomobilu(Automobil automobil) throws Exception {
        OpstaSO o = new PromenaPodatakaOAutomobiluSO();
        o.izvrsiOperaciju(automobil);
    }

    public ArrayList<Automobil> pronadjiListuAutomobilaNaOsnovuJmbgVlasnika(VlasnikAutomobila valasnik) throws Exception {
        OpstaSO o = new PronadjiListuAutomobilaNaOsnovuJmbgVlasnikaSO();
        o.izvrsiOperaciju(valasnik);
        ArrayList<Automobil> listaPronadjenih = (ArrayList<Automobil>) o.getObjekat();
        return listaPronadjenih;
    }

    public void obrisiAutomobil(Automobil ao) throws Exception {
        OpstaSO o = new BrisanjeAutomobilaSO();
        o.izvrsiOperaciju(ao);
    }

    public ArrayList<Dugovanja> pronadjiDugovanjeNaOsnovuJmbgVlasnika(VlasnikAutomobila vlasnik) throws Exception {
        OpstaSO o = new PronadjiDugovanjaNaOsnovuJmbgSO();
        o.izvrsiOperaciju(vlasnik);
        ArrayList<Dugovanja> ld = (ArrayList<Dugovanja>) o.getObjekat();
        return ld;
    }

    public void obrisiDug(Dugovanja dug) throws Exception {
        OpstaSO o = new BrisanjeDugaSO();
        o.izvrsiOperaciju(dug);
    }

    public void obrisiDugoveNekogVlasnika(VlasnikAutomobila autoVlasnik) throws Exception {
        OpstaSO o = new BrisanjeSvihDugovaNekogKorisnikaSO();
        o.izvrsiOperaciju(autoVlasnik);
    }

    public Object pronadjiIntervencijuNaOsnovuRegistracije(Automobil autoRegistracija) throws Exception {
        OpstaSO o = new PronadjiIntervencijuNaOsnovuRegistracijeSO();
        o.izvrsiOperaciju(autoRegistracija);
        Intervencija i = (Intervencija) o.getObjekat();
        return i;
    }

    public void obrisiIntervenciju(Intervencija in) throws Exception {
        OpstaSO o = new BrisanjeIntervencijeSO();
        o.izvrsiOperaciju(in);
    }

    public Object pronadjiIntervencijuNaOsnovuRadnika(Zaposleni radnik) throws Exception {
        OpstaSO o = new PronadjiIntervencijuNaOsnovuRadnikaSO();
        o.izvrsiOperaciju(radnik);
        ArrayList<Intervencija> li = (ArrayList<Intervencija>) o.getObjekat();
        return li;
    }

    public Object pronadjiZaposlenogNaOsnovuKorisnickogImena(Zaposleni zapos) throws Exception {
        OpstaSO o = new PronadjiZaposlenogNaOsnovuKorisnickogImenaSO();
        o.izvrsiOperaciju(zapos);
        Zaposleni z = (Zaposleni) o.getObjekat();
        return z;
    }

    public Object ulogujSe(Zaposleni ulogovani) throws Exception {
        OpstaSO o = new UlogujSeSO();
        o.izvrsiOperaciju(ulogovani);
        Zaposleni z = (Zaposleni) o.getObjekat();
        return z;
    }

    public void izlogujSe(Zaposleni izlogovani) throws Exception {
        OpstaSO o = new IzlogujSeSO();
        o.izvrsiOperaciju(izlogovani);
    }

    public ArrayList<OpisIntervencije> vratiListuOpisaIntervencije() throws Exception {
        OpstaSO o = new VratiSveOpiseIntervencijaSO();
        o.izvrsiOperaciju(new OpisIntervencije());
        ArrayList<OpisIntervencije> listaOpisa = (ArrayList<OpisIntervencije>) o.getObjekat();
        return listaOpisa;
    }

    public void promeniStatusZaposlenog(Zaposleni zap) throws Exception {
        OpstaSO o = new PromeniStatusZaposlenogSO();
        o.izvrsiOperaciju(zap);
    }

    public void promeniPodatkeOIntervenciji(Intervencija intervencija) throws Exception {
        OpstaSO o = new PromenaPodatakaOIntervencijiSO();
        o.izvrsiOperaciju(intervencija);
    }

}
