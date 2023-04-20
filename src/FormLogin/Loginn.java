package FormLogin;

import User.AdminForm;
import User.Siswa;
import User.SiswaForm;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Loginn extends JFrame{
//    EnterSiswa enterSiswa;
//    EnterAdmin enterAdmin;
    private JPanel Content;
    private JTextField txtUsername;
    private JTextField txtName;
    private JPasswordField txtPass;
    private JComboBox txtRole;
    private JButton loginButton;
    private JButton cancelButton;

    public Loginn(){
        Database.initDataSiswa();
        setTitle("Login Kas Rumah Talenta BCA");
        setContentPane(Content);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = String.valueOf(txtUsername.getText());
                String nama = String.valueOf(txtName.getText());
                String role = txtRole.getSelectedItem().toString();
                String password = String.valueOf(txtPass.getPassword());

//                System.out.println(username+" "+nama+" "+role+" "+password);

                int validasi = Database.validate(username,nama,password,role);
                System.out.println(Database.getUserTemp().getClass());
//                System.out.println("\n\n"+validasi);

                if (validasi == 1){
                    setVisible(false);
                    System.out.println("Masuk Admin");

                    new AdminForm();
                    dispose();

//                    System.exit(0);
                } else if (validasi == 2) {
                    setVisible(false);
                    System.out.println("Masuk Siswa");

                    new SiswaForm();
                    dispose();

//                    System.exit(0);

                }else if (validasi == 0){
                    JOptionPane.showMessageDialog(Loginn.this,"Invalid Login Please ask Admission","Try again",JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Loginn.this,"See you later!","Exit",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        });
        setVisible(true);

    }



    public static void main(String[] args) {
//        Database.main(null);
        new Loginn();
    }

}

