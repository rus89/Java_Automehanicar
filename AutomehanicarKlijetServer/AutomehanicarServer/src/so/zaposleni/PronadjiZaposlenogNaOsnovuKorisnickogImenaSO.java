/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiZaposlenogNaOsnovuKorisnickogImenaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        Zaposleni z = (Zaposleni) objekat;
        z.postaviUslov(Konstante.PRONADJI_ZAPOSLENOG_NA_OSNOVU_KORISNICKOG_IMENA);
        Zaposleni zap = (Zaposleni) DBBroker.vratiInstancu().vratiObjekat(z);
        if (zap == null) {
            throw new Exception("Ne postoji zaposleni sa tim korisničkim imenom:" + z.getUsername());
        }
        super.setObjekat(zap);
    }
}
