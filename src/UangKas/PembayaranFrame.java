package UangKas;

import FormLogin.Database;
import User.Siswa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static FormLogin.Database.getUserTemp;

public class PembayaranFrame extends JFrame implements KeyListener {

    //TF stands for TextField
    private JLabel Judul = new JLabel("Pembayaran");
    private JButton xButton;
    private JTextField TagihanTF;
    private JTextField DeadlineTF;
    private JTextField DendaTF;
    private JButton cancelButton;
    private JButton bayarButton;
    private JPanel mainPanel;
    private JLabel TotalValueLabel;


    public PembayaranFrame(){
        Transaksi transaksi = new Transaksi(LocalDate.now());

        LocalDate currentDate = LocalDate.now();
        setContentPane(mainPanel);
        setTitle("Pembayaran");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        System.out.println(Database.getUserTemp().getNama());
        TagihanTF.setEditable(false);
        DeadlineTF.setEditable(false);
        DeadlineTF.setText(Transaksi.getDeadline(currentDate));
        DendaTF.setEditable(false);

        TotalValueLabel.setText(String.valueOf(transaksi.hitungPembayaran(100000.00, transaksi.hitungDenda(Database.getUserTemp().getDaysPassed()))));
        DendaTF.setText(String.valueOf(transaksi.hitungDenda(Database.getUserTemp().getDaysPassed())));

        bayarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Transaksi()
            }
        });
    }

    public static void main(String[] args) {
        new PembayaranFrame();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
