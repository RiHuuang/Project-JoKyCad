import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;


public class Siswa extends JFrame {
    private JButton backButton;
    private JButton bayarButton;
    private JButton historyButton;
    private JPanel SiswaMainFrame;
    private JPanel IsiFrame;
    private JPanel BayarFrame;
    private JPanel HistoryFrame;

    BufferedImage image;

    public Siswa(){
        setContentPane(SiswaMainFrame);
        setTitle("Selamat datang siswa");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

//        try {
//            image = ImageIO.read(new File());
//        } catch (IOException ex) {
//            ex.getStackTrace();
//        }

        bayarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setvisible bayar menu == TRUE
                //trus set this visible == FALSE
            }

        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setvisible history menu == TRUE
            }
        });
    }

    public static void main(String[] args) {
        Siswa siswaFrame = new Siswa();
    }
}
