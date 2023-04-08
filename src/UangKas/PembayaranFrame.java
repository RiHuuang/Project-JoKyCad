package UangKas;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PembayaranFrame extends JFrame implements KeyListener {


    private JLabel Judul =new JLabel("Pembayaran");
    private JButton xButton;
    private JTextField rp100000TextField;
    private JTextField a31April2023TextField;
    private JTextField textField2;
    private JButton cancelButton;
    private JButton bayarButton;
    private JPanel mainPanel;

    public PembayaranFrame(){
        setContentPane(mainPanel);
        setTitle("Pembayaran");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
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
