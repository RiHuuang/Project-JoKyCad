package UangKas;

import FormLogin.Database;
import User.AdminForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PembukuanBulananFrame extends JFrame{
    private JTextField TotalPemasukanTF;
    private JTextField PengeluaranBulanTF;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField TotalPengeluaranTF;
    private JTextField TotalKasTF;
    private JLabel PengeluananBulanLabel;

    public PembukuanBulananFrame(){
        Database.initPembukuanBulanan();
        setContentPane(mainPanel);
        setTitle("Pembukuan Bulanan");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Timer timer = new Timer(1, e -> {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("MMMM");
            String month = format.format(now);
            String text = "Pengeluaran Bulan " + month;
            PengeluananBulanLabel.setText(text);
        });
        timer.start();
        TotalPemasukanTF.setEditable(false);
        TotalPengeluaranTF.setEditable(false);
        TotalKasTF.setEditable(false);
        // ini totalnya masi coba dlo
        double TotalPemasukan = 10;
        double TotalPengeluaran = 9;
        double TotalKas = TotalPemasukan - TotalPengeluaran;
        TotalKasTF.setText("Rp. " + TotalKas);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminForm();
                dispose();
            }

        });
    }

    public static void main(String[] args) {
        new PembukuanBulananFrame();
    }
}

