/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.modeli.opis.intervencija;

import domen.OpisIntervencije;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rus
 */
public class OpisIntervencijaTableModel extends AbstractTableModel{

    private ArrayList<OpisIntervencije> listaOpisa;

    public OpisIntervencijaTableModel(ArrayList<OpisIntervencije> listaOpisa) {
        this.listaOpisa = listaOpisa;
    }
    
    @Override
    public int getRowCount() {
        return listaOpisa.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OpisIntervencije oi = listaOpisa.get(rowIndex);
        switch (columnIndex) {
            case 0: return oi.getOpis();
            default:
                return "greska";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Opis";
            default:
                return "greska";
        }
    }
}
