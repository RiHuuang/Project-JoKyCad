package UangKas;

import FormLogin.Database;
import User.AdminForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class PembukuanBulananFrame extends JFrame{
    private JTextField TotalPemasukanTF;
    private JTextField PengeluaranBulanTF;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField TotalPengeluaranTF;
    private JTextField TotalKasTF;
    private JLabel PengeluananBulanLabel;
    private JButton submitButton;

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
        //tinggal masukin database sabar yak
        Pembukuan PembukuanTerakhir = Database.getPembukuans().get(Database.getPembukuans().size()-1);
        System.out.println(PembukuanTerakhir.getPemasukanBulanan());
        System.out.println(PembukuanTerakhir.getPengeluaranBulanan());
//        System.out.println(PembukuanTerakhir.g);
        Double TotalPemasukan = PembukuanTerakhir.getPemasukanBulanan();
        Double TotalPengeluaran = PembukuanTerakhir.getPengeluaranBulanan();
        Double TotalKas = PembukuanTerakhir.getJumlahAkhir();
        TotalPemasukanTF.setText("Rp. " + TotalPemasukan);
        TotalPengeluaranTF.setText("Rp. " + TotalPengeluaran);
        TotalKasTF.setText("Rp. " + TotalKas);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Double Pengeluaran = Double.valueOf(PengeluaranBulanTF.getText());
//                TotalPengeluaran[0] = TotalPengeluaran[0] + Pengeluaran;
//                Double TotalKas = TotalPemasukan - TotalPengeluaran[0];
//                PengeluaranBulanTF.setText("");
//                TotalPemasukanTF.setText("Rp. " + TotalPemasukan);
//                TotalPengeluaranTF.setText("Rp. " + TotalPengeluaran[0]);
                String PengeluaranBulananString = String.valueOf(PengeluaranBulanTF.getText());
//                System.out.println(PengeluaranBulananString);
                Double PengeluaranBulanan = Double.parseDouble(PengeluaranBulananString);
//                System.out.println("Pengeluaran bulanan  = "+ PengeluaranBulanan);
//                TotalKasTF.setText("Rp. " + TotalKas);
                new Transaksi(PengeluaranBulanan, LocalDate.now());
                dispose();
                new PembukuanBulananFrame();
            }
        });
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

