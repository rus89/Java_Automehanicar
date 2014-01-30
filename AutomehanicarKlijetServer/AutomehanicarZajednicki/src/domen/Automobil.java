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
public class Automobil implements Serializable, OpstiDomenskiObjekat {

    private int sifraAutomobila;
    private String brojRegistracije;
    private ModelAutomobila model;
    private VlasnikAutomobila vlasnik;
    private int uslov;

    public Automobil() {
    }

    public Automobil(int sifraAutomobila, String brojRegistracije, ModelAutomobila model, VlasnikAutomobila vlasnik) {
        this.sifraAutomobila = sifraAutomobila;
        this.brojRegistracije = brojRegistracije;
        this.model = model;
        this.vlasnik = vlasnik;
    }

    /**
     * @return the sifraAutomobila
     */
    public int getSifraAutomobila() {
        return sifraAutomobila;
    }

    /**
     * @param sifraAutomobila the sifraAutomobila to set
     */
    public void setSifraAutomobila(int sifraAutomobila) {
        this.sifraAutomobila = sifraAutomobila;
    }

    /**
     * @return the brojRegistracije
     */
    public String getBrojRegistracije() {
        return brojRegistracije;
    }

    /**
     * @param brojRegistracije the brojRegistracije to set
     */
    public void setBrojRegistracije(String brojRegistracije) {
        this.brojRegistracije = brojRegistracije;
    }

