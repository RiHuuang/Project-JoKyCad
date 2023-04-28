package UangKas;
import User.SiswaForm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
//        Transaksi transaksi = new

        private final String[] COLUMN = {"Tanggal","Status"};


        @Override
        public int getRowCount() {
            return 0;
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
            return null;
        }
    }

}
