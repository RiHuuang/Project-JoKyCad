package UangKas;

import User.Siswa;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DataSiswa extends JFrame{
    private String[] status = new String[]{"Lunas", "Belum Bayar"};
    private JTable table1;
    private JPanel DataSiswaPanel;
    private JButton backButton;
    ArrayList<Siswa> siswas = new ArrayList<>();

    SiswaTableModel siswaTableModel = new SiswaTableModel(siswas);


    public DataSiswa() {
        siswas.add(new Siswa("Richard", "PPTI15", getStatus(0))); //ini dummy data

        setContentPane(DataSiswaPanel);
        setVisible(true);
        setDefaultCloseOperation(3);
        setSize(400,400);
        table1.setModel(siswaTableModel);
        table1.setAutoCreateRowSorter(true);
    }

    private static class SiswaTableModel extends AbstractTableModel{

        private final String[] COLUMN = {"Nama", "Kelas", "STATUS"};
        private List<Siswa> siswas;

        private SiswaTableModel(List<Siswa> siswas){
            this.siswas = siswas;
        }

        @Override
        public int getRowCount() {
            return siswas.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> siswas.get(rowIndex).getNama();
                case 1 -> siswas.get(rowIndex).getKelas();
                case 2 -> siswas.get(rowIndex).getStatus();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN[column];
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }

    public String getStatus(int i) {
        return status[i];
    }

    public static void main(String[] args) {
        DataSiswa ds = new DataSiswa();
    }
}
