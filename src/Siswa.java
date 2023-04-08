import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Siswa extends JFrame {
    private JButton backButton;
    private JButton bayarButton;
    private JButton historyButton;
    private JPanel SiswaMainFrame;
    private JPanel IsiFrame;
    private JPanel BayarFrame;
    private JPanel HistoryFrame;
    private JLabel BayarLogo;
    private JLabel HistoryLabel;


    public Siswa(){
        setContentPane(SiswaMainFrame);
        setTitle("Selamat datang siswa");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        BayarLogo = new JLabel( new ImageIcon("Payment Logo.png"));
        HistoryLabel = new JLabel(new ImageIcon("History Logo.png"));

    }
}
