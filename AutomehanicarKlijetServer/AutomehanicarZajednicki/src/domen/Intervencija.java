/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class Intervencija implements Serializable, OpstiDomenskiObjekat {

    private int sifraIntervencije;
    private Zaposleni zaposleni;
    private Automobil automobil;
    private Date datumIntervencije;
    private ArrayList<UradjeneIntervencije> listaUradjenih;
    private int uslov;

    public Intervencija() {
        listaUradjenih = new ArrayList<UradjeneIntervencije>();
    }

    public Intervencija(int sifraIntervencije, Zaposleni zaposleni, Automobil automobil, Date datumIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
        this.zaposleni = zaposleni;
        this.automobil = automobil;
        this.datumIntervencije = datumIntervencije;
        listaUradjenih = new ArrayList<UradjeneIntervencije>();
    }

    /**
     * @return the sifraIntervencije
     */
    public int getSifraIntervencije() {
        return sifraIntervencije;
    }

    /**
     * @param sifraIntervencije the sifraIntervencije to set
     */
    public void setSifraIntervencije(int sifraIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
    }

    /**
     * @return the zaposleni
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * @param zaposleni the zaposleni to set
     */
    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    /**
     * @return the automobil
     */
    public Automobil getAutomobil() {
        return automobil;
    }

    /**
     * @param automobil the automobil to set
     */
    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    /**
     * @return the datumIntervencije
     */
    public Date getDatumIntervencije() {
        return datumIntervencije;
    }

    /**
     * @param datumIntervencije the datumIntervencije to set
     */
    public void setDatumIntervencije(Date datumIntervencije) {
        this.datumIntervencije = datumIntervencije;
    }

    /**
     * @return the listaUradjenih
     */
    public ArrayList<UradjeneIntervencije> getListaUradjenih() {
        return listaUradjenih;
    }

    /**
     * @param listaUradjenih the listaUradjenih to set
     */
    public void setListaUradjenih(ArrayList<UradjeneIntervencije> listaUradjenih) {
        this.listaUradjenih = listaUradjenih;
    }

    public void dodajNovuStavkuSaKlijentskeStrane(OpisIntervencije oi) {
        UradjeneIntervencije ui = new UradjeneIntervencije();
        ui.setRb(listaUradjenih.size() + 1);
        ui.setOpis(oi);
        listaUradjenih.add(ui);
        System.out.println(listaUradjenih.size());
    }

    public void dodajUradjenuIntervenciju(int rb, OpisIntervencije opis) {
        UradjeneIntervencije ui = new UradjeneIntervencije();
        ui.setRb(rb);
        ui.setOpis(opis);
        listaUradjenih.add(ui);
    }

    public void obrisiUradjenuIntervenciju(int rb) {
        listaUradjenih.remove(rb);
        for (int i = rb; i < listaUradjenih.size(); i++) {
            listaUradjenih.get(i).setRb(i + 1);
        }
    }

    public int vratiRedniBroj(int i) {
        return listaUradjenih.get(i).getRb();
    }

    public OpisIntervencije vratiOpis(int i) {
        return listaUradjenih.get(i).getOpis();
    }

    @Override
    public String toString() {
        return vratiOpis(sifraIntervencije).getOpis();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intervencija) {
            Intervencija i = (Intervencija) obj;
            if (sifraIntervencije == i.sifraIntervencije) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        if (uslov == Konstante.PROMENA_PODATAKA_INTERVENCIJE) {
            return "VlasnikAutomobila INNER JOIN (Automobil INNER JOIN Intervencija ON Automobil.SifraAutomobila = Intervencija.SifraAutomobila) ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika";
        }
        return "Intervencija";
    }

    @Override
    public ArrayList napuniListuObjekata(ResultSet rs) throws Exception {

        ArrayList<Intervencija> listaIntervencija = new ArrayList<Intervencija>();

        while (rs.next()) {

            sifraIntervencije = rs.getInt("SifraIntervencije");

            int sifraZaposlenog = rs.getInt("SifraZaposlenog");
            String jmbgZaposlenog = rs.getString("zJmbg");
            String zIme = rs.getString("zIme");
            String zPrezime = rs.getString("zPrezime");
            int sifraAutomobila = rs.getInt("SifraAutomobila");
            String registracija = rs.getString("BrojRegistracije");
            int sifraModela = rs.getInt("SifraModela");
            int sifraMarke = rs.getInt("SifraMarke");
            String nazivMarke = rs.getString("NazivMarke");
            String nazivModela = rs.getString("NazivModela");
            String vJmbg = rs.getString("vJmbg");
            String vIme = rs.getString("vIme");
            String vPrezime = rs.getString("vPrezime");
            java.sql.Date datumBaza = rs.getDate("DatumIntervencije");
            datumIntervencije = new Date(datumBaza.getTime());

            zaposleni = new Zaposleni(sifraZaposlenog, jmbgZaposlenog, zIme, zPrezime, "", "", "", "", "", false);
            MarkaAutomobila marka = new MarkaAutomobila(sifraMarke, nazivMarke);
            ModelAutomobila model = new ModelAutomobila(marka, sifraModela, nazivModela);
            VlasnikAutomobila vlasnik = new VlasnikAutomobila(vJmbg, vIme, vPrezime);
            automobil = new Automobil(sifraAutomobila, registracija, model, vlasnik);
            Intervencija i = new Intervencija(sifraIntervencije, zaposleni, automobil, datumIntervencije);
            listaIntervencija.add(i);

        }
        return listaIntervencija;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {

        if (uslov == Konstante.VRATI_POSLEDNJI_OBJEKAT) {
            if (rs.next()) {
                sifraIntervencije = rs.getInt("SifraIntervencijeMax");
                return this;
            }
        }

        if (rs.next()) {

            sifraIntervencije = rs.getInt("SifraIntervencije");

            int sifraZaposlenog = rs.getInt("SifraZaposlenog");
            String jmbgZaposlenog = rs.getString("zJmbg");
            String zIme = rs.getString("zIme");
            String zPrezime = rs.getString("zPrezime");
            int sifraAutomobila = rs.getInt("SifraAutomobila");
            String registracija = rs.getString("BrojRegistracije");
            int sifraModela = rs.getInt("SifraModela");
            int sifraMarke = rs.getInt("SifraMarke");
            String nazivMarke = rs.getString("NazivMarke");
            String nazivModela = rs.getString("NazivModela");
            String vJmbg = rs.getString("vJmbg");
            String vIme = rs.getString("vIme");
            String vPrezime = rs.getString("vPrezime");
            java.sql.Date datumBaza = rs.getDate("DatumIntervencije");
            datumIntervencije = new Date(datumBaza.getTime());

            zaposleni = new Zaposleni(sifraZaposlenog, jmbgZaposlenog, zIme, zPrezime, "", "", "", "", "", false);
            MarkaAutomobila marka = new MarkaAutomobila(sifraMarke, nazivMarke);
            ModelAutomobila model = new ModelAutomobila(marka, sifraModela, nazivModela);
            VlasnikAutomobila vlasnik = new VlasnikAutomobila(vJmbg, vIme, vPrezime);
            automobil = new Automobil(sifraAutomobila, registracija, model, vlasnik);
            Intervencija i = new Intervencija(sifraIntervencije, zaposleni, automobil, datumIntervencije);
            return i;

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
        if (uslov == Konstante.URADJENE_INTERVENCIJE) {
            return "UradjeneIntervencije.RB, UradjeneIntervencije.SifraOpisa, OpisIntervencije.Opis";
        }
        if (uslov == Konstante.VRATI_POSLEDNJI_OBJEKAT) {
            return "MAX(SifraIntervencije) AS SifraIntervencijeMax";
        }
        return "Intervencija.SifraIntervencije, Zaposleni.SifraZaposlenog, Zaposleni.JMBG AS zJmbg, Zaposleni.Ime AS zIme, Zaposleni.Prezime AS zPrezime, Automobil.SifraAutomobila, Automobil.BrojRegistracije, ModelAutomobila.SifraModela, ModelAutomobila.NazivModela, MarkaAutomobila.SifraMarke, MarkaAutomobila.NazivMarke, VlasnikAutomobila.JMBG AS vJmbg, VlasnikAutomobila.Ime AS vIme, VlasnikAutomobila.Prezime AS vPrezime, Intervencija.DatumIntervencije";
    }

    @Override
    public String vratiDeoZaINSERT(int i) {
        if (uslov == Konstante.URADJENE_INTERVENCIJE) {
            return "UradjeneIntervencije";
        }
        return "Intervencija ( SifraZaposlenog, SifraAutomobila, DatumIntervencije )";
    }

    @Override
    public boolean primarniKljucJeAUTONUMBER() {
        return true;
    }

    @Override
    public String vratiVrednostiZaINSERT(int i) {
        if (uslov == Konstante.URADJENE_INTERVENCIJE) {
            return "(" + sifraIntervencije + ", " + vratiRedniBroj(i) + ", " + vratiOpis(i).getSifraOpisa() + ")";
        }
        return "(" + zaposleni.getSifraZaposlenog() + ", " + automobil.getSifraAutomobila() + ", '" + new java.sql.Date(datumIntervencije.getTime()) + "')";
    }

    @Override
    public String vratiDeoZaFROM() {
        if (uslov == Konstante.URADJENE_INTERVENCIJE) {
            return "OpisIntervencije INNER JOIN UradjeneIntervencije ON OpisIntervencije.SifraOpisa = UradjeneIntervencije.SifraOpisa";
        }
        if (uslov == Konstante.VRATI_POSLEDNJI_OBJEKAT) {
            return "Intervencija";
        }
        return "VlasnikAutomobila INNER JOIN (MarkaAutomobila INNER JOIN (ModelAutomobila INNER JOIN (Automobil INNER JOIN (Zaposleni INNER JOIN Intervencija ON Zaposleni.SifraZaposlenog = Intervencija.SifraZaposlenog) ON Automobil.SifraAutomobila = Intervencija.SifraAutomobila) ON ModelAutomobila.SifraModela = Automobil.Model) ON MarkaAutomobila.SifraMarke = ModelAutomobila.SifraMarke) ON VlasnikAutomobila.JMBG = Automobil.JMBGVlasnika";
    }

    @Override
    public String vratiVrednostiZaWHERE() {
        if (uslov == Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_REGISTRACIJE) {
            return "Automobil.BrojRegistracije='" + automobil.getBrojRegistracije() + "'";
        }
        if (uslov == Konstante.PRONADJI_INTERVENCIJU_NA_OSNOVU_RADNIKA) {
            return "Intervencija.SifraZaposlenog=" + zaposleni.getSifraZaposlenog();
        }
        if (uslov == Konstante.OBRISI_INTERVENCIJU) {
            return "Intervencija.SifraIntervencije=" + sifraIntervencije;
        }
        if (uslov == Konstante.URADJENE_INTERVENCIJE) {
            return "SifraIntervencije=" + sifraIntervencije + " ORDER BY RB";
        }
        if (uslov == Konstante.PROMENA_PODATAKA_INTERVENCIJE) {
            return "Intervencija.SifraIntervencije=" + sifraIntervencije;
        }
        return "";
    }

    @Override
    public String vratiVrednostiZaSET() {
        if (uslov == Konstante.PROMENA_PODATAKA_INTERVENCIJE) {
            return "Intervencija.DatumIntervencije =" + new java.sql.Date(datumIntervencije.getTime()) + ", VlasnikAutomobila.JMBG ='" + automobil.getVlasnik().getJmbg() + "', VlasnikAutomobila.Ime = '" + automobil.getVlasnik().getIme() + "', VlasnikAutomobila.Prezime = '" + automobil.getVlasnik().getPrezime() + "', Automobil.JMBGVlasnika ='" + automobil.getVlasnik().getJmbg() + "', Intervencija.SifraAutomobila = " + automobil.getSifraAutomobila() + ", Intervencija.SifraZaposlenog =" + zaposleni.getSifraZaposlenog();
        }
        return "";
    }

    @Override
    public boolean daLiImaSlabeObjekte() {
        return true;
    }

    @Override
    public int brojSlabihObjekata() {
        return listaUradjenih.size();
    }

    @Override
    public void napuniObjekatSlabimObjektima(ResultSet rs) throws Exception {
        while (rs.next()) {
            int sifraOpisa = rs.getInt("SifraOpisa");
            String opis = rs.getString("Opis");
            OpisIntervencije oi = new OpisIntervencije(sifraOpisa, opis);
            int rb = rs.getInt("RB");
            this.dodajUradjenuIntervenciju(rb, oi);
        }
    }

    private class UradjeneIntervencije implements Serializable {

        private int rb;
        private OpisIntervencije opis;

        public UradjeneIntervencije() {
        }

        public UradjeneIntervencije(int rb, OpisIntervencije opis) {
            this.rb = rb;
            this.opis = opis;
        }

        /**
         * @return the rb
         */
        public int getRb() {
            return rb;
        }

        /**
         * @param rb the rb to set
         */
        public void setRb(int rb) {
            this.rb = rb;
        }

        /**
         * @return the opis
         */
        public OpisIntervencije getOpis() {
            return opis;
        }

        /**
         * @param opis the opis to set
         */
        public void setOpis(OpisIntervencije opis) {
            this.opis = opis;
        }

        @Override
        public String toString() {
            return opis.getOpis();
        }
    }
}
