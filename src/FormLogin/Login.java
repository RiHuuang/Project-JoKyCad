package FormLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame{
//    EnterSiswa enterSiswa;
//    EnterAdmin enterAdmin;
    private JPanel Content;
    private JTextField txtUsername;
    private JTextField txtName;
    private JPasswordField txtPass;
    private JComboBox txtRole;
    private JButton loginButton;
    private JButton cancelButton;

    public Login(){
        setTitle("Login Kas Rumah Talenta BCA");
        setContentPane(Content);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//        enterAdmin = new EnterAdmin(null);
//        enterSiswa = new EnterSiswa(null);
//
//        enterSiswa.setVisible(false);
//        enterAdmin.setVisible(false);
//        enterAdmin.setLocationRelativeTo(null);
//        enterSiswa.setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = String.valueOf(txtUsername.getText());
                String nama = String.valueOf(txtName.getText());
                String role = txtRole.getSelectedItem().toString();
                String password = String.valueOf(txtPass.getPassword());

                System.out.println(username+" "+nama+" "+role+" "+password);

                int validasi = Database.validate(username,nama,password,role);

                if (validasi == 1){
                    setVisible(false);
                    System.out.println("Masuk Admin");
//                    enterAdmin.setVisible(true);
                } else if (validasi == 2) {
                    setVisible(false);
                    System.out.println("Masuk Siswa");
//                    enterSiswa.setVisible(true);
                }else if (validasi == 0){
                    JOptionPane.showMessageDialog(Login.this,"Invalid Login Please ask Admission","Try again",JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Login.this,"See you later!","Exit",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        });
        setVisible(true);

    }

    public static void main(String[] args) {
        new Login();
    }

}
