/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dugovanja;

import domen.Dugovanja;
import domen.VlasnikAutomobila;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiDugovanjaNaOsnovuJmbgSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        Dugovanja d = new Dugovanja();
        d.setVlasnik(va);
        d.postaviUslov(Konstante.PRONADJI_DUG);
        ArrayList<Dugovanja> ld = DBBroker.vratiInstancu().vratiObjektePoUslovu(d);
        if (ld.isEmpty()) {
            throw new Exception("Ne postoje dugovanja za traženog korisnika!");
        }
        super.setObjekat(ld);
    }
    
}
