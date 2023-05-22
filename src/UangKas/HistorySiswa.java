package UangKas;
import FormLogin.Database;
import User.Siswa;
import User.SiswaForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HistorySiswa extends JFrame{

    private JPanel HistoryPanel;
    private JTable table1;
    private JButton backButton;

    HistoryTable historyTable = new HistoryTable();

    public HistorySiswa(){
        super();
        setContentPane(HistoryPanel);
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        table1.setModel(historyTable);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SiswaForm();
                dispose();
            }
        });
    }

    public static class HistoryTable extends AbstractTableModel{
        private final String[] COLUMN = {"Tanggal","Nominal"};


        @Override
        public int getRowCount() {
            return Database.getSiswaTemp().getHistoryTransaksi().size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN.length;
        }
        @Override
        public String getColumnName(int column) {
            return COLUMN[column];
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> Database.getSiswaTemp().getHistoryTransaksi().get(rowIndex).getTanggalBayar();
                case 1 -> Database.getSiswaTemp().getHistoryTransaksi().get(rowIndex).getJumlahPembayaran();
                default -> "-";
            };
        }
    }

}
