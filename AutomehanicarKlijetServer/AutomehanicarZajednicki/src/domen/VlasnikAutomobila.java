/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Rus
 */
public class VlasnikAutomobila implements Serializable, OpstiDomenskiObjekat {

    private String jmbg;
    private String ime;
    private String prezime;
    
    private int uslov;

    public VlasnikAutomobila() {
    }

    public VlasnikAutomobila(String jmbg, String ime, String prezime) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
    }

    /**
     * @return the jmbg
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * @param jmbg the jmbg to set
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VlasnikAutomobila) {
            VlasnikAutomobila v = (VlasnikAutomobila) obj;
            if (jmbg.equals(v.jmbg)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "VlasnikAutomobila";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<VlasnikAutomobila> listaVlasnika = new ArrayList<VlasnikAutomobila>();

        while (rs.next()) {

            jmbg = rs.getString("JMBG");
            ime = rs.getString("Ime");
            prezime = rs.getString("Prezime");

            VlasnikAutomobila va = new VlasnikAutomobila(jmbg, ime, prezime);
            listaVlasnika.add(va);

        }
        return listaVlasnika;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        
        if (rs.next()) {            
            
            jmbg = rs.getString("JMBG");
            ime = rs.getString("Ime");
            prezime = rs.getString("Prezime");
            return this;
            
        }
        return null;
    }

    @Override
    public void postaviUslov(int uslov) {
        this.uslov = uslov;
    }

    @Override
    public int vratiUslov() {
        return uslov;
    }

    @Override
    public String vratiDeoZaSELECT() {
        
        return "JMBG, Ime, Prezime";
        
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        
        return "VlasnikAutomobila";
        
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return false;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        return "('"+jmbg+"', '"+ime+"', '"+prezime+"')";
    }

    @Override
    public String vratiVrednostiZaWHERE() {
        return "";
    }

    @Override
    public String vratiVrednostiZaSET() {
        return "";
    }

    @Override
    public String vratiDeoZaFROM() {
        return "VlasnikAutomobila";
    }

    @Override
    public boolean daLiImaSlabeObjekte() {
        return false;
    }

    @Override
    public int brojSlabihObjekata() {
        return 0;
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet tabela) throws Exception {
        
    }
}
