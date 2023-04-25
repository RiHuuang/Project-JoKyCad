package UangKas;

import FormLogin.Database;
import User.SiswaForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static FormLogin.Database.*;

public class PembayaranFrame extends JFrame{

    //TF stands for TextField
    private JLabel Judul = new JLabel("Pembayaran");
    private JTextField TagihanTF;
    private JTextField DeadlineTF;
    private JTextField DendaTF;
    private JButton cancelButton;
    private JButton bayarButton;
    private JPanel mainPanel;
    private JLabel TotalValueLabel;
    private final double iuranBulanan = 100000.00;


    public PembayaranFrame(){
        initDataSiswa();
        initPembukuanBulanan();
        reValidate();
        Transaksi transaksi = new Transaksi(LocalDate.now());
        String loggedUsername = Database.getSiswaTemp().getUsername();
        String loggedPassword = Database.getSiswaTemp().getPassword();

        LocalDate currentDate = LocalDate.now();
        setContentPane(mainPanel);
        setTitle("Pembayaran");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
//        System.out.println(Database.getUserTemp().getNama());
        TagihanTF.setEditable(false);
        DeadlineTF.setEditable(false);
        LocalDate deadline = currentDate.withDayOfMonth(currentDate.lengthOfMonth());;
        DeadlineTF.setText(Transaksi.getDeadline(currentDate));
        DendaTF.setEditable(false);
        double totalBayar = transaksi.hitungPembayaran(iuranBulanan, transaksi.hitungDenda(Database.getSiswaTemp().getDaysPassed()));
        double totalDenda = transaksi.hitungDenda(Database.getSiswaTemp().getDaysPassed());
        TotalValueLabel.setText("Rp. "+ totalBayar);
        DendaTF.setText("Rp. "+ totalDenda);

        bayarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transaksi(totalBayar,currentDate,0);
                initDataSiswa();
                reValidate();
                dispose();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SiswaForm();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new PembayaranFrame();

    }

}
