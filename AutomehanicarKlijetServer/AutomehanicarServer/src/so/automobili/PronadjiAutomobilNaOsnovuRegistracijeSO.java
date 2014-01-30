/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.automobili;

import domen.Automobil;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiAutomobilNaOsnovuRegistracijeSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Automobil a = (Automobil) objekat;
        a.postaviUslov(Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE);
        Automobil auto = (Automobil) DBBroker.vratiInstancu().vratiObjekat(a);
        if (auto == null) {
            throw new Exception("Ne postoji automobil sa traženom registracijom!");
        }
        super.setObjekat(auto);
    }
}
