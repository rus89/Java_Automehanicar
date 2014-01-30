/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so.automobili;

import domen.Automobil;
import domen.VlasnikAutomobila;
import java.util.ArrayList;
import komunikacija.DBBroker;
import so.OpstaSO;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class PronadjiListuAutomobilaNaOsnovuJmbgVlasnikaSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object objekat) throws Exception {
        VlasnikAutomobila va = (VlasnikAutomobila) objekat;
        Automobil a = new Automobil();
        a.setVlasnik(va);
        a.postaviUslov(Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA);
        ArrayList<Automobil> la = DBBroker.vratiInstancu().vratiObjektePoUslovu(a);
        if (la.isEmpty()) {
            throw new Exception("Ne postoji autmobil trazenog vlasnika!");
        }
        super.setObjekat(la);
    }
    
}
