package User;

import FormLogin.Loginn;
import UangKas.PembayaranFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SiswaForm extends JFrame {

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


    public SiswaForm(){
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Loginn();
                dispose();
            }
        });
    }



    public static void main(String[] args) {
        SiswaForm siswaFormFrame = new SiswaForm();
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        BayarLogo = new JLabel( new ImageIcon("Payment Logo.png"));
        HistoryLabel = new JLabel(new ImageIcon("History Logo.png"));

    }

}
