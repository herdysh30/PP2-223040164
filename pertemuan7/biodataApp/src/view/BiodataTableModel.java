package view;

import javax.swing.table.*;
import java.util.List;
import model.Biodata;

public class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Nama", "Tanggal Lahir", "Jenis Kelamin", "No Telepon"};
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getId(); // Menampilkan ID di kolom pertama
            case 1:
                return rowItem.getNama();
            case 2:
                return rowItem.getTanggalLahir();
            case 3:
                return rowItem.getJenisKelamin();
            case 4:
                return rowItem.getNoTelepon();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public List<Biodata> getData() {
        return data;
    }
    
    // Metode untuk update data pada tabel
    public void update(int row, Biodata biodata) {
        data.set(row, biodata);
        fireTableRowsUpdated(row, row);
    }
    
    // Metode untuk menghapus data pada tabel
    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
}
