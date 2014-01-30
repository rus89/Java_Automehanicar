/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.modeli.automobili;

import domen.Automobil;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class AutomobiliPrikazTableModel extends AbstractTableModel {

    ArrayList<Automobil> listaAutomobila;

    public AutomobiliPrikazTableModel(ArrayList<Automobil> listaAutomobila) {
        this.listaAutomobila = listaAutomobila;
    }

    @Override
    public int getRowCount() {
        return listaAutomobila.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Automobil a = listaAutomobila.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getSifraAutomobila();
            case 1:
                return a.getBrojRegistracije();
            case 2:
                return a.getModel().getMarkaAutomobila().getNazivMarke();
            case 3:
                return a.getModel().getNazivModela();
            case 4:
                return a.getVlasnik();
            default:
                return "Greska";

        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Sifra automobila";
            case 1:
                return "Broj registracije";
            case 2:
                return "Marka automobila";
            case 3:
                return "Model automobila";
            case 4:
                return "Vlasnik automobila";
            default:
                return "Greska";

        }
    }
}
