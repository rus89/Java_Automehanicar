/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcija;

import domen.Zaposleni;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rus
 */
public class ListaUlogovanih {

    private static ListaUlogovanih instanca;
    private ArrayList<Zaposleni> lz;
    private ArrayList<Date> ld;

    public ListaUlogovanih() {
        lz = new ArrayList<Zaposleni>();
        ld = new ArrayList<Date>();
    }

    public static ListaUlogovanih vratiInstancu() {
        if (instanca == null) {
            instanca = new ListaUlogovanih();
        }
        return instanca;
    }

    public void dodajUlogovanog(Zaposleni z, Date vremeLogovanja) {
        lz.add(z);
        ld.add(vremeLogovanja);
    }

    public void izbaciUlogovanog(Zaposleni z) {
        ld.remove(lz.indexOf(z));
        lz.remove(z);
    }

    public ArrayList<Zaposleni> vratiListuUlogovanih() {
        return lz;
    }

    public ArrayList<Date> vratiVremeLogovanja() {
        return ld;
    }
}
