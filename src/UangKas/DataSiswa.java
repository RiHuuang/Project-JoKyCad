package UangKas;

import FormLogin.Database;
import User.AdminForm;
import User.Siswa;
import User.SiswaForm;
import User.User;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DataSiswa extends JFrame implements ActionListener {

    private JTable table1;
    private JPanel DataSiswaPanel;
    private JButton backButton;
    Database database = new Database();

    SiswaTableModel siswaTableModel = new SiswaTableModel(database.getUsers());


    public DataSiswa() {


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
        private ArrayList<User> users;
        private Database database = new Database();

        private SiswaTableModel(ArrayList<User> users){
            database.initDataSiswa();
            this.users = database.getUsers();
        }

        @Override
        public int getRowCount() {
            return users.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> users.get(rowIndex).getNama();
                case 1 -> users.get(rowIndex).getKelas();
                case 2 -> User.getStatus();
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



    public static void main(String[] args) {
        DataSiswa ds = new DataSiswa();
    }
}
