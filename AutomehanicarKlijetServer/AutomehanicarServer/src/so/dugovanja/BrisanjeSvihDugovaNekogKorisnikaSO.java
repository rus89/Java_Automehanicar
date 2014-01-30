/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dugovanja;

import domen.Dugovanja;
import domen.VlasnikAutomobila;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class BrisanjeSvihDugovaNekogKorisnikaSO extends OpstaSO {

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        Dugovanja d = new Dugovanja();
        d.setVlasnik(va);
        d.postaviUslov(Konstante.OBRISI_SVE_DUGOVE_NEKOG_KORISNIKA);
        DBBroker.vratiInstancu().obrisiObjekat(d);
    }
}
