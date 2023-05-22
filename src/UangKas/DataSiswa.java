package UangKas;

import FormLogin.Database;
import User.AdminForm;
import User.Admin;
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

import static FormLogin.Database.getStatus;
import static FormLogin.Database.initDataSiswa;

public class DataSiswa extends JFrame implements ActionListener {

    private JTable table1;
    private JPanel DataSiswaPanel;
    private JButton backButton;
    static Database database = new Database();


    SiswaTableModel siswaTableModel = new SiswaTableModel(database.getUsers());


    public DataSiswa() {


        setContentPane(DataSiswaPanel);
        setVisible(true);
        setDefaultCloseOperation(3);
        setSize(400,400);
        setLocationRelativeTo(null);
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
        private ArrayList<Siswa> siswas ;
        private Siswa siswa;

        private SiswaTableModel(ArrayList<User> users){
            initDataSiswa();
            this.users = Database.getUsers();

            //ni nyoba doang
//            for(User user : users){
//                if(user instanceof Admin){
//
////                    assert this.siswas != null;
////                    this.siswas.add((Siswa) user);
////                    this.siswas = siswas.add( new Siswa(user.getNama(),user.getKelas(),((Siswa) user).getStatus(),user.getUsername(),user.getPassword(),user.getRole()));
//                }
//            }

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
                case 2 -> users.get(rowIndex).getStatus();
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
