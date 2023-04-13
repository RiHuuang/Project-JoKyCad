package User;

import FormLogin.UserLogin;
import UangKas.DataSiswa;
import UangKas.PembukuanBulananFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminForm extends JFrame {
    private JButton BackButton;
    private JButton dataSiswaButton;
    private JButton pembukuanButton;
    private JLabel DataSiswaLabel;
    private JLabel PembukuanLabel;
    private JPanel AdminFrame;//Main Panel in Admin.form


    public AdminForm(){

        setContentPane(AdminFrame);
        setTitle("Selamat datang Admin");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLogin();
                dispose();
            }
        });

        dataSiswaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataSiswa();
                dispose();
            }
        });
        pembukuanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PembukuanBulananFrame();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        AdminForm adminFormFrame = new AdminForm();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        DataSiswaLabel = new JLabel(new ImageIcon("StudentDataIcon.jpg"));
        PembukuanLabel = new JLabel(new ImageIcon("Pembukuan.png"));
    }
}
