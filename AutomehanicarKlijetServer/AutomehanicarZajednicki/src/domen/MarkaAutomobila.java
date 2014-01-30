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
public class MarkaAutomobila implements Serializable, OpstiDomenskiObjekat {

    private int sifraMarke;
    private String nazivMarke;
    private int uslov;

    public MarkaAutomobila() {
    }

    public MarkaAutomobila(int sifraMarke, String nazivMarke) {
        this.sifraMarke = sifraMarke;
        this.nazivMarke = nazivMarke;
    }

    /**
     * @return the sifraMarke
     */
    public int getSifraMarke() {
        return sifraMarke;
    }

    /**
     * @param sifraMarke the sifraMarke to set
     */
    public void setSifraMarke(int sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    /**
     * @return the nazivMarke
     */
    public String getNazivMarke() {
        return nazivMarke;
    }

    /**
     * @param nazivMarke the nazivMarke to set
     */
    public void setNazivMarke(String nazivMarke) {
        this.nazivMarke = nazivMarke;
    }

    @Override
    public String toString() {
        return nazivMarke;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof MarkaAutomobila) {
            MarkaAutomobila ma = (MarkaAutomobila) obj;
            if (sifraMarke == ma.sifraMarke) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "MarkaAutomobila";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<MarkaAutomobila> listaMarki = new ArrayList<MarkaAutomobila>();

        while (rs.next()) {

            sifraMarke = rs.getInt("SifraMarke");
            nazivMarke = rs.getString("NazivMarke");
            MarkaAutomobila ma = new MarkaAutomobila(sifraMarke, nazivMarke);
            listaMarki.add(ma);

        }
        return listaMarki;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        
        if (rs.next()) {            
            
            sifraMarke = rs.getInt("SifraMarke");
            nazivMarke = rs.getString("NazivMarke");
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
        return "SifraMarke, NazivMarke";
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        return "";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        return "";
    }

    @Override
    public String vratiDeoZaFROM() {
        return "MarkaAutomobila";
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
