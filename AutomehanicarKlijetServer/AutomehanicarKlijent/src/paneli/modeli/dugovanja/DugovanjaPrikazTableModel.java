/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.modeli.dugovanja;

import domen.Dugovanja;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class DugovanjaPrikazTableModel extends AbstractTableModel {

    ArrayList<Dugovanja> ld;

    public DugovanjaPrikazTableModel(ArrayList<Dugovanja> ld) {
        this.ld = ld;
    }

    @Override
    public int getRowCount() {
        return ld.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Dugovanja d = ld.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return d.getSifraDugovanja();
            case 1:
                return d.getVrednost();
            case 2:
                return d.getVlasnik().getJmbg();
            case 3:
                return d.getVlasnik().getIme();
            case 4:
                return d.getVlasnik().getPrezime();
            default:
                return "greska";
        }

    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Sifra dugovanja";
            case 1:
                return "Vrednost dugovanja";
            case 2:
                return "JMBG vlasnika";
            case 3:
                return "Ime";
            case 4:
                return "Prezime";
            default:
                return "Greska";
        }

    }
}
