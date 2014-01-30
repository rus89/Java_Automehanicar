/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Konstante;

/**
 *
 * @author Rus
 */
public class DBBroker {

    private static DBBroker instanca;
    private Connection konekcija;

    private DBBroker() throws ClassNotFoundException, SQLException {
        otvoriBazu();
    }

    public static DBBroker vratiInstancu() throws ClassNotFoundException, SQLException {

        if (instanca == null) {
            instanca = new DBBroker();
        }
        return instanca;
    }

    private void otvoriBazu() throws ClassNotFoundException, SQLException {

        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        konekcija = DriverManager.getConnection("jdbc:odbc:automehanicarTest");

    }

    public void pokreniTransakciju() throws SQLException {
        konekcija.setAutoCommit(false);
    }

    public void potvrdiKonekciju() throws SQLException {
        konekcija.commit();
    }

    public void ponistikonekciju() throws SQLException {
        konekcija.rollback();
    }

    public void sacuvajObjekat(OpstiDomenskiObjekat odo) throws Exception {

        try {

            String upit = "INSERT INTO " + odo.vratiDeoZaINSERT(0) + " VALUES " + odo.vratiVrednostiZaINSERT(0);
            System.out.println(upit);
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);

            if (odo.daLiImaSlabeObjekte()) {
                if (odo.primarniKljucJeAUTONUMBER()) {
                    odo.postaviUslov(Konstante.VRATI_POSLEDNJI_OBJEKAT);
                    odo = (OpstiDomenskiObjekat) vratiObjekat(odo);
                }
                odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
                for (int i = 0; i < odo.brojSlabihObjekata(); i++) {
                    upit = "INSERT INTO " + odo.vratiDeoZaINSERT(0) + " VALUES " + odo.vratiVrednostiZaINSERT(i);
                    st.executeUpdate(upit);
                }
            }

        } catch (SQLException ex) {
            throw new Exception("Objekat nije sacuvan: " + ex.getMessage());
        }
    }

    public void izmeniObjekat(OpstiDomenskiObjekat odo) throws Exception {
        try {

            String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostiZaSET() + " WHERE " + odo.vratiVrednostiZaWHERE();
            System.out.println(upit);
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);

//            if (odo.daLiImaSlabeObjekte()) {
//                odo.postaviUslov(Konstante.OBRISI_URADJENE_INTERVENCIJE);
//                obrisiObjekat(odo);
//                odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
//                for (int i = 0; i < odo.brojSlabihObjekata(); i++) {
//                    upit = "INSERT INTO " + odo.vratiDeoZaINSERT(0) + " VALUES " + odo.vratiVrednostiZaINSERT(i);
//                    st.executeUpdate(upit);
//                }
//            }

        } catch (SQLException ex) {
            throw new Exception("Objekat nije izmenjen: " + ex.getMessage());
        }
    }

    public void obrisiObjekat(OpstiDomenskiObjekat odo) throws Exception {

        try {

            String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiVrednostiZaWHERE();
            System.out.println(upit);
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);

        } catch (SQLException ex) {
            throw new Exception("Objekat nije obrisan: " + ex.getMessage());
        }
    }

    public Object vratiObjekat(OpstiDomenskiObjekat odo) throws Exception {

        try {

            String upit = "";            
            Statement st = konekcija.createStatement();

            if (odo.vratiUslov() == Konstante.VRATI_POSLEDNJI_OBJEKAT) {
                upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM();
                ResultSet rs = st.executeQuery(upit);
                return odo.vratiObjekat(rs);
            }

            upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM() + " WHERE " + odo.vratiVrednostiZaWHERE();
            System.out.println(upit);
            ResultSet rs = st.executeQuery(upit);
            odo = (OpstiDomenskiObjekat) odo.vratiObjekat(rs);
            if (odo == null) {
                return null;
            }

            if (odo.daLiImaSlabeObjekte()) {
                odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
                upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM() + " WHERE " + odo.vratiVrednostiZaWHERE();
                rs = st.executeQuery(upit);
                odo.napuniObjekatSlabimObjektima(rs);
            }

            return odo;

        } catch (SQLException ex) {
            throw new Exception("Greska u metodi vratiObjekat" + ex.getMessage());
        }

    }

    public ArrayList vratiListuObjekta(OpstiDomenskiObjekat odo) throws Exception {

        try {

            String upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM();
            System.out.println(upit);
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            ArrayList lista = odo.napuniListuObjekata(rs);

            if (odo.daLiImaSlabeObjekte()) {
                for (Object object : lista) {
                    odo = (OpstiDomenskiObjekat) object;
                    odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
                    upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM() + " WHERE " + odo.vratiVrednostiZaWHERE();
                    rs = st.executeQuery(upit);
                    odo.napuniObjekatSlabimObjektima(rs);
                }
            }

            return lista;

        } catch (SQLException ex) {
            throw new Exception("Greska u metodi vratiListuObjekata!");
        }
    }

    public ArrayList vratiObjektePoUslovu(OpstiDomenskiObjekat odo) throws Exception {
        try {

            String upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM() + " WHERE " + odo.vratiVrednostiZaWHERE();
            System.out.println(upit);
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            ArrayList lista = odo.napuniListuObjekata(rs);

            if (odo.daLiImaSlabeObjekte()) {
                for (Object object : lista) {
                    odo = (OpstiDomenskiObjekat) object;
                    odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
                    upit = "SELECT " + odo.vratiDeoZaSELECT() + " FROM " + odo.vratiDeoZaFROM() + " WHERE " + odo.vratiVrednostiZaWHERE();
                    rs = st.executeQuery(upit);
                    odo.napuniObjekatSlabimObjektima(rs);
                }
            }

            return lista;

        } catch (SQLException ex) {
            throw new Exception("Greska u metodi vratiObjektePoUslovu");
        }
    }

    public void izmeniObjekte(ArrayList lista) throws Exception {
        try {

            Statement st = konekcija.createStatement();
            for (Object object : lista) {
                OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) object;
                String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostiZaSET() + " WHERE " + odo.vratiVrednostiZaWHERE();
                st.executeUpdate(upit);

                if (odo.daLiImaSlabeObjekte()) {
                    odo.postaviUslov(Konstante.OBRISI_URADJENE_INTERVENCIJE);
                    obrisiObjekat(odo);
                    odo.postaviUslov(Konstante.URADJENE_INTERVENCIJE);
                    for (int i = 0; i < odo.brojSlabihObjekata(); i++) {
                        upit = "INSERT INTO " + odo.vratiDeoZaINSERT(0) + " VALUES " + odo.vratiVrednostiZaINSERT(i);
                        st.executeUpdate(upit);
                    }
                }

            }

        } catch (SQLException ex) {
            throw new Exception("Objekti nisu izmenjeni: " + ex.getMessage());
        }
    }
}
