package UangKas;

import User.AdminForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PembukuanBulananFrame extends JFrame{
    private JTextField TotalPemasukanTextField;
    private JTextField PengeluaranBulanTextField;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField textField;
    private JTextField textField2;

    public PembukuanBulananFrame(){
        setContentPane(mainPanel);
        setTitle("Pembukuan Bulanan");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


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

