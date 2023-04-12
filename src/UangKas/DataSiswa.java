package UangKas;

import User.AdminForm;
import User.SiswaForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataSiswa extends JFrame implements ActionListener {
    private String[] status = new String[]{"Lunas", "Belum Bayar"};
    private JTable table1;
    private JPanel DataSiswaPanel;
    private JButton backButton;
    ArrayList<SiswaForm> siswaForms = new ArrayList<>();

    SiswaTableModel siswaTableModel = new SiswaTableModel(siswaForms);


    public DataSiswa() {
        siswaForms.add(new SiswaForm("Richard", "PPTI15", getStatus(0))); //ini dummy data

        setContentPane(DataSiswaPanel);
        setVisible(true);
        setDefaultCloseOperation(3);
        setSize(400,400);
        table1.setModel(siswaTableModel);
        table1.setAutoCreateRowSorter(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                buka frame yang sebelumnya yg baru trus di dispose
                new AdminForm();
                dispose();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                buka frame yang sebelumnya yg baru trus di dispose
                new AdminForm();
                dispose();
            }
        });
    }


    private static class SiswaTableModel extends AbstractTableModel{

        private final String[] COLUMN = {"Nama", "Kelas", "STATUS"};
        private List<SiswaForm> siswaForms;

        private SiswaTableModel(List<SiswaForm> siswaForms){
            this.siswaForms = siswaForms;
        }

        @Override
        public int getRowCount() {
            return siswaForms.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> siswaForms.get(rowIndex).getNama();
                case 1 -> siswaForms.get(rowIndex).getKelas();
                case 2 -> siswaForms.get(rowIndex).getStatus();
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