    /**
     * @return the model
     */
    public ModelAutomobila getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ModelAutomobila model) {
        this.model = model;
    }

    /**
     * @return the vlasnik
     */
    public VlasnikAutomobila getVlasnik() {
        return vlasnik;
    }

    /**
     * @param vlasnik the vlasnik to set
     */
    public void setVlasnik(VlasnikAutomobila vlasnik) {
        this.vlasnik = vlasnik;
    }

    @Override
    public String toString() {
        return brojRegistracije + "  -  " + model.getMarkaAutomobila().getNazivMarke() + "  " + model.getNazivModela();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Automobil) {
            Automobil a = (Automobil) obj;
            if (sifraAutomobila == a.sifraAutomobila) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        if (uslov == Konstante.PROMENA_PODATAKA_AUTOMOBILA) {
            return "VlasnikAutomobila INNER JOIN Automobil ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika";
        }
        return "Automobil";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        if (uslov == Konstante.VRATI_SVE_AUTOMOBILE) {
            ArrayList<Automobil> listaAutomobila = new ArrayList<Automobil>();

            while (rs.next()) {

                sifraAutomobila = rs.getInt("SifraAutomobila");
                brojRegistracije = rs.getString("BrojRegistracije");

                int sifraModela = rs.getInt("Model");
                String nazivModela = rs.getString("NazivModela");
                int sifraMarke = rs.getInt("SifraMarke");
                String nazivMarke = rs.getString("NazivMarke");
                String jmbg = rs.getString("JMBGVlasnika");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");

                MarkaAutomobila marka = new MarkaAutomobila(sifraMarke, nazivMarke);
                model = new ModelAutomobila(marka, sifraModela, nazivModela);
                vlasnik = new VlasnikAutomobila(jmbg, ime, prezime);

                Automobil a = new Automobil(sifraAutomobila, brojRegistracije, model, vlasnik);
                listaAutomobila.add(a);
            }
            return listaAutomobila;
        }

        if (uslov == Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA) {

            ArrayList<Automobil> la = new ArrayList<Automobil>();

            while (rs.next()) {
                int sifraAutomobila1 = rs.getInt("SifraAutomobila");
                String brojRegistracije1 = rs.getString("BrojRegistracije");
                int model1 = rs.getInt("SifraModela");
                String vlasnikJmbg = rs.getString("JMBG");
                int markaSifra = rs.getInt("SifraMarke");
                String modelNaziv = rs.getString("NazivModela");
                String markaNaziv = rs.getString("NazivMarke");
                String vlasnikIme = rs.getString("Ime");
                String vlasnikPrezime = rs.getString("Prezime");

                MarkaAutomobila ma = new MarkaAutomobila(markaSifra, markaNaziv);
                model = new ModelAutomobila(ma, model1, modelNaziv);
                vlasnik = new VlasnikAutomobila(vlasnikJmbg, vlasnikIme, vlasnikPrezime);
                Automobil a = new Automobil(sifraAutomobila1, brojRegistracije1, model, vlasnik);
                la.add(a);

            }
            return la;
        }
        return null;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {

        if (uslov == Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE) {
            if (rs.next()) {

                sifraAutomobila = rs.getInt("SifraAutomobila");
                brojRegistracije = rs.getString("BrojRegistracije");

                int sifraModela = rs.getInt("SifraModela");
                String nazivModela = rs.getString("NazivModela");
                int sifraMarke = rs.getInt("SifraMarke");
                String nazivMarke = rs.getString("NazivMarke");
                String jmbg = rs.getString("JMBG");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");

                MarkaAutomobila marka = new MarkaAutomobila(sifraMarke, nazivMarke);
                model = new ModelAutomobila(marka, sifraModela, nazivModela);
                vlasnik = new VlasnikAutomobila(jmbg, ime, prezime);

                Automobil a = new Automobil(sifraAutomobila, brojRegistracije, model, vlasnik);
                return a;

            }
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
        switch (uslov) {
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE:
                return "Automobil.SifraAutomobila, Automobil.BrojRegistracije, ModelAutomobila.SifraModela, ModelAutomobila.NazivModela, MarkaAutomobila.SifraMarke, MarkaAutomobila.NazivMarke, VlasnikAutomobila.JMBG, VlasnikAutomobila.Ime, VlasnikAutomobila.Prezime";
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA:
                return "Automobil.SifraAutomobila, Automobil.BrojRegistracije, ModelAutomobila.SifraModela, ModelAutomobila.NazivModela, MarkaAutomobila.SifraMarke, MarkaAutomobila.NazivMarke, VlasnikAutomobila.JMBG, VlasnikAutomobila.Ime, VlasnikAutomobila.Prezime";
            default:
                return "Automobil.SifraAutomobila, Automobil.BrojRegistracije, Automobil.Model, Automobil.JMBGVlasnika, ModelAutomobila.SifraMarke, ModelAutomobila.NazivModela, MarkaAutomobila.NazivMarke, VlasnikAutomobila.Ime, VlasnikAutomobila.Prezime";
        }
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        if (uslov == Konstante.SACUVAJ_AUTOMOBIL) {
            return "Automobil ( BrojRegistracije, Model, JMBGVlasnika )";
        }
        return "";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        if (uslov == Konstante.SACUVAJ_AUTOMOBIL) {
            return "('" + brojRegistracije + "', '" + model.getSifraModela() + "', '" + vlasnik.getJmbg() + "')";
        }
        return "";
    }

    @Override
    public String vratiDeoZaFROM() {
        switch (uslov) {
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE:
                return "VlasnikAutomobila INNER JOIN (MarkaAutomobila INNER JOIN (ModelAutomobila INNER JOIN Automobil ON ModelAutomobila.SifraModela = Automobil.Model) ON MarkaAutomobila.SifraMarke = ModelAutomobila.SifraMarke) ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika";
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA:
                return "VlasnikAutomobila INNER JOIN ((MarkaAutomobila INNER JOIN ModelAutomobila ON MarkaAutomobila.SifraMarke = ModelAutomobila.SifraMarke) INNER JOIN Automobil ON ModelAutomobila.SifraModela = Automobil.Model) ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika";
            default:
                return "VlasnikAutomobila INNER JOIN ((MarkaAutomobila INNER JOIN ModelAutomobila ON MarkaAutomobila.SifraMarke = ModelAutomobila.SifraMarke) INNER JOIN Automobil ON ModelAutomobila.SifraModela = Automobil.Model) ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika ORDER BY SifraAutomobila";
        }
    }

    @Override
    public String vratiVrednostiZaWHERE() {
        switch (uslov) {
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_REGISTRACIJE:
                return "Automobil.BrojRegistracije='" + brojRegistracije + "'";
            case Konstante.PRONADJI_AUTOMOBIL_NA_OSNOVU_JMBG_VLASNIKA_AUTOMOBILA:
                return "VlasnikAutomobila.JMBG='" + vlasnik.getJmbg() + "'";
            case Konstante.PROMENA_PODATAKA_AUTOMOBILA:
                return "Automobil.SifraAutomobila=" + sifraAutomobila + "";
            case Konstante.OBRISI_AUTOMOBIL:
                return "Automobil.SifraAutomobila=" + sifraAutomobila;
            default:
                return "";
        }
    }

    @Override
    public String vratiVrednostiZaSET() {

        if (uslov == Konstante.PROMENA_PODATAKA_AUTOMOBILA) {
            return "Automobil.BrojRegistracije='" + brojRegistracije + "', Automobil.Model=" + model.getSifraModela() + ", VlasnikAutomobila.JMBG='" + vlasnik.getJmbg() + "', VlasnikAutomobila.Ime='" + vlasnik.getIme() + "', VlasnikAutomobila.Prezime='" + vlasnik.getPrezime() + "', Automobil.JMBGVlasnika='" + vlasnik.getJmbg() + "'";
        }
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
