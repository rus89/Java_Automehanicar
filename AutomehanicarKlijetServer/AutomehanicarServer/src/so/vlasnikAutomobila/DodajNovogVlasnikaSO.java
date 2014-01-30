/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vlasnikAutomobila;

import domen.VlasnikAutomobila;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;

/**
 *
 * @author Rus
 */
public class DodajNovogVlasnikaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        OpstaSO o = new VratiSveVlasnikeAutomobilaSO();
        o.izvrsiOperaciju(new VlasnikAutomobila());
        ArrayList<VlasnikAutomobila> lva = (ArrayList<VlasnikAutomobila>) o.getObjekat();
        if (lva.isEmpty()) {
            return;
        }
        
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        for (VlasnikAutomobila vlasnikAutomobila : lva) {
            if (vlasnikAutomobila.getJmbg().equals(va.getJmbg())) {
                throw new Exception("Vlasnik sa tim JMBG-om već postoji!");
            }
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        DBBroker.vratiInstancu().sacuvajObjekat(va);
    }
    
}
