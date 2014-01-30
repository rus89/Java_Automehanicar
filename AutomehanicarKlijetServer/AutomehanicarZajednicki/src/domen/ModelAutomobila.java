/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class ModelAutomobila implements Serializable, OpstiDomenskiObjekat {

    private MarkaAutomobila markaAutomobila;
    private int sifraModela;
    private String nazivModela;
    private int uslov;

    public ModelAutomobila() {
    }

    public ModelAutomobila(MarkaAutomobila markaAutomobila, int sifraModela, String nazivModela) {
        this.markaAutomobila = markaAutomobila;
        this.sifraModela = sifraModela;
        this.nazivModela = nazivModela;
    }

    /**
     * @return the markaAutomobila
     */
    public MarkaAutomobila getMarkaAutomobila() {
        return markaAutomobila;
    }

    /**
     * @param markaAutomobila the markaAutomobila to set
     */
    public void setMarkaAutomobila(MarkaAutomobila markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    /**
     * @return the sifraModela
     */
    public int getSifraModela() {
        return sifraModela;
    }

    /**
     * @param sifraModela the sifraModela to set
     */
    public void setSifraModela(int sifraModela) {
        this.sifraModela = sifraModela;
    }

    /**
     * @return the nazivModela
     */
    public String getNazivModela() {
        return nazivModela;
    }

    /**
     * @param nazivModela the nazivModela to set
     */
    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    @Override
    public String toString() {
        return nazivModela;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ModelAutomobila) {
            ModelAutomobila ma = (ModelAutomobila) obj;
            if (sifraModela == ma.sifraModela) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "ModelAutomobila";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<ModelAutomobila> listaModela = new ArrayList<ModelAutomobila>();

        while (rs.next()) {
            
            sifraModela = rs.getInt("SifraModela");
            nazivModela = rs.getString("NazivModela");
            ModelAutomobila ma = new ModelAutomobila(markaAutomobila, sifraModela, nazivModela);
            listaModela.add(ma);

        }
        return listaModela;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {

        if (rs.next()) {
            
            sifraModela = rs.getInt("SifraModela");
            nazivModela = rs.getString("NazivModela");
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

        if (uslov == Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA) {
            return "ModelAutomobila.SifraMarke, ModelAutomobila.SifraModela, ModelAutomobila.NazivModela";
        }
        return "MarkaAutomobila.SifraMarke, MarkaAutomobila.NazivMarke, ModelAutomobila.SifraModela, ModelAutomobila.NazivModela";

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

        if (uslov == Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA) {
            return "ModelAutomobila";
        }
        return "MarkaAutomobila INNER JOIN ModelAutomobila ON MarkaAutomobila.SifraMarke = ModelAutomobila.SifraMarke";

    }

    @Override
    public String vratiVrednostiZaWHERE() {
        if (uslov == Konstante.VRATI_SVE_MODELE_ZA_MARKU_AUTOMOBILA) {
            return "ModelAutomobila.SifraMarke=" + markaAutomobila.getSifraMarke();
        }
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
