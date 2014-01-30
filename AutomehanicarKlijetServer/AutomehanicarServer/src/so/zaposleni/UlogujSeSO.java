/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import java.util.ArrayList;
import java.util.Date;
import kolekcija.ListaUlogovanih;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class UlogujSeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {

        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.VRATI_SVE_ZAPOLSENE);
        ArrayList<Zaposleni> listaZaposlenih = DBBroker.vratiInstancu().vratiListuObjekta(new Zaposleni());

        Zaposleni zaposleni = null;
        for (Zaposleni noviZaposleni : listaZaposlenih) {
            if (z.getUsername().equals(noviZaposleni.getUsername())&(z.getPassword().equals(noviZaposleni.getPassword()))) {
                zaposleni = noviZaposleni;
                break;
            }
        }
        if (zaposleni == null) {
            throw new Exception("Pogresno korisnicko ime ili sifra!");
        }
        for (Zaposleni zap : listaZaposlenih) {
            if (z.getUsername().contains(zap.getUsername()) && (z.getPassword().equals(zap.getPassword()))) {
                ArrayList<Zaposleni> lz = ListaUlogovanih.vratiInstancu().vratiListuUlogovanih();
                for (Zaposleni za : lz) {
                    if (z.getUsername().equals(za.getUsername())) {
                        throw new Exception("Zaposleni " + z.getUsername() + " je već ulogovan!");
                    }
                }
            }
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.PRONADJI_ZAPOSLENOG_NA_OSNOVU_KORISNICKOG_IMENA);
        z = (Zaposleni) DBBroker.vratiInstancu().vratiObjekat(z);
        Date vremeLogovanja = new Date();
        ListaUlogovanih.vratiInstancu().dodajUlogovanog(z, vremeLogovanja);        
        super.setObjekat(z);
    }
}
