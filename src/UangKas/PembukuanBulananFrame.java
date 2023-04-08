package UangKas;

import javax.swing.*;

public class PembukuanBulananFrame extends JFrame{
    private JTextField a0TextField;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton backButton;
    private JPanel mainPanel;

    public PembukuanBulananFrame(){
        setContentPane(mainPanel);
        setTitle("Pembukuan Bulanan");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PembukuanBulananFrame();
    }
}
