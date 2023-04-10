package User;

import UangKas.PembayaranFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Siswa extends JFrame {
    private String kelas;
    private String nama;
    private String status;
    private JButton backButton;
    private JButton bayarButton;
    private JButton historyButton;
    private JPanel SiswaMainFrame;
    private JPanel IsiFrame;
    private JPanel BayarFrame;
    private JPanel HistoryFrame;
    private JLabel BayarLogo;
    private JLabel HistoryLabel;
    private JLabel SiswaTitleLabel;


    public Siswa(){
        setContentPane(SiswaMainFrame);
        setTitle("Selamat datang siswa");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        bayarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PembayaranFrame();
                dispose();
            }

        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new
            }
        });
    }

    public Siswa(String name, String kelas, String status){
        this.nama = name;
        this.kelas = kelas;
        this.status = status;
    }

    public static void main(String[] args) {
        Siswa siswaFrame = new Siswa();
    }


    public String getKelas() {
        return kelas;
    }


    public String getNama() {
        return nama;
    }


    public String getStatus() {
        return status;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        BayarLogo = new JLabel( new ImageIcon("Payment Logo.png"));
        HistoryLabel = new JLabel(new ImageIcon("History Logo.png"));

    }

}
