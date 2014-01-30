/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.intervencije;

import domen.Automobil;
import domen.Intervencija;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiIntervencijuNaOsnovuRegistracijeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Automobil a = (Automobil) objekat;
        Intervencija i = new Intervencija();
        i.setAutomobil(a);
        i.postaviUslov(Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_REGISTRACIJE);
        Intervencija in = (Intervencija) DBBroker.vratiInstancu().vratiObjekat(i);
        if (in == null) {
            throw new Exception("Ne postoji tražena intervencija!");
        }
        super.setObjekat(in);
    }
}
